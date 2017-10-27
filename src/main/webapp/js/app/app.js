var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/module7',
    BOOK_SERVICE_API : 'http://localhost:8080/module7/book/',
    USER_SERVICE_API : 'http://localhost:8080/module7/user/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/home'
            })
            .state('users', {
                url: '/users',
                templateUrl: 'partials/userlist',
                controller:'UserController',
                controllerAs:'ctrl',
                resolve: {
                    books: function ($q, UserService) {
                        console.log('Load all users');
                        var deferred1 = $q.defer();
                        UserService.loadAllUsers().then(deferred1.resolve, deferred1.resolve);
                        return deferred1.promise;
                    }
                }
            })
            .state('books', {
                url: '/books',
                templateUrl: 'partials/booklist',
                controller:'BookController',
                controllerAs:'bookCtrl',
                resolve: {
                    books: function ($q, BookService) {
                        console.log('Load all books');
                        var deferred2 = $q.defer();
                        BookService.loadAllBooks().then(deferred2.resolve, deferred2.resolve);
                        return deferred2.promise;
                    }
                }
            });

        $urlRouterProvider.otherwise('/');
    }]);

