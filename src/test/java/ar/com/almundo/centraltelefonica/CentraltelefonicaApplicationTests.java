package ar.com.almundo.centraltelefonica;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class CentraltelefonicaApplicationTests {

	@Test
	public void callConcurrency() {
		Dispatcher dispatcher = this.createDispatcher();

		List<Operator> ops = this.generateOpe(dispatcher);
		Supervisor supervisor = new Supervisor(ops, dispatcher);
		Director director = new Director(supervisor, dispatcher);

		new Monitor(ops, supervisor, director, dispatcher);
		this.generateCall(dispatcher);
		//Assert.assertEquals(EstadoEnum.ATENDIENDO, supervisor.getEstado());
		//Assert.assertEquals(EstadoEnum.ATENDIENDO, director.getEstado());

	}
	private List<Operator> generateOpe(Dispatcher dispatcher){
		List<Operator> operarios = new ArrayList<>();
		operarios.add(new Operator(dispatcher, "OP1"));
		//operarios.add(new Operator(dispatcher, "OP2"));
		return operarios;
	}

	private Dispatcher createDispatcher(){
		Dispatcher dispatcher = new Dispatcher();
		return dispatcher;
	}
	private void generateCall(Dispatcher dispatcher){
		dispatcher.entrante(new Llamada("1"));
		dispatcher.entrante(new Llamada("2"));
		dispatcher.entrante(new Llamada("3"));
		dispatcher.entrante(new Llamada("4"));
		dispatcher.entrante(new Llamada("5"));
		dispatcher.entrante(new Llamada("6"));
		dispatcher.entrante(new Llamada("7"));
		dispatcher.entrante(new Llamada("8"));
		dispatcher.entrante(new Llamada("9"));
		dispatcher.entrante(new Llamada("10"));
		//return dispatcher;

	}

}
