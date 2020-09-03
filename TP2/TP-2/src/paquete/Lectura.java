package paquete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Lectura {

	private String ruta = "Archivos\\PersonasEmpresa.txt";
	private String ruta_Escritura = "Resultado.txt";

	public boolean existeEscritura()
	{
		File archivo = new File(ruta_Escritura);
		if(archivo.exists())
		{
			return true;
		}
		return false;
	}
	
	public boolean existeLectura()
	{
		File archivo = new File(ruta);
		if(archivo.exists())
		{
			return true;
		}
		return false;
	}
	
	public boolean CrearArchivo()
	{
		FileWriter Escritura;
		try
		{
			Escritura = new FileWriter("Resultado.txt",true);
			Escritura.write("");
			Escritura.close();
			
			System.out.println("Archivo creado");
			return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void DividirLinea(String linea, Persona pipol) {
		String Nombre = "";
		String Apellido = "";
		String DNI = "";
		int Pos1 = 0;
		int Pos2 = 0;
		Pos1 = linea.indexOf("-");
		Nombre = linea.substring(0, Pos1);
		Pos1++;
		Pos2 = linea.indexOf("-", Pos1);
		Apellido = linea.substring(Pos1, Pos2);
		Pos2++;
		DNI = linea.substring(Pos2);

		int DNI_N = Integer.parseInt(DNI);

		String original = Nombre;
		String cadenaNormalize = Normalizer.normalize(original, Normalizer.Form.NFD);
		String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

		pipol.setNombre(cadenaSinAcentos);
		pipol.setApellido(Apellido);
		pipol.setDni(DNI_N);
	}

	public void CargarLista() throws Excepcion_DNI{

		int Registros = ContarRegistros();
		
		FileReader entrada;
		int a, x;

		Persona pipol = new Persona();

		Persona[] Lista = new Persona[Registros];
		
		int Tope = -1;

		try {
			entrada = new FileReader(ruta);
			BufferedReader Buffer = new BufferedReader(entrada);

			String linea = "";
			for (a = 0; a < Registros; a++) {
				linea = Buffer.readLine();
				Tope++;
				DividirLinea(linea, pipol);

				// EXCEPCION
				if(a!=0)
				{
					try
					{
					if(!(pipol.getDni() > 99999999 || pipol.getDni() < 10000000))
					{
						if(ExisteDNI(pipol.getDni(),Lista, Tope)==false)
						{
							Lista[Tope] = new Persona(pipol.getDni(), pipol.getNombre(), pipol.getApellido());
						}
						else
						{
							Tope--;
						}
					}
					else
					{
						Tope--;
						throw new Excepcion_DNI();
					}
				}
					catch(Excepcion_DNI e)
					{
						e.printStackTrace();
					}
					
				}
				else
				{
					Lista[Tope] = new Persona(pipol.getDni(), pipol.getNombre(), pipol.getApellido());
				}
			}
			Buffer.close();
			entrada.close();
			
			/// Contar Registros cargados
			for(x=0; x<Registros; x++)
			{
				if(Lista[x]==null)
				{
					Registros=x;
					break;
				}
			}

		} catch (IOException e) {
			System.out.println("No se encontro el archivo");
		}

		OrdenarLista(Lista, Registros);

		CargarArchivo(Lista, Registros);
	}
	
	public boolean ExisteDNI(int dni, Persona[] Lista, int tope)
	{
		for(int x=0; x<tope; x++)
		{
			if(dni == Lista[x].getDni())
			{
				return true;
			}
		}
		return false;
	}

	private void CargarArchivo(Persona[] Lista, int Registros) {
		try {
			
			FileWriter Escribidura = new FileWriter("Resultado.txt", false);
			BufferedWriter buffer = new BufferedWriter(Escribidura);
						
			for(int x=0; x<Registros; x++)
			{
				buffer.write(Lista[x].getNombre()+"-"+Lista[x].getApellido()+"-"+Lista[x].getDni()+"\n");
			}
			buffer.close();
			Escribidura.close();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void OrdenarLista(Persona[] Lista, int Registros) {
		Persona Aux = new Persona();

		for (int x = 0; x < Registros; x++) {
			for (int j = x + 1; j < Registros; j++) {
				if (Lista[x].getNombre().compareToIgnoreCase(Lista[j].getNombre()) < 0) {
					Aux = Lista[x];
					Lista[x] = Lista[j];
					Lista[j] = Aux;
				}
			}
		}
	}

	public int ContarRegistros() {
		FileReader entrada;

		try {
			int Cantidad = 0;
			entrada = new FileReader(ruta);
			BufferedReader Buffer = new BufferedReader(entrada);

			String linea = "";
			while (linea != null) {
				linea = Buffer.readLine();
				Cantidad = Cantidad + 1;
				// System.out.println(Cantidad);
			}
			Buffer.close();
			entrada.close();
			return Cantidad - 1;

		} catch (IOException e) {
			System.out.println("No se encontro el archivo");
			return 0;
		}
	}
}
