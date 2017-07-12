package br.com.viperfish.mpbmamaepagabarato.dao.produto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.dao.DatabaseHelper;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Created by Av on 16/11/2016.
 * <p>
 * Seguindo a recomendacao: https://developer.android.com/training/basics/data-storage/databases.html
 */

public class ProdutoDao {

    private DatabaseHelper databaseHelper;
    //private SQLiteDatabase db;

    public ProdutoDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void close() {
        databaseHelper.close();
    }

    public List<br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto> buscarTodos() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Produto.TABELA,
                DatabaseHelper.Produto.COLUNAS,
                null, null, null, null, null);

        List<Produto> produtos = new ArrayList<Produto>();

        while (cursor.moveToNext()) {
            br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto produto = criarFabricante(cursor);
            produtos.add(produto);
        }

        cursor.close();
        close();

        return produtos;
    }

    public br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto buscarPorId(Integer id) {

        br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto produto = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Produto.TABELA,
                DatabaseHelper.Produto.COLUNAS,
                DatabaseHelper.Produto._ID + " = ?",
                new String[]{id.toString()},
                null, null, null);

        if (cursor.moveToNext()) {
            produto = criarFabricante(cursor);
        }

        cursor.close();
        close();

        return produto;
    }

    private br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto criarFabricante(Cursor cursor) {

        br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto produto = new br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto(
                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Produto._ID)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Produto.NOME))
        );

        return produto;
    }

}
