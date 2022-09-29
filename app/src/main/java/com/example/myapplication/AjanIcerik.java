package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AjanIcerik extends AppCompatActivity {
    TextView txtad,txtaciklama;
    Button geridon;
    SQLiteHelper dbhelper;
    Context context=this;
    Ajan seciliAjan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajan);
        txtaciklama=findViewById(R.id.txtaciklama);
        txtad=findViewById(R.id.txtad);
        geridon=findViewById(R.id.geridon);
        dbhelper=new SQLiteHelper(this);
        Intent intent=getIntent();
        int id=intent.getIntExtra("ajan",-1);
        seciliAjan= dbhelper.ajanOku(id);
        txtad.setText(seciliAjan.getAd());
        txtaciklama.setText(seciliAjan.getAciklama());

        geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(context,AjanActivity.class);
                startActivity(intent1);
            }
        });



    }
}
