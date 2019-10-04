package ar.com.almundo.centraltelefonica;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Permite monitorear los empleados
 * @author oliber garcia
 *
 */
public class Monitor extends Thread {
	Logger logger = LoggerFactory.getLogger(Monitor.class);
    private List<Operator> ops;
    private Supervisor supervisor;
    private Director director;
    private Dispatcher dispatcher;

    public Monitor(List<Operator> ops, Supervisor supervisor, Director director, Dispatcher dispatcher) {
        this.ops = ops;
        this.supervisor = supervisor;
        this.director = director;
        this.dispatcher = dispatcher;
        this.start();
    }

    @Override
    public void run() {
        while(true){
            ops.stream().forEach(o -> logger.info(o.getNombre()+" "+o.getEstado()));
            logger.info("SUP "+supervisor.getEstado());
            logger.info("DIR "+director.getEstado());
            logger.info("LLL "+dispatcher.numCall());
            logger.info("------------------------------");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // crear un loger para indicar un error
            }
        }
    }
}
