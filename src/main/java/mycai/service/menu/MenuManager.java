package mycai.service.menu;

/**
 * Created by darlingtld on 2015/2/20.
 */


import mycai.pojo.AccessToken;
import mycai.pojo.button.*;
import mycai.util.PropertyHolder;
import mycai.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        String appId = PropertyHolder.APPID;
        String appSecret = PropertyHolder.APPSECRET;

        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            if (0 == result) {
                log.info("菜单创建成功");
            } else {
                log.info("菜单创建失败，错误码：{}", result);
            }
        }
    }

    private static Menu getMenu() {
        ViewButton btn11 = new ViewButton();
        btn11.setName(PropertyHolder.MENU_GO_ORDER);
        btn11.setUrl(PropertyHolder.SERVER + "/index.html");

        ViewButton btn21 = new ViewButton();
        btn21.setName(PropertyHolder.MENU_MY_ORDER);
        btn21.setUrl(PropertyHolder.SERVER + "/index.html");

        ClickButton btn31 = new ClickButton();
        btn31.setName(PropertyHolder.MENU_ABOUT_US);
        btn31.setKey(PropertyHolder.MENU_ABOUT_US);

        ClickButton btn32 = new ClickButton();
        btn32.setName(PropertyHolder.MENU_NEW_PRODUCT);
        btn32.setKey(PropertyHolder.MENU_NEW_PRODUCT);

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName(PropertyHolder.MENU_ABOUT_US);
        mainBtn3.setSub_button(new Button[]{btn31, btn32});

        Menu menu = new Menu();
        menu.setButton(new Button[]{btn11, btn21, mainBtn3});

        return menu;
    }
}
