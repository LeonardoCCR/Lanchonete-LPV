package org.example.lanchonete.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CentralComandos {

    private final Stack<PedidoMemento> historicoMementos = new Stack<>();
    private final Stack<Pedido> historicoPedidos = new Stack<>();

    private final List<Command> historicoComandos = new ArrayList<>();

    public void executar(Command comando) {
        historicoComandos.add(comando);
        comando.executar();
    }

    public void executar(Command comando, Pedido pedido) {
        historicoMementos.push(pedido.salvar());
        historicoPedidos.push(pedido);
        historicoComandos.add(comando);
        comando.executar();
    }

    public void desfazer() {
        if (!historicoMementos.isEmpty() && !historicoPedidos.isEmpty()) {
            PedidoMemento memento = historicoMementos.pop();
            Pedido pedido = historicoPedidos.pop();
            pedido.restaurar(memento);

            if (!historicoComandos.isEmpty()) historicoComandos.remove(historicoComandos.size() - 1);
        }
    }

    public List<Command> getHistorico() {
        return historicoComandos;
    }

}