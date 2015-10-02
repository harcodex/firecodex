harcodex.controller('ListCtrl', function ($rootScope, $scope, hcxService) {
	$rootScope.menu = {home:false, har:true, about:false, contact:false};

	$scope.list = [];
	$scope.overviews = [];
	$scope.scenarios = [];
	$scope.sites = [];
	$scope.environments = [];
	$scope.pages = [];
	
	$scope.selectedScenario = '';
	$scope.selectedSite = '';
	$scope.selectedEnvironment = '';
	$scope.selectedPage = '';
	$scope.loading = false;
	
	hcxService.getOverviews().then(function(data){
		$scope.loading = true;
		$scope.overviews = data;
		$scope.list = data;
		$scope.setFilters();
		$scope.loading = false;
	}, function(data){
		$scope.loading = false;
		alert(data);
	});
	
	$scope.resetFilter = function(){
		$scope.selectedScenario = '';
		$scope.selectedSite = '';
		$scope.selectedEnvironment = '';
		$scope.selectedPage = '';
		$scope.overviews = $scope.list;
	}
	
	$scope.filterOnChange = function(){
		$scope.overviews = [];
		for(var i=0; i<$scope.list.length; i++){
			var addable = false;
			if(($scope.selectedScenario!='' && $scope.list[i].scenario==$scope.selectedScenario)){
				addable = true;
			}
			if(!addable && $scope.selectedSite!='' && $scope.list[i].site==$scope.selectedSite){
				addable = true;
			}
			if(!addable && $scope.selectedEnvironment!='' && $scope.list[i].environment==$scope.selectedEnvironment){
				addable = true;
			}
			if(!addable && $scope.selectedPage!='' && $scope.list[i].page==$scope.selectedPage){
				addable = true;
			}
			if(addable){
				$scope.overviews.push($scope.list[i]);
			}
		}
	}
	
	$scope.setFilters = function(){
		$scope.scenarios = [];
		$scope.sites = [];
		$scope.environments = [];
		$scope.pages = [];
		for(var i=0; i<$scope.overviews.length; i++){
			if($scope.scenarios.indexOf($scope.overviews[i].scenario)<0){
				$scope.scenarios.push($scope.overviews[i].scenario);
			}
			if($scope.sites.indexOf($scope.overviews[i].site)<0){
				$scope.sites.push($scope.overviews[i].site);
			}
			if($scope.environments.indexOf($scope.overviews[i].environment)<0){
				$scope.environments.push($scope.overviews[i].environment);
			}
			if($scope.pages.indexOf($scope.overviews[i].page)<0){
				$scope.pages.push($scope.overviews[i].page);
			}
		}
	}
	
});
