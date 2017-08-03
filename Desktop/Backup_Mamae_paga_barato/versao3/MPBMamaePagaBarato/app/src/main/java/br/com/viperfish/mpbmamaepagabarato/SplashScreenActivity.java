package br.com.viperfish.mpbmamaepagabarato;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.viperfish.mpbmamaepagabarato.activity.anuncio.ListaAnunciosActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarTelaPrincipal();
            }
        }, 2000);
    }

    private void mostrarTelaPrincipal() {
        Intent intent = new Intent(SplashScreenActivity.this, ListaAnunciosActivity.class);
        startActivity(intent);
        finish();
    }
}
