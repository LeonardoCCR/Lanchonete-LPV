package org.example.lanchonete.gerenciamento;
import org.example.lanchonete.pedido.Pedido;
import org.example.lanchonete.usuario.Funcionario;

public interface GerenciadorPedidosMediator {
    void registrarFuncionario(Funcionario funcionario);
    void enviarParaFilaGlobal(Pedido pedido);
    void notificarConclusaoTarefa(Funcionario funcionario, Pedido pedido);
}
