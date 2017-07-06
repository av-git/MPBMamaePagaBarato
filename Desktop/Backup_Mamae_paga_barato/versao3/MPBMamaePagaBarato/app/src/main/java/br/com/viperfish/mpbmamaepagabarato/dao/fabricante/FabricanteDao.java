package br.com.viperfish.mpbmamaepagabarato.dao.fabricante;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.dao.DatabaseHelper;
import br.com.viperfish.mpbmamaepagabarato.modelo.fabricante.Fabricante;

/**
 * Created by Av on 16/11/2016.
 *
 * Seguindo a recomendacao: https://developer.android.com/training/basics/data-storage/databases.html
 */

public class FabricanteDao {

    private DatabaseHelper databaseHelper;
    //private SQLiteDatabase db;

    public FabricanteDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void close(){
        databaseHelper.close();
    }

    public List<Fabricante> buscarTodos() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Fabricante.TABELA,
                DatabaseHelper.Fabricante.COLUNAS,
                null, null, null, null, null);

        List<Fabricante> fabricantes = new ArrayList<Fabricante>();

        while(cursor.moveToNext()){
            Fabricante fabricante = criarFabricante(cursor);
            fabricantes.add(fabricante);
        }

        cursor.close();
        close();

        return fabricantes;
    }

    public Fabricante buscarPorId(Integer id) {

        Fabricante fabricante = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Fabricante.TABELA,
                DatabaseHelper.Fabricante.COLUNAS,
                DatabaseHelper.Fabricante._ID + " = ?",
                new String[]{ id.toString() },
                null, null, null);

        if(cursor.moveToNext()) {
            fabricante = criarFabricante(cursor);
        }

        cursor.close();
        close();

        return fabricante;
    }

    private Fabricante criarFabricante(Cursor cursor) {

        Fabricante fabricante = new Fabricante(
                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Fabricante._ID)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Fabricante.NOME))
        );

        return fabricante;
    }

}
