package br.com.viperfish.mpbmamaepagabarato.dao.produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.dao.DatabaseHelper;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Created by Av on 16/11/2016.
 */

public class ProdutoDao {

    private DatabaseHelper databaseHelper;
    //private SQLiteDatabase db;

    public ProdutoDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void close(){
        databaseHelper.close();
    }

    private Produto criarProduto(Cursor cursor) {

        Produto produto = new Produto(

                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Produto._ID)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Produto.TITULO)),


                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Produto.DESCRICAO)),


                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Produto.CATEGORIA_ID)),


                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Produto.FABRICANTE_ID)),


                cursor.getDouble(cursor.getColumnIndex(
                        DatabaseHelper.Produto.PRECO))
        );

        return produto;
    }

    public List<Produto> buscarTodos() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Produto.TABELA,
                DatabaseHelper.Produto.COLUNAS,
                null, null, null, null, null);

        List<Produto> produtos = new ArrayList<Produto>();

        while(cursor.moveToNext()){
            Produto produto = criarProduto(cursor);
            produtos.add(produto);
        }

        cursor.close();
        close();

        return produtos;
    }

    public Produto buscarPorId(Integer id) {

        Produto produto = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Produto.TABELA,
                DatabaseHelper.Produto.COLUNAS,
                DatabaseHelper.Produto._ID + " = ?",
                new String[]{ id.toString() },
                null, null, null);

        if(cursor.moveToNext()) {
            produto = criarProduto(cursor);
        }

        cursor.close();
        close();

        return produto;
    }

    public long inserir(Produto produto) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.Produto.TITULO,
                produto.getTitulo());

        values.put(DatabaseHelper.Produto.DESCRICAO,
                produto.getDescricao());

        values.put(DatabaseHelper.Produto.CATEGORIA_ID,
                produto.getCategoria_id());

        values.put(DatabaseHelper.Produto.FABRICANTE_ID,
                produto.getFabricante_id());

        values.put(DatabaseHelper.Produto.PRECO,
                produto.getPreco());

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        long resultado = db.insert(DatabaseHelper.Produto.TABELA, null, values);

        close();

        return resultado;
    }

    public long atualizar(Produto produto) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.Produto.TITULO,
                produto.getTitulo());

        values.put(DatabaseHelper.Produto.DESCRICAO,
                produto.getDescricao());

        values.put(DatabaseHelper.Produto.CATEGORIA_ID,
                produto.getCategoria_id());

        values.put(DatabaseHelper.Produto.FABRICANTE_ID,
                produto.getFabricante_id());

        values.put(DatabaseHelper.Produto.PRECO,
                produto.getPreco());

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        int resultado = db.update(DatabaseHelper.Produto.TABELA,
                values, DatabaseHelper.Produto._ID + " = ?",
                new String[]{produto.getId().toString()});

        close();

        return resultado;
    }

    /**
     * Nao utilizar. somente para mostrar uma outra alternativa de criar
     * uma consulta
     * @return
     */
    @Deprecated
    public List<Produto> buscaTodosOld() {

        String sql = "SELECT * FROM PRODUTO;";
        SQLiteDatabase db = databaseHelper.getReadableDatabase(); // realizar a leitura
        Cursor c = db.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<Produto>();

        while (c.moveToNext()) {

            Produto produto = new Produto();
            produto.setId(c.getLong(c.getColumnIndex("id")));
            produto.setCategoria_id(c.getLong(c.getColumnIndex("CATEGORIA_id")));
            produto.setFabricante_id(c.getLong(c.getColumnIndex("FABRICANTE_id")));
            produto.setTitulo(c.getString(c.getColumnIndex("titulo")));
            produto.setDescricao(c.getString(c.getColumnIndex("descricao")));
            produto.setPreco(c.getDouble(c.getColumnIndex("preco")));

            produtos.add(produto);
        }
        c.close(); // Boa Pratica e fechar o cursor para liberar memoria e recursos do android
        //close(); // Boa Pratica e fechar tambem a conexao com Bando Dados

        return  produtos;
    }

    /**
     * Utilizar outro metodo insert. existe somente para mostrar outra forma
     *
     * @param produto
     */
    @Deprecated
    public void insereOld(Produto produto)  {

        SQLiteDatabase db = databaseHelper.getWritableDatabase(); // escrever no banco dados

        ContentValues dados = getPrepararDadosParaSalvar(produto);

        db.insert("PRODUTO", null, dados );
        close(); // Boa Pratica e fechar tambem a conexao com Bando Dados
    }

    /**
     * O {@link ContentValues} prepara os dados antes de serem submetidos
     * para o banco de dados. Evita SQL Injection
     * @param produto
     * @return ContentValues
     */
    @NonNull
    private ContentValues getPrepararDadosParaSalvar(Produto produto) {

        ContentValues dados = new ContentValues();
        dados.put("titulo", produto.getTitulo());
        dados.put("descricao", produto.getDescricao());
        dados.put("categoria_id", produto.getCategoria_id());
        dados.put("fabricante_id", produto.getFabricante_id());
        dados.put("preco", produto.getPreco());

        return dados;
    }

    @Deprecated
    public void deletaOld(Produto produto) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        //DICA. PODE SER UM ARRAY DE PARAMETROS
        //EX: String [] params = {String.valueOf(produto.getId(), produto.getCategoria, ... etc)};
        String [] params = {String.valueOf(produto.getId())};
        db.delete("PRODUTO", "id = ?", params);
    }

    @Deprecated
    public void alteraOld(Produto produto) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase(); // escrever no banco dados
        ContentValues dados = getPrepararDadosParaSalvar(produto);
        String [] params = {String.valueOf(produto.getId())};
        db.update("PRODUTO", dados, "id = ?", params);
        close(); // Boa Pratica e fechar tambem a conexao com Bando Dados
    }
}
