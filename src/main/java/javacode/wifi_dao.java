package javacode;

import java.sql.Timestamp;

public class wifi_dao{
	int id;
	String wifi_id;
	String district;
	String name;
	String street_address;
	String detail_address;
	String floor;
	String type;
	String agency;
	String service_type;
	String network_type;
	String installation_year;
	int is_indoor;
	String network_setting;
	Double latX;
	Double lonY;
	Timestamp reg_date;
	int use_YN;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWifi_id() {
		return wifi_id;
	}
	public void setWifi_id(String wifi_id) {
		this.wifi_id = wifi_id;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet_address() {
		return street_address;
	}
	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getNetwork_type() {
		return network_type;
	}
	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
	}
	public String getInstallation_year() {
		return installation_year;
	}
	public void setInstallation_year(String installation_year) {
		this.installation_year = installation_year;
	}
	public int getIs_indoor() {
		return is_indoor;
	}
	public void setIs_indoor(int is_indoor) {
		this.is_indoor = is_indoor;
	}
	public String getNetwork_setting() {
		return network_setting;
	}
	public void setNetwork_setting(String network_setting) {
		this.network_setting = network_setting;
	}
	public Double getLatX() {
		return latX;
	}
	public void setLatX(Double latX) {
		this.latX = latX;
	}
	public Double getLonY() {
		return lonY;
	}
	public void setLonY(Double lonY) {
		this.lonY = lonY;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getUse_YN() {
		return use_YN;
	}
	public void setUse_YN(int use_YN) {
		this.use_YN = use_YN;
	}
}