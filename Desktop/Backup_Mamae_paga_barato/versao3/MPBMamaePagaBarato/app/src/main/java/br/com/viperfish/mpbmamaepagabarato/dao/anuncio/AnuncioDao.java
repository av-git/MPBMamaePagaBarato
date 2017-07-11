package br.com.viperfish.mpbmamaepagabarato.dao.anuncio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.dao.DatabaseHelper;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;

/**
 * Created by Av on 16/11/2016.
 */

public class AnuncioDao {

    private DatabaseHelper databaseHelper;
    //private SQLiteDatabase db;

    public AnuncioDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void close(){
        databaseHelper.close();
    }

    private Anuncio criarAnuncio(Cursor cursor) {

        Anuncio anuncio = new Anuncio(

                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Anuncio._ID)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Anuncio.TITULO)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Anuncio.DESCRICAO)),

                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Anuncio.PRODUTO_ID)),

                cursor.getDouble(cursor.getColumnIndex(
                        DatabaseHelper.Anuncio.PRECO))
        );

        return anuncio;
    }

    public List<br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio> buscarTodos() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Anuncio.TABELA,
                DatabaseHelper.Anuncio.COLUNAS,
                null, null, null, null, null);

        List<br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio> anuncios = new ArrayList<br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio>();

        while(cursor.moveToNext()){
            br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio anuncio = criarAnuncio(cursor);
            anuncios.add(anuncio);
        }

        cursor.close();
        close();

        return anuncios;
    }

    public br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio buscarPorId(Integer id) {

        br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio anuncio = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Anuncio.TABELA,
                DatabaseHelper.Anuncio.COLUNAS,
                DatabaseHelper.Anuncio._ID + " = ?",
                new String[]{ id.toString() },
                null, null, null);

        if(cursor.moveToNext()) {
            anuncio = criarAnuncio(cursor);
        }

        cursor.close();
        close();

        return anuncio;
    }

    public long inserir(br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio anuncio) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.Anuncio.TITULO,
                anuncio.getTitulo());

        values.put(DatabaseHelper.Anuncio.DESCRICAO,
                anuncio.getDescricao());

        values.put(DatabaseHelper.Anuncio.PRODUTO_ID,
                anuncio.getProdutoId());

        values.put(DatabaseHelper.Anuncio.DATA_ANUNCIO,
                new Date().getTime());

        values.put(DatabaseHelper.Anuncio.PRECO,
                anuncio.getPreco());

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        long resultado = db.insert(DatabaseHelper.Anuncio.TABELA, null, values);

        close();

        return resultado;
    }

    public long atualizar(br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio anuncio) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.Anuncio.TITULO,
                anuncio.getTitulo());

        values.put(DatabaseHelper.Anuncio.DESCRICAO,
                anuncio.getDescricao());

        values.put(DatabaseHelper.Anuncio.PRODUTO_ID,
                anuncio.getProdutoId());

        values.put(DatabaseHelper.Anuncio.PRECO,
                anuncio.getPreco());

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        int resultado = db.update(DatabaseHelper.Anuncio.TABELA,
                values, DatabaseHelper.Anuncio._ID + " = ?",
                new String[]{anuncio.getId().toString()});

        close();

        return resultado;
    }
}
