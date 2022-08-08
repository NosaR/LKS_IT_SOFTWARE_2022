package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button btn_entri_menu, btn_entri_pesanan, btn_laporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_entri_menu = (Button) findViewById(R.id.btn_entri_menu);
        btn_entri_pesanan = (Button) findViewById(R.id.btn_entri_pesanan);
        btn_laporan = (Button) findViewById(R.id.btn_laporan);

        btn_entri_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, EntriMenu.class);
                startActivity(intent);
            }
        });

        btn_entri_pesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, EntriPesanan.class);
                startActivity(intent);
            }
        });

        btn_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, Laporan.class);
                startActivity(intent);
            }
        });
    }
}