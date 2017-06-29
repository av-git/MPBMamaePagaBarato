package br.com.viperfish.mpbmamaepagabarato.modelo.fabricante;

/**
 * Created by Av on 13/11/16.
 */
public class Fabricante {

    private Long id;
    private String nome;

    public Fabricante(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Fabricante() {
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
        return "Fabricante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
