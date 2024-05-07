package javacode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class file
 */
@WebServlet("/save_wifi_servlet")
public class save_wifi_servlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public save_wifi_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// API 최대 검색 횟수 제한 값 설정
		int start = 1;
		int end = 1000;
		
		//api통해 받은 데이터를 db에 저장하고 페이지에 개수를 보여줄 result 배열
		ArrayList<wifi_dto> result = new ArrayList<wifi_dto>();
		while(true) {
			
	        String apiUrl = "http://openapi.seoul.go.kr:8088/764b527a626c6b6d34327056436d6e/json/TbPublicWifiInfo/" + String.valueOf(start) + "/" + String.valueOf(end);
	        // HTTP 요청 보내기
	        URL url = new URL(apiUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        // 응답 코드 확인
	        int responseCode = connection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            // 응답 데이터 읽기
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String inputLine;
	            StringBuilder responseBuilder = new StringBuilder();
	            while ((inputLine = in.readLine()) != null) {
	                responseBuilder.append(inputLine);
	            }
	            in.close();
	            Gson gson = new Gson();
	            Type type = new TypeToken<Map<String, Map<String, Object>>>() {
	            }.getType();
	            Map<String, Map<String, Object>> map = gson.fromJson(responseBuilder.toString(), type);
	            
	            // 탈출 조건
	            if (map.get("TbPublicWifiInfo") == null || map.get("TbPublicWifiInfo").get("row") == null) {
	                break;
	            }
	            
	            List<Map<String, String>> rowList = (List<Map<String, String>>) map.get("TbPublicWifiInfo").get("row");

	            for (Map<String, String> row : rowList) {
	                wifi_dto wifiInfo = new wifi_dto();
	                wifiInfo.setxSwifiMgrNo(row.get("X_SWIFI_MGR_NO"));
	                wifiInfo.setxSwifiWrdocf(row.get("X_SWIFI_WRDOFC"));
	                wifiInfo.setxSwifiMainNm(row.get("X_SWIFI_MAIN_NM"));
	                wifiInfo.setxSwifiAdres1(row.get("X_SWIFI_ADRES1"));
	                wifiInfo.setxSwifiAdres2(row.get("X_SWIFI_ADRES2"));
	                wifiInfo.setxSwifiInstlFloor(row.get("X_SWIFI_INSTL_FLOOR"));
	                wifiInfo.setxSwifiInstlTy(row.get("X_SWIFI_INSTL_TY"));
	                wifiInfo.setxSwifiInstlMby(row.get("X_SWIFI_INSTL_MBY"));
	                wifiInfo.setxSwifiSvcSe(row.get("X_SWIFI_SVC_SE"));
	                wifiInfo.setxSwifiCmcwr(row.get("X_SWIFI_CMCWR"));
	                wifiInfo.setxSwifiCnstcYear(row.get("X_SWIFI_CNSTC_YEAR"));
	                wifiInfo.setxSwifiInoutDoor(row.get("X_SWIFI_INOUT_DOOR"));
	                wifiInfo.setxSwifiRemars3(row.get("X_SWIFI_REMARS3"));
	                wifiInfo.setLat(row.get("LAT"));
	                wifiInfo.setLnt(row.get("LNT"));
	                wifiInfo.setWorkDttm(row.get("WORK_DTTM"));

	                result.add(wifiInfo);
	            }
	            
	            
	            start += 1000;
	            end += 1000;
	        } else {
	            // 만약 응답 코드가 HTTP_OK가 아니면 종료
	            break;
	        }
		}
		// ####
		// DB 저장 로직 
		wifi_repo wifi_repository =  new wifi_repo();
		wifi_repository.insertWifiInfo(result);
		// ####
            int size = result.size();         
            // JSP로 데이터 전달
            request.setAttribute("count", size);
            RequestDispatcher dispatcher = request.getRequestDispatcher("api_save_result.jsp");
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