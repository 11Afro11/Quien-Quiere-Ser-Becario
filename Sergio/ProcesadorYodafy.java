//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class ProcesadorYodafy {
	//Preguntas
	private ArrayList<String> preguntas = new ArrayList();
	 
	private ArrayList<String> respuestas = new ArrayList();
	 



	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private Socket socketServicio;
	// stream de lectura (por aquí se recibe lo que envía el cliente)
	private InputStream inputStream;
	// stream de escritura (por aquí se envía los datos al cliente)
	private OutputStream outputStream;
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
	
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafy(Socket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
		preguntas.add("pregunta uno");
	 	preguntas.add("pregunta dos");
	 	preguntas.add("preguntas tres");
	 	respuestas.add("a");
	 	respuestas.add("b");
	 	respuestas.add("c");
	}
	int GenPregunta(){
		return (random.nextInt() % 3);
	}
	
	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){
		
		// Como máximo leeremos un bloque de 1024 bytes. Esto se puede modificar.
		byte [] datosRecibidos=new byte[1024];
		int bytesRecibidos=0;
		
		// Array de bytes para enviar la respuesta. Podemos reservar memoria cuando vayamos a enviarka:
		byte [] datosEnviar;
		int npregunta = GenPregunta();
		String pregunta = preguntas.get(npregunta);
		System.out.print("La cadena es" + pregunta);
		try {
			//Lee del cliente
			inputStream=socketServicio.getInputStream();
			//Escribe en el cliente
			outputStream=socketServicio.getOutputStream();
			while(true){
				// Convertimos el String de respuesta en una array de bytes:
				datosEnviar=pregunta.getBytes();
				//Envia la respuesta al cliente
				outputStream.write(datosEnviar,0,datosEnviar.length);
				//Lee la entrada y la pone en datosRecibidos
				/*
				bytesRecibidos = inputStream.read(datosRecibidos);
				// Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
				String peticion=new String(datosRecibidos,0,bytesRecibidos);
				String respuesta=null;
				if(peticion == respuestas.get(npregunta)){
					respuesta = "Acierto";
				}
				else{
					respuesta = "Error"; 
				}
				// Convertimos el String de respuesta en una array de bytes:
				datosEnviar=respuesta.getBytes();
				//Envia la respuesta al cliente
				outputStream.write(datosEnviar,0,datosEnviar.length);
				*/
			}
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion){
		return peticion;
	}
}
