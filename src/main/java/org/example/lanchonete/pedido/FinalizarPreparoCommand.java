package org.example.lanchonete.pedido;

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
