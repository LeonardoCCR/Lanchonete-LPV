package org.example.lanchonete.usuario;


public class Funcionario extends Pessoa {

    private final String cargo;
    private boolean ocupado;
    
    public Funcionario(String id, String nome, Cidade cidadeResidencia, String cargo) {
        super(id, nome, cidadeResidencia);
        this.cargo = cargo;
        this.ocupado = false;
    }

    public String getCargo() {
        return cargo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public String toString() {
        return cargo + " " + getNome() + " (ID: " + getId() + ")";
    }
}
