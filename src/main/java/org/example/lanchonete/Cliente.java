package org.example.lanchonete;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa implements PedidoObservador {

	private PreferenciasNotificacao preferencias;

	public Cliente(String id, String nome, Cidade cidadeResidencia) {
		super(id, nome, cidadeResidencia);
	}

	public Cliente(String id, String nome, Cidade cidadeResidencia, PreferenciasNotificacao preferencias) {
		this(id, nome, cidadeResidencia);

		this.preferencias = preferencias;
	}


    public void receberNotificacao(Notificacao notificacao) {
		// TODO implement here
	}

	public PreferenciasNotificacao getPreferencias() {
		if(preferencias == null){
			return getPreferenciasPadrao();
		}
		return preferencias.clonePrototype();
	}

	private static PreferenciasNotificacao getPreferenciasPadrao() {
		List<String> canais = new ArrayList<>();

		canais.add("PEDIDO_RECEBIDO");
		canais.add("PEDIDO_SAIU_ENTREGA");
		canais.add("PEDIDO_CANCELADO");


		return new PreferenciasNotificacao(canais);

	}
}