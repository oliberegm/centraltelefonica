package ar.com.almundo.centraltelefonica;

import java.time.LocalDateTime;

/**
 * Clase que permite representar una llamada de un cliente
 * 
 * @author oliber garcia
 */
public class Call {
    private String identificador;
    private LocalDateTime horaEntrante;
    private LocalDateTime horaAtencion;
    private LocalDateTime horaFinalizada;

    public Call(){
        this.entrante();
    }
    public Call(String identificador){
        this.entrante();
        this.identificador = identificador;
    }

    /**
     * actualiza la hora entrante de llamada
     */
    public void entrante(){
        this.horaEntrante = LocalDateTime.now();
    }

    /**
     * actualiza la hora por la atencion
     */
    public void atencion(){
        this.horaAtencion = LocalDateTime.now();
    }

    /**
     * actualiza la hora cuando a sido atendido
     */
    public void finalizada(){
        this.horaFinalizada = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Identificador: "+identificador+" Entrada: "+horaEntrante+" atencion: "+horaAtencion+" finalizada: "+horaFinalizada;
    }
}
