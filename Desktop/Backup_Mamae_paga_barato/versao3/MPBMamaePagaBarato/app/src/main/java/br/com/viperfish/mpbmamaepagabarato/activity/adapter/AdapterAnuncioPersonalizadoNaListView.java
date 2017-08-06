package br.com.viperfish.mpbmamaepagabarato.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.holder.AnuncioViewHolder;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;

/**
 * Classe de Apoio para Personalizar a exibicao dos dados do produto na ListView
 * Created by BBTS on 14/11/2016.
 *
 * Dica: foi utilizado dica de Otimizacao do site: Dica Otimizacao disponiel em: http://blog.alura.com.br/utilizando-o-padrao-viewholder
 */

public class AdapterAnuncioPersonalizadoNaListView extends BaseAdapter {

    private final List<Anuncio> anuncios;
    private final Activity activity;

    public AdapterAnuncioPersonalizadoNaListView(List<Anuncio> produtos, Activity activity) {
        this.anuncios = produtos;
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
        return anuncios.get(posicao).getId();
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

        Anuncio anuncio = anuncios.get(posicao);

        //populando as Views
        anuncioViewHolder.titulo.setText(anuncio.getTitulo());
        anuncioViewHolder.descricao.setText(anuncio.getDescricao());
        //produtoHolder.preco.setText("R$ "+produto.getPreco().toString());
        anuncioViewHolder.preco.setText("R$ " + new Double("23.4"));

        if (anuncio.getId() == 2) {
            anuncioViewHolder.imagem.setImageResource(R.drawable.pamper);
        } else if (anuncio.getId() == 1) {
            anuncioViewHolder.imagem.setImageResource(R.drawable.aptamil);
        } else if (anuncio.getId() == 3) {
            anuncioViewHolder.imagem.setImageResource(R.drawable.nan_pro);
        } else if (anuncio.getId() == 4) {
            anuncioViewHolder.imagem.setImageResource(R.drawable.ninho_fase1);
        } else if (anuncio.getId() == 6) {
            //produtoHolder.imagem.setImageResource(R.drawable.mamadeira);
        } else {
            anuncioViewHolder.imagem.setImageResource(R.drawable.foto);
        }


        return view;
    }
}
