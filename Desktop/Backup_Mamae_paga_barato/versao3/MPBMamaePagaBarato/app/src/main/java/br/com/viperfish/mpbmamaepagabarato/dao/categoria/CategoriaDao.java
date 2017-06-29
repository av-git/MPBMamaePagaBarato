package br.com.viperfish.mpbmamaepagabarato.dao.categoria;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.ConexaoBancoDados;
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;

/**
 * Created by BBTS on 22/11/2016.
 */

public class CategoriaDao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "mpb.db";
    private static final String TABELA = "CATEGORIA";
    private static final int VERSAO = 3;

    /**
     *
     * @param context
     */
    public CategoriaDao(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABELA  +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " idPai INTEGER, " +
                " nome TEXT);";
        db.execSQL(sql);
        db.execSQL(carregarScriptInicial());
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

    private String carregarScriptInicial (){

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO categoria (id, idPai, nome) VALUES ");
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

    public List<Categoria> buscaCategorias() {
        String sql = "SELECT * FROM " + TABELA + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Categoria> categorias = new ArrayList<Categoria>();

        while (c.moveToNext()) {
//            Log.i("consulta", String.valueOf(c.getColumnIndex("id")));
//            Log.i("consulta", String.valueOf(c.getColumnIndex("idPai")));
//            Log.i("consulta", String.valueOf(c.getColumnIndex("nome")));
            Categoria categoria = new Categoria();
            categoria.setId(c.getLong(c.getColumnIndex("id")));
            categoria.setIdPai(c.getLong(c.getColumnIndex("idPai")));
            categoria.setNome(c.getString(c.getColumnIndex("nome")));
            categorias.add(categoria);
        }
        c.close();
        return  categorias;
    }

    public List<Categoria> buscarCategoriasOrganizadas() {
        StringBuffer  varname1 = new StringBuffer();
        varname1.append("WITH LINK(ID, NOME, LEVEL) AS ( ");
        varname1.append("SELECT ID, NOME, 0 FROM CATEGORIA WHERE ID_PAI IS NULL ");
        varname1.append("UNION ALL ");
        varname1.append("SELECT CATEGORIA.ID, IFNULL(LINK.NOME || '-->', '') || CATEGORIA.NOME, LEVEL + 1 ");
        varname1.append("FROM LINK INNER JOIN CATEGORIA ON LINK.ID = CATEGORIA.ID_PAI ");
        varname1.append(") ");
        varname1.append("SELECT NOME FROM LINK WHERE NOME IS NOT NULL ORDER BY ID;");

        return null;
    }
}
