package com.firecodex.harcodex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.firecodex.harcodex.api.model.Overview;
import com.firecodex.harcodex.api.model.view.ViewHarcodex;
import com.firecodex.harcodex.api.service.HarcodexService;

@RestController
@RequestMapping("/harcodex")
public class HarcodexCtrl {
	@Autowired HarcodexService harcodexService;
	
	@RequestMapping(value = "/get/overviews", method = RequestMethod.GET)
	public List<Overview> getHarOverviews() {
		return harcodexService.getHarOverviews();
	}

	@RequestMapping(value = "/get/harcodexes/{label}", method = RequestMethod.GET)
	public List<ViewHarcodex> getHarcodexes(@PathVariable String label) {
		return harcodexService.getHarcodexes(label);
	}
	
}
