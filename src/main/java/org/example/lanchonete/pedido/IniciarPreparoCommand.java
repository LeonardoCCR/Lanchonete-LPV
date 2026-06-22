package org.example.lanchonete.pedido;

import org.example.lanchonete.Command;

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
