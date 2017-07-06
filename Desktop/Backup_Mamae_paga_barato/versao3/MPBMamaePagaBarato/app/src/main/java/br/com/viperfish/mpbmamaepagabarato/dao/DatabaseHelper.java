package br.com.viperfish.mpbmamaepagabarato.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ddark on 04/07/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "mpb.db";
    private static final int VERSAO = 1;

    public static class Categoria {
        public static final String TABELA = "CATEGORIA";
        public static final String _ID = "_id";
        public static final String IDPAI = "id_pai";
        public static final String NOME =  "nome";

        public static final String[] COLUNAS = new String[] {
                _ID, IDPAI, NOME
        };
    }

    public static class Fabricante {
        public static final String TABELA = "FABRICANTE";
        public static final String _ID = "_id";
        public static final String NOME =  "nome";

        public static final String[] COLUNAS = new String[] {
                _ID, NOME
        };
    }

    public static class Produto {
        public static final String TABELA = "PRODUTO";
        public static final String _ID = "_id";
        public static final String TITULO =  "titulo";
        public static final String DESCRICAO =  "descricao";
        public static final String CATEGORIA_ID =  "categoria_id";
        public static final String FABRICANTE_ID =  "fabricante_id";
        public static final String PRECO =  "preco";

        public static final String[] COLUNAS = new String[] {
                _ID, TITULO, DESCRICAO, CATEGORIA_ID, FABRICANTE_ID, PRECO
        };
    }

    private static final String SQL_CREATE_PRODUTO =
            "CREATE TABLE " + Produto.TABELA + " (" +
                    Produto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Produto.CATEGORIA_ID+ " INTEGER references "+Categoria.TABELA +Produto._ID+ ", " +
                    Produto.FABRICANTE_ID+ " INTEGER references "+Fabricante.TABELA +Produto._ID+", " +
                    Produto.TITULO+ " TEXT, " +
                    Produto.DESCRICAO+ " TEXT, " +
                    Produto.PRECO + " REAL)";

    private static final String SQL_CREATE_CATEGORIA =
            "CREATE TABLE " + Categoria.TABELA + " (" +
                    Categoria._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Categoria.IDPAI+ " INTEGER, " +
                    Categoria.NOME+ " TEXT); ";

    private static final String SQL_CREATE_FABRICANTE =
            "CREATE TABLE " + Fabricante.TABELA + " (" +
                    Fabricante._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Fabricante.NOME+ " TEXT NOT NULL); ";

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        criarTabelas(db);
    }

    //TODO REMOVER ASSIM QUE O CASO DE USO ESTIVER PRONTO.
    //A IDEIA DO METODO E SEMPRE RECRIAR O BANCO APOS ATUALIZAR SUA ESTRUTURA.
    //LEMBRAR DE VERSIONAR O BANCO PARA O onUpgrade Funcionar
    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS "+Produto.TABELA +"; ");
        db.execSQL("DROP TABLE IF EXISTS "+Fabricante.TABELA +"; ");
        db.execSQL("DROP TABLE IF EXISTS "+Categoria.TABELA +"; ");
        onCreate(db);
    }

    private void criarTabelas(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FABRICANTE);
        db.execSQL(SQL_CREATE_CATEGORIA);
        db.execSQL(SQL_CREATE_PRODUTO);
        db.execSQL(carregarInicialCategoria());
    }

    // TODO AVELINO REFATORAR PARA UMA CONSTANTE
    private String carregarInicialCategoria(){

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO CATEGORIA (_id, id_pai, nome) VALUES ");
        sql.append("(1, null, null),");
        sql.append("(2, 1, 'Higiene e Saúde'),");
        sql.append("(3, 2, 'Fraldas'),");
        sql.append("(4, 2, 'Lenços Umedecidos'),");
        sql.append("(5, 2, 'Pomadas de Assadura'),");
        sql.append("(6, 2, 'Corpo e Banho'),");
        sql.append("(7, 2, 'Higiene Bucal'),");
        sql.append("(8, 2, 'OUTROS'),");
        sql.append("(9, 1, 'Alimentação'),");
        sql.append("(10, 9, 'Leites e Fórmulas'),");
        sql.append("(11, 9, 'Papinhas'),");
        sql.append("(12, 9, 'Amamentação/Acessórios'),");
        sql.append("(13, 9, 'Garrafas/Potes/Copos'),");
        sql.append("(14, 9, 'OUTROS'),");
        sql.append("(15, 1, 'Medicamentos'),");
        sql.append("(16, 15, 'Dor e Febre'),");
        sql.append("(17, 1, 'OUTROS'),");
        sql.append("(18, 17, 'OUTROS');");

        return sql.toString();
    }

    //TODO. AVELINO: VERIFICAR SE A QUERY DEVE TER FOREIGN KEY
    //TODO. VERIFICAR SE DEVE SER REAL OU DOUBLE
    @Deprecated
    private void criarTabelaProdutoOld(SQLiteDatabase db) {

        String sql = "CREATE TABLE PRODUTO " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CATEGORIA_id INTEGER references CATEGORIA(_id), " +
                "FABRICANTE_id INTEGER references FABRICANTE(_id), " +
                "titulo TEXT NOT NULL, " +
                "descricao TEXT, " +
                "preco REAL );";
        db.execSQL(sql);
    }

    @Deprecated
    private void criarTabelaFabricante(SQLiteDatabase db) {
        String sql = "CREATE TABLE FABRICANTE " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Deprecated
    private void criarTabelaCategoria(SQLiteDatabase db) {
        String sql = "CREATE TABLE CATEGORIA "   +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " id_Pai INTEGER, " +
                " nome TEXT);";
        db.execSQL(sql);
        db.execSQL(carregarInicialCategoria());
    }
}
