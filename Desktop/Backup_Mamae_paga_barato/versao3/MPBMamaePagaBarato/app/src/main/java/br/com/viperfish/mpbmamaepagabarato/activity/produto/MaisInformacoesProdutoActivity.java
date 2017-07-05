package br.com.viperfish.mpbmamaepagabarato.activity.produto;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.helper.FormularioProdutoHelper;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.helper.MaisInformacoesProdutoHelper;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

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
        Produto produto = getProduto();
        helper.preencheFormulario(produto);
    }

    /**
     * Recupera o produto selecionado da tela anterior atravez
     * da {@link Intent}
     * @return Produto
     */
    private Produto getProduto() {
        Intent intent = getIntent();
        return (Produto) intent.getSerializableExtra("produto");
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
