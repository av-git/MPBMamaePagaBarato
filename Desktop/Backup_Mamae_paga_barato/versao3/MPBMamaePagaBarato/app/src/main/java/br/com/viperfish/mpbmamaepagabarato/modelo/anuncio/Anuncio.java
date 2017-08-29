package br.com.viperfish.mpbmamaepagabarato.modelo.anuncio;

import com.google.android.gms.location.places.Place;

import java.io.Serializable;
import java.util.Date;

import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.marca.Marca;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Created by av on 13/11/16.
 */
public class Anuncio implements Serializable {

    private Long id;
    private Long produtoId;
    private String titulo;
    private String descricao;
    private Date dataAnuncio;
    private Double preco;

    //Campos Transients
    private Produto produto;
    private Categoria categoria;
    private Categoria subCategoria;
    private Marca marca;
    private Loja loja;

    public Anuncio(long id, long codigoProduto, String titulo, String descricao, long dataAnuncio, Double preco) {

        this.id = id;
        this.produtoId = codigoProduto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataAnuncio = new Date(dataAnuncio);
        this.preco = preco;

    }

    public Anuncio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
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

    public Date getDataAnuncio() {
        return dataAnuncio;
    }

    public void setDataAnuncio(Date dataAnuncio) {
        this.dataAnuncio = dataAnuncio;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", produtoId=" + produtoId +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataAnuncio=" + dataAnuncio +
                ", preco=" + preco +
                '}';
    }


}
