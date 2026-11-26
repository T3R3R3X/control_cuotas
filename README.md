# Control de cuotas

Api para el control de cuotas de estudiantes para academias deportivas. Mejorando el seguimiento de pagos, inscripciones
y la gestión e información de los estudiantes.

## Tecnologías

- Java 25
- Spring Boot 4.0.10
- Spring MVC
- Lombok
- Spring Validation
- SpringDoc OpenAPI / Swagger UI
- Maven
- JUnit y Spring Boot Test
- MapStruct
- Validation
- Spring Data JPA

## Instalación

1. Clona el repositorio:

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd control_cuotas
   ```

2. Verifica que Java esté disponible:

   ```bash
   java -version
   ```

3. Ejecuta la aplicación con el wrapper de Maven:

   En Windows:

   ```bash
   .\mvnw.cmd spring-boot:run
   ```

   En Linux o macOS:

   ```bash
   ./mvnw spring-boot:run
   ```

La aplicación se inicia, por defecto, en `http://localhost:8080`.

## Configuración

La configuración principal se encuentra en `src/main/resources/application.yaml`:

```yaml
spring:
  application:
    name: control_cuotas
```

## Documentación Swagger

Con la aplicación en ejecución:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- Especificación OpenAPI: `http://localhost:8080/v3/api-docs`

## Endpoints actuales

Los endpoints se encuentran bajo la ruta base `/api/v1/estudiantes`, `/api/v1/categorias`:

| Método   | Ruta                                  | Descripción                                                 |
|----------|---------------------------------------|-------------------------------------------------------------|
| `GET`    | `/api/v1/estudiantes`                 | Lista estudiantes paginados (`pagina`, `tamanio`, `estado`) |
| `GET`    | `/api/v1/estudiantes/{id}`            | Obtiene un estudiante por ID                                |
| `POST`   | `/api/v1/estudiantes`                 | Crea un estudiante                                          |
| `PUT`    | `/api/v1/estudiantes/{id}`            | Actualiza un estudiante                                     |
| `DELETE` | `/api/v1/estudiantes/{id}/desactivar` | Desactiva un estudiante                                     |
| `PUT`    | `/api/v1/estudiantes/{id}/activar`    | Reactiva un estudiante                                      |
| `GET`    | `/api/v1/categorias`                  | Lista categorias                                            |
| `GET`    | `/api/v1/categorias/{id}`             | Obtiene una categoria por ID                                |
| `POST`   | `/api/v1/categorias`                  | Crea una categoria                                          | 

El listado acepta los parámetros opcionales `pagina` (desde `0`), `tamanio` (mayor que `0`) y `estado` (`true` para
activos o `false` para inactivos). Por ejemplo:

```text
GET /api/v1/estudiantes?pagina=0&tamanio=10&estado=true
```

Si `pagina` es negativo o `tamanio` es menor o igual a `0`, la API responde `400 Bad Request` con el título
`Parámetros de paginación inválidos`.

Ejemplo de cuerpo JSON para crear o actualizar un estudiante:

```json
{
  "nombre": "Ana",
  "apellido": "García",
  "dni": "12345678"
}
```

El estado y las fechas se administran en el servidor; no deben incluirse en la solicitud.

## Arquitectura

El proyecto utiliza una arquitectura basada en capas y separada por funcionalidades:

- `controller`: expone los endpoints REST.
- `service`: define la lógica de negocio.
- `repository`: contendrá el acceso a datos.
- `model`: representa las entidades de dominio.
- `dto`: contendrá los objetos de transferencia de datos.
- `config`: contiene la configuración de OpenAPI y otras configuraciones generales.

## Pruebas

Ejecuta todas las pruebas con:

```bash
.\mvnw.cmd test
```

## Contacto

- Email: [mendoza.e.matias@gmail.com](mailto:mendoza.e.matias@gmail.com)
