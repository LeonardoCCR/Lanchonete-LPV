package org.example.lanchonete.pedido;

import org.example.lanchonete.Command;

public class FinalizarPreparoCommand implements Command {

    private final Pedido pedido;

    public FinalizarPreparoCommand(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void executar() {
        pedido.finalizarPreparo();
    }
}
