package utp.edu.mvp_firestore_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.model.Historial;
import utp.edu.mvp_firestore_java.model.Sesion;
import utp.edu.mvp_firestore_java.view.HistorialActivity;
import utp.edu.mvp_firestore_java.view.MenuModuloActivity;

public class SesionHistorialAdapter extends RecyclerView.Adapter<SesionHistorialAdapter.ViewHolder> {

    ArrayList<Sesion> list;
    Context context;

    public SesionHistorialAdapter(ArrayList<Sesion> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sesion_historial, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sesion s = list.get(position);

        holder.txNombreSesion.setText(s.getNombre());
        holder.txFechaCreacion.setText(s.getFecha_creacion());
        holder.itemSesionHistorial.setOnClickListener(view -> {
            Intent intent = new Intent(context, HistorialActivity.class);
            intent.putExtra("ID_SESION", s.getId());
            intent.putExtra("CANT_A", s.getCant_actividad1());
            intent.putExtra("CANT_B", s.getCant_actividad2());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txNombreSesion, txFechaCreacion;
        LinearLayout itemSesionHistorial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemSesionHistorial = itemView.findViewById(R.id.itemSesionHistorial);
            txNombreSesion = itemView.findViewById(R.id.txNombreSesion);
            txFechaCreacion = itemView.findViewById(R.id.txFecha);

        }
    }
}
