package br.com.viperfish.mpbmamaepagabarato.formularios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.UUID;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.anuncio.ListaAnunciosActivity;
import br.com.viperfish.mpbmamaepagabarato.activity.bc.AnuncioBC;
import br.com.viperfish.mpbmamaepagabarato.activity.helper.FormularioResumoAnuncioHelper;
import br.com.viperfish.mpbmamaepagabarato.dao.anuncio.AnuncioDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;

public class ResumoAnuncioActivity extends AppCompatActivity {

    public static final String EXTRA_DADOS_ANUNCIO = "EXTRA_DADOS_ANUNCIO";

    private Anuncio dadosAnuncio;
    private AnuncioBC anuncioBC;
    private TextView campoComentario;

    private FormularioResumoAnuncioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_anuncio);

        setTitle("Resumo Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        Log.i("Avelino", "ResumoAnuncioActivity OnCreate");

        carregarDadosNoFormulario();
    }

    private void carregarDadosNoFormulario() {

        helper = new FormularioResumoAnuncioHelper(this);
        Intent intent = getIntent();
        dadosAnuncio = (Anuncio) intent.getSerializableExtra(EXTRA_DADOS_ANUNCIO);
        campoComentario = (TextView) findViewById(R.id.resumo_anuncio_descricao);

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

    private void exemplo3_criando_estruta_com_objeto_anuncio() {
        //ex3 - passando um objeto para firebase salvar
        Anuncio anuncio = new Anuncio();
        //anuncio.setId("001");
        anuncio.setDataAnuncio(new Date());
        //anuncio.setDescricao("promo");
        anuncio.setPreco(22.3);
        //anuncio.setProdutoId(new Long(2));
        //anuncio.setTitulo("Fralda Pamper");

        String uniqueID = UUID.randomUUID().toString();

        //anunciosReferencia.child(uniqueID).setValue(anuncio);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.formulario_produto_item_salvar:

                //FormularioResumoAnuncioHelper helper = new FormularioResumoAnuncioHelper(ResumoAnuncioActivity.this);
                dadosAnuncio.setComentario(campoComentario.getText().toString());
                anuncioBC = new AnuncioBC(this);

                if(anuncioBC.salvar(dadosAnuncio)) {

                    Toast.makeText(ResumoAnuncioActivity.this, "Anúncio registrado com sucesso. Obrigado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), ListaAnunciosActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                    finish(); // Finaliza a Activity e volta para a quem chamou
                } else {
                    Toast.makeText(ResumoAnuncioActivity.this, "Não foi possível publicar o anúncio. :(", Toast.LENGTH_LONG).show();
                }

                break;

            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Avelino", "ResumoAnuncioActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "ResumoAnuncioActivity OnResume");

        AnuncioDao anuncioDao = AnuncioDao.getInstance(this);
        anuncioDao.buscarAnunciosDaView();

        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "ResumoAnuncioActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "ResumoAnuncioActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "ResumoAnuncioActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "ResumoAnuncioActivity OnDestroy");
        super.onDestroy();
    }
}