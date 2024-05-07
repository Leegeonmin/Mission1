package javacode;

public class wifi_service{
	wifi_repo repository = new wifi_repo();
	Util util = new Util();
	public DistanceWifi getWifiDetail(int id, double latX, double lonY) {
		
		wifi_dao dao = repository.findById(id);
		double distance = util.calculateDistance(latX, lonY, dao.getLatX(), dao.getLonY());
		return new DistanceWifi(dao, distance);
	}
}