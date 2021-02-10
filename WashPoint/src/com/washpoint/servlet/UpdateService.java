package com.washpoint.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.washpoint.model.LaundaryServices;
import com.washpoint.service.LaundaryServicesImple;

/**
 * Servlet implementation class UpdateService
 */
@WebServlet("/UpdateService")
public class UpdateService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateService() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");
		
		LaundaryServices laundaryServices = new LaundaryServices();
		
		/*
		 * Initialize values for LaundaryService object
		 * */
		
		String type = request.getParameter("type");
		
		laundaryServices.setName(request.getParameter("name"));
		laundaryServices.setDescription(request.getParameter("description"));
		laundaryServices.setUnitPrice(Double.parseDouble(request.getParameter("price")));
		laundaryServices.setServiceId(request.getParameter("serviceId"));
		
		LaundaryServicesImple laundaryServicesImple = new LaundaryServicesImple();
		int status = laundaryServicesImple.updateService(laundaryServices);
		
		if(type.equals("mn") && status == 1) {
			request.setAttribute("message", "Update Succesful");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMsgCustomer.jsp");
			dispatcher.forward(request, response);
		} else if(type.equals("mn") && status == 0) {
			request.setAttribute("message", "Update Failed");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMsgCustomer.jsp");
			dispatcher.forward(request, response);
		} else if(status == 1) {
			request.setAttribute("message", "Update Succesful");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMessage.jsp");
			dispatcher.forward(request, response);
		} else if (status == 0) {
			request.setAttribute("message", "Update Failed");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMessage.jsp");
			dispatcher.forward(request, response);
		}
	}

}
