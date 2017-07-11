package br.com.viperfish.mpbmamaepagabarato.activity.anuncio.formularios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;

public class DescricaoAnuncioActivity extends AppCompatActivity {

    public static final String EXTRA_DADOS_ANUNCIO = "EXTRA_DADOS_ANUNCIO";
    private Anuncio dadosAnuncio;

    EditText descricaoAnuncioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_anuncio);

        setTitle("Inserir Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "DescricaoAnuncioActivity OnCreate");
        this.descricaoAnuncioEditText = (EditText) findViewById(R.id.formulario_descricao_anuncio);

        Intent intent = getIntent();
        dadosAnuncio = (Anuncio) intent.getSerializableExtra(EXTRA_DADOS_ANUNCIO);
    }

    /**
     * adicionar um Up botão para uma atividade declarando o pai
     * da atividade no manifesto, e permitindo que o de barra de aplicativos Up botão
     *
     * disponivel em: https://developer.android.com/training/appbar/up-action.html
     */
    private void configurarBotaoVoltarParaTelaPrincipal() {

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    public void irParaFormularioPrecoAnuncio(View v) {
        Editable text = this.descricaoAnuncioEditText.getText();

        Toast.makeText(DescricaoAnuncioActivity.this, text.toString()+ " Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();

        dadosAnuncio.setDescricao(text.toString());

        Intent irParaFormularioQualPrecoAnuncio = new Intent(DescricaoAnuncioActivity.this, QualPrecoActivity.class);
        irParaFormularioQualPrecoAnuncio.putExtra(QualPrecoActivity.EXTRA_DADOS_ANUNCIO, dadosAnuncio);
        startActivity(irParaFormularioQualPrecoAnuncio);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Avelino", "DescricaoAnuncioActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "DescricaoAnuncioActivity OnResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "DescricaoAnuncioActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "DescricaoAnuncioActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "DescricaoAnuncioActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "DescricaoAnuncioActivity OnDestroy");
        super.onDestroy();
    }
}
