package org.example.lanchonete.combo;
import org.example.lanchonete.produtos.ItemConsumivel;
import org.example.lanchonete.relatorio.RelatorioVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComboComposite implements ItemConsumivel {
    private String nomeCombo;
    private final List<ItemConsumivel> itens = new ArrayList<>();
    private double descontoPercentual;

    public ComboComposite(String nomeCombo, double descontoPercentual) {
        this.nomeCombo = nomeCombo;
        this.descontoPercentual = descontoPercentual;
    }


    public void adicionarItem(ItemConsumivel item) {
        this.itens.add(item);
    }

    public void removerItem(ItemConsumivel item) {
        this.itens.remove(item);
    }


    @Override
    public String getDescricao() {
        String descricaoDosItens = itens.stream()
                .map(ItemConsumivel::getDescricao)
                .collect(Collectors.joining(" + "));

        return String.format("%s (%s)", nomeCombo, descricaoDosItens);
    }


    @Override
    public double getPreco() {
        double precoSoma = itens.stream()
                .mapToDouble(ItemConsumivel::getPreco)
                .sum();

        return precoSoma - (precoSoma * descontoPercentual);
    }

    @Override
    public void accept(RelatorioVisitor visitor) {
        visitor.visit(this);
    }

    public List<ItemConsumivel> getItens() {
        return itens;
    }
}