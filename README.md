# pqrs-system

Sistema de gestión de PQRS (Peticiones, Quejas, Reclamos y Sugerencias) construido con **Spring Boot 3.5.14** y **Java 21**.

---

## 🏗️ Arquitectura

Aplicación backend basada en **Spring Boot** con una arquitectura de dominio limpio para la gestión completa del ciclo de vida de solicitudes PQRS.

### Stack tecnológico

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.14 |
| Maven | 3.x |
| PostgreSQL | Runtime |
| Lombok | Opcional |

### Dependencias principales

- **spring-boot-starter-web** — Capa REST
- **spring-boot-starter-data-jpa** — Persistencia con JPA/Hibernate
- **spring-boot-starter-validation** — Validación de entrada
- **postgresql** — Base de datos relacional
- **lombok** — Reducción de boilerplate

---

## 📦 Estructura del proyecto

```
src/
├── main/
│   ├── java/com/jgtech/pqrs/
│   │   ├── PqrsSystemApplication.java          # Punto de entrada de la aplicación
│   │   └── domain/
│   │       ├── model/
│   │       │   └── Request.java                 # Modelo de dominio PQRS
│   │       └── enums/
│   │           └── RequestStatus.java           # Estados posibles de una solicitud
│   └── resources/
│       └── application.properties               # Configuración de la aplicación
└── test/
    └── java/com/jgtech/pqrs/
        └── PqrsSystemApplicationTests.java      # Tests de integración
```

---

## 📋 Modelo de dominio

### Request (Solicitud PQRS)

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | `Long` | Identificador único |
| `title` | `String` | Título de la solicitud |
| `description` | `String` | Descripción detallada |
| `status` | `RequestStatus` | Estado actual |
| `createdAt` | `LocalDateTime` | Fecha de creación |

### Estados de una solicitud (`RequestStatus`)

| Estado | Descripción |
|---|---|
| `PENDING` | Solicitud creada, pendiente de revisión |
| `IN_REVIEW` | En proceso de revisión |
| `RESOLVED` | Resuelta satisfactoriamente |
| `REJECTED` | Rechazada |

### Transiciones de estado

| Método | Desde | Hacia |
|---|---|---|
| `startReview()` | `PENDING` | `IN_REVIEW` |
| `resolve()` | `IN_REVIEW` | `RESOLVED` |
| `reject()` | `IN_REVIEW` | `REJECTED` |

Todas las transiciones incluyen validaciones de estado para evitar cambios inválidos (ej: resolver una solicitud ya resuelta).

---

## 🚀 Ejecución local

### Requisitos previos

- **Java 21+**
- **Maven 3.x**
- **PostgreSQL** (configurar credenciales en `application.properties`)

### Pasos

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/<usuario>/pqrs-system.git
   cd pqrs-system
   ```

2. **Configurar la base de datos** en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/pqrs_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Compilar y ejecutar**
   ```bash
   ./mvnw spring-boot:run
   ```

4. La aplicación estará disponible en `http://localhost:8080`

---

## 🧪 Tests

```bash
./mvnw test
```

---

## 🚧 Estado del proyecto

**En desarrollo activo.** El modelo de dominio está definido y funcional. Pendiente de implementar:

- Controladores REST (API endpoints)
- Repositorio JPA (`RequestRepository`)
- Servicios de negocio
- DTOs y mapeadores
- Manejo de excepciones global
- Seguridad / autenticación
- Documentación Swagger/OpenAPI
- Docker / Docker Compose

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Haz fork del proyecto
2. Crea una rama descriptiva (`feature/correo-soporte`)
3. Realiza los cambios
4. Envía un Pull Request