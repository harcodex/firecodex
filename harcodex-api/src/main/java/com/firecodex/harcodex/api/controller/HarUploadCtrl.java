package com.firecodex.harcodex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.firecodex.harcodex.api.service.HarcodexService;
import com.firecodex.harcodex.commons.har.Har;
import com.firecodex.harcodex.commons.har.Log;

@RestController
@RequestMapping("/upload")
public class HarUploadCtrl {
	@Autowired HarcodexService harcodexService;
	
	@RequestMapping(value = "/harlog", method = RequestMethod.GET)
	public @ResponseBody String uploadHarlog() {
		return "You can upload har log by posting to this same URL.";
	}
	@RequestMapping(value = "/harlog", method = RequestMethod.POST, consumes = {"application/json"})
	public @ResponseBody String uploadHarlog(@RequestBody Log log, @RequestParam String url) {
		try{
			harcodexService.uploadHarlog(url, log);
			return "You successfully uploaded the har log!";
		}catch(Exception e){
			return "You failed to upload the har log => " + e.getMessage();
		}
	}
	
	@RequestMapping(value = "/har", method = RequestMethod.GET)
	public @ResponseBody String uploadHar() {
		return "You can upload har by posting to this same URL.";
	}
	@RequestMapping(value = "/har", method = RequestMethod.POST, consumes = {"application/json"})
	public @ResponseBody String uploadHar(@RequestBody Har har, @RequestParam String url) {
		try{
			harcodexService.uploadHar(url, har);
			return "You successfully uploaded the har!";
		}catch(Exception e){
			return "You failed to upload the har => " + e.getMessage();
		}
	}
	
	@RequestMapping(value = "/harfile", method = RequestMethod.GET)
	public @ResponseBody String uploadHarfile() {
		return "You can upload har file by posting to this same URL.";
	}
	@RequestMapping(value = "/harfile", method = RequestMethod.POST)
	public @ResponseBody String uploadHarfile(@RequestParam("file") MultipartFile file, @RequestParam String url) {
		if (!file.isEmpty()) {
			try {
                harcodexService.uploadHar(url, file.getBytes());
                return "You successfully uploaded the har file!";
            } catch (Exception e) {
                return "You failed to upload the har file => " + e.getMessage();
            }
		}
		return "You failed to upload the har file, because the file was empty.";
	}
	
	@RequestMapping(value = "/harlogfile", method = RequestMethod.GET)
	public @ResponseBody String uploadLogfile() {
		return "You can upload log file by posting to this same URL.";
	}
	@RequestMapping(value = "/harlogfile", method = RequestMethod.POST)
	public @ResponseBody String uploadLogfile(@RequestParam("file") MultipartFile file, @RequestParam String url) {
		if (!file.isEmpty()) {
			try {
				System.out.println(file.getBytes());
                harcodexService.uploadLog(url, file.getBytes());
                return "You successfully uploaded the harlog file!";
            } catch (Exception e) {
                return "You failed to upload the harlog file => " + e.getMessage();
            }
		}
		return "You failed to upload the harlog file, because the file was empty.";
	}
}
