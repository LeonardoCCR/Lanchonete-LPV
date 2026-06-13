package org.example;

import org.example.lanchonete.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoratorTest {
    @Test
    void deveRetornarPrecoSanduicheSimples() {

        ItemPersonalizavel sanduiche = new Sanduiche("X-Burger", 15.0);

        assertEquals(15.0, sanduiche.getPreco());
    }

    @Test
    void deveRetornarDescricaoSanduicheSimples() {
        ItemPersonalizavel sanduiche = new Sanduiche("X-Burger", 15.0);

        assertEquals("X-Burger", sanduiche.getDescricao());
    }

    @Test
    void deveAdicionarQueijoExtra() {

        ItemPersonalizavel sanduiche = new Sanduiche("X-Burger", 15.0);

        sanduiche = new QueijoExtra(sanduiche);

        assertEquals("X-Burger + Queijo Extra", sanduiche.getDescricao());

        assertEquals(18.0, sanduiche.getPreco());
    }

    @Test
    void deveAdicionarBaconExtra() {

        ItemPersonalizavel sanduiche = new Sanduiche("X-Burger", 15.0);

        sanduiche = new BaconExtra(sanduiche);

        assertEquals("X-Burger + Bacon", sanduiche.getDescricao());

        assertEquals(19.0, sanduiche.getPreco());
    }

    @Test
    void deveAdicionarQueijoEBacon() {

        ItemPersonalizavel sanduiche = new Sanduiche("X-Burger", 15.0);

        sanduiche = new QueijoExtra(sanduiche);

        sanduiche = new BaconExtra(sanduiche);

        assertEquals("X-Burger + Queijo Extra + Bacon", sanduiche.getDescricao());

        assertEquals(22.0, sanduiche.getPreco());
    }
}
