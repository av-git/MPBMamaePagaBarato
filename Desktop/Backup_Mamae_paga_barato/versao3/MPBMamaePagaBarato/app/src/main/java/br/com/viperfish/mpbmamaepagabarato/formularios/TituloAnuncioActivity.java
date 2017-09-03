package br.com.viperfish.mpbmamaepagabarato.formularios;

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
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.marca.Marca;

public class TituloAnuncioActivity extends AppCompatActivity {

    public static final String EXTRA_DADOS_MARCA_SELECIONADA = "EXTRA_DADOS_MARCA_SELECIONADA";
    private EditText tituloAnuncioEditText;
    private Anuncio anuncio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulo_anuncio);

        setTitle("Informe o produto");
        configurarBotaoVoltarParaTelaPrincipal();

        this.tituloAnuncioEditText = (EditText) findViewById(R.id.formulario_titulo_anuncio);

        Intent intent = getIntent();
        anuncio = (Anuncio) intent.getSerializableExtra(EXTRA_DADOS_MARCA_SELECIONADA);

        Log.i("Avelino", "TituloAnuncioActivity OnCreate");
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
        Toast.makeText(TituloAnuncioActivity.this, produto.getNomeProdutoInformadorPeloUsuario() +" Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
    }

    public void irParaFormularioPrecoProduto(View v) {

        anuncio.setNomeProdutoInformadorPeloUsuario(tituloAnuncioEditText.getText().toString());
        //TODO AVELINO DEFINIR UNIDADE MEDIDA
        //anuncio.setTitulo(tituloAnuncioEditText.getText().toString());

        Intent irParaFormularioPrecoProduto = new Intent(TituloAnuncioActivity.this, QualPrecoActivity.class);
        irParaFormularioPrecoProduto.putExtra(QualPrecoActivity.EXTRA_DADOS_ANUNCIO, anuncio);
        //irParaFormularioPrecoProduto.putExtra(QualPrecoActivity.EXTRA_DADOS_ANUNCIO, "AV FALTA FAZER" );
        startActivity(irParaFormularioPrecoProduto);
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
