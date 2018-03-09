package utils;
import java.io.*;
import java.lang.*;


public class ReadWriteText {
    
    // Name text temp por default, se cambia en main.
    // por si se quiere otro nombre.
    private String fileName = "temp.txt";

    public ReadWriteText(String nameFile){
        this.fileName  = nameFile;        
    }

    public void writeFile(String number) {

        try {
            
            String bytes = number;
            byte[] buffer = bytes.getBytes();

            FileOutputStream outputStream =
                new FileOutputStream(fileName);

            
            // escribimos los datos en la salida
            // del stream
            outputStream.write(buffer);

            // cerrar el archivo.
            outputStream.close();       

            System.out.println("Wrote " + buffer.length + 
                " bytes");
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing file '"
                + fileName + "'");
        }
    }

    public String readFile(){
        String stringread = "";
        try {
            //creamos el tamaño de buffer de almacenamiento.
            byte[] buffer = new byte[1000];

            FileInputStream inputStream = 
                new FileInputStream(fileName);

            // read llena el búfer con datos y devuelve
            // la cantidad de bytes leídos 
            int total = 0;
            int nRead = 0;
            while((nRead = inputStream.read(buffer)) != -1) {
                // Convertir el buffer en string.
                System.out.println(new String(buffer));
                total += nRead;
            }   
            
            // Convertir el buffer en string
            stringread = new String(buffer);

            //cerrar documento
            inputStream.close();

            System.out.println("Read " + total + " bytes in " + stringread);
        }
        
        catch(FileNotFoundException ex) {
            //Si todavia no existe el archivo lo creamos con el formato 0.
            this.writeFile("0");

            //devolvemos  la informacion.
            stringread = "0";

            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }

        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
        return stringread;
    }
}