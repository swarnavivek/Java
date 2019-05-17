package com.github.controller.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.controller.service.GitHubService;
import com.github.dto.GitHubUser;

@Service
public class GitHubServiceImpl implements GitHubService {
	private final String FOLLOWER_URL = "https://api.github.com/users/octocat/followers";
	@Override
	public List<GitHubUser> getFollowerDetails(Integer gitHubId){
		GitHubUser[] results = getFollowers(FOLLOWER_URL);
	    List<GitHubUser> userDetails = new ArrayList<>();
	    int parentCount = 0;
	    for(GitHubUser user: results){
	    	if(parentCount==5) break;
	    	if(gitHubId.equals(user.getId())){
	    		parentCount++;
	    		GitHubUser[] secondLevel = getFollowers(user.getFollowersUrl());
	    		 for(int i=0;i<secondLevel.length;i++){
	    			 GitHubUser thridLevel = secondLevel[i];
	    			 thridLevel.setUsers(getFollowers(thridLevel.getFollowersUrl()));
	    			 secondLevel[i] = thridLevel;
	    		 }
	    		user.setUsers(secondLevel);
	    		userDetails.add(user);
	    	}
	    }
	    return userDetails;
	}

	private GitHubUser[] getFollowers(String url) {
		return new RestTemplate().getForObject(url, GitHubUser[].class);
	}
}
