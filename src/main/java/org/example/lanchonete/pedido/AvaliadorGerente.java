package org.example.lanchonete.pedido;

import org.example.lanchonete.Funcionario;

public class AvaliadorGerente extends AvaliadorDesconto {

    public AvaliadorGerente(Funcionario funcionarioReal) {
        super(funcionarioReal);
        // Gerentes pode assinar descontos de 15% e 20%
        this.listaDescontosPermitidos.add(15.0);
        this.listaDescontosPermitidos.add(20.0);
    }
}