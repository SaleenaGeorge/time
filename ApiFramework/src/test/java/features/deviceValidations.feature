Feature: Validating device API'S
@AddDevice
Scenario Outline: Verify if device is being Successfully registered using AddDeviceAPI
    Given Register device pay load with "<access_id>" "<display_name>" "<station_type>" "<user_identifier>" "<user_identifiertype>" "<Contact_list>"
    When  user calls "RegisterDeviceAPI" with "Post" request
    Then  the API call is success with status code 200
    And   "success" in response body is "true"
    And   verify device_id created maps to "<display_name>" using "GetDeviceAPI"
    
    
    Examples:
    |access_id   |display_name  |station_type  |user_identifier|user_identifiertype|Contact_list|
    |ex1255    |ex_ex55  |extron      |saleena_mgeorge|LOGIN            |saleena.george@yuja.com|
    
@DeleteDevice   
Scenario: Verify if delete device functionality is working
    Given Delete device request
    When  user calls "DeleteDeviceAPI" with "delete" request
    Then  the API call is success with status code 200
    And   "success" in response body is "true"   