package org.example.lanchonete;

import java.time.LocalDate;


public class ContadorPedido {

	private int contagemPedidos;
	private LocalDate dataAtual;


	private static final ContadorPedido instance = new ContadorPedido();

	public ContadorPedido() {
		contagemPedidos = 0;
		dataAtual = LocalDate.now();
	}

	public String gerarCodigo() {

		if(!dataAtual.equals(LocalDate.now())){
			dataAtual = LocalDate.now();
			contagemPedidos = 0;
		}

		return dataAtual.toString() + "-" + (++contagemPedidos);
	}

	public static ContadorPedido get(){
		return instance;
	}

}