package org.example.lanchonete.produtos;

public abstract class AdicionalDecorator implements ItemPersonalizavel {

	protected ItemPersonalizavel item;

	public AdicionalDecorator(ItemPersonalizavel item) {
		this.item = item;
	}
}
