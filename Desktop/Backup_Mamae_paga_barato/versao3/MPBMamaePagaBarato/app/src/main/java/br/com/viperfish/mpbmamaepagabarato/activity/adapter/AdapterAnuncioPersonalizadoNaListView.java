package br.com.viperfish.mpbmamaepagabarato.activity.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.holder.AnuncioViewHolder;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.AnuncioDTO;

/**
 * Classe de Apoio para Personalizar a exibicao dos dados do anuncio na ListView
 * Created by BBTS on 14/11/2016.
 *
 * Dica: foi utilizado dica de Otimizacao do site: Dica Otimizacao disponiel em: http://blog.alura.com.br/utilizando-o-padrao-viewholder
 */

public class AdapterAnuncioPersonalizadoNaListView extends BaseAdapter {

    private final List<AnuncioDTO> anuncios;
    private final Activity activity;

    public AdapterAnuncioPersonalizadoNaListView(List<AnuncioDTO> anuncios, Activity activity) {
        this.anuncios = anuncios;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return anuncios.size();
    }

    @Override
    public Object getItem(int posicao) {
        return anuncios.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return anuncios.get(posicao).getIdAnuncio();
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
        AnuncioViewHolder anuncioViewHolder;

        if (convertView == null) {
            //Log.i("Avelino", "Cirei uma nova View");
            view = LayoutInflater.from(activity).inflate(R.layout.lista_anuncio_personalizado, layoutPai, false);
            anuncioViewHolder = new AnuncioViewHolder(view);

            //DICA!!! Isso faz o Holder ficar 'Pendurado' na view que ele pertence para posterior reuso
            view.setTag(anuncioViewHolder);

        } else {
            view = convertView;

            ////DICA!!! recupera o Holder da view para reaproveiar reuso
            anuncioViewHolder = (AnuncioViewHolder) view.getTag();
            //Log.i("Avelino", "Reuso da View");
        }

        AnuncioDTO anuncio = anuncios.get(posicao);

        //populando as Views
        Log.i("AnuncioDTO", anuncio.toString());
        anuncioViewHolder.loja.setText(anuncio.getLoja_nome());
        anuncioViewHolder.titulo.setText(anuncio.getNomeProdutoAnunciado());
        anuncioViewHolder.subCategoria.setText(anuncio.getSubcat_id() != null ? anuncio.getSubcat_nome() : anuncio.getCategoria_nome());
        anuncioViewHolder.descricao.setText(anuncio.getComentarioAnuncio());
        anuncioViewHolder.preco.setText("R$ " + new Double(anuncio.getPrecoAnuncio()));

        if (anuncio.getIdAnuncio() == 2) {
            anuncioViewHolder.imagem.setImageResource(R.drawable.prod_01);
        } else if (anuncio.getIdAnuncio() == 1) {
            anuncioViewHolder.imagem.setImageResource(R.drawable.prod_01);
        } else if (anuncio.getIdAnuncio() == 3) {
            anuncioViewHolder.imagem.setImageResource(R.drawable.prod_01);
        } else if (anuncio.getIdAnuncio() == 4) {
            anuncioViewHolder.imagem.setImageResource(R.drawable.prod_01);
        } else if (anuncio.getIdAnuncio() == 6) {
            //produtoHolder.imagem.setImageResource(R.drawable.mamadeira);
        } else {
            anuncioViewHolder.imagem.setImageResource(R.drawable.foto);
        }


        return view;
    }
}
