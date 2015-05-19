package mycai.util;


import java.io.IOException;
import java.util.Properties;

/**
 * Created by tangld on 2015/3/30.
 */
public class PropertyHolder {
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(PropertyHolder.class.getClassLoader().getResourceAsStream("mycai.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final String HEADER_MSG = "message";

    public static final String TOKEN = prop.getProperty("wechat.token");
    public static final String APPID = prop.getProperty("wechat.app_id");
    public static final String APPSECRET = prop.getProperty("wechat.app_secret");
    public static final String SERVER = prop.getProperty("wechat.server");

}
