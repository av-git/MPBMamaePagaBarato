package br.com.viperfish.mpbmamaepagabarato.dao.produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Created by Av on 16/11/2016.
 */

public class ProdutoDao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "mpb.db";
    private static final String TABELA = "PRODUTOS";
    private static final int VERSAO = 7;

    /**
     * @param context
     */
    public ProdutoDao(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    /**
     * Cria a Tabela
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABELA+
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "CATEGORIA_id INTEGER references CATEGORIA(id)," +
                        "FABRICANTE_id INTEGER references FABRICANTE(id)," +
                        "titulo TEXT NOT NULL, " +
                        "descricao TEXT," +
                        "preco REAL );";
        sqLiteDatabase.execSQL(sql);
    }

    //TODO REMOVER ASSIM QUE O CASO DE USO ESTIVER PRONTO.
    //A IDEIA DO METODO E SEMPRE RECRIAR O BANCO APOS ATUALIZAR SUA ESTRUTURA.
    //LEMBRAR DE VERSIONAR O BANCO PARA O onUpgrade Funcionar
    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        String sql = "DROP TABLE IF EXISTS " +TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public List<Produto> buscaTodos() {

        String sql = "SELECT * FROM " + TABELA + ";";
        SQLiteDatabase db = getReadableDatabase(); // realizar a leitura
        Cursor c = db.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<Produto>();

        while (c.moveToNext()) {
//            Log.i("consulta", String.valueOf(c.getColumnIndex("id")));
//            Log.i("consulta", String.valueOf(c.getColumnIndex("idPai")));
//            Log.i("consulta", String.valueOf(c.getColumnIndex("nome")));
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
        close(); // Boa Pratica e fechar tambem a conexao com Bando Dados

        return  produtos;
    }

    public void insere(Produto produto)  {

        SQLiteDatabase db = getWritableDatabase(); // escrever no banco dados

        ContentValues dados = getPrepararDadosParaSalvar(produto);

        db.insert(TABELA, null, dados );
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

    public void deleta(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();

        //DICA. PODE SER UM ARRAY DE PARAMETROS
        //EX: String [] params = {String.valueOf(produto.getId(), produto.getCategoria, ... etc)};
        String [] params = {String.valueOf(produto.getId())};
        db.delete(TABELA, "id = ?", params);
    }

    public void altera(Produto produto) {
        SQLiteDatabase db = getWritableDatabase(); // escrever no banco dados
        ContentValues dados = getPrepararDadosParaSalvar(produto);
        String [] params = {String.valueOf(produto.getId())};
        db.update(TABELA, dados, "id = ?", params);
        close(); // Boa Pratica e fechar tambem a conexao com Bando Dados
    }
}
