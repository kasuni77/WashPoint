package com.washpoint.service;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.washpoint.model.LaundaryServices;

public interface LaundaryServicesInterface {
	    //Initialize logger
		public static final Logger logger = Logger.getLogger(LaundaryServicesInterface.class.getName());
		
		/**
		 * Add Services for Laundary services table
		 * 
		 * @param LaundaryServices
		 */
		public int addService(LaundaryServices lauServices);
		
		/**
		 * View laudary services from Laudary Services table
		 * 
		 * @return ArrayList<LaundaryServices>
		 */
		public ArrayList<LaundaryServices> viewLaundaryServices();
		

		/**
		 * Update laudary services from Laudary Services table
		 * 
		 * @return int
		 */
		public int updateService(LaundaryServices laundaryServices);
		
		/**
		 * Delete laudary services from Laudary Services table
		 * 
		 * @return int
		 */
		public int deleteService(String id);
		
		/**
		 * Select laudary services from Laudary Services table by Id
		 * 
		 * @return LaundaryServices
		 */
		public LaundaryServices getServiceById(String id);
		
		/**
		 * Select laudary services from Laudary Services table by Id
		 * 
		 * @return ArrayList<LaundaryServices>
		 */
		public ArrayList<LaundaryServices> getServicesById(String id);
	
		
}
