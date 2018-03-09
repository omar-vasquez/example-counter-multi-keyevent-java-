import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

import utils.KeyMonitor;
import utils.ReadWriteText;

import interfaces.ListenerChangeKey;

public class Main{
	/**
	 * Creamos una clase repositorio, que guardara y 
	 * leera los datos cuando haya algun cambio.
	 */
	private static ReadWriteText repository = null; 
    
    //Cagita de texto donde se mostrara el cambio en la intefaz.
    private JLabel labelShowIncrement = new JLabel("0");
	
	//Definimos por default el incremento en cero.
	private int contador = 0 ; 
	
	public Main(){
		//Metodo para preparar 
		//la interfaz.
	   prepareGUI();
	}

	/**
	 * main Método de entrada de nuestra aplicacion.
	 * @param args [description]
	 */
    public static void main(String[] args) { 
    	//Metodo de entrada de nuestra aplicacion
 		Main main = new Main();
    }

    /**
     * prepareGUI description
     * Metodo de prearara para mostrar la interfaz.
     */
    private void prepareGUI(){

        //Agregamos el nombre del text que
        //con la que se guardara la informacion.
        String nameTemporal = "temp.txt";


        //Inicializamos, la calse repositorio.
    	repository = new ReadWriteText(nameTemporal);


        //Definimos el tamaño de la ventana.
        JFrame frame = new JFrame("Contador Control + F2"); 
        frame.setSize(200, 200);

        //Boton para resetear
        JButton boton = new JButton("Resetear");


        //Mostramos el tipo de ventana que aremos 
        //Esto muestra una X en la esquina.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Centramos la ventana.
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        

        //Invocamos la clase para captar los eventos 
        //en la interfaz.
        KeyMonitor monitor = new KeyMonitor();


        //enviamos la interface escucha de nuestra aplicacion.
        //Se ejecuta cuando se detecta la combinacion de teclas.
        monitor.setChangeKeyListener(new ListenerChangeKey(){
        	public void onChange(){

        		//Valida que se haya creado la clase repositorio.
        		if (repository != null) {

        			//Aumentamos el contador y enviamos 
        			//al repotirio que guarde el nuevo valor..
        			int contador  = Integer.parseInt( repository.readFile().trim() );
        			contador += 1;
		    		System.out.println("new Data" + contador );

		    		repository.writeFile( contador  + "" ); 
		    		
		    		//Escribimos lo ultimos que se escribio en el repositorio.
		    		labelShowIncrement.setText(repository.readFile().trim());
		    	}
        	}
        });

        //Quitamos el foco del boton para escuchar siempre desde
        //la pantalla principal.
		boton.setFocusable(false);

        //Crear un escucha para cuando se haga un click en el boton.
        boton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			    
			    //Reseteamos el contador.
		    	repository.writeFile("0"); 
		    		
		    	//Escribimos lo ultimos que se escribio en el repositorio.
		    	labelShowIncrement.setText(repository.readFile().trim());


			} 
		});


        frame.setFocusable(true);

        //Enviamos el escucha a nuestra ventana
        frame.addKeyListener(monitor);

        //Escribimos lo ultimo escribió en nuestro texto.
        labelShowIncrement.setText(repository.readFile().trim());

        //agregamos nuestro label
        frame.add(labelShowIncrement);

        //agregamos el boton a la pantalla.
        frame.add(boton);

        //mostramos la ventana.
        frame.setVisible(true);
   }

}
