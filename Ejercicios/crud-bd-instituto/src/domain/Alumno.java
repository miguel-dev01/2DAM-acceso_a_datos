package domain;

import static Snippets.Snippet.getString;
import static Snippets.Snippet.*;
import User.Info;
import static Validate.Validate.obtenerDato;

import java.sql.Date;
import java.util.List;

import BD.Table;
import DAO.AlumnoDAO;

public class Alumno {

	private String nre;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String tipoVia;
    private String nombreVia;
    private String numero;
    private String escalera;
    private String piso;
    private String puerta;
    private String cp;
    private String pais;
    private String tlfnFijo;
    private String tlfnMovil;
    private String email;
    private Date fechaNac;
    private String tutor;
    private String codCurso;

    public Alumno() {}
    public Alumno(String nre, String dni, String nombre, String apellido1, String apellido2, 
    		String tipoVia, String nombreVia, String numero, String escalera, String piso, 
    		String puerta, String cp, String pais, String tlfnFijo, String tlfnMovil, 
    		String email, Date fechaNac, String tutor) {
        this.nre = nre;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.tipoVia = tipoVia;
        this.nombreVia = nombreVia;
        this.numero = numero;
        this.escalera = escalera;
        this.piso = piso;
        this.puerta = puerta;
        this.cp = cp;
        this.pais = pais;
        this.tlfnFijo = tlfnFijo;
        this.tlfnMovil = tlfnMovil;
        this.email = email;
        this.fechaNac = fechaNac;
        this.tutor = tutor;
    }

    public String getNre() {
        return nre;
    }

