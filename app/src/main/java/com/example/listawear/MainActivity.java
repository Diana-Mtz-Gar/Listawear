package com.example.listawear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private List<ItemsList> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WearableRecyclerView recyclerView = (WearableRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);

        WearableLinearLayoutManager layoutManager = new WearableLinearLayoutManager(this);
        layoutManager.setOrientation(WearableLinearLayoutManager.VERTICAL);
        layoutManager.setLayoutCallback(new CustomScrollingLayoutCallback());

        recyclerView.setLayoutManager(layoutManager);

        items.add(new ItemsList(R.drawable.flutter,"Flutter","Ofrecemos un curso de Flutter, que es un framework de código abierto desarrollado por Google para crear aplicaciones nativas de forma fácil, rápida y sencilla."));
        items.add(new ItemsList(R.drawable.programmer,"Desarrollo web fullStack","Toma cursos online gratis sobre desarrollo web full stack para dominar este tema. Únete hoy."));
        items.add(new ItemsList(R.drawable.javascript,"JavaScript","Aprende a fondo JavaScript, el lenguaje de programación más usado en el mundo detrás de los ancestrales C y Java"));
        items.add(new ItemsList(R.drawable.java, "Java", "Aprende Java en línea de un instructor experto. Idioma Español. 106 Horas De Vídeo. 17 Artículos. "));
        items.add(new ItemsList(R.drawable.c_pro, "C#", "Aprende Programación en C# en línea. Únete a estudiantes alrededor del mundo que ya están aprendiendo en Udemy."));
        items.add(new ItemsList(R.drawable.php, "PHP", "Aprende PHP en línea con un instructor muy calificado. Instructores Expertos. Compre En Línea."));

        ListAdapter listAdapter = new ListAdapter(items, new ListAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(View v, int itemPosition) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("titulo", items.get(itemPosition).getNameItem());
                intent.putExtra("descripcion", items.get(itemPosition).getDescriptionItem());
                intent.putExtra("icon", items.get(itemPosition).getImageItem());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(listAdapter);

    }
}

class CustomScrollingLayoutCallback extends WearableLinearLayoutManager.LayoutCallback {
    private static final float MAX_ICON_PROGRESS = 0.65f;

    private float progressToCenter;

    @Override
    public void onLayoutFinished(View child, RecyclerView parent) {
        float centerOffset = ((float) child.getHeight() / 2.0f) / (float) parent.getHeight();
        float yRelativeToCenterOffset = (child.getY() / parent.getHeight()) + centerOffset;
        progressToCenter = Math.abs(0.5f - yRelativeToCenterOffset);
        progressToCenter = Math.min(progressToCenter, MAX_ICON_PROGRESS);
        child.setScaleX(1 - progressToCenter);
        child.setScaleY(1 - progressToCenter);
    }
}