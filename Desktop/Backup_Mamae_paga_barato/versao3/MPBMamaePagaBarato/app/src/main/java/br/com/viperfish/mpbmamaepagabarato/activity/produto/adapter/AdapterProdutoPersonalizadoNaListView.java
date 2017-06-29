package br.com.viperfish.mpbmamaepagabarato.activity.produto.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.holder.ProdutoViewHolder;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Classe de Apoio para Personalizar a exibicao dos dados do produto na ListView
 * Created by BBTS on 14/11/2016.
 *
 * Dica: foi utilizado dica de Otimizacao do site: Dica Otimizacao disponiel em: http://blog.alura.com.br/utilizando-o-padrao-viewholder
 */

public class AdapterProdutoPersonalizadoNaListView extends BaseAdapter {

    private final List<Produto> produtos;
    private final Activity activity;

    public AdapterProdutoPersonalizadoNaListView(List<Produto> produtos, Activity activity) {
        this.produtos = produtos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return produtos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return produtos.get(posicao).getId();
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
        ProdutoViewHolder produtoHolder;

        if (convertView == null) {
            //Log.i("Avelino", "Cirei uma nova View");
            view = LayoutInflater.from(activity).inflate(R.layout.lista_produto_personalizado, layoutPai, false);
            produtoHolder = new ProdutoViewHolder(view);

            //DICA!!! Isso faz o Holder ficar 'Pendurado' na view que ele pertence para posterior reuso
            view.setTag(produtoHolder);

        } else {
            view = convertView;

            ////DICA!!! recupera o Holder da view para reaproveiar reuso
            produtoHolder = (ProdutoViewHolder) view.getTag();
            //Log.i("Avelino", "Reuso da View");
        }

        Produto produto = produtos.get(posicao);

        //populando as Views
        produtoHolder.titulo.setText(produto.getTitulo());
        produtoHolder.descricao.setText(produto.getDescricao());
        //produtoHolder.preco.setText("R$ "+produto.getPreco().toString());
        produtoHolder.preco.setText("R$ " + new Double("23.4"));

        if (produto.getId() == 2) {
            produtoHolder.imagem.setImageResource(R.drawable.pamper);
        } else if (produto.getId() == 3) {
            produtoHolder.imagem.setImageResource(R.drawable.nan_pro);
        } else if (produto.getId() == 4) {
            produtoHolder.imagem.setImageResource(R.drawable.ninho_fase1);
        } else if (produto.getId() == 6) {
            produtoHolder.imagem.setImageResource(R.drawable.mamadeira);
        } else {
            produtoHolder.imagem.setImageResource(R.drawable.aptamil);
        }


        return view;
    }
}
