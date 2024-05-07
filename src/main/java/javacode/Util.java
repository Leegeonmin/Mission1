package javacode;

public class Util{
	// 두 점 사이의 거리를 계산하는 메서드
	static double calculateDistance(double x1, double y1, double x2, double y2) {
	    // 실제 거리를 계산하는 코드를 여기에 추가할 수 있습니다.
	    // 여기서는 단순히 두 점 사이의 유클리디안 거리를 계산한 예시를 제공합니다.
	    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}