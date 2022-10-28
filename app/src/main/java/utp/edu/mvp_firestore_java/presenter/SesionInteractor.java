package utp.edu.mvp_firestore_java.presenter;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import utp.edu.mvp_firestore_java.model.Sesion;

public class SesionInteractor {
    ArrayList<Sesion> list = new ArrayList<>();
    CollectionReference sesionCollection = FirebaseFirestore.getInstance().collection("sesion");
    SesionPresenter presenter;
    //FirebaseAuth auth;

    SesionInteractor(SesionPresenter presenter,String filtroUsuario ) {
        this.presenter = presenter;
        sesionListener(filtroUsuario);
    }

    private void sesionListener(String filtroUsuario ) {
        //auth = FirebaseAuth.getInstance();
        //.whereEqualTo("id_usuario",auth.getUid()+"")

        Query query = sesionCollection;
        if(!filtroUsuario.equals("")){
            query =  sesionCollection.whereEqualTo("id_usuario",filtroUsuario);
        }
        query.addSnapshotListener((queryDocumentSnapshots, error) -> {
            updateValues(queryDocumentSnapshots.getDocumentChanges());
        });
    }

    protected void updateValues(List<DocumentChange> changeList) {
        int position;
        for (DocumentChange document_change : changeList) {
            DocumentSnapshot document = document_change.getDocument();

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
                    presenter.addItemP(sesion);
                }
            }
        }
    }

    protected Sesion getSesion(DocumentSnapshot document) {
        Sesion sesion = document.toObject(Sesion.class);

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
