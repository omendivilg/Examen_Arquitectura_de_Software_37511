### 3. Pregunta 2C — Adapter · Integración con el Catálogo CETYS - si mañana CETYS cambia de proveedor de catálogo a uno con una interfaz completamente diferente, ¿cuánto código habría que modificar? ¿Por qué?

Si CETYS decide cambiar su catálogo por uno totalmente nuevo, la cantidad de código que tendría que modificar es mínima. No tendría que tocar ninguna parte de mi lógica de préstamos, ni mis servicios, ni mis controladores. Lo único que tendría que hacer es crear una nueva clase que implemente la misma interfaz CatalogoBiblioteca.

Esto funciona porque el resto de mi sistema está "casado" con la interfaz, no con la implementación concreta. Solo tendría que cambiar una línea de código en el lugar donde instancio el adaptador (o en mi fábrica) para que apunte al nuevo proveedor. Al usar este patrón, protejo mi núcleo de negocio de los cambios externos que yo no puedo controlar, manteniendo el sistema desacoplado y fácil de mantener.
