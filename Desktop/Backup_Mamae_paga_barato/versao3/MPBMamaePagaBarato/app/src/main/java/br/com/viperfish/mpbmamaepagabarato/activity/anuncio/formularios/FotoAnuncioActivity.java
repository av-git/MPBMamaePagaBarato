package br.com.viperfish.mpbmamaepagabarato.activity.anuncio.formularios;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.BuildConfig;
import br.com.viperfish.mpbmamaepagabarato.R;
import util.CarregadorDeFoto;

public class FotoAnuncioActivity extends AppCompatActivity {

    private Button botaoFoto;
    private static final int CAPTURAR_IMAGEM = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static final int GALERIA = 2;
    private Uri uri;
    private String caminhoDaFoto;


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

                                //VERIFICA SE O USUARIO DEU PERMISSAO PARA O USO DA CAMERA. ATENDE API > 22
                                if (ActivityCompat.checkSelfPermission(FotoAnuncioActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(FotoAnuncioActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
                                } else {
                                    dispararIntentCamera();
                                }
                                return;

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void dispararIntentCamera() {
        chamaIntentCamera();
    }

    /**
     * Atende API acima de 23. Contudo e compativel ate a versao 17. Menor que isso Nao funciona e devemos
     * implementar a outra solucao
     */
    private void chamaIntentCamera() {

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        caminhoDaFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
        File arquivoFoto = new File(caminhoDaFoto);

        Uri fotoURI = FileProvider.getUriForFile(FotoAnuncioActivity.this, BuildConfig.APPLICATION_ID + ".fileprovider", arquivoFoto);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoURI);
        //intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", arquivoFoto));

        if (intentCamera.resolveActivity(getPackageManager()) != null) {

            List<ResolveInfo> resolvedIntentActivities = getApplicationContext().getPackageManager().queryIntentActivities(intentCamera, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
                String packageName = resolvedIntentInfo.activityInfo.packageName;

                getApplicationContext().grantUriPermission(packageName, fotoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            startActivityForResult(intentCamera, REQUEST_IMAGE_CAPTURE);
        }

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

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            ImageView foto = (ImageView) findViewById(R.id.formulario_foto_anuncio_foto);

            //AV TODO ESSA FORMA ABAIXO FUNCIONA. COMENTADO PARA OUTROS TESTES
            //Bitmap bitmapOrientado = CarregadorDeFoto.rotateImage(bitmap, orientation);

            Bitmap bitmapFinal = null;
            try {
                 bitmapFinal = CarregadorDeFoto.handleSamplingAndRotationBitmap(FotoAnuncioActivity.this, Uri.fromFile(new File(caminhoDaFoto)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //AV TODO NOVIDADE - VERIFICAR SE NAO QUEBRA EM OUTRAS RESOLUCOES
            foto.setScaleType(ImageView.ScaleType.FIT_XY);

            foto.setImageBitmap(bitmapFinal);

            adicionarImagemNaGaleria();
        }
    }

    private void adicionarImagemNaGaleria() {
        //VERIFICA SE O SD ESTA MONTADO
        if (!Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(), "O CARTAO SD NAO ESTA MONTADO", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(caminhoDaFoto);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


    private void mostrarMensagem(String mensagem) {
        Toast.makeText(FotoAnuncioActivity.this, mensagem, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
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
