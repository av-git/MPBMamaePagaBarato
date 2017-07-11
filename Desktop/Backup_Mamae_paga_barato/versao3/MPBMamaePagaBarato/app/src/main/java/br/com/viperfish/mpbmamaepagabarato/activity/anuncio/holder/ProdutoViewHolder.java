package br.com.viperfish.mpbmamaepagabarato.activity.anuncio.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.viperfish.mpbmamaepagabarato.R;

/**
 * Classe de Apoio para Segurar a referencia das TextView (segurar as informações da view)
 * <p>
 * Created by BBTS on 14/11/2016.
 *
 * Dica Otimizacao disponiel em: http://blog.alura.com.br/utilizando-o-padrao-viewholder/
 *
 */

public class ProdutoViewHolder {

    public final TextView titulo;
    public final TextView descricao;
    public final TextView preco;
    public final ImageView imagem;


    public ProdutoViewHolder(View view) {

        //pegando as referências das Views
        titulo = (TextView) view.findViewById(R.id.lista_produto_personalizada_titulo);
        descricao = (TextView) view.findViewById(R.id.lista_produto_personalizada_descricao);
        preco = (TextView) view.findViewById(R.id.lista_produto_personalizada_preco);
        imagem = (ImageView) view.findViewById(R.id.lista_produto_personalizada_imagem);
    }
}
