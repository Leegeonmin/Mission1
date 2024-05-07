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
 * Servlet implementation class delete_bookmark_servlet
 */
@WebServlet("/delete_bookmarkgroup_servlet")
public class delete_bookmarkgroup_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete_bookmarkgroup_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
		
		bookmark_repo bookmark_repository = new bookmark_repo();
		bookmark_repository.deleteBookmarkyInfo(id);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('북마크를 삭제 하였습니다.'); location.href='bookmark_group.jsp';</script>"); 
		writer.close();
		

        RequestDispatcher dispatcher = request.getRequestDispatcher("bookmark_group.jsp");
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
