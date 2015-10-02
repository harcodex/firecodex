var properties = {
		apiUrl:'http://localhost:8181/harcodex-api'
};

var harcodex = angular.module('harcodex', ['ui.router', 'caco.paginator', 'highcharts-ng']);

harcodex.value("properties", properties);

harcodex.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/');
    
    $stateProvider
    .state('home',{
        url: '/',
        views: {
            'header': {
                templateUrl: 'resources/view/common/header.html',
                controller: 'HeaderCtrl'
            },
            'content': {
                templateUrl: 'resources/view/common/home.html',
                controller: 'HomeCtrl'
            },
            'footer': {
                templateUrl: 'resources/view/common/footer.html',
                controller: 'FooterCtrl'
            }
        }
    }).state('home.har',{
        url: 'har',
        views: {
            'content@': {
                templateUrl: 'resources/view/harcodex/list.html',
                controller: 'ListCtrl'
            }
        }
    }).state('home.har.details',{
        url: 'details/:label',
        views: {
            'content@': {
                templateUrl: 'resources/view/harcodex/details.html',
                controller: 'DetailsCtrl'
            }
        }
    }).state('home.about',{
        url: 'about',
        views: {
            'content@': {
                templateUrl: 'resources/view/common/about.html',
                controller: 'AboutCtrl'
            }
        }
    }).state('home.contact',{
        url: 'contact',
        views: {
            'content@': {
                templateUrl: 'resources/view/common/contact.html',
                controller: 'ContactCtrl'
            }
        }
    });
});

harcodex.run(['$state', function ($state) {
	   $state.transitionTo('home');
}]);