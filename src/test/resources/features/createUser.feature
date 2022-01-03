Feature: Create user

  As a user of the system I need to generate a new user so that I can use the service

  Scenario: Correct Creation of new User

    Given a person is on the user creation page and enters the username "morpheus" and job "leader"
    When this person sends the service request
    Then the person should be able to view a message in response