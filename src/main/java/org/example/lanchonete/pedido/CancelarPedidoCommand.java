package org.example.lanchonete.pedido;

import org.example.lanchonete.Command;

public class CancelarPedidoCommand implements Command {

    private final Pedido pedido;

    public CancelarPedidoCommand(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void executar() {
        pedido.cancelar();
    }
}
