package org.example.lanchonete.pedido;

import org.example.lanchonete.Funcionario;

public class AvaliadorGerente extends AvaliadorDesconto {

    public AvaliadorGerente(Funcionario funcionarioReal) {
        super(funcionarioReal);
        this.listaDescontosPermitidos.add(15.0);
        this.listaDescontosPermitidos.add(20.0);
    }
}