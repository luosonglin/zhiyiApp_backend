package cn.luosonglin.test.qiniu.api;

import cn.luosonglin.test.qiniu.config.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

/**
 * Created by luosonglin on 23/12/2016.
 */
public class BucketManagment {

    public static void main(String[] args) {
        Auth auth = Auth.create(Config.ACCESS_KEY, Config.SECRET_KEY);

        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManagment = new BucketManager(auth, c);
        //要测试的空间和key，并且这个key在你空间中存在
        String bucket = "test";
        String key = "";
        try {
            //调用stat()方法获取文件的信息
            FileInfo info = bucketManagment.stat(bucket, key);
            System.out.println(info.hash);
            System.out.println(info.key);
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
    }

}
