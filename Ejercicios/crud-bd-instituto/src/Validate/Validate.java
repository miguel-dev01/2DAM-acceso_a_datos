package Validate;

import static Snippets.Snippet.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.cj.util.StringUtils;

public class Validate {
	
	/**
	 * <T> --> Indica que el metodo es generico y puede manejar cualquier tipo de dato
	 * @param nombreCampo
	 * @param metodoValidacion
	 * @param tipoDato: Puede ser un entero o un cadena(string)
	 * @return el valor validado que ira hacia la base de datos, de no ser asi, no se valida, y
	 * termina retornando null 
	 */
	// Obtiene dato(puede ser un int o una cadena) para ser validado y llamada a su metodo automaticamente
	public static <T>T obtenerDato(String nombreCampo, String metodoValidacion, Class<T> tipoDato) {
		T dato = null;
		if (tipoDato == String.class) {
			dato = tipoDato.cast(getString("Introduce " + nombreCampo + ": "));
		} else if (tipoDato == Integer.class) {
			dato = tipoDato.cast(getInt("Introduce " + nombreCampo + ": "));
		}
	    if (!invocarMetodoValidacion(metodoValidacion, dato)) {
	        System.out.println(nombreCampo + " no valido.");
	        return null;
	    }
	    return dato;
	}
	// Obtenemos el metodo de la clase Validate que va a validar el dato
	private static boolean invocarMetodoValidacion(String nombreMetodo, Object dato) {
	    try {
	        java.lang.reflect.Method method = Validate.class.getMethod(nombreMetodo, dato.getClass());
	        return (boolean) method.invoke(null, dato);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static <T> boolean existeClave(String elemento, List<T> lista) {
	    if (elemento == null) {
	        System.out.println("\nEl elemento introducido es nulo.");
	        return false;
	    }
	    boolean elementoExiste = lista.contains(elemento);
	    if (!elementoExiste) {
	        System.out.println("\nEl codigo de clave foranea introducido no existe en la lista.");
	    }
	    return elementoExiste;
	}
	
	// Metodo que valida que un codigo sea como un entero
	public static boolean validateCodigo(String codigo) {
		return StringUtils.isStrictlyNumeric(codigo);
	}
	
	public static boolean validateNumbers(Integer num) {
	    if (num <= 0) {
	    	return false;
	    }
	    return true;
	}
	
	// Valida tanto NRE como NRP
	public static boolean validateNRE(String nre) {
		String regex = "^\\d{8,}$";
		return nre.matches(regex);
	}
	
	public static boolean validateDNI(String dni) {
		if (dni == "") {
			return false;
		}
        // Expresión regular para el formato del DNI
        String regex = "\\d{8}[A-HJ-NP-TV-Z]";
        
        // Comprobar el formato del DNI
        return Pattern.matches(regex, dni);
	}
	
	// Metodo que se usara para validar nombres y cadenas
	public static boolean validateName(String string) {
		if (string == "") {
			return false;
		}
	    // valida si el nombre (puede ser de cualquier campo que sea un nombre) comienza
	    // con una letra (mayuscula o minuscula), tiene al menos 
		// dos caracteres y contiene solo letras y espacios
		String regex = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$";
        return string.matches(regex);
	}
	
	public static boolean validateNumero(String numero) {
	    if (numero == null || numero.isEmpty()) {
	        System.out.println("\nNumero no puede estar vacio.");
	        return false;
	    }
	    // valida que sea un numero y no negativo
	    String regex = "^\\d+$";
	    return numero.matches(regex);
	}
	
	public static boolean validateEscaleraPiso(String string) {
	    if (string == null || string.isEmpty()) {
	        System.out.println("\nEscalera y/o piso no puede estar vacio.");
	        return false;
	    }
	    // valida letras y numeros
	    String regex = "^[a-zA-Z0-9]+$";
	    return string.matches(regex);
	}

	public static boolean validatePuerta(String puerta) {
	    if (puerta == null) {
	        System.out.println("\nPuerta no puede estar vacia.");
	        return false;
	    }
	    // valida letras y numeros, o cadena vacia
	    String regex = "^[a-zA-Z0-9]*$";
	    return puerta.matches(regex);
	}

	public static boolean validateCodigoPostal(String codigoPostal) {
	    if (codigoPostal == null || codigoPostal.isEmpty()) {
	        System.out.println("\nCodigo postal no puede estar vacio.");
	        return false;
	    }
	    // valida un codigo postal español de 5 digitos
	    String regex = "^\\d{5}$";
	    return codigoPostal.matches(regex);
	}
	
	public static boolean validateTelefono(String telefono) {
	    if (telefono == null || telefono.isEmpty()) {
	        System.out.println("\nNumero de telefono no puede estar vacio.");
	        return false;
	    }
	    // valida un numero de telefono español
	    String regex = "^[6-9]\\d{8}$";
	    return telefono.matches(regex);
	}
	
	public static boolean validateEmail(String email) {
	    if (email == null || email.isEmpty()) {
	        System.out.println("\nLa direccion de correo electronico no puede estar vacia.");
	        return false;
	    }
	    // expresion regular basica para validar direcciones de email
	    String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
	    return email.matches(regex);
	}
	
    public static boolean validarFormatoFecha(String fecha) {
        // patron de expresion regular para el formato yyyy-MM-dd
        String patron = "\\d{4}-\\d{2}-\\d{2}";
        // Compilar la expresion regular
        Pattern pattern = Pattern.compile(patron);
        // Crear un matcher que comparara la cadena proporcionada con el patron
        Matcher matcher = pattern.matcher(fecha);
        // Verificar si la cadena coincide con el patron
        return matcher.matches();
    }
    
    public static boolean validarAnio(Integer anio) {
        // Definir el rango permitido para los años
        final int ANIO_MINIMO = 1000;
        final int ANIO_MAXIMO = 9999;

        // Verificar si el año esta dentro del rango permitido
        if (anio >= ANIO_MINIMO && anio <= ANIO_MAXIMO) {
            return true;
        } else {
            return false;
        }
    }
}
