package org.example.lanchonete;

//builder

public class ItemPedidoBuilder {
    private ItemConsumivel itemAtual;
    private int quantidade = 1;

    // Construtor obriga a passagem de item consumível como base
    public ItemPedidoBuilder(ItemConsumivel itemBase) {
        if (itemBase == null) {
            throw new IllegalArgumentException("O item base não pode ser nulo.");
        }
        this.itemAtual = itemBase;
    }

    public ItemPedidoBuilder comQueijoExtra() {
        if (this.itemAtual instanceof ItemPersonalizavel) {
            this.itemAtual = new QueijoExtra((ItemPersonalizavel) this.itemAtual);
        }
        return this;
    }

    public ItemPedidoBuilder comBaconExtra() {
        if (this.itemAtual instanceof ItemPersonalizavel) {
            this.itemAtual = new BaconExtra((ItemPersonalizavel) this.itemAtual);
        }
        return this;
    }

    public ItemPedidoBuilder comQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    //Fabrica e tretorna o ItemPedido
    public ItemPedido build() {
        return new ItemPedido(this.itemAtual, this.quantidade);
    }
}