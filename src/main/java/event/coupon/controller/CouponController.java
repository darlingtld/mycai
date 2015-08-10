package event.coupon.controller;

import event.coupon.pojo.Coupon;
import event.coupon.pojo.Voucher;
import event.coupon.service.CouponService;
import mycai.pojo.Product;
import mycai.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by darlingtld on 2015/8/9 0009.
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {
    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/all/wechatid/{wechatid}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Coupon> getCouponList(@PathVariable("wechatid") String wechatid) {
        return couponService.getCouponList(wechatid);
    }

    @RequestMapping(value = "/voucher/wechatid/{wechatid}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Voucher> getVoucherList(@PathVariable("wechatid") String wechatid) {
        return couponService.getCouponList(wechatid, Voucher.class);
    }
}
