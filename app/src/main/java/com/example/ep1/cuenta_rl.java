package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

public class cuenta_rl extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_rl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RadioButton mrbEditar = findViewById(R.id.rbEditar);
        ImageView imgIrSeguimiento = findViewById(R.id.imgIrSeguimiento);
        ImageView imgIrTarjetas = findViewById(R.id.imgIrTarjetas);

        mrbEditar.setOnClickListener(this);//para ir de cuenta_rl a PerfilFragment
        imgIrSeguimiento.setOnClickListener(this);//Para ir de cuenta_rl a Tracking_rl
        imgIrTarjetas.setOnClickListener(this);//Para ir de cuenta_rl a TarjetaActivity2
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.rbEditar:
                mostrarEditarPerfil();
            case R.id.imgIrSeguimiento:
                startActivity(new Intent(this, Tracking_rl.class));
                break;
            case R.id.imgIrTarjetas:
                startActivity(new Intent(this, TarjetaActivity2.class));
                break;
        }
    }

    private void mostrarEditarPerfil() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorAcciones, new PerfilFragment()).commit();
    }
}