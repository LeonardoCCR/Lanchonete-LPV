package org.example.lanchonete.pedido;

import java.util.ArrayList;
import java.util.List;
import org.example.lanchonete.Funcionario;

public abstract class AvaliadorDesconto {

    protected List<Double> listaDescontosPermitidos = new ArrayList<>();
    private AvaliadorDesconto supervisorSuperior;
    protected final Funcionario funcionarioReal;

    public AvaliadorDesconto(Funcionario funcionarioReal) {
        this.funcionarioReal = funcionarioReal;
    }

    public AvaliadorDesconto getSupervisorSuperior() {
        return supervisorSuperior;
    }

    public void setSupervisorSuperior(AvaliadorDesconto supervisorSuperior) {
        this.supervisorSuperior = supervisorSuperior;
    }

    public String getDescricaoCargo() {
        return funcionarioReal != null ? funcionarioReal.getCargo() : "Desconhecido";
    }

    public String assinarDesconto(Pedido pedido, double porcentagemSolicitada) {
        if (listaDescontosPermitidos.contains(porcentagemSolicitada)) {
            return getDescricaoCargo();
        } else {

            if (supervisorSuperior != null) {
                return supervisorSuperior.assinarDesconto(pedido, porcentagemSolicitada);
            } else {
                return "Sem assinatura";
            }
        }
    }
}