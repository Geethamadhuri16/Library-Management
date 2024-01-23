# Library-Management
### This is a Library Management System designed to manage books and users with functionalities like adding books, retrieving book details, deleting books, allotting books to users, and submitting books.
## Book Service
### The BookService class provides various functionalities related to book management. Key methods include:

### addBook: Adds a new book to the library.
#### bookDetails: Retrieves details of a specific book by ID.
#### deleteBook: Deletes a book from the library by ID.
#### allotBook: Allots books to a user, updating book availability and user information.
#### submitBook: Submits books from a user, updating book availability and calculating fines.
## Adding a Book
To add a book, use the addBook endpoint with a POST request. Provide the necessary book details in the request body.
```
POST /add
{
  "bookName": "The Catcher in the Rye",
  "author": "J.D. Salinger",
  "edition": "First Edition"
}
```
## Retrieving Book Details
To retrieve details of a specific book, use the getBookDetails endpoint with a GET request.
```
GET /{id}

```
## Deleting a Book
To delete a book, use the delBook endpoint with a DELETE request.
```
DELETE /{id}

```
## Allotting Books to a User
To allot books to a user, use the allotBook endpoint with a POST request. Provide the user ID in the path variable and a list of book IDs in the request body.
```
POST /allotBook/{uid}
{
  "bid": [1, 2, 3]
}

```
## Submitting Books from a User
To submit books from a user, use the submitBook endpoint with a POST request. Provide the user ID in the path variable and a list of book IDs in the request body.
```
POST /submit/{uid}
{
  "bids": [1, 2, 3]
}

```


