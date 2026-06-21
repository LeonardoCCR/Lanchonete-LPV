package org.example.lanchonete;

public interface PedidoObservador {


	void receberNotificacao(Notificacao notificacao);

	PreferenciasNotificacao getPreferencias();

}