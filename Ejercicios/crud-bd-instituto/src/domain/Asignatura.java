package domain;

import static Snippets.Snippet.*;
import static Validate.Validate.obtenerDato;
import User.Info;
import java.util.List;

import DAO.*;

public class Asignatura {

    private String codAsignatura;
    private String codInterno;
    private String descripcion;
    private int nHoras;
    private String codCurso;

    public Asignatura() {}

    public Asignatura(String codAsignatura, String codInterno, String descripcion, int nHoras, String codCurso) {
        this.codAsignatura = codAsignatura;
        this.codInterno = codInterno;
        this.descripcion = descripcion;
        this.nHoras = nHoras;
        this.codCurso = codCurso;
    }

    public String getCodAsignatura() {
        return codAsignatura;
    }

    public void setCodAsignatura(String codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public String getCodInterno() {
        return codInterno;
    }

    public void setCodInterno(String codInterno) {
        this.codInterno = codInterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNHoras() {
        return nHoras;
    }

    public void setNHoras(int nHoras) {
        this.nHoras = nHoras;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    @Override
    public String toString() {
        return "Asignatura [codAsignatura=" + codAsignatura + ", codInterno=" + codInterno + ", descripcion=" + descripcion + ", nHoras=" + nHoras + ", codCurso=" + codCurso + "]";
    }

    public static void asignaturaCRUD() {
        AsignaturaDAO asignaturaDao = new AsignaturaDAO();
        int option = 0;
        do {
            option = getInt(Info.CRUD_MENU);
            switch(option) {
                case 1:
                	insertarAsignatura(asignaturaDao);
                    break;
                case 2:
                	eliminarAsignatura(asignaturaDao);
                    break;
                case 3:
                	actualizarAsignatura(asignaturaDao);
                    break;
                case 4:
                	consultarAsignatura(asignaturaDao);
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
    
	private static void insertarAsignatura(AsignaturaDAO asignaturaDao) {
        Asignatura asignatura = setDataAsignatura();
	    if (asignatura != null) {
	        try {
	        	asignaturaDao.insertar(asignatura);
	        } catch (Exception e) {
	            System.out.println("\nNo se ha realizado la inserción de datos correctamente");
	        }
	    }
	}

	private static void eliminarAsignatura(AsignaturaDAO asignaturaDao) {
	    String cod = getString("Indica codigo asignatura para eliminarlo de la BD: ");
	    asignaturaDao.eliminar(cod);
	}

	private static void actualizarAsignatura(AsignaturaDAO asignaturaDao) {
	    Asignatura asig = updateDataAsignatura();
	    if (asig != null) {
	    	asignaturaDao.actualizar(asig);
	    } else {
	    	System.out.println("\nNo se ha realizado la actualizacion de datos correctamente");
	    }
	}

	private static void consultarAsignatura(AsignaturaDAO asignaturaDao) {
	    List<Asignatura> asignaturas = asignaturaDao.consultar();
	    for (Asignatura asig : asignaturas) {
	        System.out.println(asig);
	    }
	}

    private static Asignatura setDataAsignatura() {
    	AsignaturaDAO asignaturaDao = new AsignaturaDAO();
    	List<Asignatura> asignaturas = asignaturaDao.consultar();
    	
        String codAsignatura = obtenerDato("codigo de asignatura", "validateCodigo", String.class);
	    if (codAsignatura == null) return null;
		for(Asignatura asig : asignaturas) {
			if (asig.codAsignatura.equals(codAsignatura)) {
				System.out.println("\nEl codigo de asignatura introducido ya existe en la base de datos.");
				return null;
			}
		}
        String codInterno = obtenerDato("codigo interno", "validateCodigo", String.class);
	    if (codInterno == null) return null;
		for(Asignatura asig : asignaturas) {
			if (asig.codInterno.equals(codInterno)) {
				System.out.println("\nEl codigo interno introducido ya existe en la base de datos.");
				return null;
			}
		}
		
        String descripcion = getString("Introduce descripcion: ");
        Integer nHoras = obtenerDato("numero de horas", "validateNumbers", Integer.class);
        if (nHoras == null) return null;
        
        String codCurso = obtenerDato("codigo curso", "validateCodigo", String.class);
        if (codCurso == null) return null;

        return new Asignatura(codAsignatura, codInterno, descripcion, nHoras, codCurso);
    }
    
    private static Asignatura updateDataAsignatura() {
    	
        String codAsignatura = obtenerDato("codigo de asignatura", "validateCodigo", String.class);
	    if (codAsignatura == null) return null;
	    
        String codInterno = obtenerDato("codigo interno", "validateCodigo", String.class);
	    if (codInterno == null) return null;
		
        String descripcion = getString("Introduce descripcion: ");
        Integer nHoras = obtenerDato("numero de horas", "validateNumbers", Integer.class);
        if (nHoras == null) return null;
        
        String codCurso = obtenerDato("codigo curso", "validateCodigo", String.class);
        if (codCurso == null) return null;

        return new Asignatura(codAsignatura, codInterno, descripcion, nHoras, codCurso);
    }

}
