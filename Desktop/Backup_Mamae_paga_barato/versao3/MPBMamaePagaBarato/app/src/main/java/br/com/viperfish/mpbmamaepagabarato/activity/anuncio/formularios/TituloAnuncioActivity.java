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
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;

public class TituloAnuncioActivity extends AppCompatActivity {

    public static final String EXTRA_DADOS_ANUNCIO = "EXTRA_DADOS_ANUNCIO";

    Categoria categoriaSelecionada;

    EditText tituloAnuncioEditText;

    private Anuncio dadosAnuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulo_anuncio);

        setTitle("Inserir Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "TituloAnuncioActivity OnCreate");
        this.tituloAnuncioEditText = (EditText) findViewById(R.id.formulario_titulo_anuncio);

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
        // Set up action bar.
        final ActionBar actionBar = getSupportActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void salvar(Anuncio produto) {
        Toast.makeText(TituloAnuncioActivity.this, produto.getTitulo() +" Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
    }

    public void irParaFormularioDescricaoAnuncio(View v) {
        Editable text = this.tituloAnuncioEditText.getText();

        Toast.makeText(TituloAnuncioActivity.this, text.toString()+ " Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();

        dadosAnuncio.setTitulo(text.toString());

        Intent irParaFormularioDescricaoAnuncio = new Intent(TituloAnuncioActivity.this, DescricaoAnuncioActivity.class);
        irParaFormularioDescricaoAnuncio.putExtra(DescricaoAnuncioActivity.EXTRA_DADOS_ANUNCIO, dadosAnuncio);
        startActivity(irParaFormularioDescricaoAnuncio);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Avelino", "TituloAnuncioActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "TituloAnuncioActivity OnResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "TituloAnuncioActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "TituloAnuncioActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "TituloAnuncioActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "TituloAnuncioActivity OnDestroy");
        super.onDestroy();
    }
}
