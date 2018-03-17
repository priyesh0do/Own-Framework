package SupportLibraries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	public static Properties configProp=PropertiesRead.readConfigProperty();
	public static final String ORACS = configProp.getProperty("dburl");
    public static final String ORAUSER = configProp.getProperty("dbuser");
    public static final String ORAPWD = configProp.getProperty("dbpassword");
    public static final String SQLSERVCS ="jdbc:sqlserver://ADCACODBQA.oakleafwastemgmt.com\\QA;DatabaseName=AcornQA;user=WM\\srathore;password=June@2017";
    //jdbc:sqlserver://localhost:1433;DatabaseName=YourDBName;user=UserName;Password=YourPassword
    //jdbc:sqlserver://ADCACODBQA.oakleafwastemgmt.com\\QA;databaseName=MYDB;integratedSecurity=true
    public static final String SQLSERVUSER="UID=WM\\srathore";
    public static final String SQLSERVPWD = "June@2017";
    public static final String NETEZZAURL = "jdbc:netezza://adcnza1/SYSTEM" ;
    public static final String NETEZZAUSER = "rbedi";
    public static final String NETEZZAPWD = "waste123";
    public static Connection getConnection(DBType dbType) throws SQLException, ClassNotFoundException{
                    switch(dbType) {
                    case ORADB:
                                    return DriverManager.getConnection(ORACS, ORAUSER, ORAPWD);
                                    
                    case MYSQLDB:
                                    break;
                                    
                    case SQLSERVER:
                                    //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                    return DriverManager.getConnection(SQLSERVCS);
                    case NETEZZA:
                    			
                    			//Class.forName("org.netezza.Driver");
                    			return DriverManager.getConnection(NETEZZAURL, NETEZZAUSER, NETEZZAPWD);
                    	
                                    
                    }
                    return null;
    }

    public static void showErrorMessage(SQLException e){
                    System.err.println("Error :"+ e.getMessage() );
                    System.err.println("Error :"+ e.getErrorCode() );                
                    
    }

}


