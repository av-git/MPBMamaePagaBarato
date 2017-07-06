package br.com.viperfish.mpbmamaepagabarato.activity.produto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.adapter.AdapterProdutoPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.dao.categoria.CategoriaDao;
import br.com.viperfish.mpbmamaepagabarato.dao.produto.ProdutoDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

public class ListaProdutosActivity extends AppCompatActivity {

    //String[] produtos = {"Aptamil 1 Premuim", "Nan Pro 2", "Ninho Fase +1","Nestogeno 1 ", "Enfamil Premium ", "Ninho Fase +1","Aptamil 1 Premuim", "Nan Pro 3", "Ninho Fase +3","Aptamil 2 Premuim", "Nan Pro 2", "Ninho Fase +4"};
    private List<Produto> listaProdutos;

    //Representa a lista de produtos.
    ListView listViewProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        //recupera componente ListView (Bynding)
        listViewProdutos = (ListView) findViewById(R.id.lista_produtos);

        configurarAcaoOnClickLista();

        //carregarListaProdutos(); // carrega a lista no OnResume Boa pratica pois a Activity poderia estar Em Pause
        configurarBotaoFlutuanteNovo();
    }

    /**
     * Define a acao de onlick no item da lista de produtos
     */
    private void configurarAcaoOnClickLista() {
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Produto produto = (Produto) lista.getItemAtPosition(posicao);
                //Toast.makeText(ListaProdutosActivity.this, "Produto selecionado: " + produto.getTitulo(), Toast.LENGTH_LONG).show();
                navegarParaTelaMaisInformacoes(produto);
            }
        });
    }

    /**
     *
     * @param produto
     */
    private void navegarParaTelaMaisInformacoes(Produto produto) {
        //Navegacao para uma nova activity MaisInformacoesProduto
        Intent irParaMaisInformacoesProduto = new Intent(ListaProdutosActivity.this, MaisInformacoesProdutoActivity.class);
        //PENDURA NA INTENT O PRODUTO SELECIONADO PARA SER RECUPERADO PELA OUTRA ACTIVITY
        irParaMaisInformacoesProduto.putExtra("produto", produto);
        startActivity(irParaMaisInformacoesProduto);
    }

    private void carregarListaProdutos() {
        criarMockComProdutos();
        popularListViewComProdutos();
    }

    //TODO AVELINO REMOVER ESSE MOCK QUANDO A CONEXAO BD ESTIVER CONCLUIDO
    private void criarMockComProdutos() {
        listaProdutos = new ArrayList<Produto>();

        Produto produto1 = new Produto(new Long(1),"Aptamil 1 Premuim", "Corram Mamães!!! Promoção BigBen Da Doca", new Long(1), new Long(1) , new Double(23.3));
        listaProdutos.add(produto1);

        Produto fralda = new Produto(new Long(2),"Pacote de Fraldas com 86 Unidades Confort sec M Pampers", "Achei no Walmart da Curuzu", new Long(1), new Long(1) , new Double(23.3));
        listaProdutos.add(fralda);

        Produto produto2 = new Produto(new Long(3),"Nan Pro 2", "Melhor Preço da Cidade djkasdlaskjiwuhjsdajskhdkjashdjsahjdkhsaj sjkahduaj jka jkash jkash jey hqwkjhq qwhkejh qwjk ehqkwjehqwkj ", new Long(1), new Long(1) , new Double(23.3));
        listaProdutos.add(produto2);

        Produto produto3 = new Produto(new Long(4),"Ninho Fase +1", "Lojas Americanas Patio Belem. Fraldas com desconto", new Long(1), new Long(1) , new Double(23.3));
        listaProdutos.add(produto3);

        Produto produto4 = new Produto(new Long(5),"Nestogeno 1", "Corram Mamães!!! Promoção BigBen Da Doca", new Long(1), new Long(1) , new Double(23.3));
        listaProdutos.add(produto4);

        Produto produto6 = new Produto(new Long(6),"Mamaderas Avent", "Promoção na Casa do Paulo ", new Long(1), new Long(1) , new Double(23.3));
        listaProdutos.add(produto6);

        listarCategorias();
        listarProdutos();
    }

    // TODO AVELINO SOMENTE PARA TESTES. VERIFICAR SE ESTA CARREGANDO DO BANCO DADOS
    private void listarCategorias() {
        /*
        CategoriaDao categoriaDao = new CategoriaDao(ListaProdutosActivity.this);
        List<Categoria> c = categoriaDao.buscaCategorias();

        for (Categoria cat : c) {
            Log.i("avelino: ", "Listando as categorias" + String.valueOf(cat.getNome()));
        }
        */
    }

    // TODO AVELINO SOMENTE PARA TESTES. VERIFICAR SE ESTA CARREGANDO DO BANCO DADOS
    private void listarProdutos() {

        ProdutoDao produtoDao = new ProdutoDao(ListaProdutosActivity.this);
        List<Produto> produtos = produtoDao.buscarTodos();

        for (Produto p : produtos){
           Log.i("avelino: ", "Listando os produtos"+ String.valueOf(p.toString()));
        }
        //TODO AVELINO. REMOVER. ESTOU SO FORCANDO UMA ATUALIZACAO DA LISTA VIA BANCO DE DADOS
        listaProdutos.addAll(produtos);
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
                Intent irParaFormularioProduto = new Intent(ListaProdutosActivity.this, FormularioProdutoActivity.class);
                startActivity(irParaFormularioProduto);
            }
        });
    }

    /**
     * Popula o componente ListView com os Produtos
     */
    private void popularListViewComProdutos() {

        //precisamos criar um adapter para colocar os dados no ListView
        AdapterProdutoPersonalizadoNaListView adapterProdutos = new AdapterProdutoPersonalizadoNaListView(listaProdutos, ListaProdutosActivity.this);
        //setamos o adapter na ListView
        listViewProdutos.setAdapter(adapterProdutos);
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
