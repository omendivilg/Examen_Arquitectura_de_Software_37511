public class SolicitudPrestamo {
    private final String estudiante;
    private final String libro;
    private final java.util.Date fechaDevolucion;
    private final String notasEspeciales;

    // Constructor privado
    private SolicitudPrestamo(Builder builder) {
        this.estudiante = builder.estudiante;
        this.libro = builder.libro;
        this.fechaDevolucion = builder.fechaDevolucion;
        this.notasEspeciales = builder.notasEspeciales;
    }

    public static class Builder {
        private String estudiante;
        private String libro;
        private java.util.Date fechaDevolucion;
        private String notasEspeciales;

        public Builder conEstudiante(String estudiante) {
            this.estudiante = estudiante;
            return this;
        }

        public Builder conLibro(String libro) {
            this.libro = libro;
            return this;
        }

        public Builder conFecha(java.util.Date fecha) {
            this.fechaDevolucion = fecha;
            return this;
        }

        public SolicitudPrestamo construir() {
            if (estudiante == null || libro == null || fechaDevolucion == null) {
                throw new IllegalStateException("Faltan campos obligatorios");
            }
            return new SolicitudPrestamo(this);
        }
    }
}