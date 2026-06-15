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
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void adicionarLinha(ItemPedido itemPedido) {
		if (itemPedido == null) {
			throw new IllegalArgumentException("O item do pedido não pode ser nulo.");
		}
		this.linhas.add(itemPedido);
	}

	public double getValorBruto() {
		double total = 0;
		for (ItemPedido linha : linhas) {
			total += linha.getSubtotal();
		}
		return total;
	}

	//Strategy
	public double getValorTotalFinal(EstrategiaDesconto estrategia) {
		if (estrategia == null) {
			throw new IllegalArgumentException("A estratégia de desconto não pode ser nula.");
		}
		double bruto = getValorBruto();
		return estrategia.aplicar(bruto);
	}

	//Iterator
	@Override
	public Iterator<ItemPedido> iterator() {
		return linhas.iterator();
	}
}