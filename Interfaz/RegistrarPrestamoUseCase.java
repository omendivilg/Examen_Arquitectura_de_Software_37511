public class RegistrarPrestamoUseCase {
    private final RepositorioPrestamos repositorio;
    private final AuditoriaLogger logger;

    public RegistrarPrestamoUseCase(RepositorioPrestamos repositorio) {
        this.repositorio = repositorio;
        this.logger = AuditoriaLogger.obtenerInstancia();
    }

    public void ejecutar(String isbn, String alumnoId) {
        // Lógica de negocio independiente de la DB
        if (repositorio.existeLibro(isbn)) {
            SolicitudPrestamo prestamo = new SolicitudPrestamo.Builder()
                    .conEstudiante(alumnoId)
                    .conLibro(isbn)
                    .conFecha(new java.util.Date())
                    .construir();

            repositorio.guardar(prestamo);
            logger.registrar("Préstamo exitoso", alumnoId);
        }
    }
}

// Interfaz del repositorio (Capa de Dominio/Use Case)
interface RepositorioPrestamos {
    void guardar(SolicitudPrestamo p);

    boolean existeLibro(String isbn);
}