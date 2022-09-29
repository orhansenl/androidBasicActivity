package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin, btndegerlendir;
    Vibrator vibrator;
    RatingBar rtn;
    TextView txtdeger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btnlogin);
        btndegerlendir = findViewById(R.id.btndegerlendir);
        rtn = findViewById(R.id.rtn);
        txtdeger = findViewById(R.id.txtdeger);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("bildirim", "VALORANT", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Intent ajan=new Intent(MainActivity.this,AjanActivity.class);
                    startActivity(ajan);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "bildirim");
                    builder.setContentTitle("Valorant Ajan Rehberi");
                    builder.setContentText("Giriş Yaptınız");
                    builder.setSmallIcon(R.drawable.icon);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                    managerCompat.notify(1, builder.build());

                    Toast.makeText(MainActivity.this, "Giriş Başarılı", Toast.LENGTH_LONG).show();
                    vibrator.vibrate(1000);



                } else {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "bildirim");
                    builder.setContentTitle("Valorant Ajan Rehberi");
                    builder.setContentText("Kullanıcı Adınız veya Şifreniz Yanlış");
                    builder.setSmallIcon(R.drawable.icon);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                    managerCompat.notify(1, builder.build());
                    Toast.makeText(MainActivity.this, "Giriş Başarısız Tekrar Deneyin", Toast.LENGTH_LONG).show();
                    vibrator.vibrate(500);
                    password.setText("");
                    username.setText("");

                }
            }
        });

        btndegerlendir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, String.valueOf(rtn.getRating()), Toast.LENGTH_LONG).show();
                txtdeger.setText("Değerlendirmeniz: " + String.valueOf(rtn.getRating()));
            }
        });

    }
}