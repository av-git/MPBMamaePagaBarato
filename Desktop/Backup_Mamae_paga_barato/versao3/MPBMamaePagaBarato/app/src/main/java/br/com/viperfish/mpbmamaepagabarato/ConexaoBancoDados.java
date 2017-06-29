package br.com.viperfish.mpbmamaepagabarato;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Av on 16/11/2016.
 */

public abstract class ConexaoBancoDados extends SQLiteOpenHelper {


    private Context context;
    private static final String NOME_BANCO = "mpb.db";
    private static final int VERSAO = 1;

    /**
     *
     * @param context
     * @param " MPB"  Banco dados
     * @param " 1 "  Versão do Banco dados
     */
    public ConexaoBancoDados(Context context) {

        super(context, NOME_BANCO, null, VERSAO);
        this.context = context;
    }
}
