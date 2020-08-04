package com.gospec.recommend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.gospec.domain.ClusterDto;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class KMeansClustering {

	public BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;

		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}

		return inputReader;
	}

	public List<ClusterDto> readData() throws Exception {
		SimpleKMeans kmeans = new SimpleKMeans();

		kmeans.setSeed(10);

		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(7);

		BufferedReader datafile = readDataFile("src/main/resources/gospec.arff");
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
}