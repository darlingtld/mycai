package mycai.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import mycai.pojo.User;
import mycai.util.PropertyHolder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by darlingtld on 2015/6/24 0024.
 */
@Service
public class UserService {

    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return converters;
    }

    public User getUserInformation(String code) {

        String getAccessTokenUrl = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", PropertyHolder.APPID, PropertyHolder.APPSECRET, code);
        System.out.println(getAccessTokenUrl);
        String retData = restTemplate.getForObject(getAccessTokenUrl, String.class, new HashMap<String, Object>());
        System.out.println("getAccessTokenUrl : " + retData);

        /** jsonObject should be something like below
         * {
         "access_token":"ACCESS_TOKEN",
         "expires_in":7200,
         "refresh_token":"REFRESH_TOKEN",
         "openid":"OPENID",
         "scope":"SCOPE",
         "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
         }
         */
        JSONObject jsonObject = JSON.parseObject(retData);
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
//        https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        String getUserInfoUrl = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", accessToken, openid, code);
        String userData = restTemplate.getForObject(getUserInfoUrl, String.class, new HashMap<String, Object>());
        System.out.println("getUserInfoUrl : " + userData);
        User user = JSONObject.parseObject(userData, User.class);
        System.out.println(user);

        return user;
    }
}