Feature: Discourse

  Scenario: Description of all closed topics
    Given that Carla opened the discourse page
    When she click on the Demo
    And scroll down the page
    Then she should see the description of all closed topics

  Scenario: Quantity of itens for each category
    Given that Carla opened the discourse page
    When she click on the Demo
    And scroll down the page
    Then she should see the quantity of topics for each category

  Scenario: The topic with most views
    Given that Carla opened the discourse page
    When she click on the Demo
    And scroll down the page
    Then she should see the title of the topic with most views