package javacode;

public class DistanceWifi {
        wifi_dao wifi;
        double distance;
        
        public DistanceWifi(wifi_dao wifi, double distance) {
            this.wifi = wifi;
            this.distance = distance;
        }

		public wifi_dao getWifi() {
			return wifi;
		}

		public void setWifi(wifi_dao wifi) {
			this.wifi = wifi;
		}

		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}
    }