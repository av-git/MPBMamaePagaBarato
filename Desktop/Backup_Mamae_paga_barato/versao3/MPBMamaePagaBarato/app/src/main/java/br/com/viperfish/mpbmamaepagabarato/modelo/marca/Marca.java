package br.com.viperfish.mpbmamaepagabarato.modelo.marca;

import java.io.Serializable;

/**
 * Created by ddark on 20/07/17.
 */

public class Marca implements Serializable {

    private Long id;
    private String nome;

    public Marca() {
    }

    public Marca(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public boolean isTipoOutros() {
        return getNome().equalsIgnoreCase("outros");
    }
}