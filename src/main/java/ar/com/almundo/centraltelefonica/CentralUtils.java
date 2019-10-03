package ar.com.almundo.centraltelefonica;

public class CentralUtils {
    public static final Long MIN = 5000L;
    public static final Long MAX = 10000L;

    public static Long tiempoAtencion(){
        return (long)(Math.random() * ((MIN - MAX) + 1)) + MAX;
    }

}
