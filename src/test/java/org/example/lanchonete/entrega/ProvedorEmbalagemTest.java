package org.example.lanchonete.entrega;

import org.example.lanchonete.produtos.Bebida;
import org.example.lanchonete.produtos.ItemConsumivel;
import org.example.lanchonete.produtos.Salgado;
import org.example.lanchonete.produtos.Sanduiche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProvedorEmbalagemTest {

    private ProvedorEmbalagem provedorDelivery;
    private ProvedorEmbalagem provedorLocal;
    private ItemConsumivel bebida;
    private ItemConsumivel sanduiche;
    private ItemConsumivel salgado;

    @BeforeEach
    void setUp() {
        provedorDelivery = new ProvedorDeliveryFactory();
        provedorLocal = new ProvedorLocalFactory();

        bebida = new Bebida("Refrigerante", 5.0);
        sanduiche = new Sanduiche("X-Burguer", 15.0);
        salgado = new Salgado("Coxinha", 6.0);
    }

    @Test
    void dadoProvedorDelivery_quandoItemLiquido_entaoRetornaCopoLacrado() {
        Embalagem embalagem = provedorDelivery.gerarEmbalagem(bebida);

        assertEquals("Copo Descartável Lacrado", embalagem.embalagem());
        assertEquals(0.5, embalagem.preco(), 0.001);
    }

    @Test
    void dadoProvedorDelivery_quandoItemSolido_entaoRetornaCaixaIsopor() {
        Embalagem embalagemSanduiche = provedorDelivery.gerarEmbalagem(sanduiche);
        Embalagem embalagemSalgado = provedorDelivery.gerarEmbalagem(salgado);

        assertEquals("Caixa de Isopor", embalagemSanduiche.embalagem());
        assertEquals(0.5, embalagemSanduiche.preco(), 0.001);

        assertEquals("Caixa de Isopor", embalagemSalgado.embalagem());
        assertEquals(0.5, embalagemSalgado.preco(), 0.001);
    }

    @Test
    void dadoProvedorLocal_quandoItemLiquido_entaoRetornaCopoDescartavelNormal() {
        Embalagem embalagem = provedorLocal.gerarEmbalagem(bebida);

        assertEquals("Copo Descartável", embalagem.embalagem());
        assertEquals(0.3, embalagem.preco(), 0.001);
    }

    @Test
    void dadoProvedorLocal_quandoItemSolido_entaoRetornaBandejaSemCusto() {
        Embalagem embalagemSanduiche = provedorLocal.gerarEmbalagem(sanduiche);
        Embalagem embalagemSalgado = provedorLocal.gerarEmbalagem(salgado);

        assertEquals("Bandeja", embalagemSanduiche.embalagem());
        assertEquals(0.0, embalagemSanduiche.preco(), 0.001);

        assertEquals("Bandeja", embalagemSalgado.embalagem());
        assertEquals(0.0, embalagemSalgado.preco(), 0.001);
    }
}