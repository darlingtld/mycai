/**
 * Created by sarazhu on 15/5/24.
 */

var _order;

$(function () {
    _initialPage();
});

function _initialPage() {

    //init table
    $.ajax({
        type: "get",
        url: "/mycai/order/getall",
        dataType: "json",
        success: function (data) {
            $("#orderTemplate").tmpl(data).appendTo("#orderList");
            _order = data;
            _pageTable(10);
            $("#loading").addClass("hidden");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.responseText);
        }
    });

    $("#pagination").pagination(15, {
        items_per_page: 10
    });
}


function _updateOrder() {
    var service = _getServiceBySerivceId($("#serviceId").val());
    service.orderId = $("#orderId").val();
    service.type = $("#orderType").val();
    service.price = $("#orderPrice").val();
    service.ts = $("#orderTs").val();
    $.ajax({
        type: "post",
        url: "/service/update",
        contentType: "application/json",
        data: JSON.stringify(service),
        success: function (data) {
            $("#loading").addClass("hidden");
            location.reload();
        },
        error: function (data) {
            $("#loading").addClass("hidden");
            alert(data.status);
        }
    });
}


function paginationEnter(evt, val) {
    var evt = evt ? evt : (window.event ? window.event : null);//兼容IE和FF
    if (evt.keyCode == 13) {
        if(val < 1){
            alert("请输入大于0的数！");
            return;
        }
        _pageTable($("#numPerPage").val());
    }
}

function _pageTable(numPerPage) {
    $("#pagination").pagination(_order.length, {
        callback: pageSelectCallback,
        items_per_page: numPerPage,
        prev_text: "上一页",
        next_text: "下一页"
    });
}

function pageSelectCallback(page_index) {
    var numPerPage = $("#numPerPage").val();
    $("#orderList tr").hide();
    if (page_index == 0) {
        $("#orderList tr:lt(" + numPerPage + ")").show();
    } else {
        $("#orderList tr:gt(" + (page_index * numPerPage - 1) + ")").show();
        $("#orderList tr:gt(" + ((page_index + 1) * numPerPage - 1) + ")").hide();
    }
}