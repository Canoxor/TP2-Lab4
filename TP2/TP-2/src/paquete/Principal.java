package paquete;

public class Principal {

	public static void main(String[] args) throws Excepcion_DNI {
		// TODO Auto-generated method stub
		
		Lectura Leido = new Lectura();
		
		if(Leido.existeEscritura() == true)
		{
			System.out.println("El archivo ya existe.");
			Leido.CargarLista();
		}
		else
		{
			System.out.println("Archivo no encontrado, se procedera a crear uno nuevo.");
			Leido.CrearArchivo();
			Leido.CargarLista();
		}
	}
}
