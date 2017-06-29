package br.com.viperfish.mpbmamaepagabarato.dao.fabricante;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.modelo.fabricante.Fabricante;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Created by Av on 16/11/2016.
 */

public class FabricanteDao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "mpb.db";
    private static final String TABELA = "FABRICANTE";
    private static final int VERSAO = 3;

    /**
     * @param context
     */
    public FabricanteDao(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    /**
     * Cria a Tabela
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABELA+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nome TEXT NOT NULL);";
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

    public List<Fabricante> buscaTodos() {
        String sql = "SELECT * FROM " + TABELA + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Fabricante> fabricantes = new ArrayList<Fabricante>();

        while (c.moveToNext()) {
//            Log.i("consulta", String.valueOf(c.getColumnIndex("id")));
//            Log.i("consulta", String.valueOf(c.getColumnIndex("idPai")));
//            Log.i("consulta", String.valueOf(c.getColumnIndex("nome")));
            Fabricante fabricante = new Fabricante();
            fabricante.setId(c.getLong(c.getColumnIndex("id")));
            fabricante.setNome(c.getString(c.getColumnIndex("nome")));

            fabricantes.add(fabricante);
        }
        c.close();
        return  fabricantes;
    }

}
