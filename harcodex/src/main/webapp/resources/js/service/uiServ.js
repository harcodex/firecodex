harcodex.service('uiService', function($http, $rootScope) {
	delete $http.defaults.headers.common['X-Requested-With'];

	this.broadcast = function(id, data) {
		$rootScope.$broadcast(id, data);
    };
    
	this.showModal = function(id) {
		angular.element(id).modal('show');
    };
    this.hideModal = function(id) {
		angular.element(id).modal('hide');
    };
    this.enableTab = function(id){
		angular.element(id).tab();
	};
	this.enableDropdown = function(id){
		angular.element(id).dropdown();
	};
	this.selectModal = function(id, selector){
		angular.element(id).modal(selector);
	};
	this.search = function(id, source){
		angular.element(id).search(source);
	};
});