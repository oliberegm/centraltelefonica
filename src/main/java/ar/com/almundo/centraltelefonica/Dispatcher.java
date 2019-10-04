package ar.com.almundo.centraltelefonica;

import java.util.ArrayList;

/**
 * Maneja la logica de las llamas atendidas con la sincronizacion y bloque para evitar
 * errores de concurrencia
 * @author oliber garcia
 *
 */
public class Dispatcher {
	// manejo una lista de llamas para mantener la secuencia de llamas y dar espera mientras son atendidas
    private ArrayList<Call> call;
    public Dispatcher(){
        this.call = new ArrayList<>();
    }

    /**
     * Permite realizar la recepcion de las llamadas por parte de la central
     * 
     * @param llamada
     */
    public synchronized void entrante( Call llamada){
            call.add(llamada);
            // notificamos a los operarios la llegadas de una llamada
            notifyAll();
    }

    /**
     * realiza la atencion de una llamada para un operario
     * es sincronizado para evitar errores de concurrencia
     * @return el primero de la lista en espera para ser atendido
     *
     */
    public synchronized Call dispatchCall(){
        while(call.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Call llamada = call.remove(0);
        this.notifyAll();
        return llamada;
    }

    /**
     * Indica el numero de llamadas sin ser contestadas
     * @return
     */
    public synchronized Integer numCall(){
        return call.size();
    }

    /**
     * Indica si hay llamadas sin ser atendidas
     * @return
     */
    public synchronized boolean sinAtencion(){
        return !call.isEmpty();
    }
}
