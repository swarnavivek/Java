package com.github.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.controller.service.GitHubService;
import com.github.dto.GitHubUser;

@RestController
public class GitHubController {
	
	@Autowired
	private GitHubService gitHubService;
	
	@GetMapping("/getFollowers")
	public List<GitHubUser> getFollowerDetails(Integer gitHubId){
	    return gitHubService.getFollowerDetails(gitHubId);
	}
}
