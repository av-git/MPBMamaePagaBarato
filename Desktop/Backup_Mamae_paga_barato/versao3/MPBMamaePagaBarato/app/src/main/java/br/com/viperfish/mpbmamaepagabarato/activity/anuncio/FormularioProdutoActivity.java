package br.com.viperfish.mpbmamaepagabarato.activity.anuncio;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.dao.anuncio.AnuncioDao;
import br.com.viperfish.mpbmamaepagabarato.activity.helper.FormularioProdutoHelper;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;

public class FormularioProdutoActivity extends AppCompatActivity {

    public static final String EXTRA_DADOS_PRODUTO = "EXTRA_DADOS_PRODUTO";
    private Button btnEscolherCategoria;
    private TextInputLayout tilCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_anuncio);
        setTitle("Inserir Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

       btnEscolherCategoria = (Button) findViewById(R.id.formulario_anuncio_btn_categoria);
       tilCategoria = (TextInputLayout) findViewById(R.id.formulario_anuncio_til_categoria);

       Log.i("Avelino", "FormularioAnuncioActivity OnCreate");
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
     * Personaliza Itens (Botoes) na actionBar do android com comandos
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario_produto, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Verifica qual item da actionBar foi selecionado
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.formulario_produto_item_salvar :

                FormularioAnuncioHelper helper = new FormularioAnuncioHelper(FormularioAnuncioActivity.this);

                if (helper.isCamposObritagoriosPreenchidos() ){
                    Anuncio anuncio = helper.obterProduto();

                    Log.i("Avelino", "Salvando o Anuncio: " +anuncio.toString());
                    salvar(anuncio);
                    finish(); // Finaliza a Activity e volta para a quem chamou
                }
                break;

            case R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(Anuncio anuncio) {

        AnuncioDao anuncioDao = AnuncioDao.getInstance(FormularioAnuncioActivity.this);

        //NOVO REGISTRO
        if (anuncio.getId() == null ) {
            inserir(anuncio);
        } else {
            //ALTERAR REGISTRO
            alterar(anuncio);
        }
    }

    private void alterar(Anuncio anuncio) {

        AnuncioDao anuncioDao = AnuncioDao.getInstance(FormularioAnuncioActivity.this);

        anuncioDao.atualizar(anuncio);
        if ( anuncioDao.atualizar(anuncio) ) {
            Toast.makeText(FormularioAnuncioActivity.this, "Alterado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(FormularioAnuncioActivity.this, "Ocorreu um erro ao salvar. Tente novamente", Toast.LENGTH_LONG).show();
        }
    }

    private void inserir(Anuncio produto) {

        AnuncioDao anuncioDao = AnuncioDao.getInstance(FormularioAnuncioActivity.this);
        anuncioDao.inserir(produto);

        if (anuncioDao.inserir(produto) ) {
            Toast.makeText(FormularioAnuncioActivity.this, "Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(FormularioAnuncioActivity.this, "Ocorreu um erro ao salvar. Tente novamente", Toast.LENGTH_LONG).show();
        }
    }

    public void irParaListagemDasCategorias(View v) {

        btnEscolherCategoria.setText("Higine --> Fralda");

        tilCategoria.getEditText().setText("Higine --> Fralda");
        tilCategoria.getEditText().setEnabled(false);


        Toast.makeText(FormularioAnuncioActivity.this, "troquei o nome do botao"+ " Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();

        Intent irParaFormularioResumoAnuncio = new Intent(FormularioAnuncioActivity.this, CategoriaAnuncioActivity.class);
        //irParaFormularioResumoAnuncio.putExtra(ResumoAnuncioActivity.EXTRA_DADOS_ANUNCIO, dadosAnuncio);
        startActivity(irParaFormularioResumoAnuncio);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Avelino", "FormularioProdutoActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "FormularioProdutoActivity OnResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "FormularioProdutoActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "FormularioProdutoActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "FormularioProdutoActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "FormularioProdutoActivity OnDestroy");
        super.onDestroy();
    }

   
}
