package org.example.lanchonete;

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
