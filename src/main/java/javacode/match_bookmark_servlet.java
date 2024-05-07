package javacode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class match_bookmark_servlet
 */
@WebServlet("/match_bookmark_servlet")
public class match_bookmark_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public match_bookmark_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookmark_id = Integer.parseInt(request.getParameter("bookmark_id")); 
		int wifi_id = Integer.parseInt(request.getParameter("wifi_id"));
		String wifi_name = request.getParameter("wifi_name");
		
		bookmark_repo bookmark_repo = new bookmark_repo();
		bookmark_repo.updateWiifiInfo(wifi_id, wifi_name, bookmark_id);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('북마크 정보를 추가 하였습니다.'); location.href='matched_bookmark.jsp';</script>"); 
		writer.close();
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("matched_bookmark.jsp");
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
