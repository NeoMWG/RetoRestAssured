Feature: Single User Information

  As a user I want to obtain the unique user information that has been registered

  Scenario: Get User Information

    Given someone has pre-registered
    When this person sends the request
    Then the person should be able to get the registered information