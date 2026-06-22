package org.example.lanchonete;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreferenciasNotificacaoTest {

    @Test
    void dadoPreferencias_quandoClonar_entaoRetornaNovaInstanciaComMesmosDados() {
        PreferenciasNotificacao original = new PreferenciasNotificacao(Arrays.asList("CANAL_A", "CANAL_B"));

        PreferenciasNotificacao clone = original.clonePrototype();

        assertNotSame(original, clone);
        assertEquals(original.getCanaisInscritos().size(), clone.getCanaisInscritos().size());
        assertTrue(clone.getCanaisInscritos().contains("CANAL_A"));
    }

    @Test
    void dadoClone_quandoModificarClone_entaoNaoAfetaOriginal() {
        PreferenciasNotificacao original = new PreferenciasNotificacao(List.of("CANAL_A"));
        PreferenciasNotificacao clone = original.clonePrototype();

        assertThrows(UnsupportedOperationException.class, () -> {
            clone.getCanaisInscritos().add("CANAL_C");
        }, "Deveria lançar exceção");

        assertEquals(1, original.getCanaisInscritos().size());
        assertFalse(original.getCanaisInscritos().contains("CANAL_C"));
    }
}