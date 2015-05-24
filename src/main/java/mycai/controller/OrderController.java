package mycai.controller;

import mycai.pojo.Order;
import mycai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return orderService.save(order);
    }

    @RequestMapping(value = "/get/{userid}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getOrders(@PathVariable("userid") String userid) {
        return orderService.getList(userid);
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
}