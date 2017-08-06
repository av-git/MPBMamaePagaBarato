package br.com.viperfish.mpbmamaepagabarato.activity.categoria;

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
import br.com.viperfish.mpbmamaepagabarato.activity.adapter.AdapterCategoriaPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.activity.marca.ListaMarcaActivity;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.ListaProdutosActivity;
import br.com.viperfish.mpbmamaepagabarato.dao.categoria.CategoriaDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;

public class SubCategoriaActivity extends AppCompatActivity {

    private List<Categoria> listaSubCategorias;
    private ListView listViewSubCategoria;
    private Anuncio anuncio;
    public static final String EXTRA_DADOS_CATEGORIA_SELECIONADA = "EXTRA_DADOS_CATEGORIA_SELECIONADA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categoria);

        Intent intent = getIntent();
        anuncio = (Anuncio) intent.getSerializableExtra(EXTRA_DADOS_CATEGORIA_SELECIONADA);

        setTitle(anuncio.getCategoria().getNome());
        configurarBotaoVoltarParaTelaPrincipal();

        //recupera componente ListView (Bynding)
        listViewSubCategoria = (ListView) findViewById(R.id.lista_sub_categoria);
        configurarAcaoOnClickLista();

        Log.i("Avelino", "SubCategoriaActivity OnCreate");
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

        listViewSubCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {

                Categoria subCategoria = (Categoria) lista.getItemAtPosition(posicao);
                anuncio.setSubCategoria(subCategoria);

                Toast.makeText(SubCategoriaActivity.this, "sub categoria selecionado: " + subCategoria.getNome(), Toast.LENGTH_LONG).show();

                Intent irParaListaMarcas = new Intent(SubCategoriaActivity.this, ListaMarcaActivity.class);
                irParaListaMarcas.putExtra(ListaMarcaActivity.EXTRA_DADOS_SUB_CATEGORIA_SELECIONADA, anuncio);
                startActivity(irParaListaMarcas);

            }
        });
    }

    private void carregarListaSubCategorias() {
        popularListViewComSubCategorias();
    }

    /**
     * Popula o componente ListView com os Produtos
     */
    private void popularListViewComSubCategorias() {

        CategoriaDao categoriaDao = CategoriaDao.getInstance(SubCategoriaActivity.this);
        listaSubCategorias = categoriaDao.buscarPorIdPai(anuncio.getCategoria().getId());

        if (listaSubCategorias != null && !listaSubCategorias.isEmpty()) {
            //precisamos criar um adapter para colocar os dados no ListView
            AdapterCategoriaPersonalizadoNaListView adapterCategorias = new AdapterCategoriaPersonalizadoNaListView(listaSubCategorias, SubCategoriaActivity.this);
            //setamos o adapter na ListView
            listViewSubCategoria.setAdapter(adapterCategorias);
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
        Log.i("Avelino", "SubCategoriaActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "SubCategoriaActivity OnResume");
        carregarListaSubCategorias();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "SubCategoriaActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "SubCategoriaActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "SubCategoriaActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "SubCategoriaActivity OnDestroy");
        super.onDestroy();
    }
}
