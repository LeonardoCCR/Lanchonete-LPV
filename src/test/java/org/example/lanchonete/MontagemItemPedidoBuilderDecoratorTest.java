package org.example.lanchonete;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Decorator e Builder
public class MontagemItemPedidoBuilderDecoratorTest {
    @Test
    public void deveMontarSanduicheComDecoratorsEComQuantidadeViaBuilder() {
        ItemConsumivel sanduicheBase = new Sanduiche("X-Burguer", 15.00);

        ItemPedido itemPedido = new ItemPedidoBuilder(sanduicheBase)
                .comQueijoExtra()
                .comBaconExtra()
                .comQuantidade(2)
                .build();


        double precoBacon = new BaconExtra(new Sanduiche("", 0)).getPreco();
        double precoUnitarioEsperado = 15.00 + 3.00 + precoBacon;
        double subtotalEsperado = precoUnitarioEsperado * 2;

        assertEquals(2, itemPedido.getQuantidade());
        assertEquals(subtotalEsperado, itemPedido.getSubtotal(), 0.001);
        assertTrue(itemPedido.getItem().getDescricao().contains("Queijo Extra"));
        assertTrue(itemPedido.getItem().getDescricao().contains("Bacon Extra"));
    }

    @Test
    public void naoDeveDecorarSalgadoComAdicionais() {
        ItemConsumivel coxinha = new Salgado("Coxinha", 6.00);

        ItemPedido itemPedido = new ItemPedidoBuilder(coxinha)
                .comQueijoExtra()
                .comBaconExtra()
                .comQuantidade(1)
                .build();

        assertEquals(6.00, itemPedido.getSubtotal(), 0.001);
        assertEquals("Coxinha", itemPedido.getItem().getDescricao());
    }
}
