harcodex.controller('HarcodexCtrl', function($rootScope, $scope) {
	$rootScope.menu = {home:true, har:false, about:false, contact:false};
});
harcodex.controller('HomeCtrl', function($rootScope, $scope) {
	$rootScope.menu = {home:true, har:false, about:false, contact:false};
});
harcodex.controller('HeaderCtrl', function($scope) {
});
harcodex.controller('FooterCtrl', function($scope) {
});
harcodex.controller('AboutCtrl', function($rootScope, $scope) {
	$rootScope.menu = {home:false, har:false, about:true, contact:false};
});
harcodex.controller('ContactCtrl', function($rootScope, $scope) {
	$rootScope.menu = {home:false, har:false, about:false, contact:true};
});