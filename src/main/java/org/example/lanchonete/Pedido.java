package org.example.lanchonete;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Pedido implements Iterable<ItemPedido> {


	private final Cliente cliente;
	private final List<ItemPedido> linhas = new ArrayList<>();


	public Pedido(Cliente cliente) {
		if (cliente == null) {
			throw new IllegalArgumentException("Todo pedido precisa ter um cliente associado.");
		}
		this.cliente = cliente;
        this.estadoAtual = new EstadoRecebido();
        this.codigo = ContadorPedido.get().gerarCodigo();

	}

	private EstadoPedido estadoAtual;
	private final String codigo;



	public Cliente getCliente() {
		return cliente;
	}


	public void setEstado(EstadoPedido estado){
		estadoAtual = estado;
	}
	public void adicionarLinha(ItemPedido itemPedido) {
		if (itemPedido == null) {
			throw new IllegalArgumentException("O item do pedido não pode ser nulo.");
		}
		this.linhas.add(itemPedido);
	}


	public void notificar(String notificacao) {
		// TODO implement here
    }
	public double getValorBruto() {
		double total = 0;
		for (ItemPedido linha : linhas) {
			total += linha.getSubtotal();
		}
		return total;
	}

	public void cancelar(){
		estadoAtual.cancelar(this);
	}

	public void iniciarPreparo(){
		estadoAtual.iniciarPreparo(this);
	}

	public void finalizarPreparo(){
		estadoAtual.finalizarPreparo(this);
	}

	public void enviarEntrega(){
		estadoAtual.enviarEntrega(this);
	}

	public void confirmarEntrega(){
		estadoAtual.confirmarEntrega(this);
	}


	public void addObserver(PedidoObserver observer) {
		// TODO implement here
    }
	//Strategy
	public double getValorTotalFinal(EstrategiaDesconto estrategia) {
		if (estrategia == null) {
			throw new IllegalArgumentException("A estratégia de desconto não pode ser nula.");
		}
		double bruto = getValorBruto();
		return estrategia.aplicar(bruto);
	}

	@Override
	public String toString(){
		return codigo;
	}

	//Iterator
	@Override
	public Iterator<ItemPedido> iterator() {
		return linhas.iterator();
	}
}