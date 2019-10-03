package ar.com.almundo.centraltelefonica;

public class Director extends Operator {
    private Supervisor supervisor;
    public Director(Supervisor supervisor, Dispatcher dispatcher){
        super(dispatcher, "Dir");
        this.supervisor = supervisor;
    }
    @Override
    public void run() {
        while (true) {
            if(supervisor.getEstado().equals(EstadoEnum.ATENDIENDO) &&
                    getDispatcher().sinAtencion()){
                System.out.println("Realizar atencion por Director");
                try {
                    this.callAttention();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println(supervisor.getEstado().equals(EstadoEnum.ATENDIENDO) && getDispatcher().sinAtencion());
        }
    }

    @Override
    public String toString() {
        return "Director: "+super.toString();
    }
}
