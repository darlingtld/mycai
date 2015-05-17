package mycai.pojo;

/**
 * Created by darlingtld on 2015/5/16.
 */
public enum Category {
    YECAILEI("叶菜类"),
    GENJINGLEI("根茎类"),
    QIEGUOLEI("茄果类"),
    DOULEI("豆类"),
    CONGJIANGSUAN("葱姜蒜"),
    JUNLEI("菌类"),
    TECAI("特菜"),

    YIJIBAITIAO("一级白条"),
    ERJIBAITIAO("二级白条"),
    DONGZHUROU("冻猪肉"),
    YANGROU("羊肉"),
    NIUROU("牛肉"),
    XIANJIROU("鲜鸡肉"),
    DONGJIROU("冻鸡肉"),
    YAROU("鸭肉"),
    QINLEI("禽类"),
    QINDANLEI("禽蛋类"),
    SHUSHIJIAGONG("熟食加工"),

    HAIXIANSHUICHAN("海鲜水产"),
    YUWANHUOGUO("鱼丸火锅"),

    DAMI("大米"),
    MIANFEIMIANTIAO("面粉面条"),
    SHIYONGYOU("食用油"),
    ZALIANG("杂粮"),
    MIANDIAN("面点"),
    HONGBEIZUOLIAO("烘焙佐料"),

    TIAOWEIPIN("调味品"),
    FENSIFENTIAO("粉丝粉条"),
    DOUZHIPIN("豆制品"),
    GANHUO("干货"),
    TIAOWEIZHIJIANG("调味品"),
    JIANGYOUCU("酱油醋"),
    YANCAIGUANTOU("腌菜罐头"),

    ZHIPINSHIJIN("纸品湿巾"),
    CANYINYONGJU("餐饮用具"),

    YINLIAO("饮料"),
    YINYONGSHUI("饮用水");


    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
