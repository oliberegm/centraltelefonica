package ar.com.almundo.centraltelefonica;

import java.util.List;

public class Monitor extends Thread {
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
            ops.stream().forEach(o -> System.out.println(o.getNombre()+" "+o.getEstado()));
            System.out.println("SUP "+supervisor.getEstado());
            System.out.println("DIR "+director.getEstado());
            System.out.println("LLL "+dispatcher.numCall());
            System.out.println("------------------------------");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // crear un loger para indicar un error
            }
        }
    }
}
