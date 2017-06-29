package br.com.viperfish.mpbmamaepagabarato.activity.produto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.adapter.AdapterProdutoPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.dao.categoria.CategoriaDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

public class ListaProdutosActivity extends AppCompatActivity {

    //String[] produtos = {"Aptamil 1 Premuim", "Nan Pro 2", "Ninho Fase +1","Nestogeno 1 ", "Enfamil Premium ", "Ninho Fase +1","Aptamil 1 Premuim", "Nan Pro 3", "Ninho Fase +3","Aptamil 2 Premuim", "Nan Pro 2", "Ninho Fase +4"};
    private List<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        criarMockComProdutos();

        popularListViewComProdutos();
        configurarBotaoFlutuanteNovo();
    }

    private void criarMockComProdutos() {
        listaProdutos = new ArrayList<Produto>();

        Produto produto1 = new Produto(new Long(1),"Aptamil 1 Premuim", "Corram Mamães!!! Promoção BigBen Da Doca", "Leite", "Novo", new Double(23.3));
        listaProdutos.add(produto1);

        Produto fralda = new Produto(new Long(2),"Pacote de Fraldas com 86 Unidades Confort sec M Pampers", "Achei no Walmart da Curuzu", "Fralda", "Novo", new Double(68.23));
        listaProdutos.add(fralda);

        Produto produto2 = new Produto(new Long(3),"Nan Pro 2", "Melhor Preço da Cidade djkasdlaskjiwuhjsdajskhdkjashdjsahjdkhsaj sjkahduaj jka jkash jkash jey hqwkjhq qwhkejh qwjk ehqkwjehqwkj ", "Leite", "Novo", new Double(68.23));
        listaProdutos.add(produto2);

        Produto produto3 = new Produto(new Long(4),"Ninho Fase +1", "Lojas Americanas Patio Belem. Fraldas com desconto", "Leite", "Novo", new Double(33.33));
        listaProdutos.add(produto3);

        Produto produto4 = new Produto(new Long(5),"Nestogeno 1", "Corram Mamães!!! Promoção BigBen Da Doca", "Leite", "Usado", new Double(11.68));
        listaProdutos.add(produto4);

        Produto produto6 = new Produto(new Long(6),"Mamaderas Avent", "Promoção na Casa do Paulo ", "Mamadeira", "Usado", new Double(11.68));
        listaProdutos.add(produto6);

        CategoriaDao categoriaDao = new CategoriaDao(ListaProdutosActivity.this);
        List<Categoria> c = categoriaDao.buscaCategorias();

        for (Categoria cat : c){
            //Log.i("av: ", cat.toString());
            Log.i("pena: ", String.valueOf(cat.getNome()));
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
                Intent irParaFormularioProduto = new Intent(ListaProdutosActivity.this, FormularioProdutoActivity.class);
                startActivity(irParaFormularioProduto);
            }
        });
    }

    /**
     * Popula o componente ListView com os Produtos
     */
    private void popularListViewComProdutos() {
        //recupera a componenet ListView
        ListView listViewProdutos = (ListView) findViewById(R.id.lista_produtos);
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
