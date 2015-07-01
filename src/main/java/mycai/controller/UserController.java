package mycai.controller;

import mycai.pojo.User;
import mycai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
