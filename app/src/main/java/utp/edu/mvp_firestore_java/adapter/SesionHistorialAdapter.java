package utp.edu.mvp_firestore_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.model.Sesion;

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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txNombreSesion, txFechaCreacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txNombreSesion = itemView.findViewById(R.id.txNombreSesion);
            txFechaCreacion = itemView.findViewById(R.id.txFecha);

        }
    }
}
