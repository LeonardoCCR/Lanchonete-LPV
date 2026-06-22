package org.example.lanchonete.pedido;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.example.lanchonete.Command;
import org.example.lanchonete.Funcionario;
import org.example.lanchonete.GerenciadorPedidosMediator;

public class CentralOperacionalLanchonete implements GerenciadorPedidosMediator {

    private final Queue<Pedido> filaPedidosGlobais = new LinkedList<>();
    private final List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void registrarFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            this.funcionarios.add(funcionario);
        }
    }

    @Override
    public void enviarParaFilaGlobal(Pedido pedido) {
        if (pedido != null) {
            filaPedidosGlobais.add(pedido);
            tentarDespacharPedidos();
        }
    }

    @Override
    public void notificarConclusaoTarefa(Funcionario funcionario, Pedido pedido) {
        if (funcionario != null && pedido != null) {

            Command finalizarCmd = new FinalizarPreparoCommand(pedido);
            finalizarCmd.executar();

            funcionario.setOcupado(false);
            tentarDespacharPedidos();
        }
    }

    private void tentarDespacharPedidos() {
        while (!filaPedidosGlobais.isEmpty()) {
            Funcionario cozinheiroLivre = encontrarCozinheiroLivre();

            if (cozinheiroLivre == null) {
                break;
            }

            Pedido proximoPedido = filaPedidosGlobais.poll();
            cozinheiroLivre.setOcupado(true);


            Command iniciarCmd = new IniciarPreparoCommand(proximoPedido);
            iniciarCmd.executar();
        }
    }

    private Funcionario encontrarCozinheiroLivre() {
        for (Funcionario f : funcionarios) {
            if ("Cozinheiro".equals(f.getCargo()) && !f.isOcupado()) {
                return f;
            }
        }
        return null;
    }
}