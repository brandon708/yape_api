¡Entendido! Aquí está todo el contenido del `README.md` como un solo bloque de código, listo para que lo copies y pegues directamente en tu archivo:


# Yape API

Este proyecto contiene pruebas automatizadas para la API de Yape utilizando Cucumber y Serenity BDD.

## Descripción

Este repositorio está dedicado a las pruebas automatizadas de la API de Yape, un servicio de pagos móviles muy utilizado en Perú. Las pruebas están diseñadas para garantizar el correcto funcionamiento de los principales endpoints de la API, incluyendo autenticación de usuarios, gestión de reservas y verificación del estado del servidor.

## Funcionalidades

- **Autenticación de Usuario**: Pruebas para verificar el proceso de autenticación y autorización de usuarios.
- **Gestión de Reservas**: Incluye pruebas para la creación, actualización y eliminación de reservas.
- **Verificación del Estado del Servidor**: Pruebas para asegurar que el servidor responda correctamente a las solicitudes.

## Estructura del Proyecto
```markdown
src
├── main
│   └── java
│       └── com
│           └── yape
│               └── api
│                   ├── endpoint
│                   │   └── BaseUrl.java
│                   ├── questions
│                   │   ├── BookingIdsQuestions.java
│                   │   ├── StatusQuestions.java
│                   │   └── TokenQuestion.java
│                   ├── runners
│                   │   └── CucumberTestSuite.java
│                   └── tasks
│                       ├── Autenticar.java
│                       ├── CrearReserva.java
│                       ├── EliminarReservaID.java
│                       ├── ObtenerReserva.java
│                       ├── ObtenerReservaID.java
│                       ├── ActualizarReserva.java
│                       ├── ActualizarParcialReserva.java
│                       └── VerificarEstadoServidor.java
└── test
    └── resources
        └── features
            └── yape_api.feature
```

## Prerrequisitos

Para ejecutar este proyecto, asegúrate de tener instalado:

- **Java JDK 8 o superior**
- **Maven**
- **IDE de tu preferencia (IntelliJ IDEA, Eclipse, etc.)**

## Configuración

1. Clona este repositorio:

    ```bash
    git clone https://github.com/tuusuario/yape_api.git
    ```

2. Importa el proyecto en tu IDE.

3. Configura las variables de entorno necesarias en `src/main/resources/environment.properties`:

    ```properties
    restapi.baseurl=http://url_de_tu_api_yape
    ```

4. Ejecuta las pruebas desde tu IDE o mediante Maven:

    ```bash
    mvn clean verify
    ```

## Tecnologías Utilizadas

- **Java**
- **Cucumber**
- **Serenity BDD**
- **Maven**
- **Gson**
- **Hamcrest**

## Contribuciones

¡Las contribuciones son bienvenidas! Si encuentras algún problema o tienes alguna sugerencia, por favor crea un *issue* o envía un *pull request*.

## Autor

Brandon Soto Mallqui

## Licencia

Este proyecto está bajo la licencia MIT. Ver el archivo [LICENSE](./LICENSE) para más detalles.
