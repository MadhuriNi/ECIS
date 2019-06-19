package Pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Utility.DBConnection;
import Utility.readConfig;

public class custservPage {

	
	WebDriver driver;
	Properties prop;
	public Logger logger;
	
	boolean OVERNITE_RTSVC;
	String overnite;
	
    boolean IPD_IED;
    String ipd;
    
    boolean DOC_PREPSVC;
    String doc;
    
    boolean INTL_FRTSVC;
    String intlfrt;
    
    boolean COLLECT_RECPT;
    String collrecipet;
    
    boolean PREF_CUST;
    String prefcust;
    
    boolean DANGEROUSGOODS;
    String dangerousgoods;
    String selected_dangerous_good;
    
    boolean CUT_FLOWERS;
    String cutflowers1;
    
    boolean crspndance;
    String crspondence;
    
    boolean ALLOW_REROUTE;
    String allowre;
    
    boolean SIGNATURE_REQ;
    String sigreq1;
    
    
    boolean NOTIFY_SHPDMAY;
    String notifyshp;
    
    boolean PARTIAL_PYMT;
    String partial;
    
    boolean ONLINE_ELIG;
    String onlineelig1;
    
    boolean SUPPLIES_NOCUT;
    String selected_supplies_nocut;
    
	boolean EMERGE;
	String emerge;
	
    boolean gratuity;
    String gratui;
    
    boolean EDRW_POD;
    String edrw1;
    
    boolean NATL_REVIEW;
    String natlre;
    
    boolean INTL_SHIPPER;
    String intlship;
    String selected_intl_shipper;
    
    boolean SHIPPER_XPORTDCLR;
    String shipperxp;
    
    boolean FEC_CARD;
    String fec1;
    String selected_fec_card;
    
    boolean MMA;
    String mma1;
    String selected_mma;
    
    boolean REGULAR_STOP;
    String regstop;
    
    boolean WRIGHT_OFFIND;
	boolean TRANS_SMART;
	String trans;
	String selected_wright;
	
    boolean METER_ZONE;
    String meterzone;
    
    boolean ARROW_CUST;
    String arrowcust;
    
    boolean INSIGHT;
    String insig;
    
    boolean SIGNON_FILE;
    String signon1;
    
    boolean GROUND_HAZMAT;
    String groundhazmat;
    
    boolean DVX;
    String dv;
    
    boolean POWR_OFATTY;
    String powratty1;
    
    boolean POADT;
    boolean GAA;
    String ga;
    
    boolean ARCHIVEO_PTION;
    boolean SUPPLIES_ELIG;
    String supplieselig;
    
    boolean DOCEXCEPTION_IND;
    String selected_docexception_ind;
    
    boolean  MA_IS;
    String selected_mais;

    boolean  PREMIER_PGM;
    String selected_premier_pgm;
    
    boolean  IDF_ELIGIBILITY;
    String idfelig;
    
    
    boolean  EXPRESS_PLAN;
    String express1;
    
    String  AUDIT_FIRM;
    boolean   AUEXP_DT;
    String   BROK_ER;
    String   BRK_DATE;
    
    boolean   EXPRESS_MPS;
    String expressmps;
    
    boolean   NRI_BI;
    String nri1;
    
    String   hva;
    String selected_hva_s;
    
    boolean   GROUND_MPS;
    String   MARKETNG;
    String   FORWARDER_BROKER;
    String   DEBUT_COCODE;
    
    boolean   ARCHIVE_OPTION;
    String selected_arhive_option;
   
	
	
