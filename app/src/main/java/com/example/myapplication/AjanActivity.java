package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AjanActivity extends AppCompatActivity {
    Context context=this;
    ListView listemiz;
    List<Ajan> list;
    SQLiteHelper dbhelper=new SQLiteHelper(context);
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajan);
        listemiz=findViewById(R.id.listemiz);
        dbhelper.onUpgrade(dbhelper.getReadableDatabase(),1,2);
        dbhelper.AjanEkle(new Ajan("Chamber","Her daim şık ve tam teçhizat dolaşan Fransız silah tasarımcısı Chamber, ölümcül isabetliliğiyle rakipleri ortadan kaldırıyor. Özel yeteneklerini kullanarak görüş mesafesi sağlar, uzaktaki rakipleri avlar ve her plana mükemmel bir şekilde uyum sağlar."));
        dbhelper.AjanEkle(new Ajan("Jett","Anavatanı Güney Kore'yi temsil eden Jett'in çevik ve darbelerden kaçmaya dayalı dövüş tarzı, başkalarının alamadığı riskleri almasını sağlar. Her çatışmada üstünlüğünü belli eder ve rakipleri daha nerede olduğunu anlamadan onları kesip biçmeye başlar."));
        dbhelper.AjanEkle(new Ajan("Sage","Çinli ve kapı gibi Sage gittiği her yerde kendini ve takımını güvende tutar. Öldürülen silah arkadaşlarını hayata döndürme yeteneği ve agresif saldırıları bertaraf edebilmesiyle, cehennemvari savaş meydanında etrafına huzur verir."));
        dbhelper.AjanEkle(new Ajan("Brimstone","Aramıza ABD'den katılan Brimstone'un hava araçları sayesinde takımı her zaman 1-0 öndedir. Gereken her türlü işlevselliği titizlikle ve güvenli bir mesafeden sağlaması, muharebe alanında bu kumandanı eşsiz kılar."));
        dbhelper.AjanEkle(new Ajan("Reyna","Meksika'nın kalbinden kopup gelmiş Reyna teke tek çatışmalarda düşmanını ezer ve aldığı her skorla daha da coşar. Yapabilecekleri tamamen saf beceri gerektirir ve onu ciddi şekilde performansa dayalı kılar."));

        list= dbhelper.AjanlariListele();

        List<String> listBaslik = new ArrayList<>();
        for (int i=0;i<list.size();i++){
             listBaslik.add(i,list.get(i).getAd());
        }

        mAdapter=new ArrayAdapter<>(context,R.layout.satir,R.id.listMetin,listBaslik);
        listemiz.setAdapter(mAdapter);
        listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(context,AjanIcerik.class);
                intent.putExtra("ajan",list.get(i).getId());
                startActivity(intent);
            }
        });


    }
}