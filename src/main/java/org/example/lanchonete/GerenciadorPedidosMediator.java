package org.example.lanchonete;
import org.example.lanchonete.pedido.Pedido;

public interface GerenciadorPedidosMediator {
    void registrarFuncionario(Funcionario funcionario);
    void enviarParaFilaGlobal(Pedido pedido);
    void notificarConclusaoTarefa(Funcionario funcionario, Pedido pedido);
}
