package br.com.viperfish.mpbmamaepagabarato.dao.marca;

/**
 * Created by ddark on 23/07/17.
 */

interface IMarcaSchema {

    public static final String TABELA = "MARCA";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME =  "nome";

    public static final String[] COLUNAS = new String[] {
            COLUNA_ID, COLUNA_NOME
    };


}
