package org.example.lanchonete;

public class ItemPedido {

	private final ItemConsumivel item;
	private final int quantidade;

	ItemPedido(ItemConsumivel item, int quantidade) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
		}
		this.item = item;
		this.quantidade = quantidade;
	}

	public double getSubtotal() {
		return item.getPreco() * quantidade;
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