package cn.luosonglin.test.qiniu.api;

import cn.luosonglin.test.qiniu.config.Config;
import com.qiniu.util.Auth;

/**
 * Created by luosonglin on 23/12/2016.
 */
public class BucketManager {
    Auth auth = Auth.create(Config.ACCESS_KEY, Config.SECRET_KEY);



}
