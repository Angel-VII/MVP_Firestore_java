package utp.edu.mvp_firestore_java.contract;

import utp.edu.mvp_firestore_java.model.Category;

public interface CategoryContract {
    interface View{
        void addItem(Category category);
        void updateItem(int position,Category category);
        void deleteItem(int position);
    }

    interface Presenter{
        void addItem(Category category);
        void updateItem(int position,Category category);
        void deleteItem(int position);
    }
}
