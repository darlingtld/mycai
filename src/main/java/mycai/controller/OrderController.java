package mycai.controller;

import mycai.pojo.Order;
import mycai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return orderService.save(order);
    }
}