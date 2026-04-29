public class AuditoriaLogger {
    private static AuditoriaLogger instancia;

    // Constructor privado: impide el uso de 'new'
    private AuditoriaLogger() {
        System.out.println("Inicializando archivo de log centralizado...");
    }

    public static synchronized AuditoriaLogger obtenerInstancia() {
        if (instancia == null) {
            instancia = new AuditoriaLogger();
        }
        return instancia;
    }

    public void registrar(String evento, String usuario) {
        String fecha = java.time.LocalDateTime.now().toString();
        System.out.println("[AUDIT - " + fecha + "] Usuario: " + usuario + " | Evento: " + evento);
    }
}