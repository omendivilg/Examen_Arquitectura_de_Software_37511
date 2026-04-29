# Respuestas

### 1. Pregunta 2A — Singleton · Registro de auditoría - ¿qué problema concreto de este sistema resuelve tener una sola instancia?

En un sistema distribuido como el de CETYS (donde hay préstamos, pagos y autenticación LDAP), tener una sola instancia resuelve el problema de la integridad de la línea de tiempo. Si todos los procesos mandan sus eventos al mismo objeto, el log mantiene un orden cronológico real de lo que pasó en el sistema.

### - ¿Qué pasaría si hubiera dos instancias simultáneas?

Conflictos de Escritura: Si ambas instancias intentan escribir en el mismo archivo físico al mismo tiempo, el sistema operativo podría bloquear el archivo, causando que uno de los procesos falle o que el log se corrompa.

Información Fragmentada: Podrías terminar con dos archivos de log distintos en carpetas diferentes. Si ocurre un error crítico, el administrador tendría que "armar el rompecabezas" buscando en varios lugares, lo cual rompe la regla del rector de tener un único registro centralizado.

Inconsistencia: Una instancia podría estar configurada con un nivel de detalle y la otra con uno distinto, haciendo que la auditoría sea poco confiable.

### 2. Pregunta 2B — Factory · Creación de usuarios - ¿Qué principio SOLID garantiza esto?

El principio que garantiza esta facilidad de extensión es el Open/Closed Principle (Principio de Abierto/Cerrado).

    Abierto: El sistema está abierto para añadir nuevas funcionalidades (nuevos tipos de usuario como Posgrado o Externos).

    Cerrado: El código que ya funciona (la lógica de préstamos, las vistas, etc.) está cerrado a modificaciones, lo que reduce el riesgo de romper algo que ya sirve al intentar agregar algo nuevo.

### 3. Pregunta 2C — Adapter · Integración con el Catálogo CETYS - si mañana CETYS cambia de proveedor de catálogo a uno con una interfaz completamente diferente, ¿cuánto código habría que modificar? ¿Por qué?

Si mañana CETYS decide cambiar su catálogo por uno totalmente nuevo, la cantidad de código que tendría que modificar es mínima. No tendría que tocar ninguna parte de mi lógica de préstamos, ni mis servicios, ni mis controladores. Lo único que tendría que hacer es crear una nueva clase adaptadora (por ejemplo, AdaptadorNuevoCatalogo) que implemente la misma interfaz CatalogoBiblioteca.

Esto funciona porque el resto de mi sistema está "casado" con la interfaz, no con la implementación concreta. Solo tendría que cambiar una línea de código en el lugar donde instancio el adaptador (o en mi fábrica) para que apunte al nuevo proveedor. Al usar este patrón, protejo mi núcleo de negocio de los cambios externos que yo no puedo controlar, manteniendo el sistema desacoplado y fácil de mantener.

### 4. Pregunta 2D — Builder · Solicitudes de préstamo - ¿por qué conviene que SolicitudPrestamo sea inmutable una vez construida? ¿Qué problemas evitamos?

Es fundamental que la SolicitudPrestamo sea inmutable por varias razones de peso en un sistema académico. Primero, evitamos que un estado de préstamo sea alterado accidentalmente después de haber sido validado. Si el objeto pudiera cambiar sus valores en cualquier momento, correríamos el riesgo de que alguien modifique la fecha de devolución o el ID del estudiante sin pasar por las reglas de negocio necesarias, lo que corrompería la integridad de nuestra base de datos.

Además, al ser inmutable, el objeto es mucho más fácil de seguir en el flujo del programa. Si envío el préstamo a un servicio de notificaciones o a la base de datos, tengo la certeza de que los datos que llegaron son los mismos que se construyeron inicialmente. Esto elimina errores comunes donde una función secundaria modifica el objeto por referencia, causando efectos secundarios difíciles de rastrear. En resumen, la inmutabilidad nos da seguridad y hace que el código sea predecible.

### 5. Pregunta 3A — Análisis de violaciones - Identifica qué principios SOLID viola esta clase y explica por qué en cada caso.

Principio de Responsabilidad Única (SRP): Es la violación más evidente. GestorBiblioteca tiene demasiadas razones para cambiar. Si cambia el formato del PDF, el servidor de correo, la lógica de las multas o el esquema de autenticación, tienes que modificar la misma clase. Una clase debe hacer una sola cosa y hacerla bien.

Principio de Abierto/Cerrado (OCP): La clase está "cerrada" a la extensión y "abierta" a la modificación. Si mañana el rector pide enviar notificaciones por WhatsApp en lugar de Email, o generar reportes en Excel, tienes que abrir el código de GestorBiblioteca y arriesgarte a romper la lógica de préstamos que ya funcionaba.

Principio de Segregación de Interfaces (ISP): Cualquier cliente (como un módulo de consulta rápida para estudiantes) que solo necesite prestarLibro() se ve obligado a depender de métodos que no le interesan, como generarReportePDF() o registrarMulta(). Esto crea acoplamientos innecesarios.

Principio de Inversión de Dependencias (DIP): Seguramente esta clase está instanciando directamente los servicios de Email o PDF. Lo ideal sería que dependiera de abstracciones (interfaces) para que el motor de correo o de base de datos sea intercambiable.

### 6. - ¿Cómo se relaciona esta refactorización con la Dependency Rule de Clean Architecture?

Esta refactorización es el corazón de la Clean Architecture. La Regla de Dependencia establece que las dependencias deben apuntar siempre hacia adentro, hacia el centro donde reside la lógica de negocio (las Reglas de Empresa).

En el diseño original, GestorBiblioteca mezclaba todo. Con el cambio, hemos logrado lo siguiente:

    El Núcleo es independiente: ServicioPrestamos (que sería nuestro caso de uso) ya no sabe si el reporte es un PDF o si la notificación es un correo. Solo conoce las interfaces INotificador o IGeneradorReportes.

    Aislamiento de Infraestructura: Los detalles externos (el servidor LDAP de CETYS, el motor de PDFs, el protocolo de Email) ahora viven en la capa exterior.

    Protección del Negocio: Si el Directorio Estudiantil cambia su tecnología, solo modificamos la implementación de AutenticadorLDAP. El código que decide cómo se presta un libro (la lógica de negocio) permanece intacto y protegido de cambios tecnológicos externos.

Esto permite que el sistema de CETYS sea realmente extensible, tal como lo pidió el rector, permitiendo que la aplicación crezca sin convertirse en un código "frágil" que se rompe con cualquier cambio.

VOY EN LA 3B RECORDAROTIOOOOOOOOOOOO!!!!!!!!!!!!!!!!!!!!
