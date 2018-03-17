package SupportLibraries;

import java.sql.*;
import java.util.*;
import org.springframework.util.StringUtils;
import com.mongodb.connection.QueryResult;
import TestData.GlobalTestData;
import SupportLibraries.*;

public class DB {

	public static void DBConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rowcounter = 1;
		Map<Integer, String> QueryResult = new HashMap<Integer, String>();
		try {
			con = DBUtil.getConnection(DBType.ORADB);
			System.out.println("Connection established successfully");
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select * from ocs_admin.tp_customer where companyid = 'SAN' and companynumber in ('0265377')");
			while (rs.next()) {
				int columnscount = rs.getMetaData().getColumnCount();
				String data = "";
				for (int i = 1; i <= columnscount; i++) {
					if (StringUtils.isEmpty(rs.getString(i))) {
						data += rs.getMetaData().getColumnName(i) + "=|";
					} else {
						// data += rs.getMetaData().getColumnName(i) + "=" +
						// rs.getObject(i);
						data += rs.getObject(i) + ";";
					}
				}
				data = data.substring(0, data.length() - 1);
				System.out.println(data);
				QueryResult.put(rowcounter, data);
				rowcounter++;
			}
		} catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

	}

	// Iterate through Java MAP & get values in an array
	public Object[] IterateMap(Map<Integer, String> data) {
		String[] DBArray = null;
		for (Map.Entry m : data.entrySet()) {
			DBArray = (String[]) m.getValue();
		}
		return DBArray;

	}

	public static String EzpayID(String customerid, String customernumber) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rowcounter = 1;
		String EzpayID = null;
		try {
			con = DBUtil.getConnection(DBType.ORADB);
			System.out.println("Connection established successfully");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select UNIQUEID from ocs_admin.tp_customer where companyid = '" + customerid
					+ "' and companynumber = '" + customernumber + "'");
			while (rs.next()) {
				int columnscount = rs.getMetaData().getColumnCount();

				for (int i = 1; i <= columnscount; i++) {
					if (StringUtils.isEmpty(rs.getString(i))) {
						EzpayID = rs.getMetaData().getColumnName(i) + "=|";
					} else {
						// data += rs.getMetaData().getColumnName(i) + "=" +
						// rs.getObject(i);
						// data += rs.getObject(i) ;
						// data = rs.getMetaData().getColumnName(i);
						EzpayID = rs.getString(i);
					}
				}
				// data = data.substring(0, data.length() - 1);

			}
		} catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return EzpayID;

	}

	public static String reqdata(String query, String otherdata) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rowcounter = 1;
		String reqdata = null;

		try {
			con = DBUtil.getConnection(DBType.ORADB);
			System.out.println("Connection established successfully");
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int columnscount = rs.getMetaData().getColumnCount();

				for (int i = 1; i <= columnscount; i++) {
					if (StringUtils.isEmpty(rs.getString(i))) {
						reqdata = rs.getMetaData().getColumnName(i) + "=|";
					} else {
						// data += rs.getMetaData().getColumnName(i) + "=" +
						// rs.getObject(i);
						// data += rs.getObject(i) ;
						// data = rs.getMetaData().getColumnName(i);
						reqdata = rs.getString(i);
					}
				}
				// data = data.substring(0, data.length() - 1);

			}
		} catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return reqdata;

	}

	public static List<String> getData(String query) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rowcounter = 1;
		List<String> reqData = new ArrayList<String>();

		try {
			con = DBUtil.getConnection(DBType.ORADB);
			System.out.println("Connection established successfully");
			stmt = con.createStatement();
			System.out.println("Query Executing : " + query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int columnscount = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= columnscount; i++) {
					if (rs.getString(i) == null)
						reqData.add("null");
					else
						reqData.add(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return reqData;

	}
	
	public static List<String> getNetezzaData(String query) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rowcounter = 1;
		List<String> reqData = new ArrayList<String>();

		try {
			con = DBUtil.getConnection(DBType.NETEZZA);
			System.out.println("Connection established successfully");
			stmt = con.createStatement();
			System.out.println("Query Executing : " + query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int columnscount = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= columnscount; i++) {
					if (rs.getString(i) == null)
						reqData.add("null");
					else
						reqData.add(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return reqData;

	}

}
