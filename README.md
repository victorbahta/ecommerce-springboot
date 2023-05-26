# Team

| **#** | **Task**| **Done by**        |
|-------|--|-------------------|
| 1     | Order & Shopping cart service | Victor Bahta Teka |
| 2     | Authentication service | Manh Tien Dinh              |
| 3     | Customer service | Tushar Kumar Ghosh            |
| 4     | Product service | Phuong Thao Do              |


# Service ports

| **Service** | **Port** |
|--|--|
| AuthenticationService | 8080 |
| CustomerService | 8081 |
| ProductService | 8082 |
| OrderService | 8083 |
| EurekaService | 8761 |
| ConfigurationService | 8088 |

# API endpoints list

| Service        | API                  | Method | No Authentication | Require admin | Note                                                        |
|----------------|----------------------|--------|-------------------|---------------|-------------------------------------------------------------|
| Authentication | localhost:8099/login | POST   | X                 |               |                                                             |
| Authentication | localhost:8099/users | POST   | X                 |               | Using for Customer service to send user credentials when creating/update customer info |
| Product| localhost:8082/products | GET | x |  | Get all products
| Product| localhost:8082/products/{id} | GET | x |  | Get a product details  
| Product| localhost:8082/products | POST |  | x | Add a product    
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
| Order| localhost:8083/orders/{orderId}/cancel | PUT | | | Cancel an order
| Order| localhost:8083/orders/{orderId}/return | PUT | | | Return an order| Order| localhost:8083/orders/{orderId}/cancel | PUT | | | Cancel an order| Order| localhost:8083/orders/{orderId}/status?status=Shipped | PUT | | | Change order status
| Customer| http://localhost:8081/customers  | GET | | X | Fetch all customer
| Customer| http://localhost:8081/customers/{customerId}  | GET | | | Fetch a customer
| Customer| http://localhost:8081/customers/{customerId}   | PUT| | | Update customer
| Customer| http://localhost:8081/customers   | POST| X | | Save customer
| Customer| http://localhost:8081/customers/{customerId}/shipping-addresses | GET| | | Fetch all shipping address for a specific customer
| Customer| http://localhost:8081/customers/{customerId}/credit-cards | GET| | | Fetch all credit card for a specific customer
| Customer| http://localhost:8081/customers/{customerId}/credit-cards/{creditCardId} | GET| | | Fetch credit card by credit card id for a specific customer


