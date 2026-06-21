package org.example.lanchonete.pedido;

import org.example.lanchonete.Command;

public class ConfirmarEntregaCommand implements Command {

    private final Pedido pedido;

    public ConfirmarEntregaCommand(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void executar() {
        pedido.confirmarEntrega();
    }
}
