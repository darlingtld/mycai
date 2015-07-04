(function () {
    $('.menu-mask').on('touchstart click', function () {
        $(this).hide();
    })
    // clear old data
    window.clearCache();

    //global variables
    var $category = $(".js-category");
    var $navga = $('.menu');
    var $curCat1, $curCat2;
    var ccat1, ccat2;
    $(".js-content").css("height", ($(window).height() - $(".js-reminder").height() - $(".js-menu-bar").height() - $(".js-footer").height()) + "px");
    $(".js-search-content").css("height", $(window).height() - 90);

    function geneLevel2DOM(data, ccat1) {
        var level2HTML = "";
        _.each(_.pairs(data[ccat1]), function (item) {
            level2HTML += "<li class='cp'><a href='#/product/category/" + item[1] + "'>" + item[0] + "</a></li>";
        });
        $category.html("").append($(level2HTML));

        //bind category event
        _.each($category.find("li"), function (item) {
            $item = $(item);
            $item.click(function () {
                resetCategoryBgColor($curCat2);
                $this = $curCat2 = $(this);
                ccat2 = $this.text().trim();
                $this.addClass("active");
            });
        });

        $firstCategory = $($category.find("li")[0]);
        if ($firstCategory) {
            $curCat2 = $firstCategory;
            $firstCategory.click();
        }
    }

    function resetCategoryBgColor($link) {
        if (!$link) return;
        $link.removeClass("active");
    }

    window.data = {
        "蔬菜水果": {
            "叶菜类": "yecailei",
            "根茎类": "genjinglei",
            "茄果类": "qieguolei",
            "豆类": "doulei",
            "葱姜蒜": "congjiangsuan",
            "菌类": "junlei",
            "特菜": "tecai"
        },
        "禽肉蛋类": {
            "一级白条": "yijibaitiaon",
            "二级白条": "erjibaitiao",
            "冻猪肉": "dongzhurou",
            "羊肉": "yangrou",
            "牛肉": "niurou",
            "鲜鸡肉": "xianjirou",
            "冻鸡肉": "dongjirou",
            "鸭肉": "yarou",
            "禽类": "qinlei",
            "禽蛋类": "qindanlei",
            "熟食加工": "shushijiagong"
        },
        "水产冻货": {
            "海鲜水产": "haixianshuichan",
            "鱼丸火锅": "yuwanhuoguo",
        },
        "米面粮油": {
            "大米": "dami",
            "面粉面条": "mianfenmiantiao",
            "食用油": "shiyongyou",
            "杂粮": "zaliang",
            "面点": "miandian",
            "烘焙佐料": "hongbeizuoliao",
        },
        "调料其他": {
            "调味品": "tiaoweipin",
            "粉丝粉条": "fensifentiao",
            "豆制品": "douzhipin",
            "干货": "ganhuo",
            "调味品": "tiaoweipin",
            "酱油醋": "jiangyoucu",
            "腌菜罐头": "yancaiguantou"
        },
        "餐厨用品": {
            "纸品湿巾": "zhipinshijin",
            "餐饮用具": "canyinyongju",
        },
        "酒水饮料": {
            "饮料": "yinliao",
            "饮用水": "yinyongshui",
        }
    };
    var navHtml = '';
    window.hasFavorite = true;
    _.each(_.keys(data), function (key) {
        navHtml += "<li>" + key + "</li>";
    });

    $navga.append($(navHtml));


    _.each($navga.find("li"), function (item) {
        var $item = $(item);
        $item.click(function () {
            resetCategoryBgColor($curCat1);
            $this = $curCat1 = $(this);
            ccat1 = $this.text().trim();
            $this.addClass("active");
            geneLevel2DOM(data, ccat1);

        });
    });

    var $level1 = $($navga.find("li")[0]);
    if ($level1) {
        $curCat1 = $level1;
        $level1.click();
    }

    $('ul.menu').on('click', 'li', function () {
        $category.find("li>a")[0].click();
    })
}());

