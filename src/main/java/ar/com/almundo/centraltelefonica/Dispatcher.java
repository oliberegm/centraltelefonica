package ar.com.almundo.centraltelefonica;

import org.springframework.stereotype.Component;

import java.util.ArrayList;


public class Dispatcher {
    private ArrayList<Llamada> call;
    public Dispatcher(){
        this.call = new ArrayList<>();
    }

    /**
     * Permite realizar la recepcion de las llamadas por parte de la central
     * @param llamada
     */
    public synchronized void entrante( Llamada llamada){
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
    public synchronized Llamada dispatchCall(){
        while(call.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Llamada llamada = call.remove(0);
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
     * Indica si ha llamadas sin ser atendidas
     * @return
     */
    public synchronized boolean sinAtencion(){
        return !call.isEmpty();
    }
}
