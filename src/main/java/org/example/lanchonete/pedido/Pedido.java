package org.example.lanchonete.pedido;


import org.example.lanchonete.*;
import org.example.lanchonete.entrega.TipoEntrega;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class Pedido implements Iterable<ItemPedido> {


	private final String codigo;
	private final Cliente cliente;
	private final List<ItemPedido> linhas;
	private EstadoPedido estadoAtual;
	private final PedidoObservadorHandler notificacaoHandler;
	private final TipoEntrega tipoEntrega;


	public Pedido(Cliente cliente, TipoEntrega tipoEntrega) {
        if (cliente == null) {
			throw new IllegalArgumentException("Todo pedido precisa ter um cliente associado.");
		}
		this.tipoEntrega = tipoEntrega;
		this.cliente = cliente;
        this.estadoAtual = new EstadoRecebido();
        this.codigo = ContadorPedido.get().gerarCodigo();
		this.linhas = new ArrayList<>();
		this.notificacaoHandler = new PedidoObservadorHandler();
		notificacaoHandler.aplicarPreferencias(cliente);

	}


	public void adicionarLinha(ItemPedido itemPedido) {
		if (itemPedido == null) {
			throw new IllegalArgumentException("O item do pedido não pode ser nulo.");
		}
		this.linhas.add(itemPedido);
	}


	public void notificar(Notificacao notificacao) {
        notificacaoHandler.emitirNotificacao(notificacao);
    }

    public double getValorBruto() {
		double total = 0;
		for (ItemPedido linha : linhas) {
			total += linha.getSubtotal();
		}
		return total;
	}

	void cancelar(){
		estadoAtual.cancelar(this);
	}

	void iniciarPreparo(){
		estadoAtual.iniciarPreparo(this);
	}

	void finalizarPreparo(){
		estadoAtual.finalizarPreparo(this);
	}

	void enviarEntrega(){
		estadoAtual.enviarEntrega(this);
	}

	void confirmarEntrega(){
		estadoAtual.confirmarEntrega(this);
	}

	//Observer
	public void addObserver(String canal, PedidoObservador observer) {
        notificacaoHandler.adicionarObservador(canal, observer);
    }

	//Strategy
	public double getValorTotalFinal(EstrategiaDesconto estrategia) {
		if (estrategia == null) {
			throw new IllegalArgumentException("A estratégia de desconto não pode ser nula.");
		}
		double bruto = getValorBruto();

		return estrategia.aplicar(bruto) + calcularFrete();
	}

	public void aplicarEmbalagens(){
		for(ItemPedido item: linhas){
			item.aplicarEmbalagem(tipoEntrega);
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	void setEstado(EstadoPedido estado){
		estadoAtual = estado;
	}

	@Override
	public String toString(){
		return codigo;
	}

	public boolean pagar(ProcessadorPagamento mecanismoPagamento, EstrategiaDesconto estrategia) {
		if (mecanismoPagamento == null) {
			throw new IllegalArgumentException("Mecanismo de pagamento inválido.");
		}

		double valorFinal = this.getValorTotalFinal(estrategia);

		return mecanismoPagamento.processar(valorFinal);
	}

	private double calcularFrete(){
		if(tipoEntrega.cobraEntrega){
			return 5;
		}
		return 0;
	}

	//Iterator
	@Override
	public Iterator<ItemPedido> iterator() {
		return linhas.iterator();
	}

	public Object getEstadoAtual() {
		return this.estadoAtual.clonar();
	}
}