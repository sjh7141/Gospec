package com.gospec.domain;

public class ClusterDto {

	private int no;
	private int cluster;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCluster() {
		return cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	public ClusterDto(int no, int cluster) {
		this.no = no;
		this.cluster = cluster;
	}

}
