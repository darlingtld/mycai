/**
 * Created by sarazhu on 15/5/24.
 */

$(function () {
    _initialPage();
});

function _initialPage() {

    //init table
    $.ajax({
        type: "get",
        url: "/mycai/order/detail/"+getURLParameter("id"),
        dataType: "json",
        success: function (data) {
            $("#orderTs").text(data.orderTs);
            $("#deliveryTs").text(data.deliveryTs);
            $("#shopInfo").text(data.shopInfo);
            $("#consignee").text(data.consignee);
            $("#contact").text(data.consigneeContact);
            $("#status").text(data.status);
            var bill = JSON.parse(data.bill);
            $("#totalAmount").text(bill.totalAmount);
            $("#totalPrice").text(bill.totalPrice);
            var html="";
            for(var i=0;i<bill.items.length;i++){
                html+="<tr>"+
                "<td style=\"width: 50%; text-align: center; vertical-align: middle\" rowspan=\"3\">"+
                "<img class=\"img\" src=\""+bill.items[i].picurl+"\" style=\"width: 30%;\"/>"+
                "</td>"+
                "<td>商品名称：<span class=\"productName\">"+bill.items[i].productName+"</span></td>"+
                "</tr>"+
                "<tr>"+
                "<td>商品单价：<span class=\"productPrice\">"+bill.items[i].productPrice+"</span></td>"+
                "</tr>"+
                "<tr>"+
                "<td>商品数量：<span class=\"amount\">"+bill.items[i].amount+"</span></td>"+
                "</tr>";
            }
            $("#details").html(html);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.responseText);
        }
    });
}

function getURLParameter(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}