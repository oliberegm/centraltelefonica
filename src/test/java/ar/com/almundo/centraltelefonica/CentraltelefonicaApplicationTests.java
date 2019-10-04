package ar.com.almundo.centraltelefonica;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

public class CentraltelefonicaApplicationTests {

	/**
	 * Test para verificar el funcionamient cuando llegan llamas en simultaneo
	 * @throws Exception
	 */
	@Test
	public void callConcurrency() throws Exception {
		Dispatcher dispatcher = this.createDispatcher();
		// creamos una lista de operadores
		List<Operator> ops = this.generateOpe(dispatcher);
		Supervisor supervisor = new Supervisor(ops, dispatcher);
		Director director = new Director(supervisor, dispatcher);
		// muestra mensajes con los estados de los empleados
		new Monitor(ops, supervisor, director, dispatcher);
		this.generateCall(dispatcher);
		CentralUtils.sleep(4);
		// verificamos que esten todos full trabajo
		Assert.assertEquals(EstadoEnum.ATENDIENDO, supervisor.getEstado());
		Assert.assertEquals(EstadoEnum.ATENDIENDO, director.getEstado());

	}
	/**
	 * Genera Operadores
	 * @param dispatcher
	 * @return
	 */
	private List<Operator> generateOpe(Dispatcher dispatcher){
		List<Operator> operarios = new ArrayList<>();
		operarios.add(new Operator(dispatcher, "OP1"));
		operarios.add(new Operator(dispatcher, "OP2"));
		return operarios;
	}
	/**
	 * Crea el manejador de las llamadas
	 * @return
	 */
	private Dispatcher createDispatcher(){
		Dispatcher dispatcher = new Dispatcher();
		return dispatcher;
	}
	/**
	 * Genera llamas
	 * @param dispatcher
	 */
	private void generateCall(Dispatcher dispatcher){
		dispatcher.entrante(new Call("1"));
		dispatcher.entrante(new Call("2"));
		dispatcher.entrante(new Call("3"));
		dispatcher.entrante(new Call("4"));
		dispatcher.entrante(new Call("5"));
		dispatcher.entrante(new Call("6"));
		dispatcher.entrante(new Call("7"));
		dispatcher.entrante(new Call("8"));
		dispatcher.entrante(new Call("9"));
		dispatcher.entrante(new Call("10"));
		//return dispatcher;

	}
	

}
