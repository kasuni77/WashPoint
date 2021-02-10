package com.washpoint.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.washpoint.model.LaundaryServices;
import com.washpoint.service.LaundaryServicesImple;


@WebServlet("/findService")
public class findService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String id = request.getParameter("uid");
	    
	    LaundaryServicesImple laundaryServicesImple = new LaundaryServicesImple();
	    
	    try {
        	  List<LaundaryServices> serDetails = laundaryServicesImple.getServicesById(id);
        	  request.setAttribute("serDetails", serDetails);
	    }
	    catch (Exception e) {
		e.printStackTrace();
	    }
	    
	    
	    RequestDispatcher dis = request.getRequestDispatcher("managerServices.jsp");
	    dis.forward(request, response);    
	    
	}

}
