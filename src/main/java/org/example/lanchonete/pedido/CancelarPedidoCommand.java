package org.example.lanchonete.pedido;

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
