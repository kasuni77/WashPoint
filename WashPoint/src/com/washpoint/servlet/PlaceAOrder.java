package com.washpoint.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.washpoint.model.LaundaryServices;
import com.washpoint.model.Order;
import com.washpoint.service.LaundaryServicesImple;
import com.washpoint.service.OrderServiceImple;

/**
 * Servlet implementation class PlaceAOrder
 */
@WebServlet("/PlaceAOrder")
public class PlaceAOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceAOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		LaundaryServicesImple laundaryServicesImple = new LaundaryServicesImple();
		Order order = new Order();
	
		ArrayList<LaundaryServices> arrayList = laundaryServicesImple.viewLaundaryServices();
		
		String description = "";
		
		int  i = 1;
	
		for(LaundaryServices lServ : arrayList) {
			if(request.getParameter(lServ.getServiceId()) != null) {
				int quantity = Integer.parseInt(request.getParameter(lServ.getServiceId()));
				description = description + " **( " + i + " ) " + lServ.getName() + " -> " + quantity + " ** ";
				i ++;
			}
		}
		
		order.setTotal(Double.parseDouble(request.getParameter("totalP")));
		order.setCustomerId(request.getParameter("customer"));
		order.setDescription(description);
			
		OrderServiceImple orderServiceImple = new OrderServiceImple();
		
		int status = orderServiceImple.addOrder(order);
		
		if(status == 1) {
			request.setAttribute("message", "Place Order Succesful. We will collect your garments soon.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMsgCustomer.jsp");
			dispatcher.forward(request, response);
		} else if (status == 0) {
			request.setAttribute("message", "Sorry, something went wrong");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/databaseMsgCustomer.jsp");
			dispatcher.forward(request, response);
		}
	}

}