    public void setNre(String nre) {
        this.nre = nre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEscalera() {
        return escalera;
    }

    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTlfnFijo() {
        return tlfnFijo;
    }

    public void setTlfnFijo(String tlfnFijo) {
        this.tlfnFijo = tlfnFijo;
    }

    public String getTlfnMovil() {
        return tlfnMovil;
    }

    public void setTlfnMovil(String tlfnMovil) {
        this.tlfnMovil = tlfnMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }
    
    @Override
	public String toString() {
		return "Alumno [nre=" + nre + ", dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", tipoVia=" + tipoVia + ", nombreVia=" + nombreVia + ", numero="
				+ numero + ", escalera=" + escalera + ", piso=" + piso + ", puerta=" + puerta + ", cp=" + cp + ", pais="
				+ pais + ", tlfnFijo=" + tlfnFijo + ", tlfnMovil=" + tlfnMovil + ", email=" + email + ", fechaNac="
				+ fechaNac + ", tutor=" + tutor + "]";
	}
    
	public static void alumnoCRUD() {
		AlumnoDAO alumnoDao = new AlumnoDAO();
		int option = 0;
		do {
			option = getInt(Info.CRUD_MENU);
			switch(option) {
				case 1:
					insertarAlumno(alumnoDao);
			        break;
				case 2:
					eliminarAlumno(alumnoDao);
			        break;
				case 3:
					actualizarAlumno(alumnoDao);
			        break;
				case 4:
					consultarAlumnos(alumnoDao);
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
	
	private static void insertarAlumno(AlumnoDAO alumnoDao) {
	    Alumno alumno = establecerDatosAlumno();
	    if (alumno != null) {
	        try {
	            alumnoDao.insertar(alumno);
	        } catch (Exception e) {
	            System.out.println("\nNo se ha realizado la inserción de datos correctamente");
	        }
	    }
	}

	private static void eliminarAlumno(AlumnoDAO alumnoDao) {
	    String nre_delete = getString("Indica NRE del alumno para eliminarlo de la BD: ");
	    alumnoDao.eliminar(nre_delete);
	}

	private static void actualizarAlumno(AlumnoDAO alumnoDao) {
	    Alumno alumno_actualizar = updateDatosAlumno();
	    if (alumno_actualizar != null) {
	        alumnoDao.actualizar(alumno_actualizar);
	    } else {
	    	System.out.println("No se ha realizado la actualizacion de datos correctamente");
	    }
	}

	private static void consultarAlumnos(AlumnoDAO alumnoDao) {
	    List<Alumno> alumnos = alumnoDao.consultar();
	    for (Alumno alumno : alumnos) {
	        System.out.println(alumno);
	    }
	}
	private static Alumno establecerDatosAlumno() {
		AlumnoDAO alumnoDao = new AlumnoDAO();
		List<Alumno> alumnos = alumnoDao.consultar();
		
	    String nre = obtenerDato("NRE", "validateNRE", String.class);
	    if (nre == null) return null;
		for(Alumno alumno : alumnos) {
			if (alumno.nre.equals(nre)) {
				System.out.println("\nEl NRE introducido ya existe en la base de datos.");
				return null;
			}
		}

	    String dni = obtenerDato("DNI", "validateDNI", String.class);
	    if (dni == null) return null;
		for(Alumno alumno : alumnos) {
			if (alumno.dni.equals(dni)) {
				System.out.println("\nEl DNI introducido ya existe en la base de datos.");
				return null;
			}
		}

	    String nombre = obtenerDato("nombre", "validateName", String.class);
	    if (nombre == null) return null;

	    String apellido1 = obtenerDato("primer apellido", "validateName", String.class);
	    String apellido2 = obtenerDato("segundo apellido", "validateName", String.class);
	    if (apellido1 == null || apellido2 == null) return null;

	    String tipoVia = obtenerDato("tipo de via", "validateName", String.class);
	    String nombreVia = obtenerDato("nombre de via", "validateName", String.class);
	    if (tipoVia == null || nombreVia == null) return null;

	    String numero = obtenerDato("numero", "validateNumero", String.class);
	    if (numero == null) return null;

	    String escalera = obtenerDato("escalera", "validateEscaleraPiso", String.class);
	    String piso = obtenerDato("piso", "validateEscaleraPiso", String.class);
	    if (escalera == null || piso == null) return null;

	    String puerta = obtenerDato("puerta", "validatePuerta", String.class);
	    if (puerta == null) return null;

	    String cp = obtenerDato("codigo postal", "validateCodigoPostal", String.class);
	    if (cp == null) return null;

	    String pais = obtenerDato("pais", "validateName", String.class);
	    if (pais == null) return null;

	    String tlfnFijo = obtenerDato("telefono fijo", "validateTelefono", String.class);
	    String tlfnMovil = obtenerDato("telefono móvil", "validateTelefono", String.class);
	    if (tlfnFijo == null || tlfnMovil == null) return null;

	    String email = obtenerDato("email", "validateEmail", String.class);
	    if (email == null) return null;

	    String fechaNacimiento = obtenerDato("fecha de nacimiento (yyyy-MM-dd)", "validarFormatoFecha", String.class);
	    if (fechaNacimiento == null) return null;

	    String tutor = getString("Introduce tutor: ");

	    return new Alumno(nre, dni, nombre, apellido1, apellido2, tipoVia, nombreVia,
	            numero, escalera, piso, puerta, cp, pais, tlfnFijo, tlfnMovil,
	            email, Date.valueOf(fechaNacimiento), tutor);
	}
	
	private static Alumno updateDatosAlumno() {
		
	    String nre = obtenerDato("NRE", "validateNRE", String.class);
	    if (nre == null) return null;

	    String dni = obtenerDato("DNI", "validateDNI", String.class);
	    if (dni == null) return null;

	    String nombre = obtenerDato("nombre", "validateName", String.class);
	    if (nombre == null) return null;

	    String apellido1 = obtenerDato("primer apellido", "validateName", String.class);
	    String apellido2 = obtenerDato("segundo apellido", "validateName", String.class);
	    if (apellido1 == null || apellido2 == null) return null;

	    String tipoVia = obtenerDato("tipo de via", "validateName", String.class);
	    String nombreVia = obtenerDato("nombre de via", "validateName", String.class);
	    if (tipoVia == null || nombreVia == null) return null;

	    String numero = obtenerDato("numero", "validateNumero", String.class);
	    if (numero == null) return null;

	    String escalera = obtenerDato("escalera", "validateEscaleraPiso", String.class);
	    String piso = obtenerDato("piso", "validateEscaleraPiso", String.class);
	    if (escalera == null || piso == null) return null;

	    String puerta = obtenerDato("puerta", "validatePuerta", String.class);
	    if (puerta == null) return null;

	    String cp = obtenerDato("codigo postal", "validateCodigoPostal", String.class);
	    if (cp == null) return null;

	    String pais = obtenerDato("pais", "validateName", String.class);
	    if (pais == null) return null;

	    String tlfnFijo = obtenerDato("telefono fijo", "validateTelefono", String.class);
	    String tlfnMovil = obtenerDato("telefono móvil", "validateTelefono", String.class);
	    if (tlfnFijo == null || tlfnMovil == null) return null;

	    String email = obtenerDato("email", "validateEmail", String.class);
	    if (email == null) return null;

	    String fechaNacimiento = obtenerDato("fecha de nacimiento (yyyy-MM-dd)", "validarFormatoFecha", String.class);
	    if (fechaNacimiento == null) return null;

	    String tutor = getString("Introduce tutor: ");

	    return new Alumno(nre, dni, nombre, apellido1, apellido2, tipoVia, nombreVia,
	            numero, escalera, piso, puerta, cp, pais, tlfnFijo, tlfnMovil,
	            email, Date.valueOf(fechaNacimiento), tutor);
	}
}
