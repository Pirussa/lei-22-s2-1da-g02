package app.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class CheckAndExportVaccinationStatsController {

    public CheckAndExportVaccinationStatsController() {
    }

    public List<String> getVaccinationStats() {
        return null;
    }


    //Duas abordagens diferentes possíveis de seguir:
    //dar return a void e lançar exceção caso dê errado para depois na UI ser apenas printar que foi sucedido (se for) e tratar a exceção
    //retornar um boolean indicando se o ficheiro foi ou não exportado.
    public boolean exportVaccinationStats() {
        File file = new File("PorDefinir.csv");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println("x");

            //Printar tudo para aqui




        } catch (Exception FileNotFoundException) {
            return false;
        } finally {
            assert writer != null;
            writer.close();
        }

        return true;
    }


}
