package org.example.lanchonete;

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
