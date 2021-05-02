import java.rmi.*;
import java.rmi.server.*;
public class Server {
    public static void main (String args[]) {
        try {
            
            BibliotecaImpl biblioteca= new BibliotecaImpl("rmi://192.168.10.22:1099"+"/Biblioteca");
        } catch(Exception e) { 
            System.err.println("System exception" + e); 
        } 
    }
}