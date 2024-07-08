@Booking
Feature: Gestión de reservas

  # Obtener todos los IDs de las reservas
  @GetAll
  Scenario: Obtener todos los IDs de las reservas
    Given que el servicio de usuario está disponible
    When  realiza una solicitud GET al endpoint de reservas
    Then  debería recibir una lista de IDs de reservas

  # Crear una nueva reserva
  @Post
  Scenario: Crear una nueva reserva
    Given que el servicio de usuario está disponible
    When  realiza una solicitud POST al endpoint de reservas con los siguientes detalles:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Jim       | Brown    | 111        | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
    Then  se debe crear un nuevo registro de la reserva


  @Patch
  # Actualizar una reserva parcialmente
  Scenario Outline: Actualizar parcialmente una reserva existente
    Given que el servicio de usuario está disponible
    And   existe una reserva con ID <id>
    When  realiza una solicitud PATCH al endpoint de reservas con ID <id> con los siguientes detalles:
      | firstname | lastname |
      | Marvin    | Mallqui  |
    Then  debería recibir una respuesta con los datos actualizados de la reserva

    Examples:
      | id |
      | 1  |

  # Actualizar una reserva existente
  @Put
  Scenario Outline: Actualizar una reserva existente
    Given que el servicio de usuario está disponible
    And   existe una reserva con ID <id>
    When  realiza una solicitud PUT al endpoint de reservas con ID <id> con los siguientes detalles:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Brandon   | Soto     | 999        | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
    Then  debería recibir una respuesta con los datos actualizados de la reserva

    Examples:
      | id |
      | 1  |

  # Eliminar una reserva existente
  @Delete
  Scenario Outline: Eliminar una reserva existente
    Given que el servicio de usuario está disponible
    And   existe una reserva con ID <id>
    When  realiza una solicitud DELETE al endpoint de reservas con ID <id>
    Then  la reserva con ID <id> debería ser eliminada

    Examples:
      | id |
      | 10 |
