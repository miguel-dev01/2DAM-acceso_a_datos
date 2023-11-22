package domain;

import static Snippets.Snippet.*;
import static Validate.Validate.obtenerDato;
import User.Info;
import java.util.List;

import BD.Table;
import DAO.*;

public class Grupo {

    private String codGrupo;
    private String codCurso;
    private String nombre;
    private String codTurno;
    private int nMaxAlumnos;

    public Grupo() {}

    public Grupo(String codGrupo, String codCurso, String nombre, String codTurno, int nMaxAlumnos) {
        this.codGrupo = codGrupo;
        this.codCurso = codCurso;
        this.nombre = nombre;
        this.codTurno = codTurno;
        this.nMaxAlumnos = nMaxAlumnos;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
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

    public String getCodTurno() {
        return codTurno;
    }

    public void setCodTurno(String codTurno) {
        this.codTurno = codTurno;
    }

    public int getNMaxAlumnos() {
        return nMaxAlumnos;
    }

    public void setNMaxAlumnos(int nMaxAlumnos) {
        this.nMaxAlumnos = nMaxAlumnos;
    }

    @Override
    public String toString() {
        return "Grupo [codGrupo=" + codGrupo + ", codCurso=" + codCurso + ", nombre=" + nombre + ", codTurno=" + codTurno + ", nMaxAlumnos=" + nMaxAlumnos + "]";
    }

    public static void grupoCRUD() {
        GrupoDAO grupoDao = new GrupoDAO();
        int option = 0;
        do {
            option = getInt(Info.CRUD_MENU);
            switch(option) {
                case 1:
                	insertarGrupo(grupoDao);
                    break;
                case 2:
                	eliminarGrupo(grupoDao);
                    break;
                case 3:
                	actualizarGrupo(grupoDao);
                    break;
                case 4:
                	consultarGrupo(grupoDao);
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
    
	private static void insertarGrupo(GrupoDAO grupoDao) {
		Grupo grupo = setDataGrupo();
	    if (grupo != null) {
	        try {
	        	grupoDao.insertar(grupo);
	        } catch (Exception e) {
	            System.out.println("\nNo se ha realizado la inserción de datos correctamente");
	        }
	    }
	}
	private static void eliminarGrupo(GrupoDAO grupoDao) {
	    String codGrupo = getString("Indica codigo de grupo para eliminarlo de la BD: ");
	    grupoDao.eliminar(codGrupo);
	}
	private static void actualizarGrupo(GrupoDAO grupoDao) {
		Grupo grupo = updateDataGrupo();
	    if (grupo != null) {
	    	grupoDao.actualizar(grupo);
	    } else {
	    	System.out.println("\nNo se ha realizado la actualizacion de datos correctamente");
	    }
	}
	private static void consultarGrupo(GrupoDAO grupoDao) {
	    List<Grupo> grupos = grupoDao.consultar();
	    for (Grupo grupo: grupos) {
	        System.out.println(grupo);
	    }
	}

    private static Grupo setDataGrupo() {
    	GrupoDAO grupoDao = new GrupoDAO();
    	List<Grupo> grupos = grupoDao.consultar();
    	
        String codGrupo = obtenerDato("codigo de grupo", "validateCodigo", String.class);
        if (codGrupo == null) return null;
		for(Grupo grupo : grupos) {
			if (grupo.codGrupo.equals(codGrupo)) {
				System.out.println("\nEl codigo de grupo introducido ya existe en la base de datos.");
				return null;
			}
		}
        
        String codCurso = obtenerDato("codigo de curso", "validateCodigo", String.class);
        if (codCurso == null) return null;
        
        String nombre = getString("Introduce nombre del grupo: ");
        if (nombre == "") return null;
        
        String codTurno = obtenerDato("codigo de turno", "validateCodigo", String.class);
        if (codTurno == null) return null;
        
        Integer nMaxAlumnos = obtenerDato("Numero maximo alumnos", "validateNumbers", Integer.class);
        if (nMaxAlumnos == null) return null;

        return new Grupo(codGrupo, codCurso, nombre, codTurno, nMaxAlumnos);
    }
    
    private static Grupo updateDataGrupo() {
    	
        String codGrupo = obtenerDato("codigo de grupo", "validateCodigo", String.class);
        if (codGrupo == null) return null;
        
        String codCurso = obtenerDato("codigo de curso", "validateCodigo", String.class);
        if (codCurso == null) return null;
        //if (!existeClave(codCurso, cursos)) return null;
        
        String nombre = getString("Introduce nombre del grupo: ");
        if (nombre == "") return null;
        
        String codTurno = obtenerDato("codigo de turno", "validateCodigo", String.class);
        if (codTurno == null) return null;
        
        Integer nMaxAlumnos = obtenerDato("Numero maximo alumnos", "validateNumbers", Integer.class);
        if (nMaxAlumnos == null) return null;

        return new Grupo(codGrupo, codCurso, nombre, codTurno, nMaxAlumnos);
    }

}
