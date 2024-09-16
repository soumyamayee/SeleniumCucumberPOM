#Author 
Feature: Amazon Product Search and Filtering

  
  Scenario: List all Samsung phones with Camera Resolution 20 MP and above, Model Year 2023, Price Range £50 - £100
    Given user on the Amazon UK homepage
    When user navigate to the "Electronics and Computers" category
    And user select "Phones and Accessories"
    And user search for "Samsung phones"
    And user select "Electronics & Photo"
    And user apply the filter "Camera Resolution 20 MP and above"
    And user apply the filter "Model Year 2023"
    And user apply the price range filter "£120 - £150"
    Then user should see a list of Samsung phones that match the specifications
    
    
    
    
    
    
    
    
    
    
#
  #Scenario: No Samsung phones found with the specified filters
    #Given user on the Amazon UK homepage
    #When user navigate to the "Electronics and Computers" category
    #And user select "Phones and Accessories"
    #And user select "Mobile Phones"
    #And user search for "Samsung phones"
    #And user apply the filter "Camera Resolution 50 MP and above"
    #And user apply the filter "Model Year 2023"
    #And user apply the price range filter "£50 - £100"
    #Then user should see a message indicating "No results found"

  #Scenario: Verify the details of a listed Samsung phone
    #Given user have searched for Samsung phones with Camera Resolution 20 MP and above, Model Year 2023, Price Range £50 - £100
    #Given user click on one of the listed Samsung phones
    #Then user should be taken to the product details page
    #And the page should display a camera resolution of at least 20 MP
    #And the page should show that the model year is 2023
    #And the price should be between £50 - £100
#
  #Scenario: Filter Samsung phones by availability (in-stock items only)
    #Given user have applied filters for Camera Resolution 20 MP and above, Model Year 2023, Price Range £50 - £100
    #Given user filter the results by availability "In Stock"
    #Then user should see only the Samsung phones that are available for immediate purchase
#
  #Scenario: Sorting Samsung phones by price after applying filters
    #Given user have filtered the search results to show Samsung phones with Camera Resolution 20 MP and above, Model Year 2023, Price Range £50 - £100
    #When user sort the results by price "low to high"
    #Then the search results should be sorted by price in ascending order
#
  #Scenario: Non-Samsung phones appear in search results
    #Given user have searched for Samsung phones with Camera Resolution 20 MP and above, Model Year 2023, Price Range £50 - £100
    #When the results include phones from other brands
    #Then user should apply the brand filter "Samsung"
    #And the search results should only display Samsung phones
