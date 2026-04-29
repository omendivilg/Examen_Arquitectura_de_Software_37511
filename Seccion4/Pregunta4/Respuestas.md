### Pregunta 4 — Flujo completo - Identificar una decisión arquitectónica que tomaste y justificar por qué es la correcta.

Use esta forma debido a que la validación completa del objeto SolicitudPrestamo (a través del Builder) ocurra antes de realizar cualquier llamada al PaymentAdapter
En un sistema distribuido, las llamadas a sistemas externos son costosas y propensas a errores. Si el objeto de préstamo está mal formado (por ejemplo, falta la fecha de devolución), no tiene sentido iniciar una transacción bancaria de cobro de fianza.

| Paso del Flujo     | Patrón Aplicado | Nivel C4                    | Rol en la operación                                                                              |
| :----------------- | :-------------- | :-------------------------- | :----------------------------------------------------------------------------------------------- |
| **Identificación** | `Factory`       | N3 (Componente)             | Crea el tipo de usuario correcto sin que el controlador sepa si es Estudiante o Posgrado.        |
| **Preparación**    | `Builder`       | N3 (Componente)             | Asegura que la solicitud tenga todos los datos obligatorios antes de intentar cobrar.            |
| **Transacción**    | `Adapter`       | N2 (Sistema Externo)        | Permite que el sistema hable con el banco usando una interfaz limpia, ocultando el REST externo. |
| **Trazabilidad**   | `Singleton`     | N2/N3 (Contenedor/Servicio) | Garantiza que tanto el inicio como el fin de la operación queden en el único log exigido.        |
