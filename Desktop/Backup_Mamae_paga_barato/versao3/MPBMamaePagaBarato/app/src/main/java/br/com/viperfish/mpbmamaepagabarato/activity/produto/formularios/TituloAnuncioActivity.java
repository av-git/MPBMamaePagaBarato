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
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

public class TituloAnuncioActivity extends AppCompatActivity {

    public static final String EXTRA_SUB_CATEGORIA_SELECIONADA = "EXTRA_SUB_CATEGORIA_SELECIONADA";

    Categoria categoriaSelecionada;

    EditText tituloAnuncioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulo_anuncio);

        setTitle("Inserir Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "TituloAnuncioActivity OnCreate");
        this.tituloAnuncioEditText = (EditText) findViewById(R.id.formulario_titulo_anuncio);
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

    private void salvar(Produto produto) {
        Toast.makeText(TituloAnuncioActivity.this, produto.getTitulo() +" Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
    }

    public void irParaFormularioDescricaoAnuncio(View v) {
        Editable text = this.tituloAnuncioEditText.getText();

        Toast.makeText(TituloAnuncioActivity.this, text.toString()+ " Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();

        Intent irParaFormularioDescricaoAnuncio = new Intent(TituloAnuncioActivity.this, DescricaoAnuncioActivity.class);
        startActivity(irParaFormularioDescricaoAnuncio);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
