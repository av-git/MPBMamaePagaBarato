package br.com.viperfish.mpbmamaepagabarato.modelo.anuncio;

import java.io.Serializable;
import java.util.Date;

import br.com.viperfish.mpbmamaepagabarato.modelo.categoria.Categoria;
import br.com.viperfish.mpbmamaepagabarato.modelo.loja.Loja;
import br.com.viperfish.mpbmamaepagabarato.modelo.marca.Marca;
import br.com.viperfish.mpbmamaepagabarato.modelo.produto.Produto;

/**
 * Created by av on 13/11/16.
 */
public class Anuncio implements Serializable {

    private Long id;
    //private Long produtoId;
    private String nomeProdutoInformadorPeloUsuario;
    private String comentario;
    private Date dataAnuncio;
    private Double preco;

    //Campos Transients
    private Produto produto;
    private Categoria categoria;
    private Categoria subCategoria;
    private Marca marca;
    private Loja loja;

    public Anuncio(long id, long codigoProduto, String nomeProduto, String comentario, long dataAnuncio, Double preco) {
        this.id = id;
        this.nomeProdutoInformadorPeloUsuario = nomeProduto;
        this.comentario = comentario;
        this.preco = preco;

    }

    public Anuncio() {
    }

    /**
     * Verifica se o existe algum produto selecionado da lista de sugestoes de produtos
     * @return boolean
     */
    public boolean isProdutoSelecionadoPreviamente() {
        return getProduto() != null && getProduto().getId() != null ? true : false;
    }

    public boolean isInformouLojaDoAnuncio() {
        return getLoja() != null && !getLoja().getIdGoogle().isEmpty() ? true : false;
    }

    public String getNomeProdutoInformadorPeloUsuario() {
        return nomeProdutoInformadorPeloUsuario;
    }

    public void setNomeProdutoInformadorPeloUsuario(String nomeProdutoInformadorPeloUsuario) {
        this.nomeProdutoInformadorPeloUsuario = nomeProdutoInformadorPeloUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
}
