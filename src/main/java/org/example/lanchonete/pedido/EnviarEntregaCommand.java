package org.example.lanchonete.pedido;

import org.example.lanchonete.Command;

public class EnviarEntregaCommand implements Command {

    private final Pedido pedido;

    public EnviarEntregaCommand(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void executar() {
        pedido.enviarEntrega();
    }
}