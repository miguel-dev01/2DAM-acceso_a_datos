package domain;

import static Snippets.Snippet.getString;
import static Snippets.Snippet.*;
import User.Info;
import java.util.List;

import BD.Table;
import DAO.*;

public class Aula {

    private String numAula;
    private String codEdificio;

    public Aula() {}

    public Aula(String numAula, String codEdificio) {
        this.numAula = numAula;
        this.codEdificio = codEdificio;
    }

    public String getNumAula() {
        return numAula;
    }

    public void setNumAula(String numAula) {
        this.numAula = numAula;
    }

    public String getCodEdificio() {
        return codEdificio;
    }

    public void setCodEdificio(String codEdificio) {
        this.codEdificio = codEdificio;
    }

    @Override
    public String toString() {
        return "Aula [numAula=" + numAula + ", codEdificio=" + codEdificio + "]";
    }

    public static void aulaCRUD() {
        AulaDAO aulaDao = new AulaDAO();
        int option = 0;
        do {
            option = getInt(Info.CRUD_MENU);
            switch(option) {
                case 1:
                    Aula aula_insertar = setDataAula();
                    aulaDao.insertar(aula_insertar);
                    break;
                case 2:
                    String aula_eliminar = getString("Indica número de aula para eliminarlo de la BD: ");
                    aulaDao.eliminar(aula_eliminar);
                    break;
                case 3:
                    Aula aula_actualizar = setDataAula();
                    aulaDao.actualizar(aula_actualizar);
                    break;
                case 4:
                    List<Aula> aulas = aulaDao.consultar();
                    for(Aula aula : aulas) {
                        System.out.println(aula);
                    }
                    break;
                case 0:
                    System.out.println("Volviendo atras...");
					System.out.println();
					Table.showTables();
                    break;
                default:
                    System.out.println("\nNo valido");
                    break;
            } 
        } while (option != 0);
    }

    private static Aula setDataAula() {
        String numAula = getString("Introduce Número de Aula: ");
        String codEdificio = getString("Introduce Código de Edificio: ");
        return new Aula(numAula, codEdificio);
    }

}
