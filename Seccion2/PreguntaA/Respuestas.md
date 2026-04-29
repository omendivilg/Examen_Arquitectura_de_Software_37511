### 1. Pregunta 2A — Singleton · Registro de auditoría - ¿qué problema concreto de este sistema resuelve tener una sola instancia?

En un sistema distribuido como el de CETYS (donde hay préstamos, pagos y autenticación LDAP), tener una sola instancia resuelve el problema de la integridad de la línea de tiempo. Si todos los procesos mandan sus eventos al mismo objeto, el log mantiene un orden cronológico real de lo que pasó en el sistema.

### - ¿Qué pasaría si hubiera dos instancias simultáneas?

Conflictos de Escritura. Si ambas instancias intentan escribir en el mismo archivo físico al mismo tiempo, el sistema operativo podría bloquear el archivo, causando que uno de los procesos falle o que el log se corrompa.

Información Fragmentada. Podrías terminar con dos archivos de log distintos en carpetas diferentes. Si ocurre un error crítico, el administrador tendría que "armar el rompecabezas" buscando en varios lugares, lo cual rompe la regla del rector de tener un único registro centralizado.

Inconsistencia. Una instancia podría estar configurada con un nivel de detalle y la otra con uno distinto, haciendo que la auditoría sea poco confiable.
