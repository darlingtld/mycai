package mycai.controller;

import mycai.pojo.Order;
import mycai.pojo.User;
import mycai.service.OrderService;
import mycai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by darlingtld on 2015/6/24 0024.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUserInformation(@PathVariable("code") String code, HttpServletResponse response) {
        User user = userService.getUserInformation(code);
        List<Order> orderList = orderService.getList(user.getOpenid());
        user.setOrderList(orderList);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/save_or_update", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public
    @ResponseBody
    User saveUser(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @RequestMapping(value = "/wechatId/{wechatId}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUser(@PathVariable("wechatId") String wechatId) {
        return userService.getUserByWechatId(wechatId);
    }
}
