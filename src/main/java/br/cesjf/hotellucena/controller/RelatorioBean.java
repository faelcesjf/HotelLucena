package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.util.Relatorio;
import java.io.IOException;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class RelatorioBean {
    public void gerarQuartoReservado() throws IOException {
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio("RelQuartoReservado");
    }
    
    public void gerarQuartoOcupado() throws IOException {
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio("RelQuartoOcupado");
    }
    
    public void gerarQuartoDesocupado() throws IOException {
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio("RelQuartoDesocupado");
    }
}
