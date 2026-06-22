package org.example.lanchonete.entrega;

public enum TipoEntrega {

    DELIVERY (new ProvedorDeliveryFactory(), true),
    COMER_NO_LOCAL (new ProvedorLocalDelivery(), false),
    VIAGEM (new ProvedorDeliveryFactory(), false);

    TipoEntrega(ProvedorEmbalagem provedor, boolean cobraEntrega) {
        this.provedor = provedor;
        this.cobraEntrega = cobraEntrega;
    }


    public final ProvedorEmbalagem provedor;
    public final boolean cobraEntrega;

}
