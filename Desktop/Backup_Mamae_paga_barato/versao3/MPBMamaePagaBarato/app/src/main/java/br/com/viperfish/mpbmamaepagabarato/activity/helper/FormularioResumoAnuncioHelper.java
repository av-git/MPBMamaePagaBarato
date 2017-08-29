package br.com.viperfish.mpbmamaepagabarato.activity.helper;

import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.formularios.ResumoAnuncioActivity;
import br.com.viperfish.mpbmamaepagabarato.modelo.anuncio.Anuncio;
import util.Validador;

/**
 * Created by ddark on 13/11/16.
 */

public class FormularioResumoAnuncioHelper {

    private final TextView campoTitulo;
    private final TextView campoDescricao;
    private final TextView campoCategoria;
    private final TextView campoPreco;

    private final TextView campoNomeLoja;
    private final TextView campoEnderecoLoja;
    private final TextView campoFoneLoja;

    private final ResumoAnuncioActivity activity;

    private Anuncio produto;
    private Resources recursos;

    //private List<View> camposObrigatorios = new ArrayList<View>();

    public FormularioResumoAnuncioHelper(ResumoAnuncioActivity activity) {

        this.activity = activity;
        recursos = activity.getResources();
        //camposObrigatorios.add(campoTitulo);

        campoTitulo = (TextView) obterViewById(R.id.resumo_anuncio_titulo);
        campoDescricao = (TextView) obterViewById(R.id.resumo_anuncio_descricao);
        campoCategoria = (TextView) obterViewById(R.id.resumo_anuncio_categoria);
        campoPreco = (TextView) obterViewById(R.id.resumo_anuncio_preco);

        campoNomeLoja = (TextView) obterViewById(R.id.resumo_anuncio_nome_loja);
        campoEnderecoLoja = (TextView) obterViewById(R.id.resumo_anuncio_endereco_loja);
        campoFoneLoja = (TextView) obterViewById(R.id.resumo_anuncio_fone_loja);

        produto = new Anuncio();
    }

    private View obterViewById(int idView) {
        return this.activity.findViewById(idView);
    }

    //TODO descomentar assim que for implementado
    public Anuncio obterProduto() {

        produto.setTitulo(campoTitulo.getText().toString());
        produto.setDescricao(campoDescricao.getText().toString());

        //TODO descomentar assim que for implementado
        //produto.setCategoria(campoCategoria.getText().toString());
        //produto.setTipo(campoTipo.getText().toString());
        //produto.setPreco(Double.parseDouble(campoPreco.getText().toString()));


        return produto;
    }

    //TODO AVELINO VERIFICAR
    public void preencheFormulario(Anuncio anuncio) {

        campoTitulo.setText(anuncio.getProduto() != null ? anuncio.getProduto().getNome() : anuncio.getTitulo());
        //campoTitulo.setEnabled(false);

        campoDescricao.setText(anuncio.getDescricao());
        //campoDescricao.setEnabled(false);

        campoCategoria.setText(anuncio.getCategoria().getNome());
        //campoCategoria.setEnabled(false);

        campoPreco.setText(anuncio.getPreco().toString());
        //campoPreco.setEnabled(false);

        if (anuncio.getLoja() != null ) {
            campoNomeLoja.setText(anuncio.getLoja().getNome());

            campoEnderecoLoja.setText(anuncio.getLoja().getEndereco());

            campoFoneLoja.setText(anuncio.getLoja().getFone());
        }

        this.produto = produto;
    }

    public boolean isCamposObritagoriosPreenchidos(){


       // if (TextUtils.isEmpty("")) {
         //   campoTitulo.setError("adasda");
        //}

        return Validador.validarCamposObrigatorios(campoTitulo, recursos.getString(R.string.titulo_produto_obrigatorio))
                && Validador.validarCamposObrigatorios(campoPreco, recursos.getString(R.string.preco_produto_obrigatorio));
        //return false;
    }


}
