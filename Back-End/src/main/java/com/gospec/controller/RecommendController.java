package com.gospec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gospec.domain.InterestFieldDto;
import com.gospec.recommend.KMeansClustering;
import com.gospec.security.GoUserDetailsService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8181/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/recommend")
public class RecommendController {
	
	@Autowired
	private GoUserDetailsService userService;
	
	/*@GetMapping(value = "/random")
	public void random() {
		Dummy d = new Dummy();
		for(int i=19; i<=118; i++) {
			String id = "계정" + (i-18);
			d.getRandom().forEach(el -> userService.saveInterestField(id, el));
		}
	}
	
	@GetMapping(value = "/dummy")
	public void dummy() {
		Dummy d = new Dummy();
		d.makefile(userService.makeDummy());
	}*/
	
	@ApiOperation(value = "백엔드 클러스터링 api,", response = InterestFieldDto.class)
	@GetMapping(value = "/dummy")
	public void cluster() {
		KMeansClustering kmeans = new KMeansClustering();
		try {
			userService.makeCluster(kmeans.readData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
