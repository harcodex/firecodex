harcodex.service('hcxService', function($http, $q, properties) {
	delete $http.defaults.headers.common['X-Requested-With'];

	this.getOverviews = function() {
		var deferred = $q.defer();
		var url = properties.apiUrl + '/harcodex/get/overviews';
		$http.get(url).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject('No har is found!');
		});
		return deferred.promise;
    };
	this.getHarcodexes = function(label) {
		var deferred = $q.defer();
		var url = properties.apiUrl + '/harcodex/get/harcodexes/'+label;
		$http.get(url).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject('The har details are not found!');
		});
		return deferred.promise;
    };
});

