package br.com.viperfish.mpbmamaepagabarato.activity.anuncio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.anuncio.adapter.AdapterProdutoPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.activity.anuncio.formularios.CategoriaAnuncioActivity;
import br.com.viperfish.mpbmamaepagabarato.dao.anuncio.AnuncioDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;

public class ListaAnunciosActivity extends AppCompatActivity {

    //String[] produtos = {"Aptamil 1 Premuim", "Nan Pro 2", "Ninho Fase +1","Nestogeno 1 ", "Enfamil Premium ", "Ninho Fase +1","Aptamil 1 Premuim", "Nan Pro 3", "Ninho Fase +3","Aptamil 2 Premuim", "Nan Pro 2", "Ninho Fase +4"};
    private List<Anuncio> listaAnuncios;

    //Representa a lista de anuncios dos produtos.
    ListView listViewAnuncios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        //recupera componente ListView (Bynding)
        listViewAnuncios = (ListView) findViewById(R.id.lista_produtos);

        configurarAcaoOnClickLista();

        //carregarListaAnuncios(); // carrega a lista no OnResume. Boa pratica pois a Activity poderia estar Em Pause
        configurarBotaoFlutuanteNovo();
    }

    /**
     * Define a acao de onlick no item da lista de produtos
     */
    private void configurarAcaoOnClickLista() {
        listViewAnuncios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Anuncio anuncio = (Anuncio) lista.getItemAtPosition(posicao);
                //Toast.makeText(ListaAnunciosActivity.this, "Anuncio selecionado: " + produto.getTitulo(), Toast.LENGTH_LONG).show();
                navegarParaTelaMaisInformacoes(anuncio);
            }
        });
    }

    /**
     * @param anuncio
     */
    private void navegarParaTelaMaisInformacoes(Anuncio anuncio) {
        //Navegacao para uma nova activity MaisInformacoesProduto
        Intent irParaMaisInformacoesAnuncio = new Intent(ListaAnunciosActivity.this, MaisInformacoesProdutoActivity.class);
        //PENDURA NA INTENT O PRODUTO SELECIONADO PARA SER RECUPERADO PELA OUTRA ACTIVITY
        irParaMaisInformacoesAnuncio.putExtra("produto", anuncio);
        startActivity(irParaMaisInformacoesAnuncio);
    }

    private void carregarListaAnuncios() {
        criarMockAnuncios();
        popularListViewComAnuncios();
    }

    //TODO AVELINO REMOVER ESSE MOCK QUANDO A CONEXAO BD ESTIVER CONCLUIDO
    private void criarMockAnuncios() {
        listaAnuncios = new ArrayList<Anuncio>();

        Anuncio produto1 = new Anuncio(new Long(1), "Aptamil 1 Premuim", "Corram Mamães!!! Promoção BigBen Da Doca", new Long(1), new Double(23.3));
        listaAnuncios.add(produto1);

        Anuncio fralda = new Anuncio(new Long(2), "Pacote de Fraldas com 86 Unidades Confort sec M Pampers", "Achei no Walmart da Curuzu", new Long(1), new Double(23.3));
        listaAnuncios.add(fralda);

        Anuncio produto2 = new Anuncio(new Long(3), "Nan Pro 2", "Melhor Preço da Cidade djkasdlaskjiwuhjsdajskhdkjashdjsahjdkhsaj sjkahduaj jka jkash jkash jey hqwkjhq qwhkejh qwjk ehqkwjehqwkj ", new Long(1), new Double(23.3));
        listaAnuncios.add(produto2);

        Anuncio produto3 = new Anuncio(new Long(4), "Ninho Fase +1", "Lojas Americanas Patio Belem. Fraldas com desconto", new Long(1), new Double(23.3));
        listaAnuncios.add(produto3);

        Anuncio produto4 = new Anuncio(new Long(5), "Nestogeno 1", "Corram Mamães!!! Promoção BigBen Da Doca", new Long(1), new Double(23.3));
        listaAnuncios.add(produto4);

        Anuncio produto6 = new Anuncio(new Long(6), "Mamaderas Avent", "Promoção na Casa do Paulo ", new Long(1), new Double(23.3));
        listaAnuncios.add(produto6);

        listarAnuncios();
    }

    // TODO AVELINO SOMENTE PARA TESTES. VERIFICAR SE ESTA CARREGANDO DO BANCO DADOS
    private void listarAnuncios() {

        AnuncioDao anuncioDao = new AnuncioDao(ListaAnunciosActivity.this);
        List<Anuncio> produtos = anuncioDao.buscarTodos();

        for (Anuncio p : produtos) {
            Log.i("avelino: ", "Listando os anuncios" + String.valueOf(p.toString()));
        }
        //TODO AVELINO. REMOVER. ESTOU SO FORCANDO UMA ATUALIZACAO DA LISTA VIA BANCO DE DADOS
        listaAnuncios.addAll(produtos);
    }

    /**
     * Configura o botao Flutuante Novo (+)
     */
    private void configurarBotaoFlutuanteNovo() {

        Button botaoNovo = (Button) findViewById(R.id.botao_novo_produto);
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navegacao para uma nova activity Formulario
                //Intent irParaFormularioProduto = new Intent(ListaAnunciosActivity.this, FormularioProdutoActivity.class);
                //startActivity(irParaFormularioProduto);
                //Navegacao para uma nova activity Formulario
                Intent irParaFormularioCategoriaAnuncio = new Intent(ListaAnunciosActivity.this, CategoriaAnuncioActivity.class);
                startActivity(irParaFormularioCategoriaAnuncio);
            }
        });
    }

    /**
     * Popula o componente ListView com os Produtos
     */
    private void popularListViewComAnuncios() {

        //precisamos criar um adapter para colocar os dados no ListView
        AdapterProdutoPersonalizadoNaListView adapterAnuncios = new AdapterProdutoPersonalizadoNaListView(listaAnuncios, ListaAnunciosActivity.this);
        //setamos o adapter na ListView
        listViewAnuncios.setAdapter(adapterAnuncios);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Avelino", "ListaAnunciosActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "ListaAnunciosActivity OnResume");
        carregarListaAnuncios();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "ListaAnunciosActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "ListaAnunciosActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "ListaAnunciosActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "ListaAnunciosActivity OnDestroy");

        super.onDestroy();
    }
}
