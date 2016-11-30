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
	private ArrayList<String> preguntas = new ArrayList<String>();

	private ArrayList<String> respuestas = new ArrayList<String>();
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
	 	respuestas.add("a \n");
	 	respuestas.add("b \n");
	 	respuestas.add("c \n");
	}

	int GenPregunta(){
		return (random.nextInt() % 3);
	}


	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){

		// Como máximo leeremos un bloque de 1024 bytes. Esto se puede modificar.
		byte [] datosRecibidos=new byte[1024];
		int bytesRecibidos=0;
		int preg = 0;

		// Array de bytes para enviar la respuesta. Podemos reservar memoria cuando vayamos a enviarka:
		byte [] datosEnviar;
		int npregunta = GenPregunta();
		String pregunta = preguntas.get(preg);
		String acierto = "Acierto";


		try {
			// Obtiene los flujos de escritura/lectura
			String peticion=new String(datosRecibidos,0,bytesRecibidos);
			System.out.println("la respuesta que has dado es "+peticion+ " y deberia ser "+respuestas.get(preg));
			peticion=new String(datosRecibidos,0,bytesRecibidos);
			System.out.println("la respuesta que has dado es "+peticion+ " y deberia ser "+respuestas.get(preg));
			String respuesta;
			// Yoda reinterpreta el mensaje:
			while(true){
				inputStream=socketServicio.getInputStream();
				outputStream=socketServicio.getOutputStream();

				// Lee la frase a Yodaficar:
				////////////////////////////////////////////////////////
				// read ... datosRecibidos.. (Completar)
				datosEnviar=pregunta.getBytes();
				outputStream.write(datosEnviar,0,datosEnviar.length);
				////////////////////////////////////////////////////////
				bytesRecibidos = inputStream.read(datosRecibidos);
				// Yoda hace su magia:
				// datosEnviar = null;
				// Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
				peticion = null;
				peticion=new String(datosRecibidos,0,bytesRecibidos);
				String cmp = peticion.substring(0,0);
				System.out.println("la respuesta que has dado es "+peticion +"y deberia ser "+respuestas.get(preg)+"tonto");
				System.out.println("La resouesta tiene un tamanio de "+peticion.length());
				// Yoda reinterpreta el mensaje:
				respuesta=peticion;
				String resp = (respuestas.get(preg)).substring(0,0);
				if(cmp.equals(resp)){
					respuesta = respuesta+"Acierto\n";
				}
				else{
					respuesta = respuesta+"Error\n";
				}
				// Convertimos el String de respuesta en una array de bytes:
				datosEnviar=respuesta.getBytes();
				respuesta = null;

				// Enviamos la traducción de Yoda:
				////////////////////////////////////////////////////////
				// ... write ... datosEnviar... datosEnviar.length ... (Completar)
				////////////////////////////////////////////////////////
				outputStream.write(datosEnviar,0,datosEnviar.length);
				datosEnviar = null;
				npregunta = GenPregunta();
				preg++;
				if(preg >= 3){
					preg=0;
				}
				pregunta = preguntas.get(preg);
			}


		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";

		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];

			s[j]=s[k];
			s[k]=tmp;
		}

		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}

		return resultado;
	}
}
