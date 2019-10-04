package ar.com.almundo.centraltelefonica;

import java.util.concurrent.TimeUnit;

/**
 * Clase de utilidad
 * @author oliber garcia
 *
 */
public class CentralUtils {
    public static final Long MIN = 5000L;
    public static final Long MAX = 10000L;

    public static Long tiempoAtencion(){
        return (long)(Math.random() * ((MIN - MAX) + 1)) + MAX;
    }
    public static void sleep(int seconds) {

	    try {
	        TimeUnit.SECONDS.sleep(seconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

}
