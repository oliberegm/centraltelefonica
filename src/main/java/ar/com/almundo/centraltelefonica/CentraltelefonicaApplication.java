package ar.com.almundo.centraltelefonica;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase raiz
 * @author oliber garcia
 *
 */
public class CentraltelefonicaApplication {

	public static void main(String[] args) {
		CentraltelefonicaApplication ca = new CentraltelefonicaApplication();
		Dispatcher dispatcher = ca.createDispatcher();

		List<Operator> ops = ca.generateOpe(dispatcher);
		Supervisor supervisor = new Supervisor(ops, dispatcher);
		Director director = new Director(supervisor, dispatcher);

		new Monitor(ops, supervisor, director, dispatcher);
		ca.generateCall(dispatcher);

	}
	public List<Operator> generateOpe(Dispatcher dispatcher){
		List<Operator> operarios = new ArrayList<>();
		operarios.add(new Operator(dispatcher, "OP1"));
		operarios.add(new Operator(dispatcher, "OP2"));
		return operarios;
	}

	public Dispatcher createDispatcher(){
		Dispatcher dispatcher = new Dispatcher();
		return dispatcher;
	}
	public void generateCall(Dispatcher dispatcher){
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
