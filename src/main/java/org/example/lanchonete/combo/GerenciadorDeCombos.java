package org.example.lanchonete.combo;

import org.example.lanchonete.ItemPedido;
import org.example.lanchonete.ItemPedidoBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class GerenciadorDeCombos {

    private Map<String, EstruturaCombo> receitas = new HashMap<>();

    private Map<String, EstruturaCombo> registrarReceitasPadrao() {

        Map<String, EstruturaCombo> receitasPadrao = new HashMap<>();

        receitasPadrao.put("DUPLO", perfil -> {
            List<ItemPedido> itens = new ArrayList<>();
            itens.add(new ItemPedidoBuilder(perfil.criarSanduichePrincipal()).comQuantidade(2).build());
            itens.add(new ItemPedidoBuilder(perfil.criarBebidaMedia()).comQuantidade(2).build());
            itens.add(new ItemPedidoBuilder(perfil.criarAcompanhamento()).comQuantidade(1).build());
            return itens;
        });


        receitasPadrao.put("FAMILIA", perfil -> {
            List<ItemPedido> itens = new ArrayList<>();
            itens.add(new ItemPedidoBuilder(perfil.criarSanduichePrincipal()).comQuantidade(4).build());
            itens.add(new ItemPedidoBuilder(perfil.criarBebidaMedia()).comQuantidade(4).build());
            itens.add(new ItemPedidoBuilder(perfil.criarAcompanhamento()).comQuantidade(4).build());
            return itens;
        });

        return receitasPadrao;
    }


    public void adicionarNovaReceita(String nomeCombo, EstruturaCombo receita) {
        receitas.put(nomeCombo, receita);
    }

    public void adicionarNovasReceitas(Map<String, EstruturaCombo> novasReceitas) {
        receitas.putAll(novasReceitas);
    }


    public List<ItemPedido> gerarCombo(String nomeCombo, PerfilComboFactory perfilEscolhido) {
        EstruturaCombo receita = receitas.get(nomeCombo);
        if (receita == null) {
            throw new IllegalArgumentException("Combo não encontrado no cardápio: " + nomeCombo);
        }
        return receita.montar(perfilEscolhido);
    }
}
