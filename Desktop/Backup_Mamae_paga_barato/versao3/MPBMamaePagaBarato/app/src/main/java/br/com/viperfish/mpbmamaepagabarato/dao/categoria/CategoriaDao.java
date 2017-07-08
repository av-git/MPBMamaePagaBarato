package br.com.viperfish.mpbmamaepagabarato.dao.categoria;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.ConexaoBancoDados;
import br.com.viperfish.mpbmamaepagabarato.dao.DatabaseHelper;
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;

/**
 * Created by AV on 22/11/2016.
 */

public class CategoriaDao {

    private DatabaseHelper databaseHelper;

    public CategoriaDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void close(){
        databaseHelper.close();
    }

    public List<Categoria> buscarTodos() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Categoria.TABELA,
                DatabaseHelper.Categoria.COLUNAS,
                null, null, null, null, null);

        List<Categoria> categorias = new ArrayList<Categoria>();

        while(cursor.moveToNext()){
            Categoria categoria = criarCategoria(cursor);
            categorias.add(categoria);
        }

        cursor.close();
        close();

        return categorias;
    }

    public List<Categoria> buscarPorIdPai() {

        Integer idPai = 1; //Filtra somente a categoria Pai

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Categoria.TABELA,
                DatabaseHelper.Categoria.COLUNAS,
                DatabaseHelper.Categoria.IDPAI + " = ?",
                new String[]{ idPai.toString() },
                null, null, null);

        List<Categoria> categorias = new ArrayList<Categoria>();

        while(cursor.moveToNext()){
            Categoria categoria = criarCategoria(cursor);
            categorias.add(categoria);
        }

        cursor.close();
        close();

        return categorias;
    }

    public Categoria buscarPorId(Integer id) {

        Categoria categoria = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.Categoria.TABELA,
                DatabaseHelper.Categoria.COLUNAS,
                DatabaseHelper.Categoria._ID + " = ?",
                new String[]{ id.toString() },
                null, null, null);

        if(cursor.moveToNext()) {
            categoria = criarCategoria(cursor);
        }

        cursor.close();
        close();

        return categoria;
    }

    /**
     * TODO AVELINO. VERIFICAR A NECESSIDADE
     * @return
     */
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

    private Categoria criarCategoria(Cursor cursor) {

        Categoria categoria = new Categoria(
                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Categoria._ID)),

                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Categoria.IDPAI)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Categoria.NOME))
        );

        return categoria;
    }
}
