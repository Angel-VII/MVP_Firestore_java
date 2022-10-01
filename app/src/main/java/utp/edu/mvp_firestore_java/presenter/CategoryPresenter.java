package utp.edu.mvp_firestore_java.presenter;

import utp.edu.mvp_firestore_java.contract.CategoryContract;
import utp.edu.mvp_firestore_java.model.Category;

public class CategoryPresenter implements CategoryContract.Presenter {
    CategoryContract.View view;
    CategoryInteractor interactor;

    public CategoryPresenter(final CategoryContract.View view) {
        this.view = view;
        interactor = new CategoryInteractor(this);
    }

    @Override
    public void addItemP(Category category) {
        view.addItem(category);
    }

    @Override
    public void updateItemP(int position, Category category) {
        view.updateItem(position, category);
    }

    @Override
    public void deleteItemP(int position) {
        view.deleteItem(position);
    }
}
