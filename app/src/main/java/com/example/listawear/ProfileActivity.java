package com.example.listawear;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ProfileActivity extends Activity {
    private TextView titulo;
    private TextView descripcion;
    private Button cerrar;
    private Button inscribir;
    NotificationCompat.Builder notificacion;
    private static final int idUnico = 454542;
    private Intent intent;
    String CHANNEL_ID;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);

        titulo = (TextView) findViewById(R.id.txtTitulo);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);
        cerrar = (Button) findViewById(R.id.btnCerrar);
        inscribir = (Button) findViewById(R.id.btnInscripcion);
        CHANNEL_ID = getApplicationContext().getString(R.string.app_name);

        notificacion = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        intent = new Intent(this, MainActivity.class);


        Bundle extras = getIntent().getExtras();
        if( extras != null){
            titulo.setText(extras.getString("titulo"));
            descripcion.setText(extras.getString("descripcion"));
        }

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    String name = "Notificación";
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name , importance);
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                    pendingIntent = PendingIntent.getActivity(ProfileActivity.this,
                                    0,
                                    intent,
                                    PendingIntent.FLAG_UPDATE_CURRENT);

                    notificacion.setSmallIcon(extras.getInt("icon"));
                    notificacion.setTicker("Nueva notificación");
                    notificacion.setWhen(System.currentTimeMillis());
                    notificacion.setContentTitle("Inscripción");
                    notificacion.setContentText("se ha enrolado correctamente al curso "+extras.getString("titulo"));
                    notificacion.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    notificacion.setAutoCancel(true);
                    notificacion.setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    notificacion.setContentIntent(pendingIntent);

                    notificationManager.notify(idUnico, notificacion.build());
                }
            }
        });
    }
}
