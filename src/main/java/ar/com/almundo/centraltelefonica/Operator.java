package ar.com.almundo.centraltelefonica;

public class Operator extends Thread {
    private Long cantidadLlamadasAtendidas;
    private Long totalTiempoConsumido;
    private EstadoEnum estado;
    private Dispatcher dispatcher;
    private String nombre;

    public Operator(Dispatcher dispatcher, String nombre) {
        this.cantidadLlamadasAtendidas = 0L;
        this.totalTiempoConsumido = 0L;
        this.estado = EstadoEnum.DESOCUPADO;
        this.dispatcher = dispatcher;
        this.nombre = nombre;
        this.start();
    }

    /**
     * Permite dar servicio a las llamadas
     * @throws InterruptedException
     */
    public void callAttention() throws InterruptedException {
        Llamada llamada;
        llamada = dispatcher.dispatchCall();
        llamada.atencion();
        estado = EstadoEnum.ATENDIENDO;
        cantidadLlamadasAtendidas++;
        totalTiempoConsumido += CentralUtils.tiempoAtencion();
        Thread.sleep(totalTiempoConsumido);
        estado = EstadoEnum.DESOCUPADO;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.callAttention();
                //Thread.sleep(totalTiempoConsumido);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public Long getCantidadLlamadasAtendidas() {
        return cantidadLlamadasAtendidas;
    }

    public Long getTotalTiempoConsumido() {
        return totalTiempoConsumido;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Atencion "+nombre+": cantidad de llamadas: "+cantidadLlamadasAtendidas+" tiempo promedio: "+totalTiempoConsumido;
    }
}
