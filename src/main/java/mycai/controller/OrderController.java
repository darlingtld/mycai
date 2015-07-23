package mycai.controller;

import mycai.excel.ExcelFactory;
import mycai.pojo.Dispatch;
import mycai.pojo.Order;
import mycai.service.OrderService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping(value = "/dispatch/list", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Dispatch> getDispatchList() {
        return orderService.getDispatchList();
    }

    @RequestMapping(value = "/dispatch/export", method = RequestMethod.GET)
    public
    @ResponseBody
    void exportDispatchList() {
        orderService.exportDispatchList();
    }

    @RequestMapping("/export")
    public ResponseEntity<byte[]> download() throws IOException {
        String fileName = String.format("订单列表%s.xlsx", new SimpleDateFormat("yyyyMMdd").format(new Date()));
        ExcelFactory.exportOrders(fileName, orderService.getAll(), orderService.getStatusList());
        File file = new File(fileName);
        HttpHeaders headers = new HttpHeaders();
        String cnfileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", cnfileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping("/dispatch/export")
    public ResponseEntity<byte[]> downloadDispatch() throws IOException {
        String fileName = String.format("配送列表%s.xlsx", new SimpleDateFormat("yyyyMMdd").format(new Date()));
        ExcelFactory.exportDispatches(fileName, orderService.getDispatchList());
        File file = new File(fileName);
        HttpHeaders headers = new HttpHeaders();
        String cnfileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", cnfileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}