package br.com.viperfish.mpbmamaepagabarato.activity.anuncio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.anuncio.helper.MaisInformacoesProdutoHelper;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;

public class MaisInformacoesProdutoActivity extends AppCompatActivity {

    private MaisInformacoesProdutoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_informacoes_produto);
        Log.i("Avelino", "MaisInformacoesProduto OnCreate");

        setTitle("Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        carregarDadosNoFormulario();
    }

    private void carregarDadosNoFormulario() {
        helper = new MaisInformacoesProdutoHelper(this);
        Anuncio produto = getProduto();
        helper.preencheFormulario(produto);
    }

    /**
     * Recupera o produto selecionado da tela anterior atravez
     * da {@link Intent}
     * @return Anuncio
     */
    private Anuncio getProduto() {
        Intent intent = getIntent();
        return (Anuncio) intent.getSerializableExtra("produto");
    }

    /**
     * adicionar um Up botão para uma atividade declarando o pai
     * da atividade no manifesto, e permitindo que o de barra de aplicativos Up botão.
     * Lembrar de ir no arquivo android Manisfeto e configurar para qual activity o botao
     * deve retornar
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
}
