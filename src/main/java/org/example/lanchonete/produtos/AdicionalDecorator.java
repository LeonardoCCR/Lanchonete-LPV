package org.example.lanchonete.produtos;

import org.example.lanchonete.relatorio.RelatorioVisitor;

public abstract class AdicionalDecorator implements ItemPersonalizavel {

	protected ItemPersonalizavel item;

	public AdicionalDecorator(ItemPersonalizavel item) {
		this.item = item;
	}

	@Override
	public void accept(RelatorioVisitor visitor) {
		visitor.visit(this);
	}
}
