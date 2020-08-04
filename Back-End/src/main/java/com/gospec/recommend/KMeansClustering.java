package com.gospec.recommend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gospec.domain.ClusterDto;
import com.gospec.domain.InterestFieldDto;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class KMeansClustering {
	
	private final String[] FIELD = {"기획-아이디어", "광고-마케팅", "논문-리포트", "영상-UCC-사진", "디자인-캐릭터-웹툰",
			"웹-모바일-플래시", "게임-소프트웨어", "과학-공학", "문학-글-시나리오", "건축-건설-인테리어",
			"네이밍-슬로건", "예체능-미술-음악", "대외활동-서포터즈", "봉사활동", "취업-창업", "해외", "기타"
	};

	public synchronized List<ClusterDto> readData() throws Exception {
		SimpleKMeans kmeans = new SimpleKMeans();

		kmeans.setSeed(10);

		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(7);

		BufferedReader datafile =  new BufferedReader(new FileReader("src/main/resources/gospec.arff"));
		Instances data = new Instances(datafile);

		kmeans.buildClusterer(data);

		int[] assignments = kmeans.getAssignments();

		int i = 1;
		List<ClusterDto> list = new ArrayList<>();
		for (int clusterNum : assignments) {
			System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
			list.add(new ClusterDto(i, clusterNum));
			i++;
		}
		return list;
	}
	
	public synchronized void makeFile(List<InterestFieldDto> list) {
		Map<String, List<String>> map = new HashMap<>();

		for(int i=0; i<list.size(); i++) {
			InterestFieldDto cur = list.get(i);
			map.put(cur.getNo(), new ArrayList<String>());
		}
		for(int i=0; i<list.size(); i++) {
			InterestFieldDto cur = list.get(i);
			List<String> temp = map.get(cur.getNo());
			temp.add(cur.getField());
			map.put(cur.getNo(), temp);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("@relation gospec\r\n" + 
				"@attribute '기획-아이디어' {'yes', 'no'}\r\n" + 
				"@attribute '광고-마케팅' {'yes', 'no'}\r\n" + 
				"@attribute '논문-리포트' {'yes', 'no'}\r\n" + 
				"@attribute '영상-UCC-사진' {'yes', 'no'}\r\n" + 
				"@attribute '디자인-캐릭터-웹툰' {'yes', 'no'}\r\n" + 
				"@attribute '웹-모바일-플래시' {'yes', 'no'}\r\n" + 
				"@attribute '게임-소프트웨어' {'yes', 'no'}\r\n" + 
				"@attribute '과학-공학' {'yes', 'no'}\r\n" + 
				"@attribute '문학-글-시나리오' {'yes', 'no'}\r\n" + 
				"@attribute '건축-건설-인테리어' {'yes', 'no'}\r\n" + 
				"@attribute '네이밍-슬로건' {'yes', 'no'}\r\n" + 
				"@attribute '예체능-미술-음악' {'yes', 'no'}\r\n" + 
				"@attribute '대외활동-서포터즈' {'yes', 'no'}\r\n" + 
				"@attribute '봉사활동' {'yes', 'no'}\r\n" + 
				"@attribute '취업-창업' {'yes', 'no'}\r\n" + 
				"@attribute '해외' {'yes', 'no'}\r\n" + 
				"@attribute '기타' {'yes', 'no'}\r\n" + 
				"@data\r\n");
		for(int i=0; i<map.size(); i++) {
			List<String> cur = map.get((i+1) + "");
			for(int j=0, size=FIELD.length; j<size; j++) {
				if(cur.contains(FIELD[j])) {
					sb.append("\'yes\'");
				} else {
					sb.append("\'no\'");
				}
				if(j == 16) {
					sb.append("\n");
				} else {
					sb.append(", ");
				}
			}
		}
		File file = new File("src/main/resources/gospec.arff");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(sb.toString());
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}