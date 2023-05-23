
| Service        | API                  | Method | No Authentication | Require admin | Note                                                        |
|----------------|----------------------|--------|-------------------|---------------|-------------------------------------------------------------|
| Authentication | localhost:8099/login | POST   | X                 |               |                                                             |
| Authentication | localhost:8099/users | POST   | X                 |               | Using for Customer service to send user credentials when creating/update customer info |
| Product| localhost:8082/products | GET | x |  | Get all products
| Product| localhost:8082/products/{id} | GET | x |  | Get a product details  
| Product| localhost:8082/products/ | POST |  | x | Add a product    
| Product| localhost:8082/products/{id}/productItem | POST |  | x | Add an item to product     
| Product| localhost:8082/products/{id} | PUT |  | x | Update a product 
| Product| localhost:8082/products/{id} | DELETE |  | x | Delete a product 
| Product| localhost:8082/products/{id}/reviews | GET | x| | Get reviews of a product 
| Product| localhost:8082/products/{id}/reviews | POST | | | Add review to a product 
