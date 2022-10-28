package utp.edu.mvp_firestore_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.Utils.DialogSesion;
import utp.edu.mvp_firestore_java.adapter.SesionHistorialAdapter;
import utp.edu.mvp_firestore_java.contract.SesionContract;
import utp.edu.mvp_firestore_java.model.Sesion;
import utp.edu.mvp_firestore_java.presenter.SesionPresenter;

public class ListaSesionActivity extends AppCompatActivity implements View.OnClickListener, SesionContract.View {

    Button btCrearSesion;
    //RecyclerView rvListaSesion;
    ArrayList<Sesion> list = new ArrayList<>();
    DialogSesion dialogSesion;
    SesionHistorialAdapter adapter;
    SesionPresenter presenter;
    Toolbar tbListaSesion;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sesion);

        btCrearSesion = findViewById(R.id.btCrearSesion);
        tbListaSesion = findViewById(R.id.tbListaSesiones);

        auth = FirebaseAuth.getInstance();
        presenter = new SesionPresenter(this , auth.getUid());
        btCrearSesion.setOnClickListener(this);
        dialogSesion = new DialogSesion(this);

        setSupportActionBar(tbListaSesion);

        RecyclerView rvListaSesion = findViewById(R.id.rvListaSesiones);
        rvListaSesion.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvListaSesion.setLayoutManager(mLayoutManager);
        adapter = new SesionHistorialAdapter(list, this);
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
        Log.w("pruebaLista addItem", sesion.getId_usuario() + "  "+auth.getUid());
            list.add(sesion);
            adapter.notifyItemInserted(list.size() - 1);

        //list = (ArrayList<Sesion>) list.stream().filter(o -> o.getId().equals(auth.getUid())).collect(Collectors.toList());

        // size( se resta 1 - ...)  notificar (desde 0 -...)

        //adapter.notifyDataSetChanged();
    }

    @Override
    public void updateItem(int position, Sesion sesion) {

            list.set(position, sesion);
            adapter.notifyItemChanged(position);

       // adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteItem(int position) {
            list.remove(position);
            adapter.notifyItemRemoved(position);
    }

    //menu toolbar
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemCerrarSesion:
                FirebaseAuth auth =  FirebaseAuth.getInstance();
                auth.signOut();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}