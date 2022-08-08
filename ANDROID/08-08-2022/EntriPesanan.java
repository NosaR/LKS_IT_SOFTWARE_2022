package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Config;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntriPesanan extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    Spinner sp_id_menu, sp_id_pelanggan, sp_jumlah, sp_id_user;
    EditText id_pesanan;
    String URLIDMENU = "http://192.168.75.81/api/select_id_menu.php";
    String URLIDPELANGGAN = "http://192.168.75.81/api/select_id_pelanggan.php";
    String URLPESANAN = "http://192.168.75.81/api/insert_pesanan.php";

    String[] jumlah = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] id_user = {"2", "6"};

    ArrayList<String> id_menu_list = new ArrayList<>();
    ArrayList<String> id_pelanggan_list = new ArrayList<>();
    ArrayAdapter<String> id_menu_adapter;
    ArrayAdapter<String> id_pelanggan_adapter;

    Button btn_simpan2;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entri_pesanan);

        requestQueue = Volley.newRequestQueue(this);

        id_pesanan = (EditText) findViewById(R.id.id_pesanan);

        btn_simpan2 = (Button) findViewById(R.id.btn_simpan_2);

        sp_id_menu = (Spinner) findViewById(R.id.sp_id_menu);
        sp_id_pelanggan = (Spinner) findViewById(R.id.sp_id_pelanggan);

        sp_jumlah = (Spinner) findViewById(R.id.sp_jumlah);
        sp_jumlah.setOnItemSelectedListener(this);

        sp_id_user = (Spinner) findViewById(R.id.sp_id_user);
        sp_id_user.setOnItemSelectedListener(this);

        TampilkanIdMenu();
        TampilkanIdPelanggan();

        btn_simpan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputData();
            }
        });

        ArrayAdapter jumlah_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, jumlah);
        jumlah_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_jumlah.setAdapter(jumlah_adapter);

        ArrayAdapter id_user_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, id_user);
        id_user_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_id_user.setAdapter(id_user_adapter);

    }

    public void TampilkanIdMenu() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLIDMENU, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("menu");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String t_id_menu = jsonObject.optString("Idmenu");
                        id_menu_list.add(t_id_menu);
                        id_menu_adapter = new ArrayAdapter<>(EntriPesanan.this, android.R.layout.simple_spinner_item, id_menu_list);
                        id_menu_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        sp_id_menu.setAdapter(id_menu_adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EntriPesanan.this, "Gagal Koneksi Ke Server", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void TampilkanIdPelanggan() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLIDPELANGGAN, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("pelanggan");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String t_id_pelanggan = jsonObject.optString("Idpelanggan");
                        id_pelanggan_list.add(t_id_pelanggan);
                        id_pelanggan_adapter = new ArrayAdapter<>(EntriPesanan.this, android.R.layout.simple_spinner_item, id_pelanggan_list);
                        id_pelanggan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        sp_id_pelanggan.setAdapter(id_pelanggan_adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EntriPesanan.this, "Gagal Koneksi Ke Server", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    void InputData() {
        String st_id_pesanan = id_pesanan.getText().toString();
        String st_id_menu = sp_id_menu.getSelectedItem().toString();
        String st_id_pelanggan = sp_id_pelanggan.getSelectedItem().toString();
        String st_jumlah = sp_jumlah.getSelectedItem().toString();
        String st_id_user = sp_id_user.getSelectedItem().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLPESANAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EntriPesanan.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EntriPesanan.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Idpesanan", st_id_pesanan);
                params.put("Idmenu", st_id_menu);
                params.put("Idpelanggan", st_id_pelanggan);
                params.put("Jumlah", st_jumlah);
                params.put("Iduser", st_id_user);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
