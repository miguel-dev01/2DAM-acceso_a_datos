package domain;

import static Snippets.Snippet.getString;
import static Snippets.Snippet.*;
import User.Info;
import java.util.List;

import BD.Table;
import DAO.*;

public class Departamento {

    private String codDepartamento;
    private String nombre;
    private String descripcion;

    public Departamento() {}

    public Departamento(String codDepartamento, String nombre, String descripcion) {
        this.codDepartamento = codDepartamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Departamento [codDepartamento=" + codDepartamento + ", nombre=" + nombre + 
        		", descripcion=" + descripcion + "]";
    }

    public static void departamentoCRUD() {
        DepartamentoDAO departamentoDao = new DepartamentoDAO();
        int option = 0;
        do {
            option = getInt(Info.CRUD_MENU);
            switch(option) {
                case 1:
                    Departamento departamento_insertar = setDataDepartamento();
                    departamentoDao.insertar(departamento_insertar);
                    break;
                case 2:
                    String cod_eliminar = getString("Indica código del departamento para eliminarlo de la BD: ");
                    departamentoDao.eliminar(cod_eliminar);
                    break;
                case 3:
                    Departamento departamento_actualizar = setDataDepartamento();
                    departamentoDao.actualizar(departamento_actualizar);
                    break;
                case 4:
                    List<Departamento> departamentos = departamentoDao.consultar();
                    for(Departamento departamento : departamentos) {
                        System.out.println(departamento);
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

    private static Departamento setDataDepartamento() {
        String codDepartamento = getString("Introduce Código de Departamento: ");
        String nombre = getString("Introduce Nombre de Departamento: ");
        String descripcion = getString("Introduce Descripción de Departamento: ");

        Departamento departamento = new Departamento(codDepartamento, nombre, descripcion);
        return departamento;
    }

}