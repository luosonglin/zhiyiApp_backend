package cn.luosonglin.test.easemob.common;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by luosonglin on 15/12/2016.
 */
public class PropertiesUtils {
    public static Properties getProperties() {

        Properties p = new Properties();

        try {
            InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(
                    "HuanXinConfig.properties");

            p.load(inputStream);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return p;
    }

}
