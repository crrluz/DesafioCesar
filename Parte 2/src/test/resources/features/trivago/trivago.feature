Feature: Site Trivago should all deals

  Scenario: All Hotels and Deals for Natal
    Given that Carla opened the trivago page
    When she searches individual rooms in hotels that are located in Natal
    Then she should see all Deals for that place

  Scenario: Should see the name `All Hotels and Deals for Natal
    Given that Carla opened the trivago page
    When she searches individual rooms in hotels that are located in Natal
    Then she should see all Deals for that place