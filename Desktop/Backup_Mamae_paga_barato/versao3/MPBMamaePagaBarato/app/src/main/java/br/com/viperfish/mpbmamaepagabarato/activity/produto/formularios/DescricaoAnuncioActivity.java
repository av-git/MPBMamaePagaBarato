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

public class DescricaoAnuncioActivity extends AppCompatActivity {

    EditText descricaoAnuncioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_anuncio);

        setTitle("Inserir Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "DescricaoAnuncioActivity OnCreate");
        this.descricaoAnuncioEditText = (EditText) findViewById(R.id.formulario_descricao_anuncio);
    }

    /**
     * adicionar um Up botão para uma atividade declarando o pai
     * da atividade no manifesto, e permitindo que o de barra de aplicativos Up botão
     *
     * disponivel em: https://developer.android.com/training/appbar/up-action.html
     */
    private void configurarBotaoVoltarParaTelaPrincipal() {

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
    }


    public void irParaFormularioPrecoAnuncio(View v) {
        Editable text = this.descricaoAnuncioEditText.getText();

        Toast.makeText(DescricaoAnuncioActivity.this, text.toString()+ " Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();

        Intent irParaFormularioQualPrecoAnuncio = new Intent(DescricaoAnuncioActivity.this, QualPrecoActivity.class);
        //irParaFormularioSubCategoriaAnuncio.putExtra(SubCategoriaActivity.EXTRA_CATEGORIA_SELECIONADA, categoria);
        startActivity(irParaFormularioQualPrecoAnuncio);
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
