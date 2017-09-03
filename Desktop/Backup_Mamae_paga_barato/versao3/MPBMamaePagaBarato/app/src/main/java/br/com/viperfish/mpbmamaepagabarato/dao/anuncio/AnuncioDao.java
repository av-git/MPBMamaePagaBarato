package br.com.viperfish.mpbmamaepagabarato.dao.anuncio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.dao.DaoBase;
import br.com.viperfish.mpbmamaepagabarato.dao.loja.LojaDao;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.AnuncioDTO;
import br.com.viperfish.mpbmamaepagabarato.modelo.marca.Marca;


/**
 * Created by AV on 22/11/2016.
 */

public class AnuncioDao extends DaoBase {

    private static AnuncioDao instance;
    private static String TAG = "AnuncioDao"; // LogCat

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private AnuncioDao(Context context) {
        super(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static AnuncioDao getInstance(Context context) {
        if (instance == null) {
            instance = new AnuncioDao(context);
        }
        return instance;
    }

    /**
     * Insert a {@link Anuncio} into the database.
     */
    public long inserir(Anuncio anuncio) {

        ContentValues values = new ContentValues();

        if (anuncio.isProdutoSelecionadoPreviamente()) {
            values.put(IAnuncioSchema.PRODUTO_ID, anuncio.getProduto().getId());
            values.put(IAnuncioSchema.NOME_PRODUTO, anuncio.getProduto().getNome());
        } else {
            values.put(IAnuncioSchema.NOME_PRODUTO, anuncio.getNomeProdutoInformadorPeloUsuario());
            //values.put(IAnuncioSchema.NOME_PRODUTO, anuncio.getNomeProdutoInformadorPeloUsuario());
        }

        if (anuncio.isInformouLojaDoAnuncio()) {
            values.put(IAnuncioSchema.LOJA_ID, anuncio.getLoja().getId());
        }

        values.put(IAnuncioSchema.COMENTARIO, anuncio.getComentario());

        values.put(IAnuncioSchema.DATA_ANUNCIO, new Date().getTime());

        values.put(IAnuncioSchema.PRECO, anuncio.getPreco());

        return super.inserir(IAnuncioSchema.TABELA, values);
    }

    public Anuncio buscarPorId(Integer id) {

        Anuncio anuncio = null;
        Cursor cursor = null;

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(IAnuncioSchema.TABELA,
                    IAnuncioSchema.COLUNAS,
                    IAnuncioSchema._ID + " = ?",
                    new String[]{id.toString()},
                    null, null, null);

            if (cursor.moveToNext()) {
                anuncio = transformaCursorEmEntidade(cursor);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar Anuncio");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return anuncio;
    }

    public List<Anuncio> buscarTodos() {

        Cursor cursor = null;
        List<Anuncio> anuncios = new ArrayList<Anuncio>();

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(IAnuncioSchema.TABELA,
                    IAnuncioSchema.COLUNAS,
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                Anuncio anuncio = transformaCursorEmEntidade(cursor);
                anuncios.add(anuncio);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar Anuncio");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return anuncios;
    }

    /**
     * Update the contact details.
     */
    public boolean atualizar(Anuncio anuncio) {
        //DESCOMENTAR ASSIM QUE FOR NECESSARIO
/*
        ContentValues values = new ContentValues();

        values.put(IAnuncioSchema.PRODUTO_ID,
                anuncio.getProdutoId());

        values.put(IAnuncioSchema.NOME_PRODUTO,
                anuncio.getNomeProduto());

        values.put(IAnuncioSchema.COMENTARIO,
                anuncio.getComentario());

        values.put(IAnuncioSchema.DATA_ANUNCIO,
                anuncio.getDataAnuncio().getTime());

        values.put(IAnuncioSchema.PRECO,
                anuncio.getPreco());

        boolean atualizou = super.atualizar(IAnuncioSchema.TABELA,
                values, IAnuncioSchema._ID + " = ?",
                new String[]{anuncio.getId().toString()});

        return atualizou;
        */
        return false;
    }

    /**
     * Delete the provided contact.
     *
     * @param anuncio the contact to delete
     */
    public boolean deletar(Anuncio anuncio) {
        return super.deletar(IAnuncioSchema.TABELA, IAnuncioSchema._ID + " = ?", new String[]{anuncio.getId().toString()});

    }

    protected Anuncio transformaCursorEmEntidade(Cursor cursor) {

        Anuncio anuncio = new Anuncio(

                cursor.getLong(cursor.getColumnIndex(
                        IAnuncioSchema._ID)),

                cursor.getLong(cursor.getColumnIndex(
                        IAnuncioSchema.PRODUTO_ID)),

                cursor.getString(cursor.getColumnIndex(
                        IAnuncioSchema.NOME_PRODUTO)),

                cursor.getString(cursor.getColumnIndex(
                        IAnuncioSchema.COMENTARIO)),

                cursor.getLong(cursor.getColumnIndex(
                        IAnuncioSchema.DATA_ANUNCIO)),

                cursor.getDouble(cursor.getColumnIndex(
                        IAnuncioSchema.PRECO))
        );

        return anuncio;
    }

    /**
     * Obtem a estrutura da view para popular o AnuncioDTO
     */
    protected AnuncioDTO transformaCursorDaViewEmAnuncioDTO(Cursor cursor) {

        AnuncioDTO anuncioDTO = new AnuncioDTO(

                cursor.getLong(cursor.getColumnIndex(IViewAnuncioSchema.ANUNCIO_ID)),

                cursor.getString(cursor.getColumnIndex(IViewAnuncioSchema.ANUNCIO_COMENTARIO)),

                cursor.getLong(cursor.getColumnIndex(IViewAnuncioSchema.ANUNCIO_DATA)),

                cursor.getDouble(cursor.getColumnIndex(IViewAnuncioSchema.ANUNCIO_PRECO)),

                cursor.getString(cursor.getColumnIndex(IViewAnuncioSchema.ANUNCIO_NOME_PRODUTO)),

                cursor.getLong(cursor.getColumnIndex(IViewAnuncioSchema.PRODUTO_ID)),

                cursor.getString(cursor.getColumnIndex(IViewAnuncioSchema.PRODUTO_SITE)),

                cursor.getLong(cursor.getColumnIndex(
                        IViewAnuncioSchema.CATEGORIA_ID)),

                cursor.getLong(cursor.getColumnIndex(
                        IViewAnuncioSchema.CATEGORIA_ID_PAI)),

                cursor.getString(cursor.getColumnIndex(
                        IViewAnuncioSchema.CATEGORIA_NOME)),

                cursor.getLong(cursor.getColumnIndex(
                        IViewAnuncioSchema.SUBCAT_ID)),

                cursor.getLong(cursor.getColumnIndex(
                        IViewAnuncioSchema.SUBCAT_ID_PAI)),

                cursor.getString(cursor.getColumnIndex(
                        IViewAnuncioSchema.SUBCAT_NOME)),

                cursor.getLong(cursor.getColumnIndex(
                        IViewAnuncioSchema.MARCA_ID)),

                cursor.getString(cursor.getColumnIndex(
                        IViewAnuncioSchema.MARCA_NOME)),

                cursor.getLong(cursor.getColumnIndex(
                        IViewAnuncioSchema.LOJA_ID)),

                cursor.getString(cursor.getColumnIndex(
                        IViewAnuncioSchema.LOJA_NOME)),

                cursor.getString(cursor.getColumnIndex(
                        IViewAnuncioSchema.LOJA_ENDERECO)),

                cursor.getString(cursor.getColumnIndex(
                        IViewAnuncioSchema.LOJA_FONE)),

                cursor.getString(cursor.getColumnIndex(
                        IViewAnuncioSchema.LOJA_SITE)),

                cursor.getDouble(cursor.getColumnIndex(
                        IViewAnuncioSchema.LOJA_LATITUDE)),

                cursor.getDouble(cursor.getColumnIndex(
                        IViewAnuncioSchema.LOJA_LONGITUDE)));

        Log.i("anuncioDao: ", anuncioDTO.toString());

        return anuncioDTO;
    }

    public List<AnuncioDTO> buscarAnunciosDaView() {
        // TODO CUIDADO COM CAMEL CASE DOS NOMES DAS TABELAS. O CURSOR FAZ DISTINCAO DO CAMEL CASE
        // DO NOME DAS COLUNAS

        /*
        //OBTEM OS DADOS A PARTI DE UMA VIEW

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT  "+IViewAnuncioSchema.ANUNCIO_ID+", ");
        sql.append("        "+IViewAnuncioSchema.ANUNCIO_COMENTARIO+", ");
        sql.append("        "+IViewAnuncioSchema.ANUNCIO_DATA+", ");
        sql.append("        "+IViewAnuncioSchema.ANUNCIO_PRECO+", ");
        sql.append("        "+IViewAnuncioSchema.ANUNCIO_NOME_PRODUTO+", ");
        sql.append(" ");
        sql.append("        "+IViewAnuncioSchema.PRODUTO_ID+", ");
        sql.append("        "+IViewAnuncioSchema.PRODUTO_SITE+", ");
        sql.append(" ");
        sql.append("        "+IViewAnuncioSchema.CATEGORIA_ID+", ");
        sql.append("        "+IViewAnuncioSchema.CATEGORIA_ID_PAI+", ");
        sql.append("        "+IViewAnuncioSchema.CATEGORIA_NOME+", ");
        sql.append(" ");
        sql.append("        "+IViewAnuncioSchema.SUBCAT_ID+", ");
        sql.append("        "+IViewAnuncioSchema.SUBCAT_ID_PAI+", ");
        sql.append("        "+IViewAnuncioSchema.SUBCAT_NOME+", ");
        sql.append("  ");
        sql.append("        "+IViewAnuncioSchema.MARCA_ID+", ");
        sql.append("        "+IViewAnuncioSchema.MARCA_NOME+", ");
        sql.append(" ");
        sql.append("        "+IViewAnuncioSchema.LOJA_ID+", ");
        sql.append("        "+IViewAnuncioSchema.LOJA_NOME+", ");
        sql.append("        "+IViewAnuncioSchema.LOJA_ENDERECO+", ");
        sql.append("        "+IViewAnuncioSchema.LOJA_FONE+", ");
        sql.append("        "+IViewAnuncioSchema.LOJA_SITE+", ");
        sql.append("        "+IViewAnuncioSchema.LOJA_LATITUDE+", ");
        sql.append("        "+IViewAnuncioSchema.LOJA_LONGITUDE+" ");
        sql.append(" ");
        sql.append("from "+IViewAnuncioSchema.VW_CONSULTA_ANUNCIO+"");
        */

        /*
        //FORMA ANTIGA SEM O USO DA VIEW

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT  a._id as anuncio_id, ");
        sql.append("        a.comentario as anuncio_comentario, ");
        sql.append("        a.data_anuncio as anuncio_data, ");
        sql.append("        a.preco as anuncio_preco, ");
        sql.append("        a.nome_produto as anuncio_nome_produto, ");
        sql.append(" ");
        sql.append("        p._id as produto_id, ");
        sql.append("        p.site as produto_site, ");
        sql.append("         ");
        sql.append("        ca._id as categoria_id, ");
        sql.append("        ca.id_pai as categoria_id_pai, ");
        sql.append("        ca.nome as categoria_nome, ");
        sql.append(" ");
        sql.append("        subcat._id as subcat_id, ");
        sql.append("        subcat.id_pai as subcat_id_pai, ");
        sql.append("        subcat.nome as subcat_nome, ");
        sql.append("         ");
        sql.append("        m._id as marca_id, ");
        sql.append("        m.nome as marca_nome, ");
        sql.append(" ");
        sql.append("        l._id as loja_id, ");
        sql.append("        l.nome as loja_nome, ");
        sql.append("        l.endereco as loja_endereco, ");
        sql.append("        l.fone as loja_fone, ");
        sql.append("        l.site as loja_site, ");
        sql.append("        l.latitude as loja_latitude, ");
        sql.append("        l.longitude as loja_longitude ");
        sql.append(" ");
        sql.append("from (((((ANUNCIO a ");
        sql.append("left join PRODUTO p on a.produto_id = p._id) ");
        sql.append("left join CATEGORIA subcat on p.subcategoria_id = subCat._id) ");
        sql.append("left join CATEGORIA ca on subcat.id_pai = ca._id) ");
        sql.append("left join MARCA m on p.marca_id = m._id) ");
        sql.append("left JOIN LOJA l on a.loja_id = l._id)");
    */
        List<AnuncioDTO> anuncios = new ArrayList<AnuncioDTO>();
        Cursor cursor = null;
        try {

            abrirConexaoEmModoLeitura();
            /*
            //SOLUCAO PASSANDO A STRING SQL NA MAO
            cursor = getDatabase().rawQuery(sql.toString(), null);
            */
            cursor = getDatabase().query(IViewAnuncioSchema.VW_CONSULTA_ANUNCIO,
                    IViewAnuncioSchema.COLUNAS,
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                AnuncioDTO anuncio = transformaCursorDaViewEmAnuncioDTO(cursor);
                anuncios.add(anuncio);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar Anuncio");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return anuncios;
    }
}
