package cn.luosonglin.test.easemob.service;

import cn.luosonglin.test.base.util.MD5Gen;
import cn.luosonglin.test.exception.CustomizedException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import static cn.luosonglin.test.easemob.api.EasemobIMUsers.createNewIMUserSingle;

/**
 * Created by luosonglin on 21/12/2016.
 */
@Service("ChatService")
public class ChatServiceImpl implements ChatService {
    @Override
    public ObjectNode createNewIMUser(String name, String password) throws CustomizedException {
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username", name);
        datanode.put("password", MD5Gen.getMD5(password));
        ObjectNode createNewIMUserSingleNode = createNewIMUserSingle(datanode);
        if (null == createNewIMUserSingleNode) throw new CustomizedException("注册环信用户出错");
        return createNewIMUserSingleNode;
    }

    @Override
    public String imUserLogin(String name, String password) {
        return null;
    }
}
