package br.com.viperfish.mpbmamaepagabarato.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ddark on 04/07/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "mpb.db";
    private static final int VERSAO = 2;

    public static class Categoria {
        public static final String TABELA = "CATEGORIA";
        public static final String _ID = "_id";
        public static final String IDPAI = "id_pai";
        public static final String NOME =  "nome";

        public static final String[] COLUNAS = new String[] {
                _ID, IDPAI, NOME
        };
    }

    public static class Produto {
        public static final String TABELA = "PRODUTO";
        public static final String _ID = "_id";
        public static final String NOME =  "nome";
        public static final String SUBCATEGORIA_ID =  "subCategoria_id";

        public static final String[] COLUNAS = new String[] {
                _ID, NOME, SUBCATEGORIA_ID
        };
    }

    public static class Anuncio {
        public static final String TABELA = "ANUNCIO";
        public static final String _ID = "_id";
        public static final String TITULO =  "titulo";
        public static final String DESCRICAO =  "descricao";
        //public static final String SUBCATEGORIA_ID =  "subCategoria_id";
        public static final String PRODUTO_ID =  "produto_id";
        public static final String PRECO =  "preco";
        public static final String DATA_ANUNCIO = "data_anuncio";
        public static final String CAMINHO_IMAGEM = "caminho_imagem";

        public static final String[] COLUNAS = new String[] {
                _ID, TITULO, DESCRICAO, PRODUTO_ID, PRECO, DATA_ANUNCIO
        };
    }

    private static final String SQL_CREATE_ANUNCIO =
            "CREATE TABLE " + Anuncio.TABELA + " (" +
                    Anuncio._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Anuncio.PRODUTO_ID + " INTEGER, " +
                    Anuncio.TITULO+ " TEXT, " +
                    Anuncio.DESCRICAO+ " TEXT, " +
                    Anuncio.DATA_ANUNCIO+ " DATE, " +
                    Anuncio.PRECO + " REAL, " +
                    Anuncio.CAMINHO_IMAGEM+ " TEXT, " +
                    "FOREIGN KEY (" + Anuncio.PRODUTO_ID+ ") REFERENCES "+Produto.TABELA + "(" +Produto._ID+"));";

    private static final String SQL_CREATE_CATEGORIA =
            "CREATE TABLE " + Categoria.TABELA + " (" +
                    Categoria._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Categoria.IDPAI+ " INTEGER, " +
                    Categoria.NOME+ " TEXT); ";

    private static final String SQL_CREATE_PRODUTO =
            "CREATE TABLE " + Produto.TABELA + " (" +
                    Produto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Produto.NOME+ " TEXT NOT NULL, " +
                    Produto.SUBCATEGORIA_ID + "INTEGER references" + Categoria.TABELA + Categoria._ID+ "); ";

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
        db.execSQL("DROP TABLE IF EXISTS "+ Anuncio.TABELA +"; ");
        db.execSQL("DROP TABLE IF EXISTS "+ Produto.TABELA +"; ");
        db.execSQL("DROP TABLE IF EXISTS "+Categoria.TABELA +"; ");
        onCreate(db);
    }

    private void criarTabelas(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUTO);
        db.execSQL(SQL_CREATE_CATEGORIA);
        db.execSQL(SQL_CREATE_ANUNCIO);
        db.execSQL(carregarInicialCategoria());
    }

    // TODO AVELINO REFATORAR PARA UMA CONSTANTE
    private String carregarInicialCategoria(){

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO CATEGORIA (_id, id_pai, nome) VALUES ");
        sql.append("(1, null, null), ");
        sql.append("(2, 1, 'Alimentação p/ Bebê'), ");
        sql.append("(3, 2, 'Acessórios p/ Amamentação'), ");
        sql.append("(4, 2, 'Babadores'), ");
        sql.append("(5, 2, 'Garrafas, Potes e Copos'), ");
        sql.append("(6, 2, 'Leite e Fórmulas'), ");
        sql.append("(7, 2, 'Mamadeiras'), ");
        sql.append("(8, 2, 'Papinhas'), ");
        sql.append("(9, 2, 'Outros'), ");
        sql.append("(10, 1, 'Berços e Móveis p/ Bebês'), ");
        sql.append("(11, 1, 'Cadeiras p/ Bebês'), ");
        sql.append("(12, 11, 'Cadeira Alimentação'), ");
        sql.append("(13, 11, 'Bebê Confortos'), ");
        sql.append("(14, 11, 'Outros'), ");
        sql.append("(15, 1, 'Carrinho p/ Bebê'), ");
        sql.append("(16, 1, 'Higiene, Saúde, Banho'), ");
        sql.append("(17, 16, 'Fraldas'), ");
        sql.append("(18, 16, 'Lenços Umedecidos'), ");
        sql.append("(19, 16, 'Pomadas de Assadura'), ");
        sql.append("(20, 16, 'Corpo e Banho'), ");
        sql.append("(21, 16, 'Higiene Bucal'), ");
        sql.append("(22, 16, 'Outros'), ");
        sql.append("(23, 1, 'Medicamentos'), ");
        sql.append("(24, 23, 'Dor e Febre'), ");
        sql.append("(25, 23, 'Outros'), ");
        sql.append("(26, 1, 'Roupas'), ");
        sql.append("(27, 26, 'Bodies'), ");
        sql.append("(28, 26, 'Calças'), ");
        sql.append("(29, 26, 'Calçados'), ");
        sql.append("(30, 26, 'Camisas'), ");
        sql.append("(31, 26, 'Vestidos'), ");
        sql.append("(32, 26, 'Outros'), ");
        sql.append("(33, 1, 'Outros');");

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
