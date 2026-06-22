package org.example.lanchonete;

//builder

public class ItemPedidoBuilder {
    private ItemConsumivel itemAtual;
    private int quantidade = 1;

    public ItemPedidoBuilder(ItemConsumivel itemBase) {
        if (itemBase == null) {
            throw new IllegalArgumentException("O item base não pode ser nulo.");
        }
        this.itemAtual = itemBase;
    }

    public ItemPedidoBuilder comQueijoExtra() {

        try{
            ItemPersonalizavel itemCastado = (ItemPersonalizavel) itemAtual;
            this.itemAtual = new QueijoExtra(itemCastado);
        }
        catch(ClassCastException ignored){ }
        return this;

    }

    public ItemPedidoBuilder comBaconExtra() {
        try{
            ItemPersonalizavel itemCastado = (ItemPersonalizavel) itemAtual;
            this.itemAtual = new BaconExtra(itemCastado);
        }
        catch(ClassCastException ignored){ }
        return this;
        
    }

    public ItemPedidoBuilder comQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ItemPedido build() {
        return new ItemPedido(this.itemAtual, this.quantidade);
    }
}