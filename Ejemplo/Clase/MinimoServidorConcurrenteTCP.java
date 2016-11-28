import java.net.*;
import java.io.*;

public class MinimoServidorConcurrenteTCP{
  static ServerSocket socket_control;
  static Socket socket_datos;
  static int puerto;
  static Servicio servicio;

  public MinimoServidorConcurrenteTCP(){

  }

  static public void main(String args[]){
    boolean salir = false;
    boolean error = false;

    if(args.length<1){
      System.err.println("Sintaxis: MinimoServidorConcurrenteTCP <puerto>");
      System.exit(-1);
    }
    try{
      socket_control = new ServerSocket(puerto);
    }catch(IOException e){
      System.err.println("Error: no se puede abrir el puerto.");
      System.exit(-2);
    }

    do{
      try{
        socket_datos = socket_control.accept();
        new Servicio(socket_datos).start();
      }catch(IOException e){
        System.err.println("Error: no se pudo aceptar la solicitud dela conexion.");
        error = true;
      }
    }while(!salir);
  }
}

class Servicio extends Thread{
  Socket socket_datos;
  PrintWriter out;
  BufferedReader in;

  public Servicio(Socket socket_datos_){
    socket_datos = socket_datos_;
    try{
      out = new PrintWriter(socket_datos.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket_datos.getInputStream()));

    }catch(IOException e){
      System.err.println(this.getName()+ "Error: no se pudo obtener un canal para los flujos.");

    }
  }

  public void run(){
    String mensajeSolicitud = "";
    String mensajeRespuesta = "";
    try{
      mensajeSolicitud = in.readLine();
    }catch(IOException e){
      System.err.println(this.getName()+"Error: no se pudo leer el mensaje.");

    }
    mensajeRespuesta = procesaServicio(mensajeSolicitud);
    out.println(mensajeRespuesta);
    try{
      in.close();
      out.close();
      socket_datos.close();
    }catch(IOException e){
      System.err.println(this.getName()+"Erron: no se pudo cerrar la conexion.");
    }
  }

  static String procesaServicio(String mensaje){
    return mensaje;
  }

}
