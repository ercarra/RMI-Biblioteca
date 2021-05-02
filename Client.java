import java.util.Scanner;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.rmi.*;
public class Client {
    public static void main(String args[]){
        try {
            System.out.println("Buscando Objeto");
            Biblioteca biblioteca = (Biblioteca)Naming.lookup("rmi://192.168.10.22:1099/Biblioteca");

            Scanner sc = new Scanner(System.in);  //crear un objeto Scanner
            String solicitud;
            System.out.print("Ingrese peticion ");
            solicitud = sc.nextLine();             
            System.out.println("Solicitud: " +biblioteca.solicitud(solicitud));
        }
        catch(Exception e){ 
            System.err.println("System exception" + e);
            
        }
        System.exit(0); 
    }
}