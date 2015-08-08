package event.coupon.pojo;

import mycai.pojo.Order;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by darlingtld on 2015/8/8 0008.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "coupon_type")
@DiscriminatorValue("coupon")
public abstract class Coupon {
    protected static Logger logger = LoggerFactory.getLogger(Coupon.class);
    @Id
    private int id;
//    @Column(name = "coupon_type")
//    private String couponType;
    @Column(name = "openid")
    private String openid;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
//                ", couponType='" + couponType + '\'' +
                ", openid='" + openid + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

//    public String getCouponType() {
//        return couponType;
//    }
//
//    public void setCouponType(String couponType) {
//        this.couponType = couponType;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public abstract double deduct(Order order);

}
