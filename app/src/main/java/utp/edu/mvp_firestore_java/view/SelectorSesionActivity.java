package utp.edu.mvp_firestore_java.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.adapter.SesionHistorialAdapter;
import utp.edu.mvp_firestore_java.adapter.SesionActividadAdapter;
import utp.edu.mvp_firestore_java.contract.SesionContract;
import utp.edu.mvp_firestore_java.model.Sesion;
import utp.edu.mvp_firestore_java.presenter.SesionPresenter;

public class SelectorSesionActivity extends AppCompatActivity implements SesionContract.View {

    RecyclerView rvSelectorSesion;
    ArrayList<Sesion> list;

    SesionActividadAdapter adapter;
    SesionPresenter presenter;
    Toolbar tbSelectorSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_sesion);

        tbSelectorSesion = findViewById(R.id.tbSelectorSesion);
        rvSelectorSesion= findViewById(R.id.rvSelectorSesion);

        list = new ArrayList<>();
        presenter = new SesionPresenter(this,"");

        setSupportActionBar(tbSelectorSesion);

        rvSelectorSesion.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvSelectorSesion.setLayoutManager(mLayoutManager);

        adapter = new SesionActividadAdapter(list, this);

        rvSelectorSesion.setAdapter(adapter);
    }

    @Override
    public void addItem(Sesion sesion) {
        list.add(sesion);
        adapter.notifyItemInserted(list.size() - 1);
    }

    @Override
    public void updateItem(int position, Sesion sesion) {
        list.set(position, sesion);
        adapter.notifyItemChanged(position);
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
    @Override public void onBackPressed() {}
}