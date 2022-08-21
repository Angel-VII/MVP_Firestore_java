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
    public void addItem(Category category) {
        view.addItem(category);
    }

    @Override
    public void updateItem(int position, Category category) {
        view.updateItem(position, category);
    }

    @Override
    public void deleteItem(int position) {
        view.deleteItem(position);
    }
}
