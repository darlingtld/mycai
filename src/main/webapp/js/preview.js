/**
 * Created by darlingtld on 2015/7/4 0004.
 */
(function () {
    $('.menu-mask').on('touchstart click', function () {
        $(this).hide();
    })
    // clear old data
    window.clearCache();

    //global variables
    var $category = $(".js-category");
    var $categoryDetail = $(".js-category-detail");
    var $navga = $('.menu');
    var $curCat1, $curCat2;
    var ccat1, ccat2;
    $(".js-content").css("height", ($(window).height() - $(".js-reminder").height() - $(".js-menu-bar").height() - $(".js-footer").height()) + "px");
    $(".js-search-content").css("height", $(window).height() - 90);

    //search input
    $(".js-search").change(function () {
        var val = $(this).val();
        if (val.trim() != "") {
            $(".js-menu-bar").addClass('hide');
            $(".js-content").addClass('hide');
            $(".js-search-content").removeClass('hide');
            //show the back link
            $(".js-search-back-block").css("display", "inline-block");
            $(".js-search-input-block").css("width", "78%");
        } else {
            $(".js-menu-bar").removeClass('hide');
            $(".js-content").removeClass('hide');
            $(".js-search-content").addClass('hide');
            //hide the back link
            $(".js-search-back-block").css("display", "none");
            $(".js-search-input-block").css("width", "100%");
            //refresh active list
            $(".js-category .active").click();
        }
    });
    $(".js-search-back-block a").click(function () {
        $(".js-search").val("").change();
    });

    function geneLevel2DOM(data, ccat1) {
        var level2HTML = "";
        _.each(_.keys(data[ccat1]), function (item) {
            level2HTML += "<li class='cp'>" + item + "</li>";
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

    //initial page
    window.data = {
        "蔬菜水果": {
            "促销": "abc",
            "叶菜类": "cde"
        }, "蔬菜水果1": {
            "促销": "abc",
            "叶菜类": "cde"
        }, "蔬菜水果2": {
            "促销": "abc",
            "叶菜类": "cde"
        }, "蔬菜水果3": {
            "促销": "abc",
            "叶菜类": "cde"
        }, "蔬菜水果4": {
            "促销": "abc",
            "叶菜类": "cde"
        }, "蔬菜水果5": {
            "促销": "abc",
            "叶菜类": "cde"
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

}());


