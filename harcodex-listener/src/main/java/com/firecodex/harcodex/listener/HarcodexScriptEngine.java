package com.firecodex.harcodex.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HarcodexScriptEngine {
	public static String PERFNAVIGATION_OBJECT = "navigation";
	public static String PERFTIMING_OBJECT = "timing";
	public static String PERFMEMORY_OBJECT = "memory";
	
	public static String PREFIXABLE_SCRIPT = "return Boolean(window.performance['%s'] != undefined);";
	public static String RESOURCEABLE_SCRIPT = "return Boolean(!('getEntriesByType' in window.performance) || !('webkitGetEntriesByType' in window.performance) || !(window.performance.getEntriesByType('resource') instanceof Array) || !(window.performance.webkitGetEntriesByType('resource') instanceof Array));";
//	public static String BUILDABLE_SCRIPT = "return Boolean(window.performance && typeof window.performance == 'object') && !Object.keys(window.performance).length;";
	public static String MEASURABLE_SCRIPT = "return !!(window.performance && window.performance.timing);";
	public static String PERFOBJECT_SCRIPT = "return window.performance.%s.%s;";
//	public static String ENTRIES_SCRIPT = "window.performance.getEntriesByType = window.performance.getEntriesByType || window.performance.webkitGetEntriesByType; return window.performance.getEntriesByType('resource');";
	public static String ENTRIES_SCRIPT = "return window.performance.getEntries() || window.performance.%s();";
	public static String NOW_SCRIPT = "return window.performance.now();";
	
	private WebDriver driver;

	public HarcodexScriptEngine(WebDriver driver) {
		this.driver = driver;
	}

	public Double getNow(){
		return executeScriptToDouble(NOW_SCRIPT);
	}
	public String getBrowserPrefix(){
		String[] prefixes = {"webkit", "moz", "ms", "o"};
		String prefix = "";
		for(String p: prefixes){
			if(isPrefixable(p)) return p;
		}
		return prefix;
	}
	protected boolean isPrefixable(String prefix){
		return executeScriptToBoolean(String.format(PREFIXABLE_SCRIPT, getPrefixFuncName(prefix)));
	}
	protected String getPrefixFuncName(String prefix){
		return prefix.equals("") ? "now" : prefix.concat("Now");
	}
	protected String getPrefixedFuncName(String defaultFuncName){
		String prefix = getBrowserPrefix();
		return prefix.equals("") ? defaultFuncName : prefix.concat(getCamelCasedTerm(defaultFuncName));
	}
	private String getCamelCasedTerm(String term){
		return Character.toUpperCase(term.charAt(0)) + term.substring(1);
	}
	
	protected Integer getNavigationValue(String key){
		try{return executeScriptToInteger(String.format(PERFOBJECT_SCRIPT, PERFNAVIGATION_OBJECT, key));}catch(Exception e){return -1;}
	}
	protected Long getTimingValue(String key){
		try{return executeScriptToLong(String.format(PERFOBJECT_SCRIPT, PERFTIMING_OBJECT, key));}catch(Exception e){return -1L;}
	}
	protected Long getMemoryValue(String key){
		try{return executeScriptToLong(String.format(PERFOBJECT_SCRIPT, PERFMEMORY_OBJECT, key));}catch(Exception e){return -1L;}
	}
	protected List<Map<String, Object>> getEntries(){
		try{return (ArrayList<Map<String, Object>>) executeScript(String.format(ENTRIES_SCRIPT, getPrefixedFuncName("getEntries")));}catch(Exception e){return new ArrayList<Map<String, Object>>();}
	}
	protected boolean isMeasurable(){
		return executeScriptToBoolean(MEASURABLE_SCRIPT);
	}
	
	protected Object executeScript(String script, Object... objects){
		try{return ((JavascriptExecutor) driver).executeScript(script, objects);}catch(Exception e){return null;}
	}
	protected String executeScriptToString(String script, Object... objects){
		return (String)executeScript(script, objects);
	}
	protected Integer executeScriptToInteger(String script, Object... objects){
		return (Integer)executeScript(script, objects);
	}
	protected Double executeScriptToDouble(String script, Object... objects){
		return (Double)executeScript(script, objects);
	}
	protected Long executeScriptToLong(String script, Object... objects){
		return (Long)executeScript(script, objects);
	}
	protected Boolean executeScriptToBoolean(String script, Object... objects){
		return (Boolean)executeScript(script, objects);
	}
}
