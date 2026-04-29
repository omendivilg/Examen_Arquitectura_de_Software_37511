// Interfaz que mi sistema espera
interface CatalogoBiblioteca {
    Libro buscarLibro(String isbn);
}

// Clase externa (Simulando SOAP)
class CatalogoCETYS {
    public String consultarObra(String codigoCETYS, String formato) {
        return "<xml>Resultado de " + codigoCETYS + "</xml>";
    }
}

// Adaptador
class AdaptadorCatalogoCETYS implements CatalogoBiblioteca {
    private CatalogoCETYS apiExterna = new CatalogoCETYS();

    @Override
    public Libro buscarLibro(String isbn) {
        String xml = apiExterna.consultarObra(isbn, "SOAP_v1");
        System.out.println("Traduciendo XML a objeto Libro...");
        return new Libro("Título desde SOAP", isbn);
    }
}