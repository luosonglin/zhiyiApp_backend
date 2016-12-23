package cn.luosonglin.test.qiniu.config;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import java.io.IOException;;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by luosonglin on 23/12/2016.
 */
@Component
public class Config {

    @Value("${cn.luosonglin.test.qiniu.AccessKey}")
    public static String ACCESS_KEY;

    @Value("${cn.luosonglin.test.qiniu.SecretKey}")
    public static String SECRET_KEY;


    public static void main (String []args) {
        System.out.print(ACCESS_KEY+" "+SECRET_KEY);
    }

    //设置好账号的ACCESS_KEY和SECRET_KEY
//    public static String ACCESS_KEY = "r0_GHWBaOY4cbuUfQHAOsj0KoGAo_648Xc1SYCfe";
//    public static String SECRET_KEY = "HVQvB-vB6w5HmwWZnIttXJ_DXyqCxC0HSPT56RSG";
}
