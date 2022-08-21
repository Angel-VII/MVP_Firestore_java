package utp.edu.mvp_firestore_java.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.adapter.CategoryAdapter;
import utp.edu.mvp_firestore_java.contract.CategoryContract;
import utp.edu.mvp_firestore_java.model.Category;
import utp.edu.mvp_firestore_java.presenter.CategoryPresenter;

public class CategoryListActivity extends AppCompatActivity implements CategoryContract.View {

    ArrayList<Category> list = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    CategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new CategoryPresenter(this);

        RecyclerView recycler = findViewById(R.id.recyclerCategory);
        recycler.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(mLayoutManager);

        categoryAdapter = new CategoryAdapter(list, this);

        recycler.setAdapter(categoryAdapter);
    }

    @Override
    public void addItem(Category category) {
        list.add(category);
        categoryAdapter.notifyItemChanged(list.size()-1);
    }

    @Override
    public void updateItem(int position, Category category) {
        list.set(position, category);
        categoryAdapter.notifyItemChanged(position);
    }

    @Override
    public void deleteItem(int position) {
        list.remove(position);
        categoryAdapter.notifyItemRemoved(position);
    }
}