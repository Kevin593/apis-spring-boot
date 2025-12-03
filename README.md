# API Bank – Sistema de Gestión de Cuentas y Movimientos (Spring Boot)
🛠️ Tecnologías utilizadas

Java 17+

Spring Boot (Web, JPA, Validation)

Hibernate / JPA

MySQL o PostgreSQL (según configuración)

Lombok (opcional)

Maven
📌 Funcionalidades principales

✔ Gestión de Clientes

Crear, consultar y administrar información de clientes.

✔ Gestión de Cuentas

Crear cuentas asociadas a un cliente.

Consultar cuentas por número o ID.

Manejo de saldo inicial y validaciones.

✔ Gestión de Movimientos

Registrar depósitos y retiros.

Validar saldo disponible (evita saldos negativos).

Operaciones atómicas con @Transactional.

Consultar movimientos por:

Cliente

Fecha

Cuenta específica (ordenados por fecha)

✔ Validaciones

Error personalizado para saldo insuficiente.

Manejo de excepciones en operaciones CRUD.

👤 Autor

Kevin Caiza
Desarrollador Java – Spring Boot – APIs REST
