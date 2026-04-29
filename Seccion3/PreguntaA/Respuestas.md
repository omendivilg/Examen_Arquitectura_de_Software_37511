### 5. Pregunta 3A — Análisis de violaciones - Identifica qué principios SOLID viola esta clase y explica por qué en cada caso.

Principio de Responsabilidad Única (SRP): GestorBiblioteca tiene demasiadas razones para cambiar. Si cambia el formato del PDF, el servidor de correo, la lógica de las multas o el esquema de autenticación, tienes que modificar la misma clase. Una clase debe hacer una sola cosa y hacerla bien.

Principio de Abierto/Cerrado (OCP): La clase está "cerrada" a la extensión y "abierta" a la modificación. Si mañana el rector pide enviar notificaciones por WhatsApp en lugar de Email, o generar reportes en Excel, tienes que abrir el código de GestorBiblioteca y arriesgarte a romper la lógica de préstamos que ya funcionaba.

Principio de Segregación de Interfaces (ISP): Cualquier cliente que solo necesite prestarLibro() se ve obligado a depender de métodos que no le interesan, como generarReportePDF() o registrarMulta().

Principio de Inversión de Dependencias (DIP):. Lo ideal sería que dependiera de interfaces para que el motor de correo o de base de datos sea intercambiable.

### - ¿Cómo se relaciona esta refactorización con la Dependency Rule de Clean Architecture?

La Regla de Dependencia establece que las dependencias deben apuntar siempre hacia adentro, hacia el centro donde reside la lógica de negocio.

En el diseño original, GestorBiblioteca mezclaba todo. Con el cambio, hemos logrado lo siguiente:

    El Núcleo es independiente: ServicioPrestamos (que sería nuestro caso de uso) ya no sabe si el reporte es un PDF o si la notificación es un correo. Solo conoce las interfaces INotificador o IGeneradorReportes.

    Aislamiento de Infraestructura: Los detalles externos (el servidor LDAP de CETYS, el motor de PDFs, el protocolo de Email) ahora viven en la capa exterior.

    Protección del Negocio: Si el Directorio Estudiantil cambia su tecnología, solo modificamos la implementación de AutenticadorLDAP. El código que decide cómo se presta un libro (la lógica de negocio) permanece intacto y protegido de cambios tecnológicos externos.

Esto permite que el sistema sea realmente extensible.
