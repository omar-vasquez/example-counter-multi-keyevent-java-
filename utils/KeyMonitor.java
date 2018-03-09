package utils;

import java.awt.event.*;
import interfaces.ListenerChangeKey;

public class KeyMonitor extends KeyAdapter {

    private ListenerChangeKey mListener = null;

    private boolean[] keyCombination = new boolean[2];

    //Creamos un escucha para detecttar el evento.
    public void setChangeKeyListener(ListenerChangeKey listener){
    	mListener = listener;
    }

    /**
     * keyPressed description
     * Cuando una tecla es presionada.
     * @param event KeyEvent
     */
    public void keyPressed(KeyEvent event) {
 		//crear la validacion para las teclas.	   	
 		switch(event.getKeyCode() ){
 			
 			case KeyEvent.VK_CONTROL:
 					System.out.println("press control");
					keyCombination[0] = true;
 				break;

 			case KeyEvent.VK_F2:
 					System.out.println("press F2");
    				keyCombination[1] = true;
 				break;
 		}

 		//Comprueba que las dos teclas estan presionadas.
    	if( validateCobination() ){
			System.out.println("Validacion de doble presion");
    		mListener.onChange();
    	} 

    }

    /**
     * validateCobination description
     * Valida que las dos teclas esten presionadas.
     * @return boolean
     */
    private boolean validateCobination(){
    	return mListener != null && keyCombination[0] == true  && keyCombination[1] == true ;
    }

    /**
     * keyReleased description
     * Se resetean los cuando se suelta 
     * una tecla
     * @param e KeyEvent
     */
    public void keyReleased(KeyEvent e){
    	keyCombination[0] = false;
    	keyCombination[1] = false;
    }


}