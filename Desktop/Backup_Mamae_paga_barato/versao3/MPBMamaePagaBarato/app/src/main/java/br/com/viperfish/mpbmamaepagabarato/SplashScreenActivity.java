package br.com.viperfish.mpbmamaepagabarato;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.viperfish.mpbmamaepagabarato.activity.anuncio.ListaAnunciosActivity;
import br.com.viperfish.mpbmamaepagabarato.activity.login.LoginActivity;
import br.com.viperfish.mpbmamaepagabarato.config.ConexaoFirebase;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                definirNavegacao();
            }
        }, 1000);
    }

    private void definirNavegacao() {

        if(ConexaoFirebase.isUsuarioLogado()) {
            irParaTelaPrincipal();
        } else {
            irParaTelaLogin();
        }
    }

    private void irParaTelaLogin() {
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void irParaTelaPrincipal() {
        Intent intent = new Intent(SplashScreenActivity.this, ListaAnunciosActivity.class);
        startActivity(intent);
        finish();
    }
}
