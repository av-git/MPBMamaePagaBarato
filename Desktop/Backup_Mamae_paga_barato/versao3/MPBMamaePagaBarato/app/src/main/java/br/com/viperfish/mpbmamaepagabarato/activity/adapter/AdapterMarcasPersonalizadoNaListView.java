package br.com.viperfish.mpbmamaepagabarato.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.holder.MarcaViewHolder;
import br.com.viperfish.mpbmamaepagabarato.activity.holder.ProdutoViewHolder;
import br.com.viperfish.mpbmamaepagabarato.modelo.marca.Marca;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Classe de Apoio para Personalizar a exibicao dos dados do produto na ListView
 * Created by BBTS on 14/11/2016.
 *
 * Dica: foi utilizado dica de Otimizacao do site: Dica Otimizacao disponiel em: http://blog.alura.com.br/utilizando-o-padrao-viewholder
 */

public class AdapterMarcasPersonalizadoNaListView extends BaseAdapter {

    private final List<Marca> marcas;
    private final Activity activity;

    public AdapterMarcasPersonalizadoNaListView(List<Marca> marcas, Activity activity) {
        this.marcas = marcas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return marcas.size();
    }

    @Override
    public Object getItem(int posicao) {
        return marcas.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return marcas.get(posicao).getId();
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
        MarcaViewHolder marcaViewHolder;

        if (convertView == null) {
            //Log.i("Avelino", "Cirei uma nova View");
            view = LayoutInflater.from(activity).inflate(R.layout.lista_marca_personalizado, layoutPai, false);
            marcaViewHolder = new MarcaViewHolder(view);

            //DICA!!! Isso faz o Holder ficar 'Pendurado' na view que ele pertence para posterior reuso
            view.setTag(marcaViewHolder);

        } else {
            view = convertView;

            ////DICA!!! recupera o Holder da view para reaproveiar reuso
            marcaViewHolder = (MarcaViewHolder) view.getTag();
            //Log.i("Avelino", "Reuso da View");
        }

        Marca marca = marcas.get(posicao);

        //populando as Views
        marcaViewHolder.nome.setText(marca.getNome());

        //marcaViewHolder.imagem.setImageResource(R.drawable.foto);

        return view;
    }
}
