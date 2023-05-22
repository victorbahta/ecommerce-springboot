# Customer service

## API List

Fetch all customer
```bash
[GET] http://localhost:8081/customers 
```
Fetch a customer
```bash
[POST] http://localhost:8081/customers/{customerId}
```
Save customer
```bash
[POST] http://localhost:8081/customers 
```
Update customer
```bash
[PUT] http://localhost:8081/customers/{customerId}
```

## JSON Payload of customer

```JavaScript
{
    "firstName": "Frank",
    "lastName": "Jhon",
    "email": "frank.123@gmail.com",
    "billingAddress": {
        "street": "4th street",
        "city": "Fairfield",
        "state": "Iowa",
        "zip": "52557"
    },
    "shippingAddress": [
        {
            "street": "4th street",
            "city": "Fairfield",
            "state": "Iowa",
            "zip": "52557",
            "isDefault": "true"
        },
        {
            "street": "4th street",
            "city": "Fairfield",
            "state": "Iowa",
            "zip": "52557",
            "isDefault": "false"
        }
    ],
    "creditCards": [
        {
            "name": "Discover",
            "creditNumber": "12345678",
            "securityCode": "2345",
            "expirationDate": "2028-12-12"
        }
    ]
}
```
