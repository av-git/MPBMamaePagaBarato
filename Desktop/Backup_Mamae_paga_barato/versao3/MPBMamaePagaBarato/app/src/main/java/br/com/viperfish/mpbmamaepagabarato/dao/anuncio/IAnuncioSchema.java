package br.com.viperfish.mpbmamaepagabarato.dao.anuncio;

/**
 * Created by ddark on 23/07/17.
 */

interface IAnuncioSchema {

    public static final String TABELA = "ANUNCIO";
    public static final String _ID = "_id";
    public static final String LOJA_ID =  "loja_id";
    public static final String PRODUTO_ID =  "produto_id";
    public static final String NOME_PRODUTO =  "nome_produto";
    public static final String COMENTARIO =  "comentario";
    public static final String DATA_ANUNCIO = "data_anuncio";
    public static final String PRECO =  "preco";
    //public static final String CAMINHO_IMAGEM = "caminho_imagem";

    public static final String[] COLUNAS = new String[] {
            _ID, LOJA_ID, NOME_PRODUTO, COMENTARIO, PRODUTO_ID, PRECO, DATA_ANUNCIO
    };

}
