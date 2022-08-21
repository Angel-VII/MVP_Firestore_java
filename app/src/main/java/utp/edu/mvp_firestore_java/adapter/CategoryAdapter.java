package utp.edu.mvp_firestore_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<Category> list;
    Context context;

    public CategoryAdapter(ArrayList<Category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category c = list.get(position);

        holder.lblTitle.setText(c.getTitle());
        holder.lblDescription.setText(c.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblTitle, lblDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblTitle = itemView.findViewById(R.id.lblCategoryTitleItem);
            lblDescription = itemView.findViewById(R.id.lblCategoryDescItem);

        }
    }
}
