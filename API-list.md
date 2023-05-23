
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
| Order| localhost:8083/carts/{cartId} | GET | | | Get a cart 
| Order| localhost:8083/carts/ | POST | | | Create a new cart 
| Order| localhost:8083/carts/{cartId}/entries | POST | | | Add a product to cart 
| Order| localhost:8083/carts/{cartId}/entries | PUT | | | Update quantity of an line item in cart 
| Order| localhost:8083/carts/{cartId}/entries | DELETE | | | Delete an line item in cart 
| Order| localhost:8083/carts/{cartId}/shipping-address | POST | | | Set shipping address in cart 
| Order| localhost:8083/carts/{cartId}/credit-card | POST | | | Set credit card in cart
| Order| localhost:8083/orders/ | GET | | | Get all orders of a customer
| Order| localhost:8083/orders/ | POST | | | Place an order
| Order| localhost:8083/orders/{orderId} | GET | | | Get single order detail
| Order| localhost:8083/orders/{orderId}/cancel | POST | | | Cancel an order
| Order| localhost:8083/orders/{orderId}/return | POST | | | Return an order