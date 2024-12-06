package com.example.objetos_perdidos;

//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class ObjetosPerdidosActivity extends AppCompatActivity {
//
//    private ListView listView;
//    private ObjetoPerdidoAdapter adapter;
//    private List<ObjetoPerdido> objetos;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_objetos_perdidos);
//
//        listView = findViewById(R.id.list_view_objetos);
//        objetos = new ArrayList<>();
//        adapter = new ObjetoPerdidoAdapter(this, objetos);
//        listView.setAdapter(adapter);
//
//        // Cargar datos desde la API
//        cargarObjetosDesdeAPI();
//    }
//
//    private void cargarObjetosDesdeAPI() {
//        String apiUrl = "http://192.168.1.71:8000/api/usuario/";
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(apiUrl)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("API_ERROR", "Error en la solicitud: " + e.getMessage());
//                runOnUiThread(() ->
//                        Toast.makeText(ObjetosPerdidosActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show()
//                );
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String responseBody = response.body().string();
//                    JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
//
//                    // Parsear los objetos desde el JSON
//                    for (int i = 0; i < jsonArray.size(); i++) {
//                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
//                        String urlImagen = jsonObject.get("url_imagen").getAsString();
//                        String descripcion = jsonObject.get("descripcion").getAsString();
//
//                        // Crear un objeto perdido y agregarlo a la lista
//                        objetos.add(new ObjetoPerdido(urlImagen, descripcion));
//                    }
//
//                    // Actualizar el adaptador en el hilo principal
//                    runOnUiThread(() -> adapter.notifyDataSetChanged());
//                } else {
//                    Log.e("API_ERROR", "Respuesta no exitosa: " + response.code());
//                    runOnUiThread(() ->
//                            Toast.makeText(ObjetosPerdidosActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show()
//                    );
//                }
//            }
//        });
//    }
//}

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ObjetosPerdidosActivity extends AppCompatActivity {

    private ListView listView;
    private ObjetoPerdidoAdapter adapter;
    private List<ObjetoPerdido> objetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetos_perdidos);

        listView = findViewById(R.id.list_view_objetos);
        objetos = new ArrayList<>();
        adapter = new ObjetoPerdidoAdapter(this, objetos);
        listView.setAdapter(adapter);

        // Cargar objetos desde la API
        cargarObjetosDesdeAPI();
    }

    private void cargarObjetosDesdeAPI() {
        String apiUrl = "http://192.168.1.71:8000/api/usuario/";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("API_ERROR", "Error en la solicitud: " + e.getMessage());
                runOnUiThread(() ->
                        Toast.makeText(ObjetosPerdidosActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();

                    // Parsear los objetos perdidos desde el JSON
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                        String urlImagen = jsonObject.get("url_imagen").getAsString();
                        String descripcion = jsonObject.get("descripcion").getAsString();

                        // Agregar objeto a la lista
                        objetos.add(new ObjetoPerdido(urlImagen, descripcion));
                    }

                    // Actualizar el adaptador en el hilo principal
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                } else {
                    Log.e("API_ERROR", "Respuesta no exitosa: " + response.code());
                    runOnUiThread(() ->
                            Toast.makeText(ObjetosPerdidosActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                    );
                }
            }
        });
    }
}


