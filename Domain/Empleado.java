package Domain;

public class Empleado {

	private String numero;
	private String nombre;
	private int edad;
	private String departamento;
	private String categoria;
	private String contrato;

	public Empleado() {}
	public Empleado(String numero, String nombre, int edad, String departamento, String categoria, String contrato) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.edad = edad;
		this.departamento = departamento;
		this.categoria = categoria;
		this.contrato = contrato;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	
	@Override
	public String toString() {
		return "Empleado [numero=" + numero + ", nombre=" + nombre + ", edad=" + edad + ", departamento=" + departamento
				+ ", categoria=" + categoria + ", contrato=" + contrato + "]";
	}
}
