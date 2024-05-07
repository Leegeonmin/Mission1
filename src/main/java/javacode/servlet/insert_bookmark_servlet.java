package javacode.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.bookmark_repo;

/**
 * Servlet implementation class insert_bookmarkgroup_servlet
 */
@WebServlet("/insert_bookmark_servlet")
public class insert_bookmark_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert_bookmark_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int seq= Integer.parseInt(request.getParameter("seq"));
		System.out.println("뭘봐");
		bookmark_repo bookmark_repository = new bookmark_repo();
		bookmark_repository.insertBookmarkInfo(name, seq);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('북마크 그룹 정보를 추가 하였습니다'); location.href='bookmark.jsp';</script>"); 
		writer.close();
		

        RequestDispatcher dispatcher = request.getRequestDispatcher("bookmark.jsp");
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
