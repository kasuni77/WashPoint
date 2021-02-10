package com.washpoint.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.washpoint.model.LaundaryServices;
import com.washpoint.util.CommonConstants;
import com.washpoint.util.ConnectDB;
import com.washpoint.util.QueryUtil;

public class LaundaryServicesImple implements LaundaryServicesInterface {

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;

	@Override
	public int addService(LaundaryServices lauServices) {
		int status = 0;

		try {
			connection = ConnectDB.getDBConnection();
			String sql = QueryUtil.queryByID(CommonConstants.QUERY_INSERT_INTO_SERVICES);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, lauServices.getName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, lauServices.getDescription());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_THREE, lauServices.getUnitPrice());

			preparedStatement.executeUpdate();
			status = 1;
		} catch (SQLException | IOException | ParseException | org.xml.sax.SAXException
				| ParserConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return status;
	}

	@Override
	public ArrayList<LaundaryServices> viewLaundaryServices() {
		ArrayList<LaundaryServices> laundaryServiceList = new ArrayList<LaundaryServices>();

		try {
			connection = ConnectDB.getDBConnection();
			statement = connection.createStatement();

			String sql = QueryUtil.queryByID(CommonConstants.QUERY_SELECT_FROM_TABLE_SERVICES);

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				LaundaryServices laundaryServices = new LaundaryServices();
				laundaryServices.setServiceId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				laundaryServices.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				laundaryServices.setDescription(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				laundaryServices.setUnitPrice(resultSet.getDouble(CommonConstants.COLUMN_INDEX_FOUR));
				laundaryServices.setServiceImg(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));

				laundaryServiceList.add(laundaryServices);
			}
		} catch (SQLException | ParseException | SAXException | IOException | ParserConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return laundaryServiceList;
	}

	@Override
	public int updateService(LaundaryServices laundaryServices) {
		int status = 0;

		try {
			connection = ConnectDB.getDBConnection();
			String sql = QueryUtil.queryByID(CommonConstants.QUERY_UPDATE_FROM_SERVICES);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, laundaryServices.getName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, laundaryServices.getDescription());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_THREE, laundaryServices.getUnitPrice());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, laundaryServices.getServiceId());
			preparedStatement.executeUpdate();
			status = 1;
		} catch (SQLException | IOException | ParseException | org.xml.sax.SAXException
				| ParserConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return status;
	}

	@Override
	public int deleteService(String id) {
		int status = 0;

		try {
			connection = ConnectDB.getDBConnection();

			String sql = QueryUtil.queryByID(CommonConstants.QUERY_DELETE_FROM_SERVICES);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, id);

			preparedStatement.executeUpdate();

			status = 1;
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}

		} catch (SQLException | IOException | ParseException | org.xml.sax.SAXException
				| ParserConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

		return status;
	}

	@Override
	public LaundaryServices getServiceById(String id) {
		LaundaryServices laundaryServices = new LaundaryServices();
		try {
			connection = com.washpoint.util.ConnectDB.getDBConnection();

			String sql = QueryUtil.queryByID(CommonConstants.QUERY_VIEW_SERVICE_BY_ID);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				laundaryServices.setServiceId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				laundaryServices.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				laundaryServices.setDescription(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				laundaryServices.setUnitPrice(resultSet.getDouble(CommonConstants.COLUMN_INDEX_FOUR));
				laundaryServices.setServiceImg(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
			}

			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}

		} catch (SQLException | IOException | ParseException | org.xml.sax.SAXException
				| ParserConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return laundaryServices;
	}

	@Override
	public ArrayList<LaundaryServices> getServicesById(String id) {
		LaundaryServices laundaryServices = new LaundaryServices();
		ArrayList<LaundaryServices> service = new ArrayList<LaundaryServices>();
		
		try {
			connection = com.washpoint.util.ConnectDB.getDBConnection();

			String sql = QueryUtil.queryByID(CommonConstants.QUERY_VIEW_SERVICE_BY_ID);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				laundaryServices.setServiceId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				laundaryServices.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				laundaryServices.setDescription(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				laundaryServices.setUnitPrice(resultSet.getDouble(CommonConstants.COLUMN_INDEX_FOUR));
				laundaryServices.setServiceImg(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				
				service.add(laundaryServices);
			}

			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}

		} catch (SQLException | IOException | ParseException | org.xml.sax.SAXException
				| ParserConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return service;
	}

}
