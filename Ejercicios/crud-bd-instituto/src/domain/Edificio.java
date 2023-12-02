package domain;

import static Snippets.Snippet.*;
import User.Info;
import java.util.List;

import DAO.*;

public class Edificio {

    private String codEdificio;
    private String nombre;

    public Edificio() {}

    public Edificio(String codEdificio, String nombre) {
        this.codEdificio = codEdificio;
        this.nombre = nombre;
    }

    public String getCodEdificio() {
        return codEdificio;
    }

    public void setCodEdificio(String codEdificio) {
        this.codEdificio = codEdificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Edificio [codEdificio=" + codEdificio + ", nombre=" + nombre + "]";
    }

    public static void edificioCRUD() {
        EdificioDAO edificioDao = new EdificioDAO();
        int option = 0;
        do {
            option = getInt(Info.CRUD_MENU);
            switch(option) {
                case 1:
                    Edificio edificio_insertar = setDataEdificio();
                    edificioDao.insertar(edificio_insertar);
                    break;
                case 2:
                    String cod_eliminar = getString("Indica código del edificio para eliminarlo de la BD: ");
                    edificioDao.eliminar(cod_eliminar);
                    break;
                case 3:
                    Edificio edificio_actualizar = setDataEdificio();
                    edificioDao.actualizar(edificio_actualizar);
                    break;
                case 4:
                    List<Edificio> edificios = edificioDao.consultar();
                    for(Edificio edificio : edificios) {
                        System.out.println(edificio);
                    }
                    break;
                case 0:
                    System.out.println("Volviendo atras...");
					System.out.println();
					Info.showMainMenu();
                    break;
                default:
                    System.out.println("\nNo valido");
                    break;
            } 
        } while (option != 0);
    }

    private static Edificio setDataEdificio() {
        String codEdificio = getString("Introduce Código de Edificio: ");
        String nombre = getString("Introduce Nombre de Edificio: ");

        Edificio edificio = new Edificio(codEdificio, nombre);
        return edificio;
    }

}
