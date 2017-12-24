package cnmp.com.howtospeak.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.model.Category;

/**
 * Created by henry on 12/10/2017.
 */

public class ViewHolderCategory extends RecyclerView.ViewHolder {
    public ImageView imvCategoryImage;
    public TextView txtCategoryName;
    private Category category=null;

    public ViewHolderCategory(final View itemView) {
        super(itemView);
        imvCategoryImage = itemView.findViewById(R.id.imv_category_image);
        txtCategoryName = itemView.findViewById(R.id.txt_category_name);

        //called when click category
    }
}
