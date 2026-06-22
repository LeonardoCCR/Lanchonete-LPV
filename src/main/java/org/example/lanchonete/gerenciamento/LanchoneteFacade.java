package org.example.lanchonete.gerenciamento;
import org.example.lanchonete.pagamento.*;
import org.example.lanchonete.pedido.*;
import org.example.lanchonete.usuario.Cidade;
import org.example.lanchonete.usuario.CidadeFactory;
import org.example.lanchonete.usuario.Cliente;
import org.example.lanchonete.usuario.PessoaRepository;

public class LanchoneteFacade {

    private final PessoaRepository<Cliente> clienteRepository;
    private final CentralComandos centralComandos;
    private final ProcessadorPagamento processadorConfigurado;

    public LanchoneteFacade() {
        this.clienteRepository = new PessoaRepository<>();
        this.centralComandos = new CentralComandos();

        GatewayPagamentoExterno gateway = new GatewayPagamentoExterno();
        ProcessadorPagamento adaptador = new PagamentoAdapter(gateway, "TOKEN_VALIDO");
        this.processadorConfigurado = new ProcessadorPagamentoProxy(adaptador);
    }

    public ProcessadorPagamento getProcessadorConfigurado() {
        return this.processadorConfigurado;
    }

    public Cliente cadastrarCliente(String id, String nome, String nomeCidade, String uf) {
        Cidade cidade = CidadeFactory.getCidade(nomeCidade, uf);
        Cliente cliente = new Cliente(id, nome, cidade);
        clienteRepository.cadastrar(cliente);
        return cliente;
    }

    public void processarFluxoPedido(Pedido pedido, Pagamento formaPagamento, EstrategiaDesconto desconto) {
        formaPagamento.efetuarPagamento(pedido, desconto);
        if (formaPagamento.isConfirmado()) {
            centralComandos.executar(new IniciarPreparoCommand(pedido));
            centralComandos.executar(new FinalizarPreparoCommand(pedido));
        } else {
            centralComandos.executar(new CancelarPedidoCommand(pedido));
        }
    }
}