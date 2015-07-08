package mycai.controller;

import mycai.pojo.Order;
import mycai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public
    @ResponseBody
    int submitOrder(@RequestBody Order order) {
        order.setStatus("未配送");
        try {
            order.setUserId(new String(order.getUserId().getBytes(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            order.setUserId("unknown");
        }
        return orderService.save(order);
    }

    @RequestMapping(value = "/get/{wechatid}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getOrders(@PathVariable("wechatid") String wechatid) {
        return orderService.getList(wechatid);
    }

    @RequestMapping(value = "/confirm_code/{confirm_code}", method = RequestMethod.GET)
    public
    @ResponseBody
    Order getOrderByConfirmCode(@PathVariable("confirm_code") String confirmCode) {
        return orderService.getOrderByConfirmCode(confirmCode);
    }

    @RequestMapping(value = "/detail/{orderid}", method = RequestMethod.GET)
    public
    @ResponseBody
    Order getOrderDetail(@PathVariable("orderid") int orderId) {
        return orderService.getById(orderId);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getOrdersAll() {
        return orderService.getAll();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public
    @ResponseBody
    boolean updateOrder(@RequestBody Order order) {
        return orderService.update(order);
    }
}