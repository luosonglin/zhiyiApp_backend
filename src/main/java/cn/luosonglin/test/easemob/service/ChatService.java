package cn.luosonglin.test.easemob.service;

import cn.luosonglin.test.exception.CustomizedException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by luosonglin on 21/12/2016.
 *
 * 自定义的环信即时通讯服务
 */
public interface ChatService {

    //注册IM用户[单个]
    ObjectNode createNewIMUserService(String name, String password) ;

    //IM用户登录
    ObjectNode imUserLoginService(String name, String password);

    //重置IM用户密码 提供管理员token
    ObjectNode modifyIMUserPasswordService(String userName, String newPassword);

    //添加好友[单个]
    ObjectNode addFriendSingleService(String ownerUserName, String friendUserName);

    //查看好友
    ObjectNode getFriendInfoService(String ownerUserName);

    //解除好友关系
    ObjectNode deleteFriendSingleNodeService(String ownerUserName, String friendUserName);

    //检测用户是否在线
    ObjectNode getUserStatusService(String targetUserName);

    //给用户发一条文本消息
    ObjectNode sendTxtMessageusernodeService(String ownerUserName, String friendUserName, String message);

    // 聊天消息 获取最新的20条记录,count为前n条记录,环信限制为每次 limit 最大值为1000
    ObjectNode getChatMessagesCountService(String count) throws CustomizedException;

    // 聊天消息 获取7天以内的消息
    ObjectNode getChat7DaysMessagesService();

    // 聊天消息 分页获取
    ObjectNode getChatPagesMessagesService();
}
