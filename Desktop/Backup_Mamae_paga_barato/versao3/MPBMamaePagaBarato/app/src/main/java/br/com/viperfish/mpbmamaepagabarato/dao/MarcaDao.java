package br.com.viperfish.mpbmamaepagabarato.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.modelo.Marca;

/**
 * Created by AV on 22/11/2016.
 */

public class MarcaDao {

    private DatabaseHelper databaseHelper;

    public MarcaDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void close(){
        databaseHelper.close();
    }

    public List<Marca> buscarTodos() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Categoria.TABELA,
                DatabaseHelper.Categoria.COLUNAS,
                null, null, null, null, null);

        List<Marca> marcas = new ArrayList<Marca>();

        while(cursor.moveToNext()){
            Marca categoria = criarCategoria(cursor);
            marcas.add(categoria);
        }

        cursor.close();
        close();

        return marcas;
    }

    public List<Marca> buscarPorIdPai(Long idPai) {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Categoria.TABELA,
                DatabaseHelper.Marca.COLUNAS,
                DatabaseHelper.Marca._ID + " = ?",
                new String[]{ idPai.toString() },
                null, null, null);

        List<Marca> marcas = new ArrayList<Marca>();

        while(cursor.moveToNext()){
            Marca categoria = criarCategoria(cursor);
            marcas.add(categoria);
        }

        cursor.close();
        close();

        return marcas;
    }

    public Marca buscarPorId(Integer id) {

        Marca marca = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Categoria.TABELA,
                DatabaseHelper.Marca.COLUNAS,
                DatabaseHelper.Marca._ID + " = ?",
                new String[]{ id.toString() },
                null, null, null);

        if(cursor.moveToNext()) {
            marca = criarCategoria(cursor);
        }

        cursor.close();
        close();

        return marca;
    }


    private Marca criarCategoria(Cursor cursor) {

        Marca marca = new Marca(
                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Marca._ID)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Marca.NOME))
        );

        return marca;
    }

    public long inserir(Marca marca) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.Marca._ID,
                marca.getId());

        values.put(DatabaseHelper.Marca.NOME,
                marca.getNome());


        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        long resultado = db.insert(DatabaseHelper.Marca.TABELA, null, values);

        close();

        return resultado;
    }
}
