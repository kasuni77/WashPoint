package com.washpoint.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.washpoint.model.Customer;
import com.washpoint.service.CustomerServiceImple;

/**
 * Servlet implementation class PlaceAOrder
 */
@WebServlet("/customerRegister")
public class customerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer();
		
		customer.setfName(request.getParameter("firstName"));
		customer.setlName(request.getParameter("lastName"));
		customer.setEmail(request.getParameter("email"));
		customer.setNic(request.getParameter("NIC"));
		customer.setGender(request.getParameter("gender"));
		customer.setAddress(request.getParameter("address")); 
		customer.setMobile(request.getParameter("mobile"));
		customer.setPassword(request.getParameter("password"));
	
		String status;
		
		CustomerServiceImple customerServiceImple = new CustomerServiceImple();
		status = customerServiceImple.addCustomer(customer);
		
		if(status.equals("Fail")) {
			request.setAttribute("message", "Sorry, Something went wrong.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMsgCustomer.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "Registration Successful. Login Again with USER ID : " + status);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMsgCustomer.jsp");
			dispatcher.forward(request, response);
		}
	}

}
