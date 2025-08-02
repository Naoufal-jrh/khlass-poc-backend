# API Endpoint Documentation

This document provides details about the API endpoints available in this project.
Use these endpoints to interact with the server programmatically.

---

## **Base URL**

```
https://api.example.com/v1
```

---

## **Authentication**

* **Type:** Bearer Token (JWT)
* **Header Example:**

  ```
  Authorization: Bearer <your_token>
  ```
* Some endpoints may be public; see details below.

---

## **Endpoints**

### I. Client EndPoints

### **Get All Clients**

**URL:**

```
GET /client
```
**Note:**
This should be only "/client" then the company id will be fetched from the logged in company account.
But for now we will keep it as a query parameter.


**Description:**
Returns a list of all client by company or by category.

**Query Parameters:**

| Parameter | Type | Required | Description        |
|-----------|------|---------|--------------------|
| company   | long | no      | id of the company  |
| category  | long | no      | id of the category |

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "paymentIdentifier": "PID-1-1-1",
    "name": "Client 1 - Company 1",
    "phone": "12345671",
    "email": "client1@company1.com",
    "address": "adress",
    "category": {
      "id": 1,
      "name": "Company 1 - Category 1",
      "defaultAmount": 100.0,
      "company": {
        "id": 1
      }
    }
  }
]
```

---

### **Get Client by id**

**URL:**

```
GET /client/{clientId}
```

**Description:**
Returns details of a client.

**Query Parameters:**
No parameters.

**Response (200 OK):**

```json
  {
  "id": 1,
  "paymentIdentifier": "PID-1-1-1",
  "name": "Client 1 - Company 1",
  "phone": "12345671",
  "email": "client1@company1.com",
  "address": "adress",
  "category": {
    "id": 1,
    "name": "Company 1 - Category 1",
    "description": "desc",
    "color": "#45AB3",
    "defaultAmount": 100.0,
    "company": {
      "id": 1
    }
  }
}
```
---


### **Add a Client**

**URL:**

```
POST /client
```

**Description:**
Add a list of clients.

**Request Body:**

```json
[
  {
    "paymentIdentifier": "PID-1-1-1",
    "name": "Client 1 - Company 1",
    "phone": "12345671",
    "email": "client1@company1.com",
    "address": "adress",
    "category": {
      "id": 1
    }
  }
]
```

**Query Parameters:**
No Parameters.


**Response (201 Created):**

```json
[
  {
    "id": 1,
    "paymentIdentifier": "PID-1-1-1",
    "name": "Client 1 - Company 1",
    "phone": "12345671",
    "email": "client1@company1.com",
    "address": "adress",
    "category": {
      "id": 1
    }
  }
]
```

**Response (409 Conflict):**

```json
{
  "error": "Client already exists"
}
```

---

### **Update Client**

**URL:**

```
PUT  /client/{clientId}
```

**Description:**
Update the information of an existing client.

**Request Body:**

```json
{
  "phone": "12345671",
  "email": "client1@company1.com",
  "address": "adress"
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "paymentIdentifier": "PID-1-1-1",
  "name": "Client 1 - Company 1",
  "phone": "12345671",
  "email": "client1@company1.com",
  "address": "adress",
  "category": {
    "id": 1
  }
}
```

**Response (401 Not Found):**

```json
{
  "error": "Client not found"
}
```

---

### **Delete a Client**

**URL:**

```
DELETE /client/{clientId}
```

**Description:**
Delete a client by ID.

**Response (200 OK):**
No body is returned.

**Response (401 Not Found):**

```json
{
  "error": "Client not found"
}
```

---

### Category Endpoints

### **Get All Categories**

**URL:**

```
GET /category
```
**Note:**
This should be only "/category" then the company id will be fetched from the logged in company account. 
But for now we will keep it as a query parameter.

**Description:**
Returns a list of all categories of a company.

**Query Parameters:**

| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| company   | long | no       | company id  |

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "Company 1 - Category 1",
    "description": "description",
    "color": "#color",
    "defaultAmount": 100.0,
    "company": {
      "id": 1
    }
  }
]
```

---

### **Get Category by ID**

**URL:**

```
GET /category/{categoryId}
```

**Description:**
Returns the details of a category.

**Query Parameters:**
No Query Parameters.

**Response (200 OK):**

```json

  {
    "id": 1,
    "name": "Company 1 - Category 1",
    "description": "description",
    "color": "#color",
    "defaultAmount": 100.0
  }
```

### **Update Category**

**URL:**

```
PUT  /category/{categoryId}
```

**Description:**
Update the information of an existing category

**Request Body:**

```json
{
  "defaultAmount": 100.0
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "name": "Company 1 - Category 1",
  "description": "description",
  "color": "#color",
  "defaultAmount": 100.0
}
```

**Response (401 Not Found):**

```json
{
  "error": "Category not found"
}
```

---

### **Delete a Category**

**URL:**

```
DELETE /category/{categoryId}
```

**Description:**
Delete a category by ID.

**Response (200 OK):**
No body is returned.

**Response (401 Not Found):**

```json
{
  "error": "Category not found"
}
```



---




### III. Bills Endpoints

### **Get bill**

**URL:**

```
GET /bill
```

**Description:**
Returns all the bills of a client.

**Query Parameters:**

| Parameter | Type | Required | Description  |
|-----------|------|----------|--------------|
| client    | long | no       | client id    |
| company   | long | no       | company id   |
| category  | long | no       | category id  |
| payed     | bool | no       | payed status |

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "PID-1-1-1",
    "description": "Client 1 - Company 1",
    "amount": 2033.3,
    "expiration date": "12-03-2025",
    "payed": true
  }
]
```

---

### **Get Bill by ID**

**URL:**

```
GET /bill/{billId}
```

**Description:**
Returns the details of a bill.

**Query Parameters:**
No Parameters.

**Response (200 OK):**

```json

{
  "id": 1,
  "name": "PID-1-1-1",
  "description": "Client 1 - Company 1",
  "amount": 2033.3,
  "expiration date": "12-03-2025",
  "payed": true
}
```
**Response (401 Not Found):**

```json
{
  "error": "Bill not found"
}
```

### **Update Bill**

**URL:**

```
PUT  /bill/{billId}
```

**Description:**
Update the information of an existing bill.

**Request Body:**

```json
{
  "amount": 2033.3
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "name": "PID-1-1-1",
  "description": "Client 1 - Company 1",
  "amount": 2033.3,
  "expiration date": "12-03-2025"
}
```

**Response (401 Not Found):**

```json
{
  "error": "Bill not found"
}
```

---

### 5. **Delete a Bill**

**URL:**

```
DELETE /bill/{billId}
```

**Description:**
Delete a bill by ID.

**Response (200 OK):**
No body is returned.

**Response (401 Not Found):**

```json
{
  "error": "Bill not found"
}
```











---

## **Error Codes**

| Code | Description           |
| ---- | --------------------- |
| 400  | Bad Request           |
| 401  | Unauthorized          |
| 403  | Forbidden             |
| 404  | Not Found             |
| 500  | Internal Server Error |

---
























## **Versioning**

* Current API version: **v1**

---

## **Contact**

For issues or questions, please contact: **[naoufaljrh2000@gmail.com](mailto:naoufaljrh2000@gmail.com)**