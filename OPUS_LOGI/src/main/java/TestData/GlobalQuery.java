package TestData;

import TestData.*;

public class GlobalQuery {

	static String ticketNumber=null;
	
	public static final String NoofCustomerScheduledRO = "select count(fk_customer) from ocs_Admin.tp_customerorder where fk_routeorder in (select distinct(pkey)from OCS_ADMIN.vP_ROUTEORDER v where v.orderdate like to_date('"+GlobalTestData.ToDate+"','MM/DD/YYYY') and v.site.pkey = '6776' and  V.ROUTE.lob.uniqueId = 'O')";
		
	public static final String NoofCustomerScheduledCOM = "select count(fk_customer) from ocs_Admin.tp_customerorder where fk_routeorder in (select distinct(pkey)from OCS_ADMIN.vP_ROUTEORDER v where v.orderdate like to_date('"+GlobalTestData.ToDate+"','MM/DD/YYYY') and v.site.pkey = '6776' and  V.ROUTE.lob.uniqueId = 'C')";

	public static final String NoofCustomerScheduledRES = "select count(fk_customer) from ocs_Admin.tp_customerorder where fk_routeorder in (select distinct(pkey)from OCS_ADMIN.vP_ROUTEORDER v where v.orderdate like to_date('"+GlobalTestData.ToDate+"','MM/DD/YYYY') and v.site.pkey = '6776' and  V.ROUTE.lob.uniqueId = 'R')";
	
	public static final String ArriveCustomerTime = "select TO_CHAR(v.arrivecustomerstamp - (5 / 24) , 'HH:MI') , TO_CHAR(v.DEPARTCUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("+ticketNumber+"))and fk_vehicle is not null";
}

//*[@id='yui_3_1_1499407274718_226']/div[6]/ul/li/label/span