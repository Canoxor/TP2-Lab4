package paquete;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Lectura {
	public class Archivos {

		private String ruta = "E:\\UTN2C\\lab4\\PersonasEmpresa.txt";
		
		public int contar_lineas() {
			int aux = 0;
			FileReader entrada;
			try {
				entrada = new FileReader(ruta);
				BufferedReader miBuffer = new BufferedReader(entrada);
				
				String linea = "";
				while (linea != null ) {
					System.out.println(linea);
					linea = miBuffer.readLine();
					
					aux ++;
				}
				entrada.close();
		
			} catch (IOException e) {
				System.out.println("No se encontro el archivo");
			}
			return aux;
		}
		
		
		public void lee_archivo() {
			int pos , contador , dni;
			String aux="";
			String aux2 = "";
			String nombre;
			String apellido;
			try {
				List<Persona> Persona = new ArrayList<>(); //creo el array de la lista de personas
				String linea ="";
				BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "utf-8")); //crea un lector ylo pone en español	
				try {
					while ((linea = lector.readLine()) != null) {	//lee todo el archivo
							//completar con la carga de persona
							//recibir con la persona cargada y agregar a la lista
					}finally{
						
					}
				}
			}
		}

		
		

		}


}
