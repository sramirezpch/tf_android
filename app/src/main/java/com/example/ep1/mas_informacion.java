package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

public class mas_informacion extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_informacion);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RadioButton mrbHistoria = findViewById(R.id.rbHistoria);
        RadioButton mrbMision = findViewById(R.id.rbMision);
        RadioButton mrbQuienes = findViewById(R.id.rbQuienes);

        mrbHistoria.setOnClickListener(this);
        mrbQuienes.setOnClickListener(this);
        mrbMision.setOnClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbHistoria:
                mostrarHistoria();
                break;
            case R.id.rbMision:
                mostrarMision();
                break;
            case R.id.rbQuienes:
                mostrarQuienes();
                break;
        }

    }

    private void mostrarQuienes() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new QuienesFragment()).commit();
    }

    private void mostrarMision() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new MisionFragment()).commit();
    }

    private void mostrarHistoria() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new HistoriaFragment()).commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}