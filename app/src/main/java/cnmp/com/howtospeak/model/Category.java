package cnmp.com.howtospeak.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hinh1 on 12/24/2017.
 */

public class Category implements Serializable {
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("id")
    @Expose
    private Integer id;

    private int status;

    private int resImage;

    public Category(String categoryName, int id, int status, int resImage) {
        this.categoryName = categoryName;
        this.id = id;
        this.status = status;
        this.resImage = resImage;
    }

    public Category() {
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
