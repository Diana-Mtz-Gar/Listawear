package com.example.listawear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        Button boton = findViewById(R.id.salir);

        String nombre = "Nombre no traido";
        String descripcion = "Descripción no traida";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            nombre = extras.getString("nombre");
            descripcion =extras.getString("descripcion");
        }
        textView.setText(nombre);
        textView2.setText(descripcion);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
