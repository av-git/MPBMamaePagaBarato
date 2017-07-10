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

public class CategoriaAnuncioActivity extends AppCompatActivity {

    private List<Categoria> listaCategorias;
    private ListView listViewCategoria;
    //private static final String EXTRA_CATEGORIA_SELECIONADA = "CATEGORIA.EXTRA_CATEGORIA_SELECIONADA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_anuncio);

        setTitle("Cadastrar Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "FormularioProdutoActivity OnCreate");

        //recupera componente ListView (Bynding)
        listViewCategoria = (ListView) findViewById(R.id.lista_categoria);
        configurarAcaoOnClickLista();
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
     * Define a acao de onlick no item da lista de produtos
     */
    private void configurarAcaoOnClickLista() {
        listViewCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Categoria categoria = (Categoria) lista.getItemAtPosition(posicao);
                Toast.makeText(CategoriaAnuncioActivity.this, "Produto selecionado: " + categoria.getNome(), Toast.LENGTH_LONG).show();

                Intent irParaFormularioSubCategoriaAnuncio = new Intent(CategoriaAnuncioActivity.this, SubCategoriaActivity.class);
                irParaFormularioSubCategoriaAnuncio.putExtra(SubCategoriaActivity.EXTRA_CATEGORIA_SELECIONADA, categoria);
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
