package utp.edu.mvp_firestore_java.presenter;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import utp.edu.mvp_firestore_java.model.Sesion;

public class SesionInteractor {
    ArrayList<Sesion> list = new ArrayList<>();
    CollectionReference sesionCollection = FirebaseFirestore.getInstance().collection("sesion");
    SesionPresenter presenter;

    SesionInteractor(SesionPresenter presenter) {
        this.presenter = presenter;
        sesionListener();
    }

    private void sesionListener() {
        sesionCollection.addSnapshotListener((queryDocumentSnapshots, error) -> {
            updateValues(queryDocumentSnapshots.getDocumentChanges());
        });
    }

    protected void updateValues(List<DocumentChange> changeList) {
        int position;
        for (DocumentChange document_change : changeList) {
            DocumentSnapshot document = document_change.getDocument();
            Log.w("pruebaLista", document.getId());
            position = itemPosition(document.getId());
            if (document_change.getType() == DocumentChange.Type.REMOVED) {
                list.remove(position);
                presenter.deleteItemP(position);
            } else {
                Sesion sesion = getSesion(document);
                if (position >= 0) {
                    list.set(position, sesion);
                    presenter.updateItemP(position, sesion);
                } else {
                    list.add(sesion);
                    Log.w("pruebaLista add", sesion.getId());
                    presenter.addItemP(sesion);
                }
            }
        }
    }

    protected Sesion getSesion(DocumentSnapshot document) {
        Sesion sesion = document.toObject(Sesion.class);

        Log.w("pruebaLista", sesion.getId());
        //sesion.setId(document.getId());
        //esion.setNombre(document.getString("title"));
        // sesion.setFecha_creacion(document.getString("description"));
        return sesion;
    }

    protected int itemPosition(String id) {
        for (Sesion sesion : list) {
            if (sesion.getId().equals(id)) {
                return list.indexOf(sesion);
            }
        }
        return -1;
    }

}
