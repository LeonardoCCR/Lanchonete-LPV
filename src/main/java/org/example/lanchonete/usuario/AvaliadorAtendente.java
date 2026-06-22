package org.example.lanchonete.usuario;

public class AvaliadorAtendente extends AvaliadorDesconto {

    public AvaliadorAtendente(Funcionario funcionarioReal) {
        super(funcionarioReal);
        this.listaDescontosPermitidos.add(5.0);
    }
}