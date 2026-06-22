package org.example.lanchonete.pedido;

public class IniciarPreparoCommand implements Command {

    private final Pedido pedido;

    public IniciarPreparoCommand(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void executar() {
        pedido.iniciarPreparo();
    }
}
