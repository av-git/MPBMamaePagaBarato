package br.com.viperfish.mpbmamaepagabarato.activity.anuncio.formularios;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import br.com.viperfish.mpbmamaepagabarato.R;

import static java.security.AccessController.getContext;

public class FotoAnuncioActivity extends AppCompatActivity {

    private Button botaoFoto;
    private static final int CAPTURAR_IMAGEM = 1;
    private static final int GALERIA = 2;
    private Uri uri;
    private String caminhoFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_anuncio);

        setTitle("Foto do Anúncio");
        configurarBotaoVoltarParaTelaPrincipal();

        configurarBotaoFoto();

    }

    private void configurarBotaoFoto() {
        botaoFoto = (Button) findViewById(R.id.formulario_foto_botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FotoAnuncioActivity.this)

                        .setMessage("Onde esta sua foto ?")

                        .setPositiveButton("Galeria", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentAbrirGaleriaFotos = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intentAbrirGaleriaFotos, GALERIA);
                            }

                        }).setNegativeButton("Câmera", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                                File arquivoFoto = new File(caminhoFoto);
                                Uri fotoURI = FileProvider.getUriForFile(FotoAnuncioActivity.this,
                                        "br.com.viperfish.mpbmamaepagabarato.fileprovider",
                                        arquivoFoto);
                                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoURI);
                                if (intentCamera.resolveActivity(getPackageManager()) != null) {
                                    startActivityForResult(intentCamera, CAPTURAR_IMAGEM);
                                }
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void configurarBotaoVoltarParaTelaPrincipal() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void irParaFormularioCategoriaAnuncio(View v) {
        Intent irParaFormularioCategoriaAnuncio = new Intent(FotoAnuncioActivity.this, CategoriaAnuncioActivity.class);
        startActivity(irParaFormularioCategoriaAnuncio);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {

            if (requestCode == CAPTURAR_IMAGEM) {
                ImageView foto = (ImageView) findViewById(R.id.formulario_foto_anuncio_foto);
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmaReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                foto.setImageBitmap(bitmaReduzido);
                foto.setScaleType(ImageView.ScaleType.FIT_XY);

                mostrarMensagem("Imagem capturada!");
                adicionarNaGaleria();

            } else if (requestCode == GALERIA) {

                ImageView foto = (ImageView) findViewById(R.id.formulario_foto_anuncio_foto);

                Uri caminhoImagem = data.getData();

                try {
                    Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), caminhoImagem);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.PNG, 75, stream);
                    foto.setImageBitmap(imagem);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                mostrarMensagem("Imagem não capturada!");
            }
        }
    }

    private void adicionarNaGaleria() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }


    private void mostrarMensagem(String mensagem) {
        Toast.makeText(FotoAnuncioActivity.this, mensagem, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Avelino", "FotoAnuncioActivity OnStart");
    }

    @Override
    protected void onResume() {
        Log.i("Avelino", "FotoAnuncioActivity OnResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("Avelino", "FotoAnuncioActivity OnRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Avelino", "FotoAnuncioActivity OnPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Avelino", "FotoAnuncioActivity OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Avelino", "FotoAnuncioActivity OnDestroy");
        super.onDestroy();
    }
}
