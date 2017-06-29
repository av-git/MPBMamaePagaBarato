package br.com.viperfish.mpbmamaepagabarato.activity.produto;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.helper.FormularioProdutoHelper;
import br.com.viperfish.mpbmamaepagabarato.dao.produto.ProdutoDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

public class FormularioProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produto);
        setTitle("Inserir Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "FormularioProdutoActivity OnCreate");
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

    /**
     * Personaliza Itens (Botoes) na actionBar do android com comandos
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario_produto, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Verifica qual item da actionBar foi selecionado
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.formulario_produto_item_salvar :

                FormularioProdutoHelper helper = new FormularioProdutoHelper(FormularioProdutoActivity.this);

                if (helper.isCamposObritagoriosPreenchidos() ){
                    Produto produto = helper.obterProduto();

                    Log.i("Avelino", "Salvando o Produto: " +produto.toString());
                    salvar(produto);

                    Toast.makeText(FormularioProdutoActivity.this, "Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;

            case R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(Produto produto) {
        ProdutoDao produtoDao = new ProdutoDao(FormularioProdutoActivity.this);
        produtoDao.insere(produto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Avelino", "FormularioProdutoActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "FormularioProdutoActivity OnResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "FormularioProdutoActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "FormularioProdutoActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "FormularioProdutoActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "FormularioProdutoActivity OnDestroy");
        super.onDestroy();
    }
}
