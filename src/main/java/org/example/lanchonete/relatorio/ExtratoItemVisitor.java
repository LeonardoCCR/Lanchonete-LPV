package org.example.lanchonete.relatorio;

import org.example.lanchonete.combo.ComboComposite;
import org.example.lanchonete.produtos.Acompanhamento;
import org.example.lanchonete.produtos.AdicionalDecorator;
import org.example.lanchonete.produtos.Bebida;
import org.example.lanchonete.produtos.ItemConsumivel;
import org.example.lanchonete.produtos.Salgado;
import org.example.lanchonete.produtos.Sanduiche;

public class ExtratoItemVisitor implements RelatorioVisitor {

    private final StringBuilder extratoBuilder = new StringBuilder();

    @Override
    public void visit(Bebida bebida) {
        double imposto = bebida.getPreco() * 0.15;
        double lucro = bebida.getPreco() - imposto;
        extratoBuilder.append(String.format("Bebida: %s | Preço: R$ %.2f | ICMS (15%%): R$ %.2f | Lucro Líquido: R$ %.2f\n",
                bebida.getDescricao(), bebida.getPreco(), imposto, lucro));
    }

    @Override
    public void visit(Sanduiche sanduiche) {
        double custoInsumos = sanduiche.getPreco() * 0.40;
        double lucro = sanduiche.getPreco() - custoInsumos;
        extratoBuilder.append(String.format("Lanche Padrao: %s | Preço: R$ %.2f | Insumos (40%%): R$ %.2f | Lucro Bruto: R$ %.2f\n",
                sanduiche.getDescricao(), sanduiche.getPreco(), custoInsumos, lucro));
    }

    @Override
    public void visit(Salgado salgado) {
        double custoFritura = salgado.getPreco() * 0.10;
        double lucro = salgado.getPreco() - custoFritura;
        extratoBuilder.append(String.format("Salgado: %s | Preço: R$ %.2f | Op. Fritura (10%%): R$ %.2f | Lucro Bruto: R$ %.2f\n",
                salgado.getDescricao(), salgado.getPreco(), custoFritura, lucro));
    }

    @Override
    public void visit(AdicionalDecorator decorado) {
        double custoInsumosBase = decorado.getPreco() * 0.45;
        double lucro = decorado.getPreco() - custoInsumosBase;
        extratoBuilder.append(String.format("Lanche Customizado: %s | Preço: R$ %.2f | Insumos (45%%): R$ %.2f | Lucro Bruto: R$ %.2f\n",
                decorado.getDescricao(), decorado.getPreco(), custoInsumosBase, lucro));
    }

    @Override
    public void visit(Acompanhamento acompanhamento) {
        double custoEmbalagem = acompanhamento.getPreco() * 0.05;
        double lucro = acompanhamento.getPreco() - custoEmbalagem;
        extratoBuilder.append(String.format("Acompanhamento: %s | Preço: R$ %.2f | Custo Embalagem (5%%): R$ %.2f | Lucro Bruto: R$ %.2f\n",
                acompanhamento.getDescricao(), acompanhamento.getPreco(), custoEmbalagem, lucro));
    }

    @Override
    public void visit(ComboComposite combo) {
        extratoBuilder.append(String.format("Combo: %s\n", combo.getDescricao()));
        for (ItemConsumivel item : combo.getItens()) {
            item.accept(this);
        }
    }

    public String getExtrato() {
        return extratoBuilder.toString();
    }
}