# Customer service

## API List

### Cutomer API end;
Fetch all customer
```bash
[GET] http://localhost:8081/customers 
```
Fetch a customer
```bash
[GET] http://localhost:8081/customers/{customerId}
```
Save customer
```bash
[POST] http://localhost:8081/customers 
```
Update customer
```bash
[PUT] http://localhost:8081/customers/{customerId}
```

### Shipping Address API end

Fetch all shipping address for a specific customer
```bash
[GET] http://localhost:8081/customers/{customerId}/shipping-addressee
```

Fetch shipping address by shipping id for a specific customer
```bash
[GET] http://localhost:8081/customers/{customerId}/shipping-addressee/{shppingId}
```

### Credit card API end

Fetch all credit card for a specific customer
```bash
[GET] http://localhost:8081/customers/{customerId}/credit-cards
```

Fetch shipping address by shipping id for a specific customer
```bash
[GET] http://localhost:8081/customers/{customerId}/credit-cards/{creditCardId}
```




## JSON Payload of customer

```JavaScript
{
    "firstName": "Frank",
    "lastName": "Jhon",
    "email": "frank.123@gmail.com", 
        "password":"1234",
        "isAdmin":"false",
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
