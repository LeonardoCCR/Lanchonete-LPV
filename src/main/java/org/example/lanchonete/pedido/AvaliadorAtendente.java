package org.example.lanchonete.pedido;

import org.example.lanchonete.Funcionario;

public class AvaliadorAtendente extends AvaliadorDesconto {

    public AvaliadorAtendente(Funcionario funcionarioReal) {
        super(funcionarioReal);
        this.listaDescontosPermitidos.add(5.0);
    }
}