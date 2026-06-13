package org.example.lanchonete;

import java.util.*;

/**
 * 
 */
public class ComboVeganoFactory extends ComboFactory {

	/**
	 * Default constructor
	 */
	public ComboVeganoFactory() {
	}

	/**
	 * 
	 */
	private SanduicheDecorator lanche;

	/**
	 * 
	 */
	private List<Bebida> bebidas;

	/**
	 * 
	 */
	private List<Acompanhamento> acompanhamentos;

	/**
	 * @return
	 */
	protected List<LancheDecorator> criarLanches() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	protected List<Bebida> criarBebidas() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	protected List<Acompanhamento> criarAcompanhamentos() {
		// TODO implement here
		return null;
	}

	/**
	 * 
	 */
	protected void validarCombo() {
		// TODO implement here
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

}