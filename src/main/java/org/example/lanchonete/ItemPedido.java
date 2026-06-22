package org.example.lanchonete;

import org.example.lanchonete.entrega.Embalagem;
import org.example.lanchonete.entrega.TipoEntrega;

public class ItemPedido {

	private final ItemConsumivel item;
	private final int quantidade;
	private final Embalagem embalagem;

	ItemPedido(ItemConsumivel item, int quantidade, TipoEntrega tipoEntrega) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
		}
		this.item = item;
		this.quantidade = quantidade;
		this.embalagem = tipoEntrega.provedor.gerarEmbalagem(item);
	}

	public double getSubtotal() {
		return (item.getPreco() + embalagem.preco()) * quantidade;
	}

	public ItemConsumivel getItem() {
		return item;
	}

	public int getQuantidade() {
		return quantidade;
	}

	@Override
	public String toString() {
		return String.format("%s (x%d) - Subtotal: R$ %.2f",
				item.getDescricao(), quantidade, getSubtotal());
	}

}