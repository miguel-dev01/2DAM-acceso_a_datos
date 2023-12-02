package domain;

import static Snippets.Snippet.*;
import static Validate.Validate.obtenerDato;
import User.Info;

import java.sql.Date;
import java.util.List;

import BD.Table;
import DAO.ProfesorDAO;

public class Profesor {

    private String nrp;
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
    private String codDepartamento;

    public Profesor() {}

    public Profesor(String nrp, String dni, String nombre, String apellido1, String apellido2, 
    		String tipoVia, String nombreVia, String numero, String escalera, String piso, 
    		String puerta, String cp, String pais, String tlfnFijo, String tlfnMovil, String email, 
    		Date fechaNac, String codDepartamento) {
        this.nrp = nrp;
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
        this.codDepartamento = codDepartamento;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
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

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    @Override
    public String toString() {
        return "Profesor [nrp=" + nrp + ", dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1 +
                ", apellido2=" + apellido2 + ", tipoVia=" + tipoVia + ", nombreVia=" + nombreVia + ", numero=" +
                numero + ", escalera=" + escalera + ", piso=" + piso + ", puerta=" + puerta + ", cp=" + cp +
                ", pais=" + pais + ", tlfnFijo=" + tlfnFijo + ", tlfnMovil=" + tlfnMovil + ", email=" + email +
                ", fechaNac=" + fechaNac + ", codDepartamento=" + codDepartamento + "]";
    }

    public static void profesorCRUD() {
        ProfesorDAO profesorDao = new ProfesorDAO();
        int option = 0;
        do {
            option = getInt(Info.CRUD_MENU);
            switch(option) {
                case 1:
                	insertarProfesor(profesorDao);
                    break;
                case 2:
                    eliminarProfesor(profesorDao);
                    break;
                case 3:
                    actualizarProfesor(profesorDao);
                    break;
                case 4:
                    consultarProfesor(profesorDao);
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
    
	private static void insertarProfesor(ProfesorDAO profesorDao) {
		Profesor profesor = setDataProfesor();
	    if (profesor != null) {
	        try {
	        	profesorDao.insertar(profesor);
	        } catch (Exception e) {
	            System.out.println("\nNo se ha realizado la inserción de datos correctamente");
	        }
	    }
	}
	private static void eliminarProfesor(ProfesorDAO profesorDao) {
	    String nrp = getString("Indica NRP del profesor para eliminarlo de la BD: ");
	    profesorDao.eliminar(nrp);
	}
	private static void actualizarProfesor(ProfesorDAO profesorDao) {
	    Profesor profesor = updateDataProfesor();
	    if (profesor != null) {
	    	profesorDao.actualizar(profesor);
	    } else {
	    	System.out.println("\nNo se ha realizado la actualizacion de datos correctamente");
	    }
	}
	private static void consultarProfesor(ProfesorDAO profesorDao) {
	    List<Profesor> profesores = profesorDao.consultar();
	    for (Profesor prof : profesores) {
	        System.out.println(prof);
	    }
	}

    private static Profesor setDataProfesor() {
    	ProfesorDAO profesoresDao = new ProfesorDAO();
    	List<Profesor> profesores = profesoresDao.consultar();
    	
        String nrp = obtenerDato("NRP", "validateNRE", String.class);
        if (nrp == null) return null;
		for(Profesor prof : profesores) {
			if (prof.nrp.equals(nrp)) {
				System.out.println("\nEl NRP introducido ya existe en la base de datos.");
				return null;
			}
		}
        String dni = obtenerDato("DNI", "validateDNI", String.class);
        if (dni == null) return null;
		for(Profesor prof : profesores) {
			if (prof.dni.equals(dni)) {
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
	    
	    String codDepartamento = obtenerDato("codigo departamento", "validateCodigo", String.class);

        return new Profesor(nrp, dni, nombre, apellido1, apellido2, tipoVia, nombreVia,
                numero, escalera, piso, puerta, cp, pais, tlfnFijo, tlfnMovil,
                email, Date.valueOf(fechaNacimiento), codDepartamento);
    }
    
    private static Profesor updateDataProfesor() {
    	
        String nrp = obtenerDato("NRP", "validateNRE", String.class);
        if (nrp == null) return null;

        // Se llama a metodo que comprueba si se repita en bd
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
	    
	    String codDepartamento = obtenerDato("codigo departamento", "validateCodigo", String.class);

        return new Profesor(nrp, dni, nombre, apellido1, apellido2, tipoVia, nombreVia,
                numero, escalera, piso, puerta, cp, pais, tlfnFijo, tlfnMovil,
                email, Date.valueOf(fechaNacimiento), codDepartamento);
    }
}
