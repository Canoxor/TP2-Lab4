package paquete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Persona {

	private int dni;
	private String nombre;
	private String apellido;
	
	public Persona()
	{
		dni = 11111111;
		nombre = "";
		apellido = "";
	}
	
	public Persona(int dni, String nombre, String apellido)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Persona DNI = " + dni + ", NOMBRE = " + nombre + ", APELLIDO = " + apellido;
	}
	
	
	public Persona Cargar_Persona(string ruta) {
		String linea;
		int index;
		int contador;
		int dni;
		
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, true), "UTF8"));
	

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "utf-8")); //crea un lector ylo pone en español
			while ((linea = in.readLine()) != null) {	//lee todo el archivo
				index = 0;	
				contador = 0;
				while (contador < 3) {					//esto pasa cada vez que lee una parte de la linea
					index = linea.indexOf("-");
					if (index != -1) {
						switch (contador) {
						case 0:
							nombre = linea.substring(0, index);
							linea = linea.substring(index + 1, linea.length());
							break;
						case 1:
							apellido = linea.substring(0, index);
							linea = linea.substring(index + 1, linea.length());	//el index +1 es despues de "-"
							break;
						}
					} else if (contador == 2) {
						dni = Integer.parseInt(linea);	//convierte el contenido restante a int 
					}
					contador++;
				}	//fin de la carga de datos de una persona en las bariables nombre apellido dni
				
				try
                {
				    Persona p = new Persona(dni, nombre, apellido); //carga la informacion en la clase persona
                    p = null;
                }finally {
                	return this;
				}
		
		} //llave de fin de la lectura
	
	}	//llave del fin del metodo
	
}
