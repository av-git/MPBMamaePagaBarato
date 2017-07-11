package br.com.viperfish.mpbmamaepagabarato.activity.produto.formularios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
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
import br.com.viperfish.mpbmamaepagabarato.activity.produto.adapter.AdapterCategoriaPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.dao.categoria.CategoriaDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

public class CategoriaAnuncioActivity extends AppCompatActivity {

    private List<Categoria> listaCategorias;
    private ListView listViewCategoria;
    private Produto dadosAnuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_anuncio);

        setTitle("Cadastrar Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "CategoriaAnuncioActivity OnCreate");

        //recupera componente ListView (Bynding)
        listViewCategoria = (ListView) findViewById(R.id.lista_categoria);
        configurarAcaoOnClickLista();
    }


    private void configurarBotaoVoltarParaTelaPrincipal() {

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Define a acao de onlick no item da lista de produtos
     */
    private void configurarAcaoOnClickLista() {
        listViewCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {

                Categoria categoria = (Categoria) lista.getItemAtPosition(posicao);
                Toast.makeText(CategoriaAnuncioActivity.this, "categoria selecionado: " + categoria.getNome(), Toast.LENGTH_LONG).show();

                dadosAnuncio = new Produto();
                dadosAnuncio.setCategoria(categoria);

                Intent irParaFormularioSubCategoriaAnuncio = new Intent(CategoriaAnuncioActivity.this, SubCategoriaActivity.class);
                irParaFormularioSubCategoriaAnuncio.putExtra(SubCategoriaActivity.EXTRA_DADOS_ANUNCIO, dadosAnuncio);
                startActivity(irParaFormularioSubCategoriaAnuncio);

            }
        });
    }

    private void carregarListaCategorias() {
        popularListViewComCategorias();
    }

    /**
     * Popula o componente ListView com os Produtos
     */
    private void popularListViewComCategorias() {

        CategoriaDao categoriaDao = new CategoriaDao(CategoriaAnuncioActivity.this);

        listaCategorias = categoriaDao.buscarPorIdPai(new Long(1)); // obtem as categorias pai

        if (listaCategorias != null && !listaCategorias.isEmpty()) {
            //precisamos criar um adapter para colocar os dados no ListView
            AdapterCategoriaPersonalizadoNaListView adapterCategorias = new AdapterCategoriaPersonalizadoNaListView(listaCategorias, CategoriaAnuncioActivity.this);
            //setamos o adapter na ListView
            listViewCategoria.setAdapter(adapterCategorias);
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
        Log.i("Avelino", "CategoriaAnuncioActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "CategoriaAnuncioActivity OnResume");
        carregarListaCategorias();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "CategoriaAnuncioActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "CategoriaAnuncioActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "CategoriaAnuncioActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "CategoriaAnuncioActivity OnDestroy");
        super.onDestroy();
    }
}
