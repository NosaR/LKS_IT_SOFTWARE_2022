package com.example.login2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntriMenu extends AppCompatActivity {
    public static final String URLINSERT = "http://192.168.75.81/api/insert3.php";

    EditText tnama_menu, tharga;
    Button btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entri_menu);

        tnama_menu = (EditText) findViewById(R.id.nama_menu);
        tharga = (EditText) findViewById(R.id.harga);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputData();
            }
        });
    }

    void InputData() {
        String nama_menu = tnama_menu.getText().toString();
        String harga = tharga.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLINSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EntriMenu.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EntriMenu.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Namamenu", nama_menu);
                params.put("Harga", harga);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}