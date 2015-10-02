harcodex.controller('DetailsCtrl', function ($rootScope, $scope, $stateParams, $timeout, hcxService) {
	$rootScope.menu = {home:false, har:true, about:false, contact:false};
	
	Date.prototype.toLabel = function() {
		   var yy = this.getFullYear().toString();
		   var mm = (this.getMonth()+1).toString();
		   var dd  = this.getDate().toString();
		   var hr = (this.getHours()+1).toString();
		   var ms = this.getMinutes().toString();
		   var ss = this.getSeconds().toString();
		   return yy + "-" + (mm[1]?mm:"0"+mm[0]) + "-" + (dd[1]?dd:"0"+dd[0]) + " " + (hr[1]?hr:"0"+hr[0]) + ":" + (ms[1]?ms:"0"+ms[0]) + ":" + (ss[1]?ss:"0"+ss[0]);
	};
	
	$scope.label = $stateParams.label;
	$scope.hars = [];
	$scope.selectedCat = '';
	$scope.selectedHar = {};
	$scope.loading = false;
	
	$scope.trendCategories = [];
	$scope.trendSeries = [];
	$scope.trendChartConfig = {};
	
	$scope.rscSizeSeries = [];
	$scope.rscSizeChartConfig = {};
	$scope.rscReqSeries = [];
	$scope.rscReqChartConfig = {};
	
	$scope.dmnSizeSeries = [];
	$scope.dmnSizeChartConfig = {};
	$scope.dmnReqSeries = [];
	$scope.dmnReqChartConfig = {};

	$scope.psScoreCategories = [];
	$scope.psScoreSeries = [];
	$scope.psScoreChartConfig = {};

	$scope.ysScoreCategories = [];
	$scope.ysScoreSeries = [];
	$scope.ysScoreChartConfig = {};
	
	hcxService.getHarcodexes($scope.label).then(function(data){
		$scope.hars = data;
		$scope.setData();
	}, function(data){
		alert(data);
	});
	
	$scope.changeInfo = function(){
		$scope.loading = true;
		var index = 0;
		for(var i=0; i<$scope.trendCategories.length; i++){
			if($scope.trendCategories[i]==$scope.selectedCat){
				index = i;
				break;
			}
		}
		$scope.selectedHar = $scope.hars[index];
		//console.log(JSON.stringify($scope.selectedHar.log));
		
		$scope.setRscSizeChartData();
		$scope.setRscReqChartData();
		$scope.setDmnSizeChartData();
		$scope.setDmnReqChartData();
		
		$scope.setPsScoreChartData();
		$scope.setYsScoreChartData();
		$scope.loading = false;
	};
	
	$scope.infoMenu = {
		summary:'active',
		resource:'',
		domain:'',
		harview:'',
		page:'',
		yslow:'',
		manage:'',
		activate:function(key){
			if(key=='summary'){
				this.reset();
				this.summary='active';
			}else if(key=='resource'){
				this.reset();
				this.resource='active';
			}else if(key=='domain'){
				this.reset();
				this.domain='active';
			}else if(key=='harview'){
				this.reset();
				this.harview='active';
			}else if(key=='page'){
				this.reset();
				this.page='active';
			}else if(key=='yslow'){
				this.reset();
				this.yslow='active';
			}else if(key=='manage'){
				this.reset();
				this.manage='active';
			}
		},
		reset:function(){
			this.summary='';
			this.resource='';
			this.domain='';
			this.harview='';
			this.page='';
			this.yslow='';
			this.manage='';
		}
	};
	
	$scope.setData = function(){
		$scope.loading = true;
		
		$scope.setTrendChartData();
		
		$scope.selectedCat = $scope.trendCategories[0];
		$scope.selectedHar = $scope.hars[0];
		
		$scope.setRscSizeChartData();
		$scope.setRscReqChartData();
		
		$scope.setDmnSizeChartData();
		$scope.setDmnReqChartData();
		
		$scope.setPsScoreChartData();
		$scope.setYsScoreChartData();
		
		$scope.loading = false;
	};

	$scope.setPsScoreChartData = function(){
		$scope.psScoreCategories = [];
		$scope.psScoreSeries = [{type: 'bar',name: 'Score',data: [],offenders:[]}];
		for (var i=0; i<$scope.selectedHar.pagespeedScore.scores.length; i++) {
			$scope.psScoreCategories.push($scope.selectedHar.pagespeedScore.scores[i].rule);
			$scope.psScoreSeries[0].data.push($scope.selectedHar.pagespeedScore.scores[i].score);
//			$scope.psScoreSeries[0].offenders.push($scope.selectedHar.pagespeedScore.scores[i].offenders);
		}
//		$scope.psScoreChartConfig = $scope.getBarChartConfigWithTooltip('Page Speed Scores', $scope.psScoreCategories, $scope.psScoreSeries);
		$scope.psScoreChartConfig = $scope.getBarChartConfig('Page Speed Scores', $scope.psScoreCategories, $scope.psScoreSeries);
	};
	$scope.setYsScoreChartData = function(){
		$scope.ysScoreCategories = [];
		$scope.ysScoreSeries = [{type: 'bar',name: 'Score',data: []}];
		for (var i=0; i<$scope.selectedHar.yslowScores.length; i++) {
			$scope.ysScoreCategories.push($scope.selectedHar.yslowScores[i].rule);
			$scope.ysScoreSeries[0].data.push($scope.selectedHar.yslowScores[i].score);
		}
		$scope.ysScoreChartConfig = $scope.getBarChartConfig('ySlow Scores', $scope.ysScoreCategories, $scope.ysScoreSeries);
	};
	$scope.setDmnReqChartData = function(){
		$scope.dmnReqSeries = [{type: 'pie', name: 'Request',data: []}];
		for (var key in $scope.selectedHar.domainRequests) {
			$scope.dmnReqSeries[0].data.push([key, $scope.selectedHar.domainRequests[key]]);
		}
		$scope.dmnReqChartConfig = $scope.getPieChartConfig('Domain by Requests', $scope.dmnReqSeries);
	};
	$scope.setDmnSizeChartData = function(){
		$scope.dmnSizeSeries = [{type: 'pie', name: 'Size', data: []}];
		for (var key in $scope.selectedHar.domainSizes) {
			$scope.dmnSizeSeries[0].data.push([key, $scope.selectedHar.domainSizes[key]]);
		}
		$scope.dmnSizeChartConfig = $scope.getPieChartConfig('Domain by Size (KB)', $scope.dmnSizeSeries);
	};
	
	$scope.setRscReqChartData = function(){
		$scope.rscReqSeries = [{type: 'pie', name: 'Request',data: []}];
		for (var key in $scope.selectedHar.resourceRequests) {
			$scope.rscReqSeries[0].data.push([key, $scope.selectedHar.resourceRequests[key]]);
		}
		$scope.rscReqChartConfig = $scope.getPieChartConfig('Resource by Requests', $scope.rscReqSeries);
	};
	$scope.setRscSizeChartData = function(){
		$scope.rscSizeSeries = [{type: 'pie', name: 'Size', data: []}];
		for (var key in $scope.selectedHar.resourceSizes) {
			$scope.rscSizeSeries[0].data.push([key, $scope.selectedHar.resourceSizes[key]]);
		}
		$scope.rscSizeChartConfig = $scope.getPieChartConfig('Resource by Size (KB)', $scope.rscSizeSeries);
	};

	$scope.getBarChartConfigWithTooltip = function(title, categories, series){
		console.log('chart: '+series[0].offenders.length);
		return {
			func: function(chart) {
	            $timeout(function() {
	                chart.reflow();
	            }, 0);
	        },
	        chart: {type: 'bar'},
	        title: {text: title},
	        xAxis: {categories: categories},
	        yAxis: {min: 0, max: 100},
	        plotOptions: {bar: {dataLabels: {enabled: true}}},
	        tooltip: {
	        	formatter: function () {
	                return 'The value for <b>' + this.x + '</b> is <b>' + this.y + '</b>';
	            }
	        },
            series: series,
            credits: {enabled: false}
		};
	};
	
	$scope.getBarChartConfig = function(title, categories, series){
		return {
			func: function(chart) {
	            $timeout(function() {
	                chart.reflow();
	            }, 0);
	        },
	        chart: {type: 'bar'},
	        title: {text: title},
	        xAxis: {categories: categories},
	        yAxis: {min: 0, max: 100},
	        plotOptions: {bar: {dataLabels: {enabled: true}}},
            tooltip: {valueSuffix: '/100'},
            series: series,
            credits: {enabled: false}
		};
	};
	
	$scope.getPieChartConfig = function(title, series){
		return {
			func: function(chart) {
	            $timeout(function() {
	                chart.reflow();
	            }, 0);
	        },
	        chart: {type: 'pie'},
	        title: {text: title},
	        plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            tooltip: {
            	pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            series: series,
            credits: {enabled: false}
		};
	};
	
	$scope.setTrendChartData = function(){
		$scope.trendCategories = [];
		$scope.trendSeries = [];
		var flt = {name:'Full Load Time', data:[]};
		var trs = {name:'Total Requests', data:[]};
		var tsz = {name:'Total Size', data:[]};
		var pss = {name:'Page Speed Score', data:[], visible:false};
		var yss = {name:'ySlow Score', data:[], visible:false};
		var tfb = {name:'Time to First Byte', data:[], visible:false};
		var tdt = {name:'Total DSN Time', data:[], visible:false};
		var ttt = {name:'Total Transfer Time', data:[], visible:false};
		var tst = {name:'Total Server Time', data:[], visible:false};
		var act = {name:'Avg. Connecting Time', data:[], visible:false};
		var abt = {name:'Avg. Blocking Time', data:[], visible:false};
		var txs = {name:'Text Size', data:[], visible:false};
		var mds = {name:'Media Size', data:[], visible:false};
		var chs = {name:'Cache Size', data:[], visible:false};
		var rds = {name:'Redirects', data:[]};
		var brs = {name:'Bad Requests', data:[]};
		var dns = {name:'Domains', data:[]};
		for(var i=0; i<$scope.hars.length; i++){
			$scope.trendCategories.push(new Date($scope.hars[i].overview.timestamp).toLabel());
			flt.data.push($scope.hars[i].overview.loadTime);
			trs.data.push($scope.hars[i].overview.requests);
			tsz.data.push($scope.hars[i].overview.totalSize);
			pss.data.push($scope.getScore($scope.hars[i].psScores));
			yss.data.push($scope.getScore($scope.hars[i].yslowScores));
			tfb.data.push($scope.hars[i].detail.timeToFirstByte);
			tdt.data.push($scope.hars[i].detail.totalDnsTime);
			ttt.data.push($scope.hars[i].detail.totalTransferTime);
			tst.data.push($scope.hars[i].detail.totalServerTime);
			act.data.push($scope.hars[i].detail.avgConnectingTime);
			abt.data.push($scope.hars[i].detail.avgBlockingTime);
			txs.data.push($scope.hars[i].detail.textSize);
			mds.data.push($scope.hars[i].detail.mediaSize);
			chs.data.push($scope.hars[i].detail.cacheSize);
			rds.data.push($scope.hars[i].detail.redirects);
			brs.data.push($scope.hars[i].detail.badRequests);
			dns.data.push($scope.hars[i].overview.domains);
		}
		$scope.trendSeries.push(flt);
		$scope.trendSeries.push(trs);
		$scope.trendSeries.push(tsz);
		$scope.trendSeries.push(pss);
		$scope.trendSeries.push(yss);
		$scope.trendSeries.push(tfb);
		$scope.trendSeries.push(tdt);
		$scope.trendSeries.push(ttt);
		$scope.trendSeries.push(tst);
		$scope.trendSeries.push(act);
		$scope.trendSeries.push(abt);
		$scope.trendSeries.push(txs);
		$scope.trendSeries.push(mds);
		$scope.trendSeries.push(chs);
		$scope.trendSeries.push(rds);
		$scope.trendSeries.push(brs);
		$scope.trendSeries.push(dns);
		$scope.trendChartConfig = $scope.getTrendChartConfig($scope.trendCategories, $scope.trendSeries);
	};
	
	$scope.getTrendChartConfig = function(categories, series){
		return {
		func: function(chart) {
            $timeout(function() {
                chart.reflow();
            }, 0);
        },
        chart: {type: 'line'},
		title: {
            text: 'Performance Trends',
            x: -20 //center
        },
        subtitle: {
            text: 'web client performance',
            x: -20
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: 'Time'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '°C'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: series,
        credits: {enabled: false}
		}
	};

	
	$scope.getScore = function(list){
		var score = 0;
		if(list==undefined || list==null){
			return score;
		}
		var scores = [];
		for(var i=0; i<list.length; i++){
			scores.push(list[i].score);
		}
		if(scores.length > 0){
			score = $scope.getMedian(scores);
		}
		return score;
	};
	
	$scope.getMedian = function(values){
		values.sort( function(a,b){return a - b;});
	    var half = Math.floor(values.length/2);
	    if(values.length % 2) return values[half];
	    else return (values[half-1] + values[half]) / 2.0;
	};
	
});