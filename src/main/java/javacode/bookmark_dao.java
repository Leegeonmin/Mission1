package javacode;

import java.sql.Timestamp;

public class bookmark_dao{
	int id;
	int wifi_id;
	String name;
	int seq;
	String wifi_name;
	Timestamp reg_date;
	Timestamp up_date;
	int use_YN;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWifi_id() {
		return wifi_id;
	}
	public void setWifi_id(int wifi_id) {
		this.wifi_id = wifi_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int order) {
		this.seq = order;
	}
	public String getWifi_name() {
		return wifi_name;
	}
	public void setWifi_name(String wifi_name) {
		this.wifi_name = wifi_name;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public Timestamp getUp_date() {
		return up_date;
	}
	public void setUp_date(Timestamp up_date) {
		this.up_date = up_date;
	}
	public int getUse_YN() {
		return use_YN;
	}
	public void setUse_YN(int use_YN) {
		this.use_YN = use_YN;
	}
}