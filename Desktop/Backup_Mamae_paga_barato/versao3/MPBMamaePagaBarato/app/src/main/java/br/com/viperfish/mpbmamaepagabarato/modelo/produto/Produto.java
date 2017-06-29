package br.com.viperfish.mpbmamaepagabarato.modelo.produto;

import java.math.BigDecimal;

import br.com.viperfish.mpbmamaepagabarato.activity.produto.FormularioProdutoActivity;

/**
 * Created by ddark on 13/11/16.
 */
public class Produto {

    private Long id;
    private String titulo;
    private String descricao;
    private Long categoria_id;
    private Long fabricante_id;
    private Double preco;

    public Produto() {

    }

    /** Remover o id quando o cadastro estiver concluido */
    public Produto(Long aLong, String s, String s1, String leite, String novo, Double aDouble) {
        this.id = aLong;
        this.titulo = s;
        this.descricao = s1;
    }

    @Deprecated
    public Produto(Long id, String titulo, String descricao, Long categoria, Long fabricante) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria_id = categoria;
        this.fabricante_id = fabricante;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Long getFabricante_id() {
        return fabricante_id;
    }

    public void setFabricante_id(Long fabricante_id) {
        this.fabricante_id = fabricante_id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria_id=" + categoria_id + '\'' +
                ", fabricante_id=" + fabricante_id + '\'' +
                ", preco=" + preco +
                '}';
    }
}
