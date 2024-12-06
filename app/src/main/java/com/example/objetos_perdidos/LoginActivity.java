package com.example.objetos_perdidos;

//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//import java.io.IOException;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private EditText editTextEmail, editTextPassword;
//    private Button loginButton;
//
//    private final OkHttpClient client = new OkHttpClient();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        editTextEmail = findViewById(R.id.et_email);
//        editTextPassword = findViewById(R.id.et_password);
//        loginButton = findViewById(R.id.btn_login);
//
//        loginButton.setOnClickListener(v -> loginUser());
//    }
//
//    private void loginUser() {
//        String email = editTextEmail.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String apiUrl = "http://192.168.1.71:8000/api/login/";
//
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        JsonObject json = new JsonObject();
//        json.addProperty("correo", email);
//        json.addProperty("contrasena", password);
//
//        RequestBody body = RequestBody.create(JSON, json.toString());
//
//        Request request = new Request.Builder()
//                .url(apiUrl)
//                .post(body)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("LoginError", e.getMessage());
//                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Error en conexión", Toast.LENGTH_SHORT).show());
//            }
//
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                if (response.isSuccessful()) {
////                    String responseData = response.body().string();
////                    JsonObject jsonResponse = JsonParser.parseString(responseData).getAsJsonObject();
////
////                    String userType = jsonResponse.get("user_type").getAsString();
////                    int userId = jsonResponse.get("user_id").getAsInt();
////
////                    runOnUiThread(() -> {
////                        if (userType.equals("alumno")) {
////                            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
////                            startActivity(intent);
////                        } else if (userType.equals("administrador")) {
////                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
////                            startActivity(intent);
////                        }
////                        finish();
////                    });
////                } else {
////                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show());
////                }
////            }
//@Override
//public void onResponse(Call call, Response response) throws IOException {
//    if (response.isSuccessful()) {
//        String responseData = response.body().string();
//        JsonObject jsonResponse = JsonParser.parseString(responseData).getAsJsonObject();
//
//        // Aquí puedes manejar datos específicos del usuario, si es necesario
//        String userType = jsonResponse.get("user_type").getAsString(); // Si deseas usar este dato
//        int userId = jsonResponse.get("user_id").getAsInt(); // Si deseas usar este dato
//
//        runOnUiThread(() -> {
//            // Redirigir a ObjetosPerdidosActivity después del login exitoso
//            Intent intent = new Intent(LoginActivity.this, ObjetosPerdidosActivity.class);
//            startActivity(intent);
//            finish(); // Finaliza LoginActivity para que no quede en el stack
//        });
//    } else {
//        runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show());
//    }
//}
//
//
//        });
//    }
//}

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button loginButton;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String apiUrl = "http://192.168.1.71:8000/api/login/";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JsonObject json = new JsonObject();
        json.addProperty("correo", email);
        json.addProperty("contrasena", password);

        RequestBody body = RequestBody.create(JSON, json.toString());

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("LoginError", "Error en conexión: " + e.getMessage());
                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Error en conexión", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JsonObject jsonResponse = JsonParser.parseString(responseData).getAsJsonObject();

                    // Extraer datos del usuario desde la respuesta
                    String userType = jsonResponse.get("user_type").getAsString();
                    int userId = jsonResponse.get("user_id").getAsInt();

                    // Redirigir a ObjetosPerdidosActivity
                    runOnUiThread(() -> {
                        Intent intent = new Intent(LoginActivity.this, ObjetosPerdidosActivity.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        finish(); // Cierra LoginActivity
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}


