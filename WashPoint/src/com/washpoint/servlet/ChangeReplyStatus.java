package com.washpoint.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.washpoint.model.Contact;
import com.washpoint.model.Employee;
import com.washpoint.service.ContactService;
import com.washpoint.service.ContactServiceImple;
import com.washpoint.service.EmployeeService;
import com.washpoint.service.EmployeeServiceImple;

/**
 * Servlet implementation class ChangeReplyStatus
 * @param <HttpServletRequest>
 */
@WebServlet("/ChangeReplyStatus")
public class ChangeReplyStatus<HttpServletRequest> extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeReplyStatus() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		Contact contact = new Contact();
		
		/*
		 * Initialize values for employee object
		 * */
		
		int cId = Integer.parseInt(request.getParameter("cId"));
		String Rep_status = request.getParameter("status");
	
		ContactService contactService = new ContactServiceImple();
		int status = contactService.updateStatus(cId, Rep_status);
		
		if(status == 1) {
			request.setAttribute("message", "Status Update Succesful");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMessage.jsp");
			dispatcher.forward(request, response);
		} else if (status == 0) {
			request.setAttribute("message", "Status Update Failed");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMessage.jsp");
			dispatcher.forward(request, response);
		}
	}

}
