package org.example.lanchonete;

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
