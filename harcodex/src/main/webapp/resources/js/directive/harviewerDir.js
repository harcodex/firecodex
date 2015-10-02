harcodex.directive('harViewer', function() {
	return {
		restrict : 'E',
		replace : true,
		scope: {harLog: "="},
		templateUrl : 'resources/view/template/harviewer.html',
		controller: function( $scope, $element, $attrs ) {
			if (typeof String.prototype.endsWith !== 'function') {
			    String.prototype.endsWith = function(suffix) {
			        return this.indexOf(suffix, this.length - suffix.length) !== -1;
			    };
			}
			$scope.getResource= function(url){
				//change the logic using url util
				var resource = url.split("/")[url.split("/").length-1].split("?")[0];
				if(resource.length>100){
					resource = resource.substring(0, 100);
				}
				return resource;
			};
			$scope.getResourceIcon= function(url){
				var rsc = $scope.getResource(url);
				if(rsc.endsWith('.js')){
					return 'file excel outline';
				}else if(rsc.endsWith('.css')){
					return 'file archive outline';
				}else if(rsc.endsWith('.jpeg') || rsc.endsWith('.jpg') || rsc.endsWith('.gif') || rsc.endsWith('.bmp') || rsc.endsWith('.png') || rsc.endsWith('.svg')){
					return 'file image outline';
				}else if(rsc.endsWith('.pdf')){
					return 'file pdf outline';
				}else if(rsc.endsWith('.txt')){
					return 'file text outline';
				}else if(rsc.endsWith('.html') || rsc.endsWith('.htm') || rsc.endsWith('.jsp')){
					return 'file code outline';
				}else{
					return 'file outline';
				}
			};
//			console.log( $scope.harLog.entries[0].request.url );
        },
		link : function(scope, elem, attrs) {
			
//			elem.bind('mouseover', function() {
//				elem.css('cursor', 'pointer');
//			});
		}
	};
});