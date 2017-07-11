package br.com.viperfish.mpbmamaepagabarato.modelo.produto;

import java.io.Serializable;

import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;

/**
 * Created by av on 13/11/16.
 */
public class Produto implements Serializable {

    private Long id;
    private String titulo;
    private String descricao;
    private Categoria categoria;
    private Categoria subCategoria;
    private Long categoria_id;
    private Long fabricante_id;
    private Double preco;

    public Produto() {
    }

    public Produto(Long id, String titulo, String descricao, Long categoria, Long fabricante, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria_id = categoria;
        this.fabricante_id = fabricante;
        this.preco = preco;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(Categoria subCategoria) {
        this.subCategoria = subCategoria;
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
