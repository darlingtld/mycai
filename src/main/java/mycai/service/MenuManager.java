package mycai.service;

/**
 * Created by darlingtld on 2015/2/20.
 */


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mycai.pojo.AccessToken;
import mycai.pojo.button.ClickButton;
import mycai.pojo.button.ComplexButton;
import mycai.pojo.button.ViewButton;
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
        String jsonMenu = getMenu().toJSONString();
        log.debug(jsonMenu);
        if (null != at) {
            int result = WeixinUtil.createMenu(jsonMenu, at.getToken());

            if (0 == result) {
                log.info("菜单创建成功");
            } else {
                log.info("菜单创建失败，错误码：{}", result);
            }
        }
    }

    private static JSONObject getMenu() {

        ViewButton btn11 = new ViewButton();
        btn11.setName(PropertyHolder.MENU_GO_ORDER);
        btn11.setUrl(PropertyHolder.SERVER);

        ViewButton btn21 = new ViewButton();
        btn21.setName(PropertyHolder.MENU_MY_ORDER);
        btn21.setUrl(PropertyHolder.SERVER + "#/order/history");

        ClickButton btn31 = new ClickButton();
        btn31.setName(PropertyHolder.MENU_ABOUT_US);
        btn31.setKey(PropertyHolder.MENU_ABOUT_US);

        ClickButton btn32 = new ClickButton();
        btn32.setName(PropertyHolder.MENU_NEW_PRODUCT);
        btn32.setKey(PropertyHolder.MENU_NEW_PRODUCT);

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName(PropertyHolder.MENU_GO_ORDER);
        mainBtn1.setSub_button(new ViewButton[]{btn11});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName(PropertyHolder.MENU_MY_ORDER);
        mainBtn2.setSub_button(new ViewButton[]{btn21});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName(PropertyHolder.MENU_ABOUT_US);
        mainBtn3.setSub_button(new ClickButton[]{btn31, btn32});

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(btn11);
        jsonArray.add(btn21);
        jsonArray.add(mainBtn3);

        JSONObject menu = new JSONObject();
        menu.put("button", jsonArray);
        return menu;
    }
}
