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

public class MarcaViewHolder {

    public final TextView nome;
    //public final ImageView imagem;


    public MarcaViewHolder(View view) {

        //pegando as referências das Views
        nome = (TextView) view.findViewById(R.id.lista_marca_personalizada_nome );
        //imagem = (ImageView) view.findViewById(R.id.lista_marca_personalizada_imagem);
    }
}
