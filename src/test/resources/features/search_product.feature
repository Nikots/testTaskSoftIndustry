Feature: Product Search

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
### Available products: "orange", "apple", "pasta", "cola"
### Prepare Positive and negative scenarios

  Scenario Outline: Returned product list contains expected title
    When he searches for '<item>'
    Then the API should return status 200
    Then he sees displayed results contain title '<title>'
    Examples:
      | item  | title                            |
      | apple | Apple Bandit Cider classic apple |
      | cola  | Pepsi Cola fles                  |

  Scenario Outline: Returned product list does not contain other's product title
    When he searches for '<item>'
    Then he sees displayed results does not contain title '<title>'
    Examples:
      | item   | title                            |
      | pasta  | Apple Bandit Cider classic apple |
      | orange | Pepsi Cola fles                  |

  Scenario: Search for not existed products
    When he searches for 'book'
    Then the API should return status 404
    And the API should return error message 'Not found'
