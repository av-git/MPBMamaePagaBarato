package br.com.viperfish.mpbmamaepagabarato.activity.produto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.adapter.AdapterProdutosPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.dao.produto.ProdutoDao;
import br.com.viperfish.mpbmamaepagabarato.formularios.QualPrecoActivity;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

public class ListaProdutosActivity extends AppCompatActivity {

    private List<Produto> produtos;
    private ListView listViewProdutos;
    public static final String EXTRA_DADOS_MARCA_SELECIONADA = "EXTRA_DADOS_MARCA_SELECIONADA";

    private Anuncio anuncio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        anuncio = (Anuncio) intent.getSerializableExtra(EXTRA_DADOS_MARCA_SELECIONADA);

        setContentView(R.layout.activity_lista_produtos);
        setTitle(anuncio.getMarca().getNome());

        configurarBotaoVoltarParaTelaPrincipal();

        //recupera componente ListView (Bynding)
        listViewProdutos = (ListView) findViewById(R.id.lista_produtos);
        configurarAcaoOnClickLista();

        Log.i("Avelino", "ListaProdutosActivity OnCreate");
    }

    private void configurarBotaoVoltarParaTelaPrincipal() {

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Define a acao de onlick no item da lista de produtos
     */
    private void configurarAcaoOnClickLista() {

        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {

                Produto produto = (Produto) lista.getItemAtPosition(posicao);
                anuncio.setProduto(produto);

                Toast.makeText(ListaProdutosActivity.this, "produto selecionado: " + produto.getNome(), Toast.LENGTH_LONG).show();

                Intent irParaFormularioQualPrecoProduto = new Intent(ListaProdutosActivity.this, QualPrecoActivity.class);
                irParaFormularioQualPrecoProduto.putExtra(QualPrecoActivity.EXTRA_DADOS_ANUNCIO, anuncio);
                startActivity(irParaFormularioQualPrecoProduto);

            }
        });
    }

    private void carregarListaProdutos() {
        popularListViewComProdutos();
    }

    /**
     * Popula o componente ListView com os Produtos
     */
    private void popularListViewComProdutos() {

        ProdutoDao produtoDao = ProdutoDao.getInstance(ListaProdutosActivity.this);

        produtos = produtoDao.buscarPorIdSubCategoria(anuncio.getSubCategoria().getId(), anuncio.getMarca().getId()); // obtem as categorias pai

        if (produtos != null && !produtos.isEmpty()) {
            //precisamos criar um adapter para colocar os dados no ListView
            AdapterProdutosPersonalizadoNaListView adapterProdutos = new AdapterProdutosPersonalizadoNaListView(produtos, ListaProdutosActivity.this);
            //setamos o adapter na ListView
            listViewProdutos.setAdapter(adapterProdutos);
        }
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
        Log.i("Avelino", "ListaProdutosActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "ListaProdutosActivity OnResume");
        carregarListaProdutos();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "ListaProdutosActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "ListaProdutosActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "ListaProdutosActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "ListaProdutosActivity OnDestroy");
        super.onDestroy();
    }
}
