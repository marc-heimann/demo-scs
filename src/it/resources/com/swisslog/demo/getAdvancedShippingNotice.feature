Feature: The client calls the swisslog REST API to retrieve an advancedShippingNotice.

  Scenario Outline: client requests asn
    Given The client searches for asn '<searchTerm>'
    When Do GET request
    Then The client receives a JSON response with shipNoticeNumber: '<result>'
    
    Examples:
    | searchTerm 	| result	|		
		| 1 					| 4			|   
    | 2						| 8			|
    | 3						| 12		|
    | 4						| 16		|
    | 5						| 20		|
    | 6						| 24		|
    | 7						| 28		|
    | 8						| 32		|