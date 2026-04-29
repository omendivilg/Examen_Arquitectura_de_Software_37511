### 6. - Explica cómo la Dependency Rule garantiza que cambiar de MySQL a MongoDB no requiera tocar el use case.

La Dependency Rule establece que las dependencias solo pueden apuntar hacia adentro. Dado que el RegistrarPrestamoUseCase solo conoce la interfaz RepositorioPrestamos, es totalmente ignorante de la tecnología que hay detrás.

Si decides cambiar de MySQL a MongoDB seria: crear una nueva clase en la capa de Frameworks & Drivers llamada MongoPrestamoAdapter que implemente la interfaz original. El caso de uso no se entera del cambio porque sigue recibiendo un objeto que cumple con los mismos métodos (guardar, buscarPorId). No hay que tocar ni una sola línea de código en la lógica de negocio; solo se cambia la pieza de infraestructura en el punto de configuración inicial de la aplicación.

### - ¿Qué patrón de los estudiados en clase aparece implícitamente en este diseño?

El patrón que aparece de forma implícita en este diseño es el Adapter.

El repositorio concreto se encarga de "traducir" o adaptar las llamadas de alto nivel del caso de uso (guardar) a las llamadas de bajo nivel específicas del driver de la base de datos (db.collection().insertOne() o INSERT INTO...). De esta manera, el repositorio actúa como el puente que adapta el mundo exterior (la infraestructura) al lenguaje interno del sistema.
