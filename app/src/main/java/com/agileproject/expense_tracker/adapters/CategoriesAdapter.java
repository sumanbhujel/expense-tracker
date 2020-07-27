package com.agileproject.expense_tracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.activities.CategoryActivity;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.models.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private String catDiv;

    public CategoriesAdapter(Context context, List<Category> categoryList, String catDiv) {
        this.context = context;
        this.categoryList = categoryList;
        this.catDiv = catDiv;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View categoriesView;
        if (this.catDiv.equals("user")) {
            categoriesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_category, parent, false);
        } else {
            categoriesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_default_category, parent, false);
        }
        return new CategoryViewHolder(categoriesView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bindData(category, this.catDiv);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIcon;
        private TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

//            categoryIcon = itemView.findViewById(R.id.iv_icon);
//            categoryName = itemView.findViewById(R.id.tv_category_name);
//            if (catDiv.equals("user")) {
//                ImageButton btnCatDelete = itemView.findViewById(R.id.btn_cat_delete);
//                btnCatDelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ((CategoryActivity) context).confirmCategoryDelete(categoryList.get(getAdapterPosition()).get_id());
//                    }
//                });
//
//                ImageButton btnCatEdit = itemView.findViewById(R.id.btn_cat_edit);
//                btnCatEdit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ((CategoryActivity) context).showCatUpdateDialog(categoryList.get(getAdapterPosition()).get_id());
//                    }
//                });
//            }

        }
        public void bindData(Category category, String catDiv) {
            if (catDiv.equals("user")) {
                categoryName.setText(category.getName() + " ( " + category.getType() + " )");
            } else {
                categoryName.setText(category.getName());
            }
            Helper.setIcon(category.getIcon(), categoryIcon);
        }
    }
}
