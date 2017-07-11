package br.com.viperfish.mpbmamaepagabarato.activity.produto.formularios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

public class QualPrecoActivity extends AppCompatActivity {

    public static final String EXTRA_DADOS_ANUNCIO = "EXTRA_DADOS_ANUNCIO";
    private Produto dadosAnuncio;

    EditText precoAnuncioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qual_preco);

        setTitle("Inserir Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "QualPrecoActivity OnCreate");
        this.precoAnuncioEditText = (EditText) findViewById(R.id.formulario_qual_preco_anuncio);

        Intent intent = getIntent();
        dadosAnuncio = (Produto) intent.getSerializableExtra(EXTRA_DADOS_ANUNCIO);
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


    public void irParaFormularioEnderecoAnuncio(View v) {
        Editable text = this.precoAnuncioEditText.getText();

        Toast.makeText(QualPrecoActivity.this, text.toString()+ " Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();

        dadosAnuncio.setPreco(Double.valueOf(text.toString()));

        Intent irParaFormularioResumoAnuncio = new Intent(QualPrecoActivity.this, ResumoAnuncioActivity.class);
        irParaFormularioResumoAnuncio.putExtra(ResumoAnuncioActivity.EXTRA_DADOS_ANUNCIO, dadosAnuncio);
        startActivity(irParaFormularioResumoAnuncio);
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
}
