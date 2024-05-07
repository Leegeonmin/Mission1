package javacode;

import java.sql.Timestamp;

public class history_dao {
	int id;
	double latX;
	double lonY;
	Timestamp reg_date;
	int use_YN;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLatX() {
		return latX;
	}
	public void setLatX(double latX) {
		this.latX = latX;
	}
	public double getLonY() {
		return lonY;
	}
	public void setLonY(double lonY) {
		this.lonY = lonY;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp timestamp) {
		this.reg_date = timestamp;
	}
	public int getUse_YN() {
		return use_YN;
	}
	public void setUse_YN(int use_YN) {
		this.use_YN = use_YN;
	}
}