	public custservPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void get_custserv() throws Exception
	{
		logger=Logger.getLogger("custservPage");
		
		logger.info("-----------Inside the cust serv page-------------");
			
/*		Select drpscreen = new Select(driver.findElement(By.id("myForm:navigationDrpDwn1")));
		drpscreen.selectByVisibleText("Service Options & Marketing Info");
		
		By view=By.xpath(".//*[@id='myForm:viewButton']");
		driver.findElement(view).click();*/
		
		
		
		/*By home_page=By.id("myForm:quicksearLandngLbl_link");
		driver.findElement(home_page).click();*/
		
		driver.navigate().back();
		logger.info("-----------Clicking home page-----------------");
		
		
		By service_marketing=By.xpath(".//*[@id='myForm:custSrveOptions']");
		driver.findElement(service_marketing).click();
		logger.info("------------At Service and Marketing page--------------");
		
		Thread.sleep(6000);
		
		By overnight = By.id("myForm:service:freightservice");
		OVERNITE_RTSVC=driver.findElement(overnight).isSelected();
		overnite=booleantoString(OVERNITE_RTSVC);
		logger.info("OvernightFrieght service status is "+overnite);
	
		By intlpriority=By.id("myForm:service:distibservice");
		IPD_IED=driver.findElement(intlpriority).isSelected();
		ipd=booleantoString(IPD_IED);
		logger.info("International Priority Service "+ipd);
				
		By docprep = By.id("myForm:service:docprepservice");
		DOC_PREPSVC=driver.findElement(docprep).isSelected();
		doc=booleantoString(DOC_PREPSVC);
		logger.info("Document prep service is "+doc);
		
		By intlfreight = By.id("myForm:service:freightservice1");
		INTL_FRTSVC=driver.findElement(intlfreight).isSelected();
		intlfrt=booleantoString(INTL_FRTSVC);
		logger.info("International Freight service is "+intlfrt);
				
		By coll_recpt = By.id("myForm:service:collectreceipt");
		COLLECT_RECPT=driver.findElement(coll_recpt).isSelected();
		collrecipet=booleantoString(COLLECT_RECPT);
		logger.info("Collect receipt is "+collrecipet);
				
		By perfcust = By.id("myForm:service:prefcust");
		PREF_CUST=driver.findElement(perfcust).isSelected();
		prefcust=booleantoString(PREF_CUST);
		logger.info("Perferred Customer is "+PREF_CUST);
		
		
		By dangerous_goods = By.id("myForm:service:dangerousGoods");
		Select DANGEROUSGOODS=new Select(driver.findElement(dangerous_goods));
		selected_dangerous_good=DANGEROUSGOODS.getFirstSelectedOption().getText();
		//dangerousgoods=booleantoString(DANGEROUSGOODS);
		logger.info("Perferred Customer is "+selected_dangerous_good);
		
		By cutflowers = By.id("myForm:service:flowers");
		CUT_FLOWERS=driver.findElement(cutflowers).isSelected();
		cutflowers1=booleantoString(CUT_FLOWERS);		
		logger.info("Cut Flower is "+cutflowers1);
		
		
		By grndhazmat = By.id("myForm:service:flowers");
		GROUND_HAZMAT=driver.findElement(grndhazmat).isSelected();
		groundhazmat=booleantoString(GROUND_HAZMAT);		
		logger.info("Ground Hazmat is "+groundhazmat);
		
			
		By coresp = By.id("myForm:service:correspondence");
		crspndance=driver.findElement(coresp).isSelected();
		crspondence=booleantoString(crspndance);		
		logger.info("Correspondance is "+crspondence);
		
				
		By allwreroute = By.id("myForm:service:allowreroute");
		ALLOW_REROUTE=driver.findElement(allwreroute).isSelected();
		allowre=booleantoString(ALLOW_REROUTE);		
		logger.info("Allow Reroute is "+allowre);
		
		
		By sigreq = By.id("myForm:service:signreq");
		SIGNATURE_REQ=driver.findElement(sigreq).isSelected();
		sigreq1=booleantoString(SIGNATURE_REQ);		
		logger.info("Signature required is "+sigreq1);
				
		By notispmay = By.id("myForm:service:delay");
		NOTIFY_SHPDMAY=driver.findElement(notispmay).isSelected();
		notifyshp=booleantoString(NOTIFY_SHPDMAY);		
		logger.info("Notify is "+notifyshp);
		
		
		By partialpymt = By.id("myForm:service:pay");
		PARTIAL_PYMT=driver.findElement(partialpymt).isSelected();
		partial=booleantoString(PARTIAL_PYMT);		
		logger.info("Partial Payment is "+partial);
		
		
		By onlineelig = By.id("myForm:service:onlneligible");
		ONLINE_ELIG=driver.findElement(onlineelig).isSelected();
		onlineelig1=booleantoString(ONLINE_ELIG);		
		logger.info("Online Eligibility is "+onlineelig1);
		
		
		By supnocut = By.id("myForm:service:suppnocut");
		SUPPLIES_NOCUT=driver.findElement(supnocut).isSelected();	
		selected_supplies_nocut=booleantoString(SUPPLIES_NOCUT);
		logger.info("Supplies NoCut is "+selected_supplies_nocut);
		
		
		/*By supnocut = By.id("myForm:service:suppnocut");
		Select SUPPLIES_NOCUT=new Select(driver.findElement(supnocut));	
		selected_supplies_nocut=SUPPLIES_NOCUT.getFirstSelectedOption().getText().substring(0);
		logger.info("Supplies NoCut is "+selected_supplies_nocut);*/
		
		
		By emrg = By.id("myForm:service:emerge");
		EMERGE=driver.findElement(emrg).isSelected();
		emerge=booleantoString(EMERGE);		
		logger.info("Emerge is "+emerge);
		
		
		By grat = By.id("myForm:service:gratuity");
		gratuity=driver.findElement(grat).isSelected();
		gratui=booleantoString(gratuity);		
		logger.info("Gratuity is "+gratui);
				
		By edrw = By.id("myForm:service:edrwpod");
		EDRW_POD=driver.findElement(edrw).isSelected();
		edrw1=booleantoString(EDRW_POD);
		logger.info("EDRW is "+edrw1);
				
		/*By natl = By.id("myForm:service:natrev");
		NATL_REVIEW=driver.findElement(natl).isSelected();
		natlre=booleantoString(NATL_REVIEW);*/  //now
		natlre="";
		logger.info("National Review is "+natlre);
						
		By intl = By.id("myForm:service:internatnlshipr");
		Select INTL_SHIPPER=new Select(driver.findElement(intl));
		selected_intl_shipper=INTL_SHIPPER.getFirstSelectedOption().getText();
		//INTL_SHIPPER=driver.findElement(intl).isSelected();
		//intlship=booleantoString(INTL_SHIPPER);
		logger.info("International Shipper is "+selected_intl_shipper);
				
		By xport = By.id("myForm:service:shipexpdeclaration");
		SHIPPER_XPORTDCLR=driver.findElement(xport).isSelected();
		shipperxp=booleantoString(SHIPPER_XPORTDCLR);
		logger.info("Signature required is "+shipperxp);
				
		By fec = By.id("myForm:service:servfecCard");
		Select FEC_CARD=new Select(driver.findElement(fec));
		selected_fec_card=FEC_CARD.getFirstSelectedOption().getText();
		//fec1=booleantoString(FEC_CARD);
		logger.info("FEC  is "+selected_fec_card);
				
		By mma = By.id("myForm:service:mma");
		Select MMA=new Select(driver.findElement(mma));
		selected_mma=MMA.getFirstSelectedOption().getText();
		//mma1=booleantoString(MMA);
		logger.info("MMA is "+selected_mma);
				
		By reg_stop = By.id("myForm:service:regstop");
		REGULAR_STOP=driver.findElement(reg_stop).isSelected();
		regstop=booleantoString(REGULAR_STOP);
		logger.info("Regular Stop is "+regstop);
		
		
		By wright_offind = By.id("myForm:service:writeoffindic");
		Select WRIGHT_OFFIND=new Select(driver.findElement(wright_offind));
		selected_wright = WRIGHT_OFFIND.getFirstSelectedOption().getText();
		logger.info("Wright Off is "+selected_wright);
				
		By trans_smart = By.id("myForm:service:transmart");
		TRANS_SMART=driver.findElement(trans_smart).isSelected();
		trans=booleantoString(TRANS_SMART);
		logger.info("Trans Smart is "+trans);
			
		By meter = By.id("myForm:service:metzone");
		METER_ZONE=driver.findElement(meter).isSelected();
		meterzone=booleantoString(METER_ZONE);
		logger.info("Meter is "+meterzone);
		
		
		/*By arrow = By.id("myForm:service:metzone");
		ARROW_CUST=driver.findElement(meter).isSelected();
		System.out.println("Signature required is "+ARROW_CUST);*/    //unable to find on the screen
		
		
		
		By insight = By.id("myForm:service:insight");
		INSIGHT=driver.findElement(insight).isSelected();
		insig=booleantoString(INSIGHT);
		logger.info("Insight is "+insig);
		
		
		
		By signon = By.id("myForm:service:signonfile");
		SIGNON_FILE=driver.findElement(signon).isSelected();
		signon1=booleantoString(SIGNON_FILE);
		logger.info("Sign On is "+signon1);
		
		
		By dvx = By.id("myForm:service:dvx");
		DVX=driver.findElement(dvx).isSelected();
		dv=booleantoString(DVX);
		logger.info("DVX is "+dv);
		
//--------------------------------------------------------------------------------
		By powratty = By.id("myForm:service:powofattorney");
		POWR_OFATTY=driver.findElement(powratty).isSelected();
		powratty1=booleantoString(POWR_OFATTY);
		logger.info("Power of Attorney is "+powratty1);
		
		
		By poa_dt = By.id("myForm:service:expdateval");
		POADT=driver.findElement(poa_dt).isSelected();
		logger.info("POA ADT is "+POADT);
		
		
		By gaa = By.id("myForm:service:genagencyagreemnt");
		GAA=driver.findElement(gaa).isSelected();
		ga=booleantoString(GAA);
		logger.info("GAA is "+ga);
		
		
		By archive = By.id("myForm:service:servMktArchiveOptions");
		ARCHIVEO_PTION=driver.findElement(archive).isSelected();
		logger.info("Archive is "+ARCHIVEO_PTION);
		
		
		By supplies = By.id("myForm:service:custservsupeligible");
		SUPPLIES_ELIG=driver.findElement(supplies).isSelected();
		supplieselig=booleantoString(SUPPLIES_ELIG);
		logger.info("Supplies is "+supplieselig);
				
	/*	By docexcep = By.id("myForm:service:servMktdocexceptn");
		DOCEXCEPTION_IND=driver.findElement(docexcep).isSelected();
		logger.info("Doc Exception is "+DOCEXCEPTION_IND);*/ // intially
		
		By docexcep = By.id("myForm:service:servMktdocexceptn");
		Select DOCEXCEPTION_IND=new Select(driver.findElement(docexcep));
		selected_docexception_ind=DOCEXCEPTION_IND.getFirstSelectedOption().getText().substring(0,1);
		logger.info("Doc Exception is "+selected_docexception_ind);
		
		
		/*By mais = By.id("myForm:service:custservmasteraccinvoicesumary");
		MA_IS=driver.findElement(mais).isSelected();
		logger.info("MAIS is "+MA_IS);*/ //intially
		
		By mais = By.id("myForm:service:custservmasteraccinvoicesumary");
		Select MA_IS=new Select(driver.findElement(mais));
		//selected_mais=MA_IS.getFirstSelectedOption().getText().substring(0, 1);
		
		selected_mais=MA_IS.getFirstSelectedOption().getText();
		logger.info("Selected MAIS isssssssssssssss ----------------------"+selected_mais);
		if(selected_mais.isEmpty())
		{
			selected_mais="";
		}
		else
		{
			selected_mais=MA_IS.getFirstSelectedOption().getText().substring(0, 1);
		}
		
		logger.info("MAIS is "+selected_mais);
		
		
		/*By premier = By.id("myForm:service:custservprempgm");
		PREMIER_PGM=driver.findElement(premier).isSelected();
		logger.info("Premier Program is "+PREMIER_PGM);*/  //intially
		
		/*By premier = By.id("myForm:service:custservprempgm");
		Select PREMIER_PGM=new Select(driver.findElement(premier));
		selected_premier_pgm=PREMIER_PGM.getFirstSelectedOption().getText();
		if(selected_premier_pgm !=null)
		{
			selected_premier_pgm=PREMIER_PGM.getFirstSelectedOption().getText().substring(0,2);
		}
		logger.info("Premier Program is "+selected_premier_pgm);*/
		
		
				
		By idf_elig = By.id("myForm:service:custservidf");
		IDF_ELIGIBILITY=driver.findElement(idf_elig).isSelected();
		idfelig=booleantoString(IDF_ELIGIBILITY);
		logger.info("IDF Eligibility required is "+idfelig);
		
		
		By express = By.id("myForm:service:expPlan");
		EXPRESS_PLAN=driver.findElement(express).isSelected();
		express1=booleantoString(EXPRESS_PLAN);
		logger.info("Express is "+express1);
		
		/*By audit = By.id("myForm:service:auditFirm");
		AUDIT_FIRM=driver.findElement(audit).isSelected();
		logger.info("Audit is "+AUDIT_FIRM);*/  //initially
		
		By audit = By.id("myForm:service:auditFirm");
		AUDIT_FIRM=driver.findElement(audit).getAttribute("value");
		if(AUDIT_FIRM==null)
		{
			BROK_ER="";
		}
		logger.info("Audit is "+AUDIT_FIRM);

		/*By auexp = By.id("myForm:service:auditFirm");
		AUEXP_DT=driver.findElement(auexp).isSelected();
		System.out.println("Signature required is "+AUEXP_DT);*/             //unable to find
		
		
		By broker = By.id("myForm:service:brokerLbl");
		BROK_ER=driver.findElement(broker).getAttribute("value");
		if(BROK_ER==null)
		{
			BROK_ER="";
		}
		logger.info("Broker is "+BROK_ER);
		
		
		By mps = By.id("myForm:service:custservexpmps");
		EXPRESS_MPS=driver.findElement(mps).isSelected();
		logger.info("MPS is "+EXPRESS_MPS);
				
		
		By nri = By.id("myForm:service:servMktnVnribi");
		NRI_BI=driver.findElement(nri).isSelected();
		nri1=booleantoString(NRI_BI);
		logger.info("NRI is "+nri1);
		
		By HVA = By.id("myForm:service:highValAccept");
		hva=driver.findElement(HVA).getAttribute("value");
		logger.info("hva is "+hva);
		
		By HVA_S=By.id("myForm:service:servMktnVhighValAcpt");
		Select hva_s=new Select(driver.findElement(HVA_S));
		selected_hva_s=hva_s.getFirstSelectedOption().getText();
		logger.info("selected hva is "+selected_hva_s);
		selected_hva_s=hva+selected_hva_s;
		logger.info("HVA is "+selected_hva_s);
		

		By ground = By.id("myForm:service:servMktnVgrndmps");
		GROUND_MPS=driver.findElement(ground).isSelected();
		logger.info("Ground MPS is "+GROUND_MPS);
		
		
		/*By marketing = By.id("myForm:service:mktngCode");
		MARKETNG=driver.findElement(marketing).isSelected();
		logger.info("Marketing is "+MARKETNG);*/ //initially
		
		By marketing = By.id("myForm:service:mktngCode");
		MARKETNG=driver.findElement(marketing).getAttribute("value");
		if(MARKETNG==null)
		{
			MARKETNG="";
		}
		logger.info("Marketing is "+MARKETNG);
			
		
		/*By fwder = By.id("myForm:service:forwarder");
		FORWARDER_BROKER=driver.findElement(fwder).isSelected();
		logger.info("Forwarder Broker is "+FORWARDER_BROKER);*/  //initially
		
		
		By fwder = By.id("myForm:service:forwarder");
		FORWARDER_BROKER=driver.findElement(fwder).getAttribute("value");
		if(FORWARDER_BROKER==null)
		{
			FORWARDER_BROKER="";
		}
		logger.info("Forwarder Broker is "+FORWARDER_BROKER);
	  
	 /* By debut_code = By.id("myForm:service:forwarder");
	  DEBUT_COCODE=driver.findElement(debut_code).isSelected();
		System.out.println("Signature required is "+DEBUT_COCODE);*/
	    
	  
		/*	By archive_opt = By.id("myForm:service:servMktnVarpref");
	  ARCHIVE_OPTION=driver.findElement(archive_opt).isSelected();
	  logger.info("Archive Option is "+ARCHIVE_OPTION);*/ //initially
		
		  By archive_opt = By.id("myForm:service:servMktnVarpref");
		 Select ARCHIVE_OPTION=new Select(driver.findElement(archive_opt));
		 selected_arhive_option=ARCHIVE_OPTION.getFirstSelectedOption().getText().substring(0, 1);
		  logger.info("Archive Option is "+selected_arhive_option);
	  
	
		
	}
	
	
	
public static String booleantoString(Boolean b)
{
	String s=null;
	
	if(b==false)
	{
		s="N";
	}
	else
	{
		s="Y";
	}
	
	
	return s;
}
	
	

	
	public String insert_data(String test_id, String db_name, String acct_nbr, Connection con, String level, String nat_acct) throws Exception
	{
		java.util.Date date=new java.util.Date();
		
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		logger.info("Current Date is "+sqlDate);
		
		java.sql.Timestamp s=new java.sql.Timestamp(date.getTime());
		String sqlTime =s.getHours()+":"+s.getMinutes()+":"+s.getSeconds();
		logger.info("Current Time is "+sqlTime);
		
		readConfig rc = new readConfig();
		prop=rc.readC();
		
		logger.info("National account from custinfo is -------------------"+nat_acct);
		if(nat_acct!="")
		{
		String countn=nat_acct;
		String[] nats=countn.split(" ");
		if(nats.length>1)
		{
			nat_acct=nats[0];
		}
		}	
		logger.info("National account in custserv is ----------------------"+nat_acct);
		
		/*String query2="insert into E_L3_CHEERS_CUSTSERV_TEST(OVRNITEFRTSVC,IPDIED,DOCPREPSVC,INTLFRTSVC,COLLECTRECPT,PREFCUST,DANGEROUSGOODS,CUTFLOWERS,CORRESPONDENCE,ALLOWREROUTE,SIGNATUREREQ,NOTIFYSHPMTDLAY,PARTIALPYMT,ONLINEELIG,SUPPLIESNOCUT,EMERGE,GRATUITY,EDRWPOD,NATLREVIEW,INTLSHIPPER,SHIPPERXPORTDCLR,FECCARD,MMA,REGULARSTOP,WRIGHTOFFIND,TRANSSMART,METERZONE,INSIGHT,SIGNONFILE,GROUNDHAZMAT,DVX,POWROFATTY,POADT,GAA,SUPPLIESELIG,DOCEXCEPTIONIND,MAIS,PREMIERPGM,IDFELIGIBILITY,EXPRESSPLAN,AUDITFIRM,BROKER,EXPRESSMPS,NRIBI,HVA,GROUNDMPS,MARKETING,FOWARDERBROKER,ARCHIVEOPTION,ACCTNBR,DATEFLD,TIMEFLD)"
		        + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps2=con.prepareStatement(query2);*/
		
		/*String query2="insert into E_L3_CHEERS_CUSTSERV_TEST(OVRNITEFRTSVC,IPDIED,DOCPREPSVC,INTLFRTSVC,COLLECTRECPT,PREFCUST,DANGEROUSGOODS,CUTFLOWERS,CORRESPONDENCE,ALLOWREROUTE,SIGNATUREREQ,NOTIFYSHPMTDLAY,PARTIALPYMT,ONLINEELIG,SUPPLIESNOCUT,EMERGE,GRATUITY,EDRWPOD,NATLREVIEW,INTLSHIPPER,SHIPPERXPORTDCLR,FECCARD,MMA,REGULARSTOP,WRIGHTOFFIND,TRANSSMART,METERZONE,INSIGHT,SIGNONFILE,GROUNDHAZMAT,DVX,POWROFATTY,POADT,GAA,SUPPLIESELIG,DOCEXCEPTIONIND,MAIS,PREMIERPGM,IDFELIGIBILITY,EXPRESSPLAN,AUDITFIRM,BROKER,EXPRESSMPS,NRIBI,HVA,GROUNDMPS,MARKETING,FOWARDERBROKER,ARCHIVEOPTION,ACCTNBR,DATEFLD,TIMEFLD)"
		        + " values ('"+overnite+"','"+ipd+"','"+doc+"','"+intlfrt+"','"+collrecipet+"','"+prefcust+"','"+selected_dangerous_good+"','"+cutflowers1+"','"+crspondence+"','"+allowre+"','"+sigreq1+"','"+notifyshp+"','"+partial+"','"+onlineelig1+"','"+selected_supplies_nocut+"','"+emerge+"','"+gratui+"','"+edrw1+"','"+natlre+"','"+selected_intl_shipper+"','"+shipperxp+"','"+selected_fec_card+"','"+selected_mma+"','"+regstop+"','"+selected_wright+"','"+trans+"','"+meterzone+"','"+insig+"','"+signon1+"','"+groundhazmat+"','"+dv+"','"+powratty1+"','"+POADT+"','"+ga+"','"+supplieselig+"','"+selected_docexception_ind+"','"+selected_mais+"','"+selected_premier_pgm+"','"+idfelig+"','"+express1+"','"+AUDIT_FIRM+"','"+BROK_ER+"','"+EXPRESS_MPS+"','"+nri1+"','"+selected_hva_s+"','"+GROUND_MPS+"','"+MARKETNG+"','"+FORWARDER_BROKER+"','"+ARCHIVE_OPTION+"','"+acct_nbr+"','"+sqlDate+"','"+sqlTime+"')";
*/	
		
		String query2="insert into E_L"+level+"_CHEERS_CUSTSERV (OVRNITEFRTSVC,IPDIED,DOCPREPSVC,INTLFRTSVC,COLLECTRECPT,PREFCUST,DANGEROUSGOODS,CUTFLOWERS,CORRESPONDENCE,ALLOWREROUTE,SIGNATUREREQ,NOTIFYSHPMTDLAY,PARTIALPYMT,ONLINEELIG,SUPPLIESNOCUT,EMERGE,GRATUITY,EDRWPOD,NATLREVIEW,INTLSHIPPER,SHIPPERXPORTDCLR,FECCARD,MMA,REGULARSTOP,WRIGHTOFFIND,TRANSSMART,METERZONE,INSIGHT,SIGNONFILE,GROUNDHAZMAT,DVX,POWROFATTY,POADT,GAA,SUPPLIESELIG,DOCEXCEPTIONIND,MAIS,IDFELIGIBILITY,EXPRESSPLAN,AUDITFIRM,BROKER,EXPRESSMPS,NRIBI,HVA,GROUNDMPS,MARKETING,FOWARDERBROKER,ARCHIVEOPTION,ACCTNBR,DATEFLD,TIMEFLD,NATLACCT)"
		        + " values ('"+overnite+"','"+ipd+"','"+doc+"','"+intlfrt+"','"+collrecipet+"','"+prefcust+"','"+selected_dangerous_good+"','"+cutflowers1+"','"+crspondence+"','"+allowre+"','"+sigreq1+"','"+notifyshp+"','"+partial+"','"+onlineelig1+"','"+selected_supplies_nocut+"','"+emerge+"','"+gratui+"','"+edrw1+"','"+natlre+"','"+selected_intl_shipper+"','"+shipperxp+"','"+selected_fec_card+"','"+selected_mma+"','"+regstop+"','"+selected_wright+"','"+trans+"','"+meterzone+"','"+insig+"','"+signon1+"','"+groundhazmat+"','"+dv+"','"+powratty1+"','"+POADT+"','"+ga+"','"+supplieselig+"','"+selected_docexception_ind+"','"+selected_mais+"','"+idfelig+"','"+express1+"','"+AUDIT_FIRM+"','"+BROK_ER+"','"+EXPRESS_MPS+"','"+nri1+"','"+selected_hva_s+"','"+GROUND_MPS+"','"+MARKETNG+"','"+FORWARDER_BROKER+"','"+selected_arhive_option+"','"+acct_nbr+"','"+sqlDate+"','"+sqlTime+"','"+nat_acct+"')";
		
		return query2;
		
		/*
		ps2.setString(1, overnite);
		ps2.setString(2, ipd);
		ps2.setBoolean(3, DOC_PREPSVC);
		ps2.setString(4, intlfrt);
		ps2.setBoolean(5, COLLECT_RECPT);
		ps2.setString(6, prefcust);
		ps2.setString(7, selected_dangerous_good);		
		ps2.setString(8, cutflowers1);		
		ps2.setString(9, crspondence);
		ps2.setString(10, allowre);
		ps2.setString(11, sigreq1);
		ps2.setString(12, notifyshp);
		ps2.setString(13, partial);
		ps2.setString(14, onlineelig1);
		ps2.setBoolean(15, SUPPLIES_NOCUT);
		ps2.setString(16, emerge);
		ps2.setString(17, gratui);
		ps2.setString(18, edrw1);
		ps2.setString(19, natlre);		
		ps2.setString(20, selected_intl_shipper);
		ps2.setString(21, shipperxp);
		ps2.setString(22, selected_fec_card);
		ps2.setString(23, selected_mma);
		ps2.setString(24, regstop);
		ps2.setString(25, selected_wright);
		ps2.setString(26, trans);
		ps2.setString(27, meterzone);
		ps2.setString(28,insig);	
		ps2.setString(29, signon1);
		ps2.setString(30, groundhazmat);		
		ps2.setString(31, dv);
		ps2.setString(32, powratty1);
		ps2.setBoolean(33, POADT);
		ps2.setString(34, ga);
		ps2.setString(35, supplieselig);
		ps2.setBoolean(36, DOCEXCEPTION_IND);
		ps2.setBoolean(37, MA_IS);
		ps2.setBoolean(38, PREMIER_PGM);		
		ps2.setString(39, idfelig);
		ps2.setString(40, express1);		
		ps2.setBoolean(41, AUDIT_FIRM);
		ps2.setBoolean(42, BROK_ER);
		ps2.setBoolean(43, EXPRESS_MPS);
		ps2.setString(44, nri1);
		ps2.setString(45, selected_hva_s);
		ps2.setBoolean(46, GROUND_MPS);
		ps2.setBoolean(47, MARKETNG);
		ps2.setBoolean(48, FORWARDER_BROKER);
		ps2.setBoolean(49, ARCHIVE_OPTION);
		ps2.setString(50, acct_nbr);
		ps2.setDate(51,sqlDate);
		ps2.setString(52,sqlTime);
		
	
		
		//ps.execute();
		logger.info("Adding query");
		ps2.addBatch(query2);
		logger.info("Query added");
		
		//con.close();
		
		logger.info("The query for CUSTSERV is"+query2);
		logger.info("data inserted in CUSTSERV table");
		*/
		// return ps2;
		
		
		/*Select drpscreen = new Select(driver.findElement(By.id("myForm:navigationDrpDwn1")));
		drpscreen.selectByVisibleText("Customer Summary");
		
		Thread.sleep(3000);*/
		
		
	}

	
}
