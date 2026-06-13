package org.example.lanchonete;

public class BaconExtra extends AdicionalDecorator {

    public BaconExtra(ItemPersonalizavel item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + Bacon";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 4;
    }
}