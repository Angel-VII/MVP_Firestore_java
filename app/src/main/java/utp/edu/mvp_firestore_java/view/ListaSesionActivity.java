package utp.edu.mvp_firestore_java.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.Utils.DialogSesion;
import utp.edu.mvp_firestore_java.adapter.CategoryAdapter;
import utp.edu.mvp_firestore_java.adapter.SesionAdapter;
import utp.edu.mvp_firestore_java.contract.CategoryContract;
import utp.edu.mvp_firestore_java.contract.SesionContract;
import utp.edu.mvp_firestore_java.model.Category;
import utp.edu.mvp_firestore_java.model.Sesion;
import utp.edu.mvp_firestore_java.presenter.CategoryPresenter;
import utp.edu.mvp_firestore_java.presenter.SesionPresenter;

public class ListaSesionActivity extends AppCompatActivity implements View.OnClickListener, SesionContract.View {

    Button btCrearSesion;
    //RecyclerView rvListaSesion;
    ArrayList<Sesion> list = new ArrayList<>();
    DialogSesion dialogSesion;
    SesionAdapter adapter;
    SesionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sesion);

        presenter = new SesionPresenter(this);
        btCrearSesion = findViewById(R.id.btCrearSesion);
        RecyclerView rvListaSesion = findViewById(R.id.rvListaSesiones);

        btCrearSesion.setOnClickListener(this);
        dialogSesion = new DialogSesion(this);

        rvListaSesion.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvListaSesion.setLayoutManager(mLayoutManager);

        adapter = new SesionAdapter(list, this);

        rvListaSesion.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btCrearSesion) {
            dialogSesion.dialogDatosUsuario();
        }
    }

    @Override
    public void addItem(Sesion sesion) {
        Log.w("pruebaLista addItem", sesion.getId() + sesion.getNombre() +sesion.getFecha_creacion());
        list.add(sesion);
        // size( se resta 1 - ...)  notificar (desde 0 -...)
        adapter.notifyItemInserted(list.size() - 1);
        //adapter.notifyDataSetChanged();
    }

    @Override
    public void updateItem(int position, Sesion sesion) {
        Log.w("pruebaLista update", sesion.getId());
        list.set(position, sesion);
        adapter.notifyItemChanged(position);
       // adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteItem(int position) {
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }
}