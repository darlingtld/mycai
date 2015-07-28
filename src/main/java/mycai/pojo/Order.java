package mycai.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tangld on 2015/5/20.
 */
@Entity
@Table(name = "bill_order")
public class Order {
    @Id
    private int id;
    @Column(name = "userid")
    private String userId;
    @Column(name = "wechat_id")
    private String wechatId;
    @Column(name = "bill")
    private String bill;
    @Column(name = "delivery_ts")
    private String deliveryTs;
    @Column(name = "order_ts")
    private String orderTs;
    @Column(name = "shop_info")
    private String shopInfo;
    @Column(name = "shop_address")
    private String shopAddress;
    @Column(name = "consignee")
    private String consignee;
    @Column(name = "consignee_contact")
    private String consigneeContact;
    @Column(name = "status")
    private String status;
    @Column(name = "confirm_code")
    private String confirmCode;
    @Column(name = "confirm_bill")
    private String confirmBill;
    @Column(name = "confirm_ts")

    private String confirmTs;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", wechatId='" + wechatId + '\'' +
                ", bill='" + bill + '\'' +
                ", deliveryTs='" + deliveryTs + '\'' +
                ", orderTs='" + orderTs + '\'' +
                ", shopInfo='" + shopInfo + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", consignee='" + consignee + '\'' +
                ", consigneeContact='" + consigneeContact + '\'' +
                ", status='" + status + '\'' +
                ", confirmCode='" + confirmCode + '\'' +
                ", confirmBill='" + confirmBill + '\'' +
                ", confirmTs='" + confirmTs + '\'' +
                '}';
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getConfirmTs() {
        return confirmTs;
    }

    public void setConfirmTs(String confirmTs) {
        this.confirmTs = confirmTs;
    }

    public String getConfirmBill() {
        return confirmBill;
    }

    public void setConfirmBill(String confirmBill) {
        this.confirmBill = confirmBill;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    public String getOrderTs() {
        return orderTs;
    }

    public void setOrderTs(String orderTs) {
        this.orderTs = orderTs;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getDeliveryTs() {
        return deliveryTs;
    }

    public void setDeliveryTs(String deliveryTs) {
        this.deliveryTs = deliveryTs;
    }

    public String getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(String shopInfo) {
        this.shopInfo = shopInfo;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeContact() {
        return consigneeContact;
    }

    public void setConsigneeContact(String consigneeContact) {
        this.consigneeContact = consigneeContact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
