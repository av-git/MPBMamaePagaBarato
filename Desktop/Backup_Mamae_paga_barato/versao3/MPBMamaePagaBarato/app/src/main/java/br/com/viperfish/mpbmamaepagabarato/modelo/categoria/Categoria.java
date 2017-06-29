package br.com.viperfish.mpbmamaepagabarato.modelo.categoria;

/**
 * Created by Av on 16/11/2016.
 */

public class Categoria {

    private Long id;
    private Long idPai;
    private String nome;

    public Categoria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPai() {
        return idPai;
    }

    public void setIdPai(Long idPai) {
        this.idPai = idPai;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", idPai=" + idPai +
                ", nome='" + nome + '\'' +
                '}';
    }
}
