package br.com.viperfish.mpbmamaepagabarato.activity.anuncio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.adapter.AdapterAnuncioPersonalizadoNaListView;
import br.com.viperfish.mpbmamaepagabarato.activity.bc.AnuncioBC;
import br.com.viperfish.mpbmamaepagabarato.activity.categoria.CategoriaAnuncioActivity;
import br.com.viperfish.mpbmamaepagabarato.activity.login.LoginActivity;
import br.com.viperfish.mpbmamaepagabarato.config.ConexaoFirebase;
import br.com.viperfish.mpbmamaepagabarato.dao.anuncio.AnuncioDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.AnuncioDTO;

public class ListaAnunciosActivity extends AppCompatActivity {

    //String[] produtos = {"Aptamil 1 Premuim", "Nan Pro 2", "Ninho Fase +1","Nestogeno 1 ", "Enfamil Premium ", "Ninho Fase +1","Aptamil 1 Premuim", "Nan Pro 3", "Ninho Fase +3","Aptamil 2 Premuim", "Nan Pro 2", "Ninho Fase +4"};
    private List<AnuncioDTO> listaAnuncios;
    AnuncioBC anuncioBC;

    //Representa a lista de anuncios dos produtos.
    ListView listViewAnuncios;

    //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    //DatabaseReference databaseReference;
    DatabaseReference anunciosReferencia;

    private FirebaseAuth autenticacao;

    private static String TAG = "ListaAnunciosActivity"; // LogCat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_anuncios);

        //recupera componente ListView (Bynding)
        listViewAnuncios = (ListView) findViewById(R.id.lista_anuncios);

        configurarAcaoOnClickLista();

        //carregarListaAnuncios(); // carrega a lista no OnResume. Boa pratica pois a Activity poderia estar Em Pause
        configurarBotaoFlutuanteNovo();

        //databaseReference = ConexaoFirebase.getConexao();
        anunciosReferencia = ConexaoFirebase.getNoAnucio();

        configurarEventosAtualizacaoFireBase();

        /*
        anunciosReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i("Chamada no firebase", dataSnapshot.getValue().toString());
                anuncioBC = new AnuncioBC(ListaAnunciosActivity.this);
                anuncioBC.sincronizarDadosDaNuvemComBancoLocal(dataSnapshot.getValue(Anuncio.class), dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */
    }

    private void configurarEventosAtualizacaoFireBase() {

        anunciosReferencia.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    Log.i(TAG, dataSnapshot.getValue().toString());
                    anuncioBC = new AnuncioBC(ListaAnunciosActivity.this);
                    anuncioBC.sincronizarDadosDaNuvemComBancoLocal(dataSnapshot.getValue(Anuncio.class), dataSnapshot.getKey());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        /*
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
        */
        listarAnuncios();
    }

    // TODO AVELINO SOMENTE PARA TESTES. VERIFICAR SE ESTA CARREGANDO DO BANCO DADOS
    private void listarAnuncios() {

        AnuncioDao anuncioDao = AnuncioDao.getInstance(ListaAnunciosActivity.this);
        List<AnuncioDTO> anuncios = anuncioDao.buscarAnunciosDaView();
        //TODO AVELINO. REMOVER. ESTOU SO FORCANDO UMA ATUALIZACAO DA LISTA VIA BANCO DE DADOS
        listaAnuncios.addAll(anuncios);
    }

    /**
     * Personaliza Itens (Botoes) na actionBar do android com comandos
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_lista_anuncio, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Captura o item do OptionsMenu (Menu Bar)
     * selecionado
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lista_anuncio_filtro:

                //TODO AVELINO - PENDENTE IMPLEMENTAR FILTRO

                break;

            case R.id.lista_anuncio_sair:

                autenticacao = ConexaoFirebase.getFireBaseAutenticacao();
                autenticacao.signOut();

                Intent intent = new Intent(ListaAnunciosActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
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

    //ex5 - listando os registros do firebase
    //chamamos o metodo que fica ouvindo mudanças na base do firebase
    //aula: sessao 16 aula 153 Udemy
    private void listandoDadosFireBaseEmTempoReal() {
        String valor = "";


        anunciosReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DICA. PODEMOS FICAR OUVINDO UM NO RAIZ (anuncios) OU UM DOS SEUS NOS FILHOS (EX: anuncios --> 001)
                //Log.i("FIREBASE", dataSnapshot.getValue().toString());
                //Anuncio anuncio = dataSnapshot.getValue(Anuncio.class);
                //System.out.println(anuncio);
                //Log.i("FIREBASE 2", anuncio.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Toast.makeText(this, valor + "Alguem anuncio novidades", Toast.LENGTH_LONG).show();
    }

    public void carregarAnunciosFireBase() {

        anunciosReferencia.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Anuncio newPost = dataSnapshot.getValue(Anuncio.class);
                Log.i("avelino: ", "Produto: " + newPost.getProduto().getNome());
                Log.i("avelino: ", "Preco: " + newPost.getPreco());
                Log.i("avelino: ", "Previous Post ID: " + prevChildKey);

                //System.out.println("Title: " + newPost.getPreco());
                //System.out.println("Previous Post ID: " + prevChildKey);
               /*
                AnuncioDTO anuncioDTO = new AnuncioDTO(
                        new Long(1),
                        newPost.getComentario(),
                        new Long(1),
                        newPost.getPreco(),
                        newPost.getProduto().getNome(),

                        new Long(newPost.getProduto().getId()),
                        null,

                        new Long(newPost.getCategoria().getId()),
                        new Long(newPost.getCategoria().getIdPai()),
                        newPost.getCategoria().getNome(),

                        new Long(newPost.getSubCategoria().getId()),
                        new Long(newPost.getSubCategoria().getIdPai()),
                        newPost.getSubCategoria().getNome());
                */
                anuncioBC = new AnuncioBC(ListaAnunciosActivity.this);

                Anuncio anuncio = new Anuncio();
                anuncio.setLoja(newPost.getLoja());
                anuncio.setProduto(newPost.getProduto());
                anuncio.setComentario(newPost.getComentario());
                anuncio.setDataAnuncio(newPost.getDataAnuncio());
                anuncio.setPreco(newPost.getPreco());

                //listaAnuncios.add(anuncioDTO);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
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
