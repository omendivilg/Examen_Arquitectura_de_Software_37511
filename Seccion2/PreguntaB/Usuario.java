// Interfaz común
interface Usuario {
    String[] obtenerPermisos();

    String obtenerVistaPrincipal();
}

// Implementaciones
class Estudiante implements Usuario {
    public String[] obtenerPermisos() {
        return new String[] { "prestar_libro", "reservar_sala" };
    }

    public String obtenerVistaPrincipal() {
        return "Dashboard Estudiante";
    }
}

class Bibliotecario implements Usuario {
    public String[] obtenerPermisos() {
        return new String[] { "inventario", "validar" };
    }

    public String obtenerVistaPrincipal() {
        return "Panel Biblioteca";
    }
}

// La Fábrica
class FabricaDeUsuarios {
    public static Usuario crearUsuario(String tipo) {
        switch (tipo.toLowerCase()) {
            case "estudiante":
                return new Estudiante();
            case "bibliotecario":
                return new Bibliotecario();
            default:
                throw new IllegalArgumentException("Tipo desconocido");
        }
    }
}