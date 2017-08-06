package br.com.viperfish.mpbmamaepagabarato.activity.marca;

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
import br.com.viperfish.mpbmamaepagabarato.activity.adapter.AdapterMarcasPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.ListaProdutosActivity;
import br.com.viperfish.mpbmamaepagabarato.dao.marca.MarcaDao;
import br.com.viperfish.mpbmamaepagabarato.formularios.TituloAnuncioActivity;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;
import br.com.viperfish.mpbmamaepagabarato.modelo.marca.Marca;

public class ListaMarcaActivity extends AppCompatActivity {

    private List<Marca> marcas;
    private ListView listViewMarca;
    private Anuncio anuncio;
    public static final String EXTRA_DADOS_SUB_CATEGORIA_SELECIONADA = "EXTRA_DADOS_SUB_CATEGORIA_SELECIONADA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_marca);

        Intent intent = getIntent();
        anuncio = (Anuncio) intent.getSerializableExtra(EXTRA_DADOS_SUB_CATEGORIA_SELECIONADA);

        setTitle(anuncio.getSubCategoria().getNome());
        configurarBotaoVoltarParaTelaPrincipal();

        //recupera componente ListView (Bynding)
        listViewMarca = (ListView) findViewById(R.id.lista_marca);
        configurarAcaoOnClickLista();

        Log.i("Avelino", "ListaMarcaActivity OnCreate");
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

        listViewMarca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {

                Marca marca = (Marca) lista.getItemAtPosition(posicao);
                anuncio.setMarca(marca);

                Toast.makeText(ListaMarcaActivity.this, "Marca selecionado: " + marca.getNome(), Toast.LENGTH_LONG).show();

                // TODO AVELINO DEFINIR O PRODUTO ESCOLHIDO
                //dadosAnuncio.setSubCategoria(subCategoria);

                if (marca.isTipoOutros()) {

                    Intent irParaFormularioTituloAnuncio = new Intent(ListaMarcaActivity.this, TituloAnuncioActivity.class);
                    //irParaFormularioTituloAnuncio.putExtra(TituloAnuncioActivity.EXTRA_DADOS_SUBCATEGORIA_SELECIONADA, anuncio);
                    irParaFormularioTituloAnuncio.putExtra(TituloAnuncioActivity.EXTRA_DADOS_MARCA_SELECIONADA, anuncio);
                    startActivity(irParaFormularioTituloAnuncio);

                } else {
                    Intent irParaFormularioListaProdutos = new Intent(ListaMarcaActivity.this, ListaProdutosActivity.class);
                    //irParaFormularioListaProdutos.putExtra(ListaProdutosActivity.EXTRA_DADOS_SUBCATEGORIA_SELECIONADA, anuncio);
                    irParaFormularioListaProdutos.putExtra(ListaProdutosActivity.EXTRA_DADOS_MARCA_SELECIONADA, anuncio);
                    startActivity(irParaFormularioListaProdutos);
                }
            }
        });
    }

    private void carregarListaMarcas() {
        popularListViewComMarcas();
    }

    /**
     * Popula o componente ListView com os Produtos
     */
    private void popularListViewComMarcas() {

        MarcaDao marcaDao = MarcaDao.getInstance(ListaMarcaActivity.this);
        marcas = marcaDao.buscarMarcasDosProdutosPor(anuncio.getSubCategoria().getId());

        for (Marca p : marcas) {
            Log.i("avelino: ", "Listando os Marcas" + String.valueOf(p.toString()));
        }

        if (marcas != null && !marcas.isEmpty()) {
            //precisamos criar um adapter para colocar os dados no ListView
            AdapterMarcasPersonalizadoNaListView adapterCategorias = new AdapterMarcasPersonalizadoNaListView(marcas, ListaMarcaActivity.this);
            //setamos o adapter na ListView
            listViewMarca.setAdapter(adapterCategorias);
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
        Log.i("Avelino", "ListaMarcaActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "ListaMarcaActivity OnResume");
        carregarListaMarcas();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "ListaMarcaActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "ListaMarcaActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "ListaMarcaActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "ListaMarcaActivity OnDestroy");
        super.onDestroy();
    }
}
