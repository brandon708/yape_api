@Auth
Feature: Autenticación de usuario

  # Creamos el Token
  @Post
  Scenario: Crear un token de autenticación válido
    Given el usuario tiene las credenciales correctas
    When  realiza una solicitud POST al endpoint de autenticación
    Then  debería recibir un token de autenticación válido
