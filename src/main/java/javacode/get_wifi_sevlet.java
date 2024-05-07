package javacode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javacode.DistanceWifi;
/**
 * Servlet implementation class get_wifi_sevlet
 */
@WebServlet("/get_wifi_sevlet")
public class get_wifi_sevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_wifi_sevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    // 거리상 가까운 wifi 20개 찾는 메서드
    static ArrayList<DistanceWifi> get20WifiInfo(ArrayList<wifi_dao> dao, double latX, double lonY){
    	ArrayList<DistanceWifi> nearestWifi = new ArrayList<>();
        // 모든 dao 객체와의 거리를 계산하여 저장할 리스트
        List<DistanceWifi> distances = new ArrayList<>();
        
        // 모든 dao 객체와의 거리를 계산하여 distances 리스트에 저장
        for (wifi_dao wifi : dao) {
            double distance = calculateDistance(latX, lonY, wifi.getLatX(), wifi.getLonY());
            distances.add(new DistanceWifi(wifi, distance));
        }
        
        // 거리에 따라 distances 리스트를 정렬
        Collections.sort(distances, new Comparator<DistanceWifi>() {
            @Override
            public int compare(DistanceWifi wifi1, DistanceWifi wifi2) {
                return Double.compare(wifi1.distance, wifi2.distance);
            }
        });
        
        // 가장 가까운 20개의 dao 객체를 nearestWifi 리스트에 저장
        for (int i = 0; i < Math.min(20, distances.size()); i++) {
            nearestWifi.add(distances.get(i));
        }
        
    	return nearestWifi;
    	
    }
    // 두 점 사이의 거리를 계산하는 메서드
    static double calculateDistance(double x1, double y1, double x2, double y2) {
        // 실제 거리를 계산하는 코드를 여기에 추가할 수 있습니다.
        // 여기서는 단순히 두 점 사이의 유클리디안 거리를 계산한 예시를 제공합니다.
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 위도와 경도 값 가져오기
		double latX = request.getParameter("latX") != null ?  Double.parseDouble(request.getParameter("latX")) : 0;
		double lonY = request.getParameter("lonY") != null ?  Double.parseDouble(request.getParameter("lonY")) : 0;
		
		// transaction 필요
		// 히스토리 저장하는 로직
		history_repo history_repository = new history_repo();
		history_repository.insertHistoryInfo(latX, lonY);
		
		// wifi 가져오는 로직
		wifi_repo wifi_repository =  new wifi_repo();
		ArrayList<wifi_dao> dao_list = wifi_repository.findWifiInfoAll();

        ArrayList<DistanceWifi> result = get20WifiInfo(dao_list, latX, lonY);
        
        request.setAttribute("wifiList", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


