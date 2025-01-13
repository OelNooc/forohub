# Forohub

**Forohub** es una aplicación basada en Spring Boot diseñada para gestionar foros con autenticación y autorización utilizando JWT. Este proyecto utiliza una arquitectura modular y se enfoca en buenas prácticas de desarrollo para garantizar escalabilidad y seguridad.

## Tecnologías Utilizadas

El proyecto emplea las siguientes tecnologías y dependencias:

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.4.1**: Framework para construir aplicaciones robustas y escalables.
  - `spring-boot-starter-data-jpa`: Manejo de la persistencia de datos.
  - `spring-boot-starter-web`: Desarrollo de APIs RESTful.
  - `spring-boot-starter-security`: Implementación de seguridad.
  - `spring-boot-starter-validation`: Validación de datos.
  - `spring-boot-devtools`: Herramientas para facilitar el desarrollo.
- **JWT (JSON Web Tokens)**: Para autenticación basada en tokens.
  - `java-jwt`: Biblioteca para generar y validar JWT.
- **MySQL**: Base de datos utilizada para persistir la información.
- **Flyway**: Migraciones de bases de datos.
- **Lombok**: Reducción de boilerplate mediante anotaciones.
- **Hibernate Validator**: Validación adicional para objetos Java.

## Estructura del Proyecto

El proyecto sigue una estructura modular para organizar sus componentes:

```
com.forohub
├── config
│   ├── JwtFilter
│   ├── SecurityConfigurations
├── controller
│   ├── AuthenticationController
│   ├── TopicController
├── entity
│   ├── Topic
│   ├── User
├── repository
│   ├── TopicRepository
│   ├── UserRepository
├── security
│   ├── CustomUserDetailsService
│   ├── LoginRequest
│   ├── UsernamePasswordAuthenticationRequest
├── service
│   ├── TokenService
│   ├── TopicService
└── ForohubApplication.java
```

### Descripción de las Capas

1. **config**: Contiene configuraciones relacionadas con la seguridad, como `JwtFilter` y las políticas de acceso en `SecurityConfigurations`.
2. **controller**: Controladores REST para manejar solicitudes HTTP.
   - `AuthenticationController`: Maneja la autenticación de usuarios.
   - `TopicController`: Gestión de temas en los foros.
3. **entity**: Entidades que representan las tablas de la base de datos.
4. **repository**: Repositorios JPA para interactuar con la base de datos.
5. **security**: Implementaciones relacionadas con la autenticación y autorización.
6. **service**: Contiene la lógica de negocio, como la generación de tokens en `TokenService`.
7. **ForohubApplication**: Clase principal para ejecutar la aplicación.

## Características Principales

- **Autenticación JWT**: Los usuarios pueden iniciar sesión y recibir un token que les permite acceder a recursos protegidos.
- **Validación de Datos**: Utiliza Hibernate Validator para validar las entradas.
- **Gestión de Temas**: CRUD de temas a través del controlador `TopicController`.
- **Migración de Base de Datos**: Flyway para manejo de esquemas y cambios.

## Requisitos Previos

- **Java 17** o superior.
- **Maven** instalado.
- **MySQL** configurado (asegúrate de que las credenciales estén en `application.properties`).

## Configuración Inicial

1. Clona este repositorio:
   ```bash
   git clone https://github.com/usuario/forohub.git
   cd forohub
   ```

2. Configura las propiedades en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub
   spring.datasource.username=usuario
   spring.datasource.password=contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.flyway.enabled=true
   ```

3. Ejecuta las migraciones con Flyway:
   ```bash
   mvn flyway:migrate
   ```

4. Construye y ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints Principales

### Autenticación
- **POST /auth/login**: Autenticación de usuario y generación de JWT.
  ```json
  {
    "username": "usuario",
    "password": "contraseña"
  }
  ```

### Gestión de Temas
- **GET /topics**: Obtiene todos los temas.
- **POST /topics**: Crea un nuevo tema (requiere token JWT).
  - Header: `Authorization: Bearer <jwt-token>`
  ```json
  {
    "title": "Título del tema",
    "content": "Contenido del tema"
  }
  ```

## Pruebas

Ejecuta las pruebas unitarias con:
```bash
mvn test
```

## Licencia

Este proyecto está licenciado bajo la MIT License.
