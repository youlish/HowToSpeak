package cnmp.com.howtospeak.model;

/**
 * Created by henry on 12/10/2017.
 */

public class CategoryItem {
    private String categoryName;
    private int resImage;

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public CategoryItem() {

    }

    public CategoryItem(String categoryName, int resImage) {

        this.categoryName = categoryName;
        this.resImage = resImage;
    }
}
