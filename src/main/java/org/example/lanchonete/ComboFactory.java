package org.example.lanchonete;

import java.util.*;

/**
 * 
 */
public abstract class ComboFactory {

	/**
	 * Default constructor
	 */
	public ComboFactory() {
	}

	/**
	 * @return
	 */
	protected abstract List<LancheDecorator> criarLanches();

	/**
	 * @return
	 */
	protected abstract List<Bebida> criarBebidas();

	/**
	 * @return
	 */
	protected abstract List<Acompanhamento> criarAcompanhamentos();

	/**
	 * 
	 */
	protected abstract void validarCombo();

	/**
	 * @return
	 */
	public List<ItemPedido> gerarCombo() {
		// TODO implement here
		return null;
	}

}