package br.com.viperfish.mpbmamaepagabarato.formularios;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Loja;
import util.Validador;

public class EnderecoAnuncioActivity extends AppCompatActivity {

    public static final String EXTRA_DADOS_ANUNCIO = "EXTRA_DADOS_ANUNCIO";
    private Anuncio anuncio;
    private final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private TextView labelNomeLojaSelecionada;
    private TextView labelEnderecoLojaSelecionada;
    private TextView enderecoLojaSelecionada;
    private TextView nomeLojaSelecionada;
    private Loja loja;
    private PlaceAutocompleteFragment autocompleteFragment;

    private Resources recursos;

    /* os atributos abaixos sao para solucoes diferentes */
    private ListView listViewResultado;
    private EditText nomeEstabelecimentoEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_anuncio);

        setTitle("Dados Anúncio");

        configurarBotaoVoltarParaTelaPrincipal();
        Log.i("Avelino", "EnderecoAnuncioActivity OnCreate");

        Intent intent = getIntent();
        anuncio = (Anuncio) intent.getSerializableExtra(EXTRA_DADOS_ANUNCIO);

        isPossuiConexaoComInternet();

        preparaAutoCompleteFragmentEnderecos();
        configurarTextViewDadosLoja();
    }

    /**
     * Exibe os dados da loja selecionada pelo usuario
     */
    private void configurarTextViewDadosLoja() {
        labelEnderecoLojaSelecionada = (TextView) findViewById(R.id.label_loja_endereco);
        enderecoLojaSelecionada = (TextView) findViewById(R.id.loja_endereco);

        labelNomeLojaSelecionada = (TextView) findViewById(R.id.label_loja_nome);
        nomeLojaSelecionada = (TextView) findViewById(R.id.loja_nome);
    }

    /* Função para verificar existência de conexão com a internet
    */
    //TODO AVELINO - Colocar numa classe superior esse recurso
    public  boolean isPossuiConexaoComInternet() {

        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;

            //Log.i("Avelino com Internet", "SIM");
            //Toast.makeText(this, ":) Voce esta com Internet", Toast.LENGTH_SHORT).show();

        } else {
            //Log.i("Avelino com Internet", "NAO");
            Toast.makeText(this, "Você esta sem conexão com Internet no momento." , Toast.LENGTH_LONG).show();
            conectado = false;
        }

        return conectado;
    }

    /**
     * Metodo que inicializa o fragmento auto complete da api do Places para
     * pesquisa o endereco. somente um campo input
     */
    private void preparaAutoCompleteFragmentEnderecos() {

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter tipoFiltroBR = new AutocompleteFilter.Builder()
                .setCountry("BR")
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT)
                .build();
        autocompleteFragment.setFilter(tipoFiltroBR);

        LatLng sw = new LatLng(-1.455779, -48.490197);
        LatLng ne = new LatLng(-1.36457893, -48.36662292);
        LatLngBounds boundsBias = new LatLngBounds(sw, ne);
        autocompleteFragment.setBoundsBias(boundsBias);

        autocompleteFragment.setHint("Ex: ExtraFarma Pres Vargas");

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                configurarLojaSelecionada(place);
            }

            @Override
            public void onError(Status status) {
                Log.i("AV", "Ocorreu um erro: " + status);
            }
        });
    }

    private void configurarLojaSelecionada(Place place) {
        if (place != null && place.getId() != null) {
            loja = new Loja(place);

            labelNomeLojaSelecionada.setText("Loja: ");
            nomeLojaSelecionada.setText(loja.getNome());

            labelEnderecoLojaSelecionada.setText("Endereço: ");
            enderecoLojaSelecionada.setText(loja.getEndereco());

        }
    }

    /**
     * adicionar um Up botão para uma atividade declarando o pai
     * da atividade no manifesto, e permitindo que o de barra de aplicativos Up botão
     *
     * disponivel em: https://developer.android.com/training/appbar/up-action.html
     */
    private void configurarBotaoVoltarParaTelaPrincipal() {

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    public void irParaProximoFormulario(View v) {

        if (isCamposObritagoriosPreenchidos()) {
            Intent irParaFormularioResumoAnuncio = new Intent(EnderecoAnuncioActivity.this, ResumoAnuncioActivity.class);
            anuncio.setLoja(loja);
            irParaFormularioResumoAnuncio.putExtra(ResumoAnuncioActivity.EXTRA_DADOS_ANUNCIO, anuncio);
            startActivity(irParaFormularioResumoAnuncio);
        } else {
            Toast.makeText(this, "Campo Obrigatório. Você deve informar o nome da Loja" , Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isCamposObritagoriosPreenchidos(){
        //TODO AVELINO REMOVER MOCK
        return true;//local != null && local.getId() != null ? true : false;
    }

    /**
     * Solucao para buscar os endereços via google. Não muito preciso para obter Lojas
     * @param v
     */
    @Deprecated
    public void pesquisarComGeoCoder(View v) {
        isPossuiConexaoComInternet();
        Editable nome = this.nomeEstabelecimentoEditText.getText();

        try {
            Locale.setDefault(new Locale("pt", "BR"));
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            Log.i("avelino: ", "Listando enderecos" + nome.toString());
            List<Address> result = geocoder.getFromLocationName(nome.toString() + " BELÉM PARÁ", 5);

            List<String> estabelecimento = new ArrayList<>();

            for (Address p : result) {
                Log.i("avelino: ", "Listando enderecos" + String.valueOf(p.toString()));
                Log.i("avelino: ", "Listando enderecos" + String.valueOf(p.toString()));
                Log.i("avelino: ", "Nome " + String.valueOf(p.getAddressLine(0)));
                Log.i("avelino: ", "End " + String.valueOf(p.getAddressLine(1)));
                Log.i("avelino: ", "Cep " + String.valueOf(p.getAddressLine(3)));
                estabelecimento.add(p.getAddressLine(0) + p.getAddressLine(1));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, estabelecimento);

            // Assign adapter to ListView
            listViewResultado.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chama recurso de localizar Loja ou endereco via Maps
     * @param v
     */
    public void irParaFormularioBuscarLoja(View v) {
        isPossuiConexaoComInternet();
        startPlacePickerActivity();
    }

    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data) {

        Place placeSelected = PlacePicker.getPlace (data, this);
        //Place placeSelected = PlacePicker.getPlace (this, data);

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();
        String telefone = placeSelected.getPhoneNumber().toString();

        Toast.makeText(this, name +"  -  " +address  + " - " +telefone  , Toast.LENGTH_LONG).show();

        //TextView enterCurrentLocation = (TextView) findViewById(R.id.show_selected_location);
        //enterCurrentLocation.setText(name + ", " + address);
    }

    /**
     * Obtem o retorno da activity do PlacePicker Maps do google
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
        if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            displaySelectedPlaceFromPlacePicker(data);
        }
        */
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("avelino", "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Solucionar o erro.
                Log.i("avelino", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
                Log.i("avelino", "Voce cancelou");
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Avelino", "EnderecoAnuncioActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "EnderecoAnuncioActivity OnResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "EnderecoAnuncioActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "EnderecoAnuncioActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "EnderecoAnuncioActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "QualPrecoActivity OnDestroy");
        super.onDestroy();
    }
}
