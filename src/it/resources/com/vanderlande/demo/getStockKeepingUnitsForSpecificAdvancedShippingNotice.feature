Feature: The client requests the StockKeepingUnits assigned to a specified asnId.

  Scenario Outline: client posts a new asnId and calls the endpoint to receive the StockKeepingUnits
  
    Given The client requests the StockKeepingUnits for an asn with the id: '<asnId>'    
    When Do GET request    
    Then The client receives a JSON response with the result of: '<resultJSON>'
        
    Examples:
    | asnId		| resultJSON	|
    | 5				| " { 'registrationStatus': 'CREATED', 'postedAsn': { 'asnId': 100, 'purchaseOrderNumber': 2001, 'shipNoticeNumber': 3001, 'destinationLocation': { 'country': 'NL', 'state': '', 'city': 'Veghel', 'street': 'teststreet', 'zipCode': 'NL-56234', 'number': '12b' }, 'supplier': { 'id': 1, 'name': 'Superduper Supplier' }, 'shipDate': 1562501518, 'deliveryDate': 1562674318, 'skuIds': [ 0, 375, 668, 5, 6 ], 'links': [] } } " |   
    | 6				| " { 'registrationStatus': 'CREATED', 'postedAsn': { 'asnId': 100, 'purchaseOrderNumber': 2001, 'shipNoticeNumber': 3001, 'destinationLocation': { 'country': 'VL', 'state': '', 'city': 'Veghel', 'street': 'teststreet', 'zipCode': 'NL-56234', 'number': '12b' }, 'supplier': { 'id': 1, 'name': 'Superduper Supplier' }, 'shipDate': 1562501518, 'deliveryDate': 1562674318, 'skuIds': [ 0, 375, 668, 5, 6 ], 'links': [] } } " |