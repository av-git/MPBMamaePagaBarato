package br.com.viperfish.mpbmamaepagabarato.modelo.categoria;

import java.io.Serializable;

/**
 * Created by Av on 16/11/2016.
 */

public class Categoria implements Serializable {

    private Long id;
    private Long idPai;
    private String nome;

    public Categoria() {
    }

    public Categoria(Long id, Long idPai, String nome) {
        this.id = id;
        this.idPai = idPai;
        this.nome = nome;
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
