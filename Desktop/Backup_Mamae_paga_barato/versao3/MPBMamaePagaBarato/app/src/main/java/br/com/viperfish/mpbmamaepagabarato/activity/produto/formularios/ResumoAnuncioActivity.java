package br.com.viperfish.mpbmamaepagabarato.activity.produto.formularios;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.ListaProdutosActivity;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.helper.FormularioResumoAnuncioHelper;
import br.com.viperfish.mpbmamaepagabarato.dao.produto.ProdutoDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

public class ResumoAnuncioActivity extends AppCompatActivity {

    public static final String EXTRA_DADOS_ANUNCIO = "EXTRA_DADOS_ANUNCIO";
    private Produto dadosAnuncio;

    private FormularioResumoAnuncioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_anuncio);

        setTitle("Publicar Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "ResumoAnuncioActivity OnCreate");

        carregarDadosNoFormulario();
    }

    private void carregarDadosNoFormulario() {

        helper = new FormularioResumoAnuncioHelper(this);
        Intent intent = getIntent();
        dadosAnuncio = (Produto) intent.getSerializableExtra(EXTRA_DADOS_ANUNCIO);

        helper.preencheFormulario(dadosAnuncio);
    }

    private void configurarBotaoVoltarParaTelaPrincipal() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
        menuInflater.inflate(R.menu.menu_formulario_produto, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void publicarAnuncio(View v) {

        Toast.makeText(ResumoAnuncioActivity.this, dadosAnuncio + " Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.formulario_produto_item_salvar:

                FormularioResumoAnuncioHelper helper = new FormularioResumoAnuncioHelper(ResumoAnuncioActivity.this);

                Log.i("Avelino", "Salvando o Produto: " + dadosAnuncio.toString());
                salvar(dadosAnuncio);

                Intent intent = new Intent(getApplicationContext(), ListaProdutosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);

                finish(); // Finaliza a Activity e volta para a quem chamou

                break;

            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(Produto produto) {

        ProdutoDao produtoDao = new ProdutoDao(ResumoAnuncioActivity.this);

        //NOVO REGISTRO
        if (produto.getId() == null) {
            inserir(produto, produtoDao);
        } else {
            //ALTERAR REGISTRO
            alterar(produto, produtoDao);
        }
    }

    private void alterar(Produto produto, ProdutoDao produtoDao) {
        long resultado = produtoDao.atualizar(produto);
        if (resultado > 0) {
            Toast.makeText(ResumoAnuncioActivity.this, "Alterado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ResumoAnuncioActivity.this, "Ocorreu um erro ao salvar. Tente novamente", Toast.LENGTH_LONG).show();
        }
    }

    private void inserir(Produto produto, ProdutoDao produtoDao) {
        long resultado = produtoDao.inserir(produto);

        if (resultado != -1) {
            Toast.makeText(ResumoAnuncioActivity.this, "Registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ResumoAnuncioActivity.this, "Ocorreu um erro ao salvar. Tente novamente", Toast.LENGTH_LONG).show();
        }
    }

}
