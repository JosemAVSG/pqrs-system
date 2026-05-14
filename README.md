# pqrs-system

Sistema de gestión de PQRS (Peticiones, Quejas, Reclamos y Sugerencias) construido con **Spring Boot 3.5.14** y **Java 21**.

---

## 🏗️ Arquitectura

Aplicación backend basada en **Spring Boot** con **Clean Architecture** (Arquitectura Limpia) dividida en capas concéntricas:

```
src/main/java/com/jgtech/pqrs/
├── PqrsSystemApplication.java      # Punto de entrada
├── domain/                         # Capa de Dominio (núcleo)
│   ├── model/
│   │   └── Request.java            # Entidad de dominio con lógica de negocio
│   ├── enums/
│   │   └── RequestStatus.java      # Estados de la solicitud
│   └── repository/
│       └── RequestRepository.java  # Puerto (interfaz del repositorio)
├── application/                    # Capa de Aplicación
│   ├── dto/
│   │   └── CreateRequestDto.java   # DTO de entrada para crear solicitud
│   └── usecase/
│       └── CreateRequestUseCase.java # Caso de uso: crear solicitud
├── infrastructure/                 # Capa de Infraestructura
│   ├── config/
│   │   └── UseCaseConfig.java      # Configuración de beans
│   └── persistence/
│       ├── adapter/
│       │   └── RequestRepositoryAdapter.java # Adaptador del repositorio
│       ├── entity/
│       │   └── RequestEntity.java  # Entidad JPA
│       ├── mapper/
│       │   └── RequestMapper.java    # Mapeador entity ↔ dominio
│       └── repository/
│           └── JpaRequestRepository.java # Repositorio JPA
└── presentation/                   # Capa de Presentación
    └── controller/
        └── RequestController.java  # Controlador REST
```

### Principios de Clean Architecture aplicados

- **Independencia del framework**: El dominio no depende de Spring
- **Testable**: Casos de uso sin dependencias externas
- **UI independiente**: Controlador desacoplado del dominio
- **Base de datos independiente**: Adaptador JPA implementa el puerto
- **Lógica de negocio central**: En el dominio, no en servicios

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
│   │   ├── PqrsSystemApplication.java          # Punto de entrada
│   │   ├── domain/
│   │   │   ├── model/Request.java            # Entidad con lógica de negocio
│   │   │   ├── enums/RequestStatus.java        # Estados de solicitud
│   │   │   └── repository/RequestRepository.java # Puerto
│   │   ├── application/
│   │   │   ├── dto/CreateRequestDto.java       # DTO de entrada
│   │   │   └── usecase/CreateRequestUseCase.java # Caso de uso
│   │   ├── infrastructure/
│   │   │   ├── config/UseCaseConfig.java      # Bean config
│   │   │   └── persistence/
│   │   │       ├── adapter/RequestRepositoryAdapter.java
│   │   │       ├── entity/RequestEntity.java
│   │   │       ├── mapper/RequestMapper.java
│   │   │       └── repository/JpaRequestRepository.java
│   │   └── presentation/
│   │       └── controller/RequestController.java # REST API
│   └── resources/
│       └── application.properties               # Configuración
└── test/
    └── java/com/jgtech/pqrs/
        └── PqrsSystemApplicationTests.java      # Tests
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

2. **Iniciar PostgreSQL** (si no está corriendo):
    ```bash
    # macOS con Homebrew
    brew services start postgresql
    ```

3. **Crear base de datos**:
    ```bash
    createdb pqrs
    ```

4. **Configurar credenciales** en `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/pqrs
    spring.datasource.username=postgres
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

5. **Compilar y ejecutar**
    ```bash
    ./mvnw spring-boot:run
    ```

6. La aplicación estará disponible en `http://localhost:8080`

### Probar el endpoint

```bash
curl -X POST http://localhost:8080/requests \
  -H "Content-Type: application/json" \
  -d '{"title":"Sugerencia","description":"Mejorar la documentación"}'
```

---

## 🧪 Tests

```bash
./mvnw test
```

---

## 📋 API Endpoints

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/requests` | Crear nueva solicitud PQRS |

### Request Body para crear solicitud

```json
{
  "title": "Título de la solicitud",
  "description": "Descripción detallada"
}
```

### Response

```json
{
  "id": 1,
  "title": "Título de la solicitud",
  "description": "Descripción detallada",
  "status": "PENDING",
  "createdAt": "2024-01-15T10:30:00"
}
```

---

## 🚧 Estado del proyecto

**Implementado con Clean Architecture.** Componentes completados:

- ✅ Modelo de dominio con lógica de negocio y transiciones de estado
- ✅ Puertos (interfaces) del repositorio
- ✅ Casos de uso
- ✅ Adaptadores de persistencia con JPA
- ✅ DTOs de entrada
- ✅ Mapeadores entity ↔ dominio
- ✅ Configuración de beans
- ✅ Controlador REST

Pendiente de implementar:

- [ ] Endpoints GET (listar, buscar por ID)
- [ ] Endpoints PUT (transiciones de estado: resolver, rechazar, iniciar revisión)
- [ ] Manejo de excepciones global
- [ ] Seguridad / autenticación
- [ ] Validación de entrada con Bean Validation
- [ ] Documentación Swagger/OpenAPI
- [ ] Docker / Docker Compose

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Haz fork del proyecto
2. Crea una rama descriptiva (`feature/correo-soporte`)
3. Realiza los cambios
4. Envía un Pull Request