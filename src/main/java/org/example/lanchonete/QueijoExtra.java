package org.example.lanchonete;

public class QueijoExtra extends AdicionalDecorator {

    public QueijoExtra(ItemPersonalizavel item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + Queijo Extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 3.00;
    }
}