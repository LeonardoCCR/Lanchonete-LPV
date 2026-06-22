package org.example.lanchonete.notificacao;

public interface PedidoObservador {


	void receberNotificacao(Notificacao notificacao);

	PreferenciasNotificacao getPreferencias();

}