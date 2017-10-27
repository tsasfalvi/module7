'use strict';

angular.module('crudApp').factory('UserService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllUsers: loadAllUsers,
                getAllUsers: getAllUsers,
                getUser: getUser,
                createUser: createUser,
                updateUser: updateUser,
                removeUser: removeUser
            };

            return factory;

            function loadAllUsers() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('All users fetched successfully');
                            $localStorage.users = response.data;
                            console.log('users: ', $localStorage.users);
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUsers(){
                return $localStorage.users;
            }

            function getUser(email) {
                console.log('Fetching user with email :'+email);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + email)
                    .then(
                        function (response) {
                            console.log('Fetched successfully user with email :'+email);
                            deferred.resolve(response.data);
                            console.log('users: ', response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with email :'+email);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createUser(user) {
                console.log('Creating user');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating user : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateUser(user, email) {
                console.log('Updating user with email '+email);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + email, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating user with email :'+email);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUser(email) {
                console.log('Removing user with email '+email);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + email)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing user with email :'+email);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);