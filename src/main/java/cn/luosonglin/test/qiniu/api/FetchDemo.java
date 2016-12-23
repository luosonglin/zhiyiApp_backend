package cn.luosonglin.test.qiniu.api;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
/**
 * Created by luosonglin on 23/12/2016.
 */
public class FetchDemo {

    public static void main(String args[]){
        //设置需要操作的账号的AK和SK
        String ACCESS_KEY = "r0_GHWBaOY4cbuUfQHAOsj0KoGAo_648Xc1SYCfe";
        String SECRET_KEY = "HVQvB-vB6w5HmwWZnIttXJ_DXyqCxC0HSPT56RSG";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth,c);

        //文件保存的空间名和文件名
        String bucket = "test";
        String key = "0.jpg";

        //要fetch的url
        String url = "http://oimas2nso.bkt.clouddn.com";

        try {
            //调用fetch方法抓取文件
            bucketManager.fetch(url, bucket,key);
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
    }
}
