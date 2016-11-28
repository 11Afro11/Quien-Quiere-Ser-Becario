import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import sockets.conexion.Conexion;
import java.io.IOException;
import java.io.BufferedReader;

//
// Presentador
// (CC) jjramos, 2012
//
public class Presentador extends Conexion{
	public Presentador()throws IOException{super("servidor");}
	public static void main(String[] args) {

		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		byte []buffer=new byte[256];
		// Número de bytes leídos
		int bytesLeidos=0;

		try {
			System.out.println("Waiting...");//espera al Concursante

			cs=ss.accept(); //comienza el socket y espera conexxion con el cliente

			System.out.println("Concursante listo");

			salidaCliente = new DataOutputStream(cs.getOutputStream());

			salidaCliente.writeUTF("Empezamos");

			BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			// ...serverSocket=... (completar)
			//////////////////////////////////////////////////

			// Mientras ... siempre!
			do {

				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				// socketServicio=... (completar)
				//////////////////////////////////////////////////

				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				MaquinaDePreguntas procesador=new MaquinaDePreguntas(socketServicio);
				procesador.procesa();

			} while (true);

		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
