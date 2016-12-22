package cn.luosonglin.test.easemob.service;

import cn.luosonglin.test.base.util.MD5Gen;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.file.web.FileUploadController;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static cn.luosonglin.test.easemob.api.EasemobChatMessage.getChatMessages;
import static cn.luosonglin.test.easemob.api.EasemobIMUsers.*;
import static cn.luosonglin.test.easemob.api.EasemobMessages.getUserStatus;
import static cn.luosonglin.test.easemob.api.EasemobMessages.sendMessages;

/**
 * Created by luosonglin on 21/12/2016.
 */
@Service("ChatService")
public class ChatServiceImpl implements ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Override
    public ObjectNode createNewIMUserService(String name, String password) {
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username", name);
        datanode.put("password", MD5Gen.getMD5(password));
        ObjectNode createNewIMUserSingleNode = createNewIMUserSingle(datanode);
        if (null != createNewIMUserSingleNode) {
            logger.info("注册IM用户[单个]: " + createNewIMUserSingleNode.toString());
        }
        return createNewIMUserSingleNode;
    }

    @Override
    public ObjectNode imUserLoginService(String name, String password) {
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username", name);
        datanode.put("password", MD5Gen.getMD5(password));
        ObjectNode imUserLoginNode = imUserLogin(datanode.get("username").asText(), datanode.get("password").asText());
        if (null != imUserLoginNode) {
            logger.info("IM用户登录: " + imUserLoginNode.toString());
        }
        return imUserLoginNode;

    }

    @Override
    public ObjectNode modifyIMUserPasswordService(String userName, String newPassword) {
        ObjectNode json2 = JsonNodeFactory.instance.objectNode();
        json2.put("newpassword", newPassword);
        ObjectNode modifyIMUserPasswordWithAdminTokenNode = modifyIMUserPasswordWithAdminToken(userName, json2);
        if (null != modifyIMUserPasswordWithAdminTokenNode) {
            logger.info("重置IM用户密码 提供管理员token: " + modifyIMUserPasswordWithAdminTokenNode.toString());
        }
//        ObjectNode imUserLoginNode2 = imUserLogin(userName, json2.get("newpassword").asText());
//        if (null != imUserLoginNode2) {
//            logger.info("重置IM用户密码后,IM用户登录: " + imUserLoginNode2.toString());
//        }
        return modifyIMUserPasswordWithAdminTokenNode;
    }

    @Override
    public ObjectNode addFriendSingleService(String ownerUserName, String friendUserName) {
        ObjectNode addFriendSingleNode = addFriendSingle(ownerUserName, friendUserName);
        if (null != addFriendSingleNode) {
            logger.info("添加好友[单个]: " + addFriendSingleNode.toString());
        }
        return addFriendSingleNode;
    }

    @Override
    public ObjectNode getFriendInfoService(String ownerUserName) {
        ObjectNode getFriendsNode = getFriends(ownerUserName);
        if (null != getFriendsNode) {
            logger.info("查看好友: " + getFriendsNode.toString());
        }
        return getFriendsNode;
    }

    @Override
    public ObjectNode deleteFriendSingleNodeService(String ownerUserName, String friendUserName) {
        ObjectNode deleteFriendSingleNode = deleteFriendSingle(ownerUserName, friendUserName);
        if (null != deleteFriendSingleNode) {
            logger.info("解除好友关系: " + deleteFriendSingleNode.toString());
        }
        return deleteFriendSingleNode;
    }

    @Override
    public ObjectNode getUserStatusService(String targetUserName) {
        ObjectNode usernode = getUserStatus(targetUserName);
        if (null != usernode) {
            logger.info("检测用户是否在线: " + usernode.toString());
        }
        return usernode;
    }

    private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    @Override
    public ObjectNode sendTxtMessageusernodeService(String ownerUserName, String friendUserName, String message) {
//        String from = ownerUserName;	//表示消息发送者。无此字段Server会默认设置为"from":"admin"，有from字段但值为空串("")时请求失败
        String targetTypeus = "users";    // users 给用户发消息。chatgroups: 给群发消息，chatrooms: 给聊天室发消息
        ObjectNode ext = factory.objectNode();    //支持扩展字段，通过 ext 属性，APP 可以发送自己专属的消息结构。
        ArrayNode targetusers = factory.arrayNode();
        targetusers.add(friendUserName);    // 注意这里需要用数组，数组长度建议不大于20，即使只有一个用户，也要用数组 ['u1']，给用户发送时数组元素是用户名，给群组发送时数组元素是groupid
//        targetusers.add("kenshinnuser002");
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", message);
        txtmsg.put("type", "txt");
        ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus, targetusers, txtmsg, ownerUserName, ext);
        if (null != sendTxtMessageusernode) {
            logger.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
        }
        return sendTxtMessageusernode;
    }

    @Override
    public ObjectNode getChatMessagesCountService(String count) throws CustomizedException {
        ObjectNode queryStrNode = factory.objectNode();
        if (Integer.parseInt(count) > 1000) throw new CustomizedException("只提供1000以内聊天记录查询");
        queryStrNode.put("limit", count);    //环信限制为每次 limit 最大值为1000
        ObjectNode messages = getChatMessages(queryStrNode);
        return messages;
    }

    @Override
    public ObjectNode getChat7DaysMessagesService() {
        String currentTimestamp = String.valueOf(System.currentTimeMillis());
        String senvenDayAgo = String.valueOf(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        ObjectNode queryStrNode1 = factory.objectNode();
        queryStrNode1.put("ql", "select * where  timestamp > " + senvenDayAgo + " and timestamp < " + currentTimestamp);
        ObjectNode messages1 = getChatMessages(queryStrNode1);
        return messages1;
    }

    //需要修改
    @Override
    public ObjectNode getChatPagesMessagesService() {

        ObjectNode queryStrNode2 = factory.objectNode();
        queryStrNode2.put("limit", "20");
        // 第一页
        ObjectNode messages2 = getChatMessages(queryStrNode2);
        // 第二页
        String cursor = messages2.get("cursor").asText();
        queryStrNode2.put("cursor", cursor);
        ObjectNode messages3 = getChatMessages(queryStrNode2);
        return messages3;
    }
}
