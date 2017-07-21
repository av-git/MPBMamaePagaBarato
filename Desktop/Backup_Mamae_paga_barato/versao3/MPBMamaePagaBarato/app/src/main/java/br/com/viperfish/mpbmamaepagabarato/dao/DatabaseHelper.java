package br.com.viperfish.mpbmamaepagabarato.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.viperfish.mpbmamaepagabarato.modelo.Marca;

/**
 * Created by ddark on 04/07/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "mpb.db";
    private static final int VERSAO = 1;
    private final Context context;

    public static class Categoria {
        public static final String TABELA = "CATEGORIA";
        public static final String _ID = "_id";
        public static final String IDPAI = "id_pai";
        public static final String NOME =  "nome";

        public static final String[] COLUNAS = new String[] {
                _ID, IDPAI, NOME
        };
    }

    public static class Marca {
        public static final String TABELA = "MARCA";
        public static final String _ID = "_id";
        public static final String NOME =  "nome";

        public static final String[] COLUNAS = new String[] {
                _ID, NOME
        };
    }

    public static class Produto {
        public static final String TABELA = "PRODUTO";
        public static final String _ID = "_id";
        public static final String NOME =  "nome";
        public static final String SITE =  "site";
        public static final String SUBCATEGORIA_ID =  "subCategoria_id";
        public static final String MARCA_ID =  "marca_id";
        public static final String FOTO =  "foto";

        public static final String[] COLUNAS = new String[] {
                _ID, NOME, SITE , FOTO , SUBCATEGORIA_ID, MARCA_ID
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

    private static final String SQL_CREATE_MARCA =
            "CREATE TABLE " + Marca.TABELA + " (" +
                    Marca._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Marca.NOME+ " TEXT); ";

    private static final String SQL_CREATE_PRODUTO =

            "CREATE TABLE " + Produto.TABELA + " (" +
                    Produto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Produto.NOME+ " TEXT NOT NULL, " +
                    Produto.SITE+ " TEXT, " +
                    Produto.FOTO+ " BLOB, " +
                    Produto.MARCA_ID+ " INTEGER REFERENCES " +Marca.TABELA +" ("+Marca._ID+"), " +
                    Produto.SUBCATEGORIA_ID + " INTEGER REFERENCES " + Categoria.TABELA + " ("+Categoria._ID+")); ";

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.context = context;
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
        db.execSQL("DROP TABLE IF EXISTS "+ Categoria.TABELA +"; ");
        db.execSQL("DROP TABLE IF EXISTS "+ Marca.TABELA +"; ");
        onCreate(db);
    }

    private void criarTabelas(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CATEGORIA);
        db.execSQL(SQL_CREATE_MARCA);
        db.execSQL(SQL_CREATE_PRODUTO);
        db.execSQL(SQL_CREATE_ANUNCIO);

        db.execSQL(carregarInicialCategoria());
        cargaInicialMarcas(db);
        //cargaInicialProdutos(db);
    }

    // TODO AVELINO REFATORAR PARA UMA CONSTANTE
    private String carregarInicialCategoria(){

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO "+ Categoria.TABELA +" ( "+Categoria._ID+" , "+Categoria.IDPAI+" , "+Categoria.NOME+") VALUES ");
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

    // TODO AVELINO REFATORAR PARA UMA CONSTANTE
    private void cargaInicialMarcas(SQLiteDatabase db){

        try
        {

            AssetManager assetManager=  context.getAssets();
            InputStreamReader is  = new InputStreamReader(assetManager.open("carga-marcas.csv"), "UTF-8");
            BufferedReader reader = new BufferedReader(is);
            String linha;
            String id, nome;

            while ((linha=reader.readLine()) != null)
            {
                String[] dadosDaLinha=linha.split(",");
                br.com.viperfish.mpbmamaepagabarato.modelo.Marca marca = new br.com.viperfish.mpbmamaepagabarato.modelo.Marca();

                marca.setId(Long.parseLong(dadosDaLinha[0]));
                marca.setNome(dadosDaLinha[1]);

                ContentValues values = new ContentValues();

                values.put(DatabaseHelper.Marca._ID,
                        marca.getId());

                values.put(DatabaseHelper.Marca.NOME,
                        marca.getNome());

                long resultado = db.insert(DatabaseHelper.Marca.TABELA, null, values);

            }
            is.close();
        }
        catch (IOException ex)
        {
            Log.i("debug", "erro" + ex.getMessage() );
        }
    }

    // TODO AVELINO REFATORAR PARA UMA CONSTANTE
    private void cargaInicialProdutos(SQLiteDatabase db){

        ContentValues values = new ContentValues();

        values.put(Produto._ID, 1);
        values.put(Produto.NOME, "Pampers Recém-Nascido - RN");
        values.put(Produto.SUBCATEGORIA_ID, 17);
        values.put(Produto.MARCA_ID, 1);
        db.insert(Produto.TABELA, null, values);

        values.put(Produto._ID, 2);
        values.put(Produto.NOME, "Pampers Confort Sec");
        values.put(Produto.SUBCATEGORIA_ID, 17);
        values.put(Produto.MARCA_ID, 1);
        db.insert(Produto.TABELA, null, values);

        values.put(Produto._ID, 3);
        values.put(Produto.NOME, "Pampers Pants");
        values.put(Produto.SUBCATEGORIA_ID, 17);
        values.put(Produto.MARCA_ID, 1);
        db.insert(Produto.TABELA, null, values);
        /*
        StringBuffer  sql = new StringBuffer();
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (1, 'Pampers Recém-Nascido - RN', 17, 1, 'https://www.pampers.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (2, 'Pampers Confort Sec', 17, 1, 'https://www.pampers.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (3, 'Pampers Pants', 17, 1, 'https://www.pampers.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (4, 'Pampers Premium Care', 17, 1, 'https://www.pampers.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (5, 'Pampers Supersec', 17, 1, 'https://www.pampers.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (6, 'Pampers Total Confort', 17, 1, 'https://www.pampers.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (7, 'Huggies Supreme Care', 17, 2, 'https://www.huggies.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (8, 'Huggies Soft Touch Primeiros 100 Dias - RN', 17, 2, 'https://www.huggies.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (9, 'Huggies Soft Touch Fralda Roupinha', 17, 2, 'https://www.huggies.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (10, 'Huggies Tripla Proteção ', 17, 2, 'https://www.huggies.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (11, 'Huggies Tripla Proteção Fralda Roupinha ', 17, 2, 'https://www.huggies.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (12, 'Huggies Little Swimmers - piscina', 17, 2, 'https://www.huggies.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (13, 'MamyPoko Tamanho RN', 17, 6, 'http://www.mamypoko.com/br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (14, 'MamyPoko Fralda-Fita', 17, 6, 'http://www.mamypoko.com/br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (15, 'MamyPoko Fralda-Calça', 17, 6, 'http://www.mamypoko.com/br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (16, 'Pom Pom Colo de Mãe', 17, 5, 'http://www.pompom.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (17, 'Pom Pom Protek Proteção de Mãe', 17, 5, 'http://www.pompom.com.br', NULL);");
        sql.append("INSERT INTO PRODUTO (_id, nome, subCategoria_id, marca_id, site, foto) VALUES (18, 'Pom Pom Amor de Mãe Recém Nascido  RN', 17, 5, 'http://www.pompom.com.br', NULL);");

        return sql.toString();
        */
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
