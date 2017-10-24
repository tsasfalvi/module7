'use strict';

angular.module('crudApp').factory('BookService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllBooks: loadAllBooks,
                getAllBooks: getAllBooks,
                getBook: getBook,
                createBook: createBook,
                updateBook: updateBook,
                removeBook: removeBook
            };

            return factory;

            function loadAllBooks() {
                console.log('Fetching all books');
                var deferred = $q.defer();
                $http.get(urls.BOOK_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('All books fetched successfully');
                            $localStorage.books = response.data;
                            console.log('books: ', $localStorage.books);
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading books');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllBooks(){
                return $localStorage.books;
            }

            function getBook(id) {
                console.log('Fetching book with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.BOOK_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully book with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading book with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createBook(book) {
                console.log('Creating book');
                var deferred = $q.defer();
                $http.post(urls.BOOK_SERVICE_API, book)
                    .then(
                        function (response) {
                            loadAllBooks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating book : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateBook(book, id) {
                console.log('Updating book with id '+id);
                var deferred = $q.defer();
                $http.put(urls.BOOK_SERVICE_API + id, book)
                    .then(
                        function (response) {
                            loadAllBooks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating book with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeBook(id) {
                console.log('Removing book with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.BOOK_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllBooks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing book with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);