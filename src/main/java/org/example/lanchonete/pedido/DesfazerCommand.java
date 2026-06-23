package org.example.lanchonete.pedido;

public class DesfazerCommand implements Command {
    private CentralComandos central;

    public DesfazerCommand(CentralComandos central) {
        this.central = central;
    }

    @Override
    public void executar() {
        central.desfazer();
    }
}