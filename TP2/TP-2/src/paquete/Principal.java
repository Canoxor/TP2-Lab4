package paquete;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Lectura Leido = new Lectura();
		
		if(Leido.existeEscritura() == true)
		{
			System.out.println("Existio y se fug� prrrrro.");
			Leido.CargarLista();
		}
		else
		{
			System.out.println("No existe pero no preocuparos os lo crear�.");
			Leido.CrearArchivo();
			Leido.CargarLista();
		}
		
		
	}
}
