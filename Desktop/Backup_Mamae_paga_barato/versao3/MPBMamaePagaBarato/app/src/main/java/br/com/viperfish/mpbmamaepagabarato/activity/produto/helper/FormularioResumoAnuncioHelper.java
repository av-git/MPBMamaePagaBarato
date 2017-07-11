package br.com.viperfish.mpbmamaepagabarato.activity.produto.helper;

import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.FormularioProdutoActivity;
import br.com.viperfish.mpbmamaepagabarato.activity.produto.formularios.ResumoAnuncioActivity;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;
import util.Validador;

/**
 * Created by ddark on 13/11/16.
 */

public class FormularioResumoAnuncioHelper {

    private final EditText campoTitulo;
    private final EditText campoDescricao;
    private final EditText campoCategoria;
    private final EditText campoPreco;
    private final ResumoAnuncioActivity activity;

    private Produto produto;
    private Resources recursos;

    //private List<View> camposObrigatorios = new ArrayList<View>();

    public FormularioResumoAnuncioHelper(ResumoAnuncioActivity activity) {

        this.activity = activity;
        recursos = activity.getResources();
        //camposObrigatorios.add(campoTitulo);

        campoTitulo = (EditText) obterViewById(R.id.resumo_anuncio_titulo);
        campoDescricao = (EditText) obterViewById(R.id.resumo_anuncio_descricao);
        campoCategoria = (EditText) obterViewById(R.id.resumo_anuncio_categoria);
        //campoTipo = (EditText) obterViewById(R.id.formulario_produto_tipo);
        campoPreco = (EditText) obterViewById(R.id.resumo_anuncio_preco);

        produto = new Produto();
    }

    private View obterViewById(int idView) {
        return this.activity.findViewById(idView);
    }

    //TODO descomentar assim que for implementado
    public Produto obterProduto() {

        produto.setTitulo(campoTitulo.getText().toString());
        produto.setDescricao(campoDescricao.getText().toString());

        //TODO descomentar assim que for implementado
        //produto.setCategoria(campoCategoria.getText().toString());
        //produto.setTipo(campoTipo.getText().toString());
        //produto.setPreco(Double.parseDouble(campoPreco.getText().toString()));


        return produto;
    }

    public void preencheFormulario(Produto produto) {

        campoTitulo.setText(produto.getTitulo());
        campoDescricao.setText(produto.getDescricao());
        campoCategoria.setText(produto.getCategoria().toString());
        campoPreco.setText(produto.getPreco().toString());

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
