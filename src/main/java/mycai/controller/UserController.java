package mycai.controller;

import mycai.pojo.User;
import mycai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by darlingtld on 2015/6/24 0024.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUserInformation(@PathVariable("code") String code) {
        User user = userService.getUserInformation(code);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/save_or_update", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public
    @ResponseBody
    void saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }

    @RequestMapping(value = "/wechatId/{wechatId}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUser(@PathVariable("wechatId") String wechatId) {
        return userService.getUserByWechatId(wechatId);
    }
}
