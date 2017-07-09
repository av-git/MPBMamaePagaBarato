package br.com.viperfish.mpbmamaepagabarato.activity.produto.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.holder.CategoriaViewHolder;
import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;

/**
 * Classe de Apoio para Personalizar a exibicao dos dados do produto na ListView
 * Created by BBTS on 14/11/2016.
 *
 * Dica: foi utilizado dica de Otimizacao do site: Dica Otimizacao disponiel em: http://blog.alura.com.br/utilizando-o-padrao-viewholder
 */

public class AdapterCategoriaPersonalizadoNaListView extends BaseAdapter {

    private final List<Categoria> categorias;
    private final Activity activity;

    public AdapterCategoriaPersonalizadoNaListView(List<Categoria> categorias, Activity activity) {
        this.categorias = categorias;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int posicao) {
        return categorias.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return categorias.get(posicao).getId();
    }

    /**
     * método responsável pela construção de cada item (Personalizar)
     *
     * @param posicao
     * @param convertView
     * @param layoutPai
     * @return
     */
    @Override
    public View getView(int posicao, View convertView, ViewGroup layoutPai) {

        boolean criarViewAgora = false;

        //* Nova implementacao para Otimizar Reuso do Uso da view */
        //Dica Otimizacao disponiel em: http://blog.alura.com.br/utilizando-o-padrao-viewholder
        //View view = activity.getLayoutInflater().inflate(R.layout.lista_produto_personalizado, layoutPai, criarViewAgora);

        //* Nova implementacao para Otimizar Reuso do Uso da view */
        View view;
        CategoriaViewHolder categoriaHolder;

        if (convertView == null) {
            //Log.i("Avelino", "Cirei uma nova View");
            view = LayoutInflater.from(activity).inflate(R.layout.lista_categoria_personalizado, layoutPai, false);
            categoriaHolder = new CategoriaViewHolder(view);

            //DICA!!! Isso faz o Holder ficar 'Pendurado' na view que ele pertence para posterior reuso
            view.setTag(categoriaHolder);

        } else {
            view = convertView;

            ////DICA!!! recupera o Holder da view para reaproveiar reuso
            categoriaHolder = (CategoriaViewHolder) view.getTag();
            //Log.i("Avelino", "Reuso da View");
        }

        Categoria categoria = categorias.get(posicao);

        //populando as Views
        categoriaHolder.nome.setText(categoria.getNome());
        if (categoria.getId() == 2) {
            categoriaHolder.imagem.setImageResource(R.drawable.ic_mamadeira);
        }

        return view;
    }
}
