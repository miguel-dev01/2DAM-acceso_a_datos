package domain;

import static Snippets.Snippet.*;
import User.Info;
import java.util.List;

import BD.Table;
import DAO.*;

public class Curso {

    private String codCurso;
    private String nombre;
    private String descripcion;

    public Curso() {}

    public Curso(String codCurso, String nombre, String descripcion) {
        this.codCurso = codCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
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
        return "Curso [codCurso=" + codCurso + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

    public static void cursoCRUD() {
        CursoDAO cursoDao = new CursoDAO();
        int option = 0;
        do {
            option = getInt(Info.CRUD_MENU);
            switch(option) {
                case 1:
                    Curso curso_insertar = setDataCurso();
                    cursoDao.insertar(curso_insertar);
                    break;
                case 2:
                    String curso_eliminar = getString("Indica código del curso para eliminarlo de la BD: ");
                    cursoDao.eliminar(curso_eliminar);
                    break;
                case 3:
                    Curso curso_actualizar = setDataCurso();
                    cursoDao.actualizar(curso_actualizar);
                    break;
                case 4:
                    List<Curso> cursos = cursoDao.consultar();
                    for(Curso curso : cursos) {
                        System.out.println(curso);
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

    private static Curso setDataCurso() {
        String codCurso = getString("Introduce Código de Curso: ");
        String nombre = getString("Introduce Nombre de Curso: ");
        String descripcion = getString("Introduce Descripción de Curso: ");

        Curso curso = new Curso(codCurso, nombre, descripcion);
        return curso;
    }

}
