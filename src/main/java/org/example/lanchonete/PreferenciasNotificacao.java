package org.example.lanchonete;

import java.util.*;

public class PreferenciasNotificacao implements IPrototype<PreferenciasNotificacao>{

    private List<String> canaisInscritos;


    public PreferenciasNotificacao(List<String> canaisInscritos){
        this.canaisInscritos = canaisInscritos;
    }

    @Override
    public PreferenciasNotificacao clonePrototype(){

        List<String> canaisClone = new ArrayList<>(canaisInscritos);
        return new PreferenciasNotificacao(canaisClone);
    }

    public List<String> getCanaisInscritos(){
        return Collections.unmodifiableList(canaisInscritos);
    }

}
