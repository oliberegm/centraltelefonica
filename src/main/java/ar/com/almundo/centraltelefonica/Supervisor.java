package ar.com.almundo.centraltelefonica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Supervisor extends Operator {
    private List<Operator> operators;
    public Supervisor(List<Operator> operators, Dispatcher dispatcher){
        super(dispatcher, "SUP");
        this.operators = operators;
    }
    @Override
    public void run() {
        while (true) {
            if(operators.stream().filter( f -> f.getEstado().equals(EstadoEnum.ATENDIENDO)).count() == operators.size() &&
                getDispatcher().sinAtencion()){
                System.out.println("Realizar atencion por supervisor");
                try {
                    this.callAttention();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Supervisor: "+super.toString();
    }
}
