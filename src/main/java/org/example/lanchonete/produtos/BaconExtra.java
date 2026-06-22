package org.example.lanchonete.produtos;

public class BaconExtra extends AdicionalDecorator {

    public BaconExtra(ItemPersonalizavel item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + Bacon Extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 4;
    }
}