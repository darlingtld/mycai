package mycai.pojo;

/**
 * Created by darlingtld on 2015/5/16.
 */
public enum Category {
    YECAILEI("叶菜类");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
