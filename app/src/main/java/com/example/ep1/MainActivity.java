package com.example.ep1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View decorView;// 1) Ocultar las barras de estado y de navegación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decorView = getWindow().getDecorView();//2) Ocultar las barras de estado y de navegación
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override // 4) Ocultar las barras de estado y de navegación
            public void onSystemUiVisibilityChange(int visibility) {
              if (visibility == 0)
                  decorView.setSystemUiVisibility(hideSystemBars());
            }
        });


        ImageView imagenUsuario = findViewById(R.id.imgUsuario);
        SearchView buscarProductos = findViewById(R.id.searchProductos);
        LinearLayout beatsLayout = findViewById(R.id.layoutBeats);

        imagenUsuario.setOnClickListener(this);
        buscarProductos.setOnClickListener(this);
        beatsLayout.setOnClickListener(this);
    }

    @Override//3) Ocultar las barras de estado y de navegación
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }
        // 5) Ocultar las barras de estado y de navegación
        private int hideSystemBars(){
            return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    //| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //Esta oculta la barra de estado
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    //| View.SYSTEM_UI_FLAG_FULLSCREEN  //Esta oculta la barra de estado
                    | View. SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imgUsuario:
                startActivity(new Intent(this, cuenta_rl.class));
                break;
            case R.id.layoutBeats:
                startActivity(new Intent(this, ProductoActivity.class));
                break;
            case R.id.searchProductos:
                startActivity(new Intent(this, Producto2Activity.class));
                break;
        }
    }
}