package br.com.viperfish.mpbmamaepagabarato.modelo.anuncio;

import java.io.Serializable;
import java.util.Date;

import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Created by av on 13/11/16.
 */
public class Anuncio implements Serializable {

    private Long id;
    private String titulo;
    private String descricao;
    //private Long subCategoriaId;
    private Long produtoId;
    private Double preco;
    private Date dataAnuncio;
    private String caminhoDaImagem;

    //atributos transients
    private Categoria categoria;
    private Categoria subCategoria;
    private Produto produto;

    public Anuncio() {
    }

    public Anuncio(Long id, String titulo, String descricao, Long produtoId, Double preco, String caminhoDaImagem) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produtoId = produtoId;
        //this.fabricante_id = fabricante;
        this.preco = preco;
        this.caminhoDaImagem = caminhoDaImagem;
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

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Date getDataAnuncio() {
        return dataAnuncio;
    }

    public void setDataAnuncio(Date dataAnuncio) {
        this.dataAnuncio = dataAnuncio;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getCaminhoDaImagem() {
        return caminhoDaImagem;
    }

    public void setCaminhoDaImagem(String caminhoDaImagem) {
        this.caminhoDaImagem = caminhoDaImagem;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                //", subCategoria_id=" + subCategoria_id + '\'' +
                //", fabricante_id=" + fabricante_id + '\'' +
                ", preco=" + preco +
                '}';
    }
}
