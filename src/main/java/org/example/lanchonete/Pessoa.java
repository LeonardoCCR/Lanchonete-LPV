package org.example.lanchonete;

public abstract class Pessoa {

    private String id;
    private String nome;
    private Cidade cidadeResidencia; // Associação ao Flyweight

    protected Pessoa(String id, String nome, Cidade cidadeResidencia) {
        this.id = id;
        this.nome = nome;
        this.cidadeResidencia = cidadeResidencia;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Cidade getCidadeResidencia() {
        return cidadeResidencia;
    }


    public String obterPessoa() {
        return "Pessoa{" +
                "id='" + this.id + '\'' +
                ", nome='" + this.nome + '\'' +
                ", cidade='" + cidadeResidencia.getNome() + '\'' +
                ", uf='" + cidadeResidencia.getUf() + '\'' +
                '}';
    }
}