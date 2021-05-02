import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;
import java.io.*;



public class BibliotecaImpl extends UnicastRemoteObject implements Biblioteca {
    public BibliotecaImpl(String name) throws RemoteException {
        super();
        try {
            System.out.println("Rebind Object " + name);
            Naming.rebind(name, this);
        } catch (Exception e){ 
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public String solicitud (String peticion) throws RemoteException {
        File archivo = null;
        String nombreLibroSolicitud=peticion;
        RandomAccessFile fichero=null;
        long pos=0;
        String respuesta="No existencia";
        int primeraVez=1;
        int encontrado=0;
        int totalNumero=0;
        int librosPrestamo=0;
        try {
            archivo = new File ("BD.txt");
            fichero = new RandomAccessFile(archivo, "rw");
             
           // Lectura del fichero
            
            pos = 0;
            String linea= fichero.readLine();             
            String nombre;
            String id;
            String prestado;
            while(linea!=null){                
                StringTokenizer sscanf = new StringTokenizer(linea, " ");
                if(sscanf.hasMoreTokens()){
                    nombre=sscanf.nextToken();
                    id=sscanf.nextToken();
                    prestado=sscanf.nextToken();
                    if(nombre.equals(nombreLibroSolicitud)){
                        totalNumero=totalNumero+1;                       
                    }
                    if(nombre.equals(nombreLibroSolicitud)&& prestado.equals("1")){
                        librosPrestamo=librosPrestamo+1;                       
                    }
                    if(nombre.equals(nombreLibroSolicitud) && primeraVez==1){
                        primeraVez=0;
                        respuesta="Libro en prestamo";
                    }
                    if(nombre.equals(nombreLibroSolicitud) && prestado.equals("0") && encontrado==0 ){
                        fichero.seek(pos);
                        fichero.writeBytes(nombre+" "+id+" 1 01-nov.-2020");
                        respuesta="Satisfactoria";
                        encontrado=1;
                        librosPrestamo=librosPrestamo+1;
                    }
                    pos = fichero.getFilePointer();                   
                }               
                linea= fichero.readLine();                   
            }             
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fichero ){   
                fichero.close();  
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
        respuesta=respuesta+"|Numero total de libros: "+totalNumero+"|libros en prestamo : "+librosPrestamo+"|libros disponibles para prestamo: "+(totalNumero-librosPrestamo);
        return respuesta; 
    }


    


}
