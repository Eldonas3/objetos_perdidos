package com.example.objetos_perdidos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ObjetoPerdidoAdapter extends BaseAdapter {

    private Context context;
    private List<ObjetoPerdido> objetos;

    public ObjetoPerdidoAdapter(Context context, List<ObjetoPerdido> objetos) {
        this.context = context;
        this.objetos = objetos;
    }

    @Override
    public int getCount() {
        return objetos.size();
    }

    @Override
    public Object getItem(int position) {
        return objetos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_objeto_perdido, parent, false);
        }

        ObjetoPerdido objeto = objetos.get(position);

        ImageView ivObjeto = convertView.findViewById(R.id.iv_objeto);
        TextView tvDescripcion = convertView.findViewById(R.id.tv_descripcion);
        ImageView ivDetalles = convertView.findViewById(R.id.iv_detalles);

        // Cargar la imagen con Picasso
        Picasso.get().load(objeto.getUrlImagen()).into(ivObjeto);

        tvDescripcion.setText(objeto.getDescripcion());

        // Acción al presionar el botón "Detalles"
        ivDetalles.setOnClickListener(v -> {
            // Aquí defines lo que ocurre al presionar "Detalles"
        });

        return convertView;
    }
}

