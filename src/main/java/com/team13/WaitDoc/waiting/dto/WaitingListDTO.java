package com.team13.WaitDoc.waiting.dto;

import com.team13.WaitDoc.hospital.entity.Hospital;

public class WaitingListDTO {

	private Hospital hospital;
	private int myOrder;

	public WaitingListDTO(Hospital hospital, int myOrder) {
		this.hospital = hospital;
		this.myOrder = myOrder;
	}

	public Hospital getHospital() {
		return this.hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public int getMyOrder() {
		return this.myOrder;
	}

	public void setMyOrder(int myOrder) {
		this.myOrder = myOrder;
	}
}
