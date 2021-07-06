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
    private ListAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WearableRecyclerView recyclerView = (WearableRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        setOnClickListener();
        WearableLinearLayoutManager layoutManager = new WearableLinearLayoutManager(this);
        layoutManager.setOrientation(WearableLinearLayoutManager.VERTICAL);
        layoutManager.setLayoutCallback(new CustomScrollingLayoutCallback());
        recyclerView.setLayoutManager(layoutManager);

        items.add(new ItemsList(R.drawable.flutter,"Flutter","Ofrecemos un curso de Flutter, que es un framework de código abierto desarrollado por Google para crear aplicaciones nativas de forma fácil, rápida y sencilla."));
        items.add(new ItemsList(R.drawable.programmer,"Desarrollo web fullStack","Toma cursos online gratis sobre desarrollo web full stack para dominar este tema. Únete hoy."));
        items.add(new ItemsList(R.drawable.javascript,"JavaScript","Aprende a fondo JavaScript, el lenguaje de programación más usado en el mundo detrás de los ancestrales C y Java"));
        setOnClickListener();
        ListAdapter listAdapter = new ListAdapter(items,listener);
        recyclerView.setAdapter(listAdapter);
    }

    private void setOnClickListener() {
        listener = new ListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int i) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("nombre", items.get(i).getNameItem());
                intent.putExtra("descripcion", items.get(i).getDescripcion());
                startActivity(intent);
            }
        };
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