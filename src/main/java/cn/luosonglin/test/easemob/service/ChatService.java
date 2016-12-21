package cn.luosonglin.test.easemob.service;

import cn.luosonglin.test.exception.CustomizedException;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by luosonglin on 21/12/2016.
 */
public interface ChatService {

    //注册IM用户[单个]
    ObjectNode createNewIMUser(String name, String password) throws CustomizedException;

    //IM用户登录
    String imUserLogin(String name, String password);

    //

}
