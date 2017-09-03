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
import br.com.viperfish.mpbmamaepagabarato.activity.adapter.AdapterAnuncioPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.activity.categoria.CategoriaAnuncioActivity;
import br.com.viperfish.mpbmamaepagabarato.dao.anuncio.AnuncioDao;
import br.com.viperfish.mpbmamaepagabarato.dao.loja.LojaDao;
import br.com.viperfish.mpbmamaepagabarato.dao.marca.MarcaDao;
import br.com.viperfish.mpbmamaepagabarato.formularios.FotoAnuncioActivity;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.AnuncioDTO;
import br.com.viperfish.mpbmamaepagabarato.modelo.loja.Loja;
import br.com.viperfish.mpbmamaepagabarato.modelo.marca.Marca;

public class ListaAnunciosActivity extends AppCompatActivity {

    //String[] produtos = {"Aptamil 1 Premuim", "Nan Pro 2", "Ninho Fase +1","Nestogeno 1 ", "Enfamil Premium ", "Ninho Fase +1","Aptamil 1 Premuim", "Nan Pro 3", "Ninho Fase +3","Aptamil 2 Premuim", "Nan Pro 2", "Ninho Fase +4"};
    private List<AnuncioDTO> listaAnuncios;

    //Representa a lista de anuncios dos produtos.
    ListView listViewAnuncios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_anuncios);

        //recupera componente ListView (Bynding)
        listViewAnuncios = (ListView) findViewById(R.id.lista_anuncios);

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
                AnuncioDTO anuncio = (AnuncioDTO) lista.getItemAtPosition(posicao);
                //Toast.makeText(ListaAnunciosActivity.this, "Anuncio selecionado: " + produto.getTitulo(), Toast.LENGTH_LONG).show();
                navegarParaTelaMaisInformacoes(anuncio);
            }
        });
    }

    /**
     * @param anuncio
     */
    private void navegarParaTelaMaisInformacoes(AnuncioDTO anuncio) {
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
        listaAnuncios = new ArrayList<AnuncioDTO>();

        AnuncioDTO leite = new AnuncioDTO(new Long(1), new Long(1), "Aptamil 1 Premuim", "Corram Mamães!!! Promoção BigBen Da Doca", new Long(1), new Double(23.3));
        listaAnuncios.add(leite);

        AnuncioDTO fralda = new AnuncioDTO(new Long(2), new Long(1), "Pacote de Fraldas com 86 Unidades Confort sec M Pampers", "Achei no Walmart da Curuzu", new Long(1), new Double(23.3));
        listaAnuncios.add(fralda);

        AnuncioDTO leiteNan = new AnuncioDTO(new Long(3), new Long(1), "Nan Pro 2", "Melhor Preço da Cidade djkasdlaskjiwuhjsdajskhdkjashdjsahjdkhsaj sjkahduaj jka jkash jkash jey hqwkjhq qwhkejh qwjk ehqkwjehqwkj ", new Long(1), new Double(23.3));
        listaAnuncios.add(leiteNan);

        AnuncioDTO produto3 = new AnuncioDTO(new Long(4), new Long(1), "Ninho Fase +1", "Lojas Americanas Patio Belem. Fraldas com desconto", new Long(1), new Double(23.3));
        listaAnuncios.add(produto3);

        AnuncioDTO produto4 = new AnuncioDTO(new Long(5), new Long(1), "Nestogeno 1", "Corram Mamães!!! Promoção BigBen Da Doca", new Long(1), new Double(23.3));
        listaAnuncios.add(produto4);

        AnuncioDTO produto6 = new AnuncioDTO(new Long(6), new Long(1), "Mamaderas Avent", "Promoção na Casa do Paulo ", new Long(1), new Double(23.3));
        listaAnuncios.add(produto6);

        listarAnuncios();
    }

    // TODO AVELINO SOMENTE PARA TESTES. VERIFICAR SE ESTA CARREGANDO DO BANCO DADOS
    private void listarAnuncios() {

        AnuncioDao anuncioDao = AnuncioDao.getInstance(ListaAnunciosActivity.this);
        List<AnuncioDTO> anuncios = anuncioDao.buscarAnunciosDaView();
        /*
        for (AnuncioDTO a : anuncios) {
            Log.i("avelino: ", "Listando os anuncios" + String.valueOf(a.toString()));
        }
        */
        //TODO AVELINO. REMOVER. ESTOU SO FORCANDO UMA ATUALIZACAO DA LISTA VIA BANCO DE DADOS
        listaAnuncios.addAll(anuncios);

        MarcaDao marcaDao = MarcaDao.getInstance(ListaAnunciosActivity.this);
        List<Marca> marcas = marcaDao.buscarTodos();

        /*
        for (Marca p : marcas) {
            Log.i("avelino: ", "Listando os Marcas" + String.valueOf(p.toString()));
        }
        */

        LojaDao lojaDao = LojaDao.getInstance(this);
        List<Loja> lojas = lojaDao.buscarTodos();

        for (Loja p : lojas) {
            Log.i("avelino: ", "Listando os Lojas" + String.valueOf(p.toString()));
        }
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
                Intent irParaFormularioCategoria = new Intent(ListaAnunciosActivity.this, CategoriaAnuncioActivity.class);
                startActivity(irParaFormularioCategoria);

                //Intent irParaFormularioCategoria = new Intent(ListaAnunciosActivity.this, FotoAnuncioActivity.class);
                //startActivity(irParaFormularioCategoria);
            }
        });
    }

    /**
     * Popula o componente ListView com os Produtos
     */
    private void popularListViewComAnuncios() {

        //precisamos criar um adapter para colocar os dados no ListView
        AdapterAnuncioPersonalizadoNaListView adapterAnuncios = new AdapterAnuncioPersonalizadoNaListView(listaAnuncios, ListaAnunciosActivity.this);
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
