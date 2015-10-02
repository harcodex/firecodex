package com.firecodex.harcodex.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.firecodex.harcodex.api.model.Score;
import com.firecodex.harcodex.commons.har.Log;

@Service
public class YSlowService {
	public List<Score> getScores(Log log) throws Exception{
		List<Score> scores =new ArrayList<Score>();
		Score score =new Score("Total Score", 100, 0F, new ArrayList<String>());
		scores.add(score);
		return scores;
	}
}
