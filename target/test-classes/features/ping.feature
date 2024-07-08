@Ping
Feature: Verificación de estado del servidor

  # Obtener el funcionamineto del Servidor
  @Getping
  Scenario: Comprobar que el servidor está en funcionamiento
    Given que el servicio de usuario se encuentra disponible
    When  realiza una solicitud GET al endpoint de ping
    Then  debería recibir una respuesta indicando que el servidor está en funcionamiento
