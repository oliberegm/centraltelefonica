package ar.com.almundo.centraltelefonica;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Define al director
 * @author oliber garcia
 *
 */
public class Director extends Operator {
	Logger logger = LoggerFactory.getLogger(Director.class);
    private Supervisor supervisor;
    public Director(Supervisor supervisor, Dispatcher dispatcher){
        super(dispatcher, "Dir");
        this.supervisor = supervisor;
    }
    @Override
    public void run() {
        while (true) {
        	// verificamos si el supervisor esta atendiendo y hay llamas en espera atendemos llamadas
            if(supervisor.getEstado().equals(EstadoEnum.ATENDIENDO) &&
                    getDispatcher().sinAtencion()){
                logger.info("Realizar atencion por Director");
                try {
                    this.callAttention();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            CentralUtils.sleep(1);
        }
    }

    @Override
    public String toString() {
        return "Director: "+super.toString();
    }
}
