package com.github.controller.service;

import java.util.List;

import com.github.dto.GitHubUser;

public interface GitHubService {
	List<GitHubUser> getFollowerDetails(Integer gitHubId);
}
