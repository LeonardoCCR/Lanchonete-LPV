package org.example.lanchonete;

public abstract class AdicionalDecorator implements ItemPersonalizavel {

	protected ItemPersonalizavel item;

	public AdicionalDecorator(ItemPersonalizavel item) {
		this.item = item;
	}
}
