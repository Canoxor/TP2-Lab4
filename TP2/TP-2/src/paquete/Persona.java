package paquete;

public class Persona {

	private int dni;
	private String nombre;
	private String apellido;
	
	public void Persona()
	{
		dni = 11111111;
		nombre = "";
		apellido = "";
	}
	
	public void Persona(int dni, String nombre, String apellido)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Persona DNI = " + dni + ", NOMBRE = " + nombre + ", APELLIDO = " + apellido;
	}
	
	
}
