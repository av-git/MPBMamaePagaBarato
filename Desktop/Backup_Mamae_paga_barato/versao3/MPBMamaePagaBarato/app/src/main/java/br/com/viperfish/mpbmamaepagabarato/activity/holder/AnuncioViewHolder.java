package br.com.viperfish.mpbmamaepagabarato.activity.holder;

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

public class AnuncioViewHolder {

    public final TextView loja;
    public final TextView titulo;
    public final TextView subCategoria;
    public final TextView descricao;
    public final TextView preco;
    public final ImageView imagem;


    public AnuncioViewHolder(View view) {

        //pegando as referências das Views
        loja = (TextView) view.findViewById(R.id.lista_anuncio_personalizada_loja);
        titulo = (TextView) view.findViewById(R.id.lista_anuncio_personalizada_nome_produto);
        subCategoria = (TextView) view.findViewById(R.id.lista_anuncio_personalizada_sub_categoria);
        descricao = (TextView) view.findViewById(R.id.lista_anuncio_personalizada_descricao_produto);
        preco = (TextView) view.findViewById(R.id.lista_anuncio_personalizada_preco_produto);
        imagem = (ImageView) view.findViewById(R.id.lista_anuncio_personalizada_imagem_produto);
    }
}
