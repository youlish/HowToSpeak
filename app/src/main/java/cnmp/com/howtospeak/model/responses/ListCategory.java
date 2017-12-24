package cnmp.com.howtospeak.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import cnmp.com.howtospeak.model.Category;

/**
 * Created by hinh1 on 12/24/2017.
 */

public class ListCategory {
    @SerializedName("listVideo")
    @Expose
    private ArrayList<Category> listCategory = null;

    public ArrayList<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(ArrayList<Category> listCategory) {
        this.listCategory = listCategory;
    }
}
