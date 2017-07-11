package br.com.viperfish.mpbmamaepagabarato.modelo.produto;

/**
 * Created by Av on 13/11/16.
 */
public class Produto {

    private Long id;
    private String nome;
    private Long subCategoriaId;

    public Produto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getSubCategoriaId() {
        return subCategoriaId;
    }

    public void setSubCategoriaId(Long subCategoriaId) {
        this.subCategoriaId = subCategoriaId;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
