package org.example.lanchonete;


import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private EstadoPedido estadoAtual;
	private final String codigo;
	private List<ItemPedido> itens;


	public Pedido() {
		this.estadoAtual = new EstadoRecebido();
		this.codigo = ContadorPedido.get().gerarCodigo();
		itens = new ArrayList<>();
	}


	public void setEstado(EstadoPedido estado){
		estadoAtual = estado;
	}


	public void notificar(String notificacao) {
		// TODO implement here
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

	@Override
	public String toString(){
		return codigo;
	}

}