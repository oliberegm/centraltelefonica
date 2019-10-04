package ar.com.almundo.centraltelefonica;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representa a un supervisor
 * 
 * @author oliber garcia
 *
 */
public class Supervisor extends Operator {
	Logger logger = LoggerFactory.getLogger(Supervisor.class);
	private List<Operator> operators;

	public Supervisor(List<Operator> operators, Dispatcher dispatcher) {
		super(dispatcher, "SUP");
		this.operators = operators;
	}

	@Override
	public void run() {
		while (true) {
			// se determina que todos los operadores esten atendiendo y existan llamas
			// pendientes para poder atender
			if (operators != null && operators.stream().filter(f -> f.getEstado().equals(EstadoEnum.ATENDIENDO))
					.count() == operators.size() && getDispatcher().sinAtencion()) {
				logger.info("Realizar atencion por supervisor");
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
		return "Supervisor: " + super.toString();
	}
}
