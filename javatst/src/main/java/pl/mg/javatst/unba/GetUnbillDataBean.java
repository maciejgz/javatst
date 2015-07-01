package pl.mg.javatst.unba;


import pl.mg.javatst.unba.datasource.RatingScenario;
import pl.mg.javatst.unba.osp.vo.connectors.AbstractDbBean;

import java.sql.Types;
import java.util.Iterator;
import java.util.List;

/**
 * This class provide communication with UNBA database. VO Connector call method
 * on this class.
 * <p/>
 * UNBA PL/SQL stored procedure documentation: <BR>
 * <p/>
 * <pre>
 * PROCEDURE get_unbill_data(iMSISDN VARCHAR2,
 *  iStartDate DATE,
 *  iEndDate DATE,
 *  oXml OUT VARCHAR2,
 *  oSms OUT VARCHAR2,
 *  iUsageSource VARCHAR2 DEFAULT 'NOT_DEFINED')
 *
 *  example of calling procedure from connector:
 *  EXEC Unba2.get_unbill_data('696090269', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('668186040', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('604083260', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('606793766', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('694465962', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('602518801', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('602578593', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('604523225', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('606992662', NULL, NULL, :xml  , :Sms  );
 *  EXEC Unba2.get_unbill_data('606994664', NULL, NULL, :xml  , :Sms  );
 *
 *  attention:
 *  iStartDate, iEndDate always set to NULL,
 *  iUsageSource always set to default
 *
 *  &lt;B&gt;UNBA XML example (for 1 MSISDN)&lt;/B&gt;
 *  &lt;?xml version=&quot;1.0&quot; encoding=&quot;ISO-8859-2&quot;?&gt;
 *  &lt;unba2&gt;
 *  &lt;msisdn id=&quot;602212367&quot; co_id=&quot;3307&quot; customer_id=&quot;3237&quot; unba_call_date=&quot;2007-05-14 15:51:02&quot; start_date=&quot;2007-04-17 00:00:00&quot; end_date=&quot;2007-05-14 15:51:00&quot; query_no=&quot;14&quot; credit_groups_no=&quot;4&quot; usage_source=&quot;RTX&quot; charge=&quot;Y&quot;&gt;
 *  &lt;costs&gt;
 *  &lt;calls all=&quot;75.46&quot; roaming=&quot;5&quot; premium=&quot;0&quot; last_call_date=&quot;09:51 2007-04-17&quot;/&gt;
 *  &lt;minimal_consumption unused_amount=&quot;181.25&quot;/&gt;
 *  &lt;access_fee amount=&quot;248&quot;/&gt;
 *  &lt;/costs&gt;
 *  &lt;credits&gt;
 *  &lt;credits_minutes&gt;
 *  &lt;credit id=&quot;25&quot; name=&quot;Pakiet Darmowa sieć firmowa&quot; amount=&quot;1985min&quot;/&gt;
 *  &lt;credit id=&quot;10&quot; name=&quot;Pakiet promocyjny&quot; amount=&quot;362min&quot;/&gt;
 *  &lt;credit id=&quot;19&quot; name=&quot;Pakiet Rozmowy poranne&quot; amount=&quot;1997min&quot;/&gt;
 *  &lt;/credits_minutes&gt;
 *  &lt;credits_bytes&gt;
 *  &lt;credit id=&quot;8&quot; name=&quot;Paczka GPRS&quot; amount=&quot;39MB&quot;/&gt;
 *  &lt;/credits_bytes&gt;
 *  &lt;/credits&gt;
 *  &lt;/msisdn&gt;
 *  &lt;/unba2&gt;
 *
 *  &lt;B&gt;UNBA XML example (for 2 MSISDN)&lt;/B&gt;
 *  &lt;?xml version=&quot;1.0&quot; encoding=&quot;ISO-8859-2&quot;?&gt;
 *  &lt;unba2&gt;
 *  &lt;msisdn id=&quot;602212367&quot; co_id=&quot;3307&quot; customer_id=&quot;3237&quot; unba_call_date=&quot;2007-05-14 15:51:02&quot; start_date=&quot;2007-04-17 00:00:00&quot; end_date=&quot;2007-05-14 15:51:00&quot; query_no=&quot;14&quot; credit_groups_no=&quot;4&quot; usage_source=&quot;RTX&quot; charge=&quot;Y&quot;&gt;
 *  &lt;costs&gt;
 *  &lt;calls all=&quot;75.46&quot; roaming=&quot;5&quot; premium=&quot;0&quot; last_call_date=&quot;09:51 2007-04-17&quot;/&gt;
 *  &lt;minimal_consumption unused_amount=&quot;181.25&quot;/&gt;
 *  &lt;access_fee amount=&quot;248&quot;/&gt;
 *  &lt;/costs&gt;
 *  &lt;credits&gt;
 *  &lt;credits_minutes&gt;
 *  &lt;credit id=&quot;25&quot; name=&quot;Pakiet Darmowa sieć firmowa&quot; amount=&quot;1985min&quot;/&gt;
 *  &lt;credit id=&quot;10&quot; name=&quot;Pakiet promocyjny&quot; amount=&quot;362min&quot;/&gt;
 *  &lt;credit id=&quot;19&quot; name=&quot;Pakiet Rozmowy poranne&quot; amount=&quot;1997min&quot;/&gt;
 *  &lt;/credits_minutes&gt;
 *  &lt;credits_bytes&gt;
 *  &lt;credit id=&quot;8&quot; name=&quot;Paczka GPRS&quot; amount=&quot;39MB&quot;/&gt;
 *  &lt;/credits_bytes&gt;
 *  &lt;/credits&gt;
 *  &lt;/msisdn&gt;
 *  &lt;msisdn id=&quot;602212368&quot; co_id=&quot;3307&quot; customer_id=&quot;3237&quot; unba_call_date=&quot;2007-05-14 15:51:02&quot; start_date=&quot;2007-04-17 00:00:00&quot; end_date=&quot;2007-05-14 15:51:00&quot; query_no=&quot;14&quot; credit_groups_no=&quot;4&quot; usage_source=&quot;RTX&quot; charge=&quot;Y&quot;&gt;
 *
 *  &lt;costs&gt;
 *  &lt;calls all=&quot;75.46&quot; roaming=&quot;5&quot; premium=&quot;0&quot; last_call_date=&quot;09:51 2007-04-17&quot;/&gt;
 *  &lt;minimal_consumption unused_amount=&quot;181.25&quot;/&gt;
 *  &lt;access_fee amount=&quot;248&quot;/&gt;
 *  &lt;/costs&gt;
 *  &lt;credits&gt;
 *  &lt;credits_minutes&gt;
 *  &lt;credit id=&quot;25&quot; name=&quot;Pakiet Darmowa sieć firmowa&quot; amount=&quot;1985min&quot;/&gt;
 *  &lt;credit id=&quot;10&quot; name=&quot;Pakiet promocyjny&quot; amount=&quot;362min&quot;/&gt;
 *  &lt;credit id=&quot;19&quot; name=&quot;Pakiet Rozmowy poranne&quot; amount=&quot;1997min&quot;/&gt;
 *  &lt;/credits_minutes&gt;
 *  &lt;credits_bytes&gt;
 *  &lt;credit id=&quot;8&quot; name=&quot;Paczka GPRS&quot; amount=&quot;39MB&quot;/&gt;
 *  &lt;/credits_bytes&gt;
 *  &lt;/credits&gt;
 *  &lt;/msisdn&gt;
 *  &lt;/unba2&gt;
 *
 *  &lt;B&gt;UNBA SMS example (for 1 MSISDN)&lt;/B&gt;
 *
 *  MSISDN:602212367;Koszty: 75,46 zl net.,
 *  (roam. 5,00 zl, prem. 0,00 zl),
 *  Pak. Wart.: 181,25 zl net.,
 *  Abonament 248,00 zl net.,
 *  Pakiet Darmowa siec firmowa: 1985min,
 *  Pakiet promocyjny: 362min,
 *  Pakiet Rozmowy poranne: 1997min,
 *  Paczka GPRS: 39MB,
 *  Ost. polacz. 09:51 2007-04-17
 *
 *  &lt;B&gt;UNBA SMS example (for 2 MSISDN)&lt;/B&gt;
 *  MSISDN:602212367;Koszty: 75,46 zl net.,
 *  (roam. 5,00 zl, prem. 0,00 zl),
 *  Pak. Wart.: 181,25 zl net.,
 *  Abonament 248,00 zl net.,
 *  Pakiet Darmowa siec firmowa: 1985min,
 *  Pakiet promocyjny: 362min,
 *  Pakiet Rozmowy poranne: 1997min,
 *  Paczka GPRS: 39MB,
 *  Ost. polacz. 09:51 2007-04-17
 *  MSISDN:602212368;Koszty: 75,46 zl net.,
 *  (roam. 5,00 zl, prem. 0,00 zl),
 *  Pak. Wart.: 181,25 zl net.,
 *  Abonament 248,00 zl net.,
 *  Pakiet Darmowa siec firmowa: 1985min,
 *  Pakiet promocyjny: 362min,
 *  Pakiet Rozmowy poranne: 1997min,
 *  Paczka GPRS: 39MB,
 *  Ost. polacz. 09:51 2007-04-17
 * </pre>
 *
 * @author Geomant Inc. Pawel Biedronski
 */
public class GetUnbillDataBean extends AbstractUnbaBean {

    // Errors definiotion
    private final String ERR_PARAMS = "9";
    private final String ERR_PARSE_XML = "10";
    private final String ERR_REWRITE_XML = "11";
    private final String ERR_PARSE_SMS = "12";

    // stage: parse response
    private static String STAGE_PARSERESPONSE = "ParseResponse";
    // stage: rewrite response to VO
    private static String STAGE_REWRITERESPONSE = "RewriteResponse";

    // zmienna na numer MSISDN
    private String MSISDN = null;

    // zmienna na xml z procedury PL/SQL
    private String procResultXML = "";
    // zmienna na sms z procedury PL/SQL
    private String procResultSMS = "";

    // zmienna na SMS 1
    private String SMS1 = "";
    // zmienna na SMS 2 (not used - feature)
    private String SMS2 = "";
    // variable - number of credit groups
    private String creditGroupsNo = "0";
    // variable - 'charge' flag
    private String charge = "0";
    // variable - 'no_charge_reason'
    private String noChargeReason = "";
    // zmienna na obiekt VO collection UNBACostsCalls
    private String UNBACostsCalls = "";
    // zmienna na obiekt VO collection UNBACreditsAvaliability
    private String UNBACreditsAvaliability = "";
    // zmienna na obiekt VO collection UNBACreditsMinutesList
    private String UNBACreditsMinutesList = "";
    // zmienna na obiekt VO collection UNBACreditsSMSList
    private String UNBACreditsSMSList = "";
    // zmienna na obiekt VO collection UNBACreditsMMSList
    private String UNBACreditsMMSList = "";
    // zmienna na obiekt VO collection UNBACreditsBytesList
    private String UNBACreditsBytesList = "";
    // zmienna na obiekt VO collection UNBACreditsCurrencyList
    private String UNBACreditsCurrencyList = "";
    // zmienna na obiekt VO collection PWParams
    private String PWParams = "";
    // zmienna na obiekt VO collection PWAccessFeesList
    private String PWAccessFeesList = "";
    // zmienna na obiekt VO collection PWDiscountsList
    private String PWDiscountsList = "";
    // zmienna na obiekt VO string taryfa rozliczana w grupie taryf
    private String MCGroup = "N";
    // zmianna na obiekt VO collection RatingScenarios
    private String ratingScenarios = "";

    /**
     * seting msisdn (Calling Number)
     *
     * @param msisdn Calling Number (9 digits)
     */
    public void setMSISDN(String _msisdn) {
        this.logger.debug("setMSISDN=" + _msisdn);
        this.MSISDN = _msisdn;
    }

    /**
     * main method, executed during processing VO Connector
     */
    public void dbCall() throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("getData - START, MSISDN: " + this.MSISDN);
        }

        if (XMLParser.isEmpty(this.MSISDN)) {
            this.errorCode = ERR_PARAMS;
            this.errorMessage = "Severity:minor; Object:" + application + ":"
                    + this.getClass().toString() + "System:" + this.systemName
                    + "; Function:" + this.packageName + this.functionName
                    + "; ConnectionPool: " + connectionPool
                    + "; Text:Parameter MSISDN can not be null; Error number:"
                    + this.errorCode + "; Stage:" + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage);
            return;
        }

        // wywolanie procedury PL/SQL
        stage = AbstractDbBean.STAGEPREPARECALL;
        logger.debug("dbCall - conn.prepareCall()");
        cs = conn.prepareCall("{call " + UnbaConfiguration.packageName
                + "get_unbill_data(?,?,?,?,?) }");

        stage = AbstractDbBean.STAGEREGISTERPARAMS;
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.registerOutParameter(5, Types.VARCHAR);
        cs.setString(1, this.MSISDN);
        cs.setNull(2, Types.DATE);
        cs.setNull(3, Types.DATE);

        stage = AbstractDbBean.STAGEEXECUTE;

        logger.debug("getData - CallableStatement.execute()");
        cs.setQueryTimeout(UnbaConfiguration.timeOut);
        cs.execute();

        stage = AbstractDbBean.STAGEGETPARAMS;
        this.procResultXML = cs.getString(4);
        this.procResultSMS = cs.getString(5);

        // log result to Logger
        if (logger.isDebugEnabled()) {
            logger.debug("procResultSMS:\n" + this.procResultSMS);
            logger.debug("procResultXML:\n" + this.procResultXML);
        }

        // secure NULL variables (for Bean <-> VTK/VO compability)
        if (this.procResultXML == null)
            this.procResultXML = "";
        if (this.procResultSMS == null)
            this.procResultSMS = "";
        // parser XML'a from SQL-UNBA
        this.logger.debug("XMLParser.parse()");
        stage = GetUnbillDataBean.STAGE_PARSERESPONSE;
        XMLParser xmlParser = new XMLParser(this.logger, this.procResultXML,
                this.MSISDN);
        try {
            xmlParser.parse();
        } catch (Exception ex) {
            logger.error("getData - error during parsing XML", ex);
            this.errorCode = ERR_PARSE_XML;
            this.errorMessage = "Severity:minor; Object:" + application + ":"
                    + this.getClass().toString() + "System:" + this.systemName
                    + "; Function:" + this.packageName + this.functionName
                    + "; ConnectionPool: " + connectionPool
                    + "; Text:Error parsing UNBA XML (" + ex.getMessage()
                    + "); Error number:" + this.errorCode + "; Stage:" + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, ex);
            return;
        }
        // rewrite UNBA XML -> VoiceObjects
        stage = GetUnbillDataBean.STAGE_REWRITERESPONSE;
        this.logger.debug("rewrite UNBA XML -> VoiceObjects");
        try {
            this.rewriteUNBAToVoiceObjects(xmlParser);
        } catch (Exception ex) {
            logger.error("getData - error during rewriting XML UNBA->VO", ex);
            this.errorCode = ERR_REWRITE_XML;
            this.errorMessage = "Severity:minor; Object:" + application + ":"
                    + this.getClass().toString() + "System:" + this.systemName
                    + "; Function:" + this.packageName + this.functionName
                    + "; ConnectionPool: " + connectionPool
                    + "; Text:Error rewriting XML UNBA->VO (" + ex.getMessage()
                    + "); Error number:" + this.errorCode + "; Stage:" + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, ex);
            return;
        }

        // parse SMS
        stage = GetUnbillDataBean.STAGE_REWRITERESPONSE;
        this.logger.debug("rewrite UNBA SMS -> VoiceObjects");
        try {
            this.rewriteUNBASMSToVoiceObjects();
        } catch (Exception ex) {
            this.errorCode = ERR_PARSE_SMS;
            this.errorMessage = "Severity:minor; Object:" + application + ":"
                    + this.getClass().toString() + "System:" + this.systemName
                    + "; Function:" + this.packageName + this.functionName
                    + "; ConnectionPool: " + connectionPool
                    + "; Text:Error rewriting SMS UNBA->VO (" + ex.getMessage()
                    + "); Error number:" + this.errorCode + "; Stage:" + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, ex);
            return;
        }
    }

    /**
     * Gets complete XML from UNBA Pl/SQL procedure
     *
     * @return
     */
    public String getProcResultXML() {
        return this.procResultXML;
    }

    /**
     * function return source XML (from UNBA) prepared to write to custom_log
     *
     * @return prepared UNBA XML
     */
    public String getXMLLog() {
        int maxSize = 2000;
        if (this.procResultXML.length() < maxSize)
            return this.procResultXML;
        else
            return this.procResultXML.substring(0, maxSize - 1);
    }

    public String getProcResultSMS() {
        return this.procResultSMS;
    }

    public String getSMS1() {
        return this.SMS1;
    }

    public String getSMS2() {
        return this.SMS2;
    }

    public static void main(String[] args) {
        GetUnbillDataBean unba = new GetUnbillDataBean();
        unba.setMSISDN("602212368");
        try {
            unba.dbCall();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // rewrite List of Credit objects to VO-XML
    private void rewiteCreditListToVoiceObjectsCollection(List<Credit> list,
                                                          StringBuffer sb) {
        sb.setLength(0);
        sb.append("<root>\n");
        Iterator<Credit> iter = list.iterator();
        while (iter.hasNext()) {
            Credit credit = iter.next();
            sb.append("<row>\n");

            sb.append("<col name=\"GROUP_ID\">");
            sb.append(credit.getId());
            sb.append("</col>\n");

            sb.append("<col name=\"GROUP_NAME\">");
            sb.append(credit.getName());
            sb.append("</col>\n");

            sb.append("<col name=\"AMOUNTS\">");
            sb.append(credit.getAmount());
            sb.append("</col>\n");

            sb.append("<col name=\"UNIT\">");
            sb.append(credit.getUnit());
            sb.append("</col>\n");

            sb.append("</row>\n");
        }
        sb.append("</root>");
    }

    // rewrite List of AccessFees objects to VO-XML
    private void rewitePWAccessFeesListToVoiceObjectsCollection(
            List<AccessFee> list, StringBuffer sb) {
        sb.setLength(0);
        sb.append("<root>\n");
        Iterator<AccessFee> iter = list.iterator();
        while (iter.hasNext()) {
            AccessFee acFee = iter.next();
            sb.append("<row>\n");

            sb.append("<col name=\"ID\">");
            sb.append(acFee.getID());
            sb.append("</col>\n");

            sb.append("<col name=\"DES\">");
            sb.append(acFee.getDes());
            sb.append("</col>\n");

            sb.append("<col name=\"ADVANCE\">");
            sb.append(acFee.getAdvance());
            sb.append("</col>\n");

            sb.append("<col name=\"PAST\">");
            sb.append(acFee.getPast());
            sb.append("</col>\n");

            sb.append("<col name=\"CORRECTION\">");
            sb.append(acFee.getCorrection());
            sb.append("</col>\n");

            sb.append("</row>\n");
        }
        sb.append("</root>");
    }

    // rewrite List of Discount objects to VO-XML
    private void rewitePWDiscountsListToVoiceObjectsCollection(
            List<Discount> list, StringBuffer sb) {
        sb.setLength(0);
        sb.append("<root>\n");
        Iterator<Discount> iter = list.iterator();
        while (iter.hasNext()) {
            Discount disc = iter.next();
            sb.append("<row>\n");

            sb.append("<col name=\"ID\">");
            sb.append(disc.getID());
            sb.append("</col>\n");

            sb.append("<col name=\"DES\">");
            sb.append(disc.getDes());
            sb.append("</col>\n");

            sb.append("<col name=\"AMOUNT\">");
            sb.append(disc.getAmount());
            sb.append("</col>\n");

            sb.append("</row>\n");
        }
        sb.append("</root>");
    }

    // gets data from xmlParser and rewrite them into collection objects
    private void rewriteUNBASMSToVoiceObjects() throws Exception {
        int start = this.procResultSMS.indexOf("MSISDN:");
        int end = this.procResultSMS.indexOf("MSISDN:", start
                + "MSISDN:".length());
        // only one 'MSISDN:' section
        if (end == -1) {
            // get MSISDN
            String msisdn = this.procResultSMS.substring(this.procResultSMS
                    .indexOf(":") + 1, this.procResultSMS.indexOf(";"));
            if (this.MSISDN.equals(msisdn)) {
                // get SMS1 body
                this.SMS1 = this.procResultSMS.trim();
            } else {
                this.logger.warn("UNBA SMS only for '" + msisdn
                        + "' expected: '" + this.MSISDN + "'");
            }
        }
        // two 'MSISDN:' section
        else {
            // get SMS1 body
            start = this.procResultSMS.indexOf(";");
            // get MSISDN
            String msisdn = this.procResultSMS.substring("MSISDN:".length(),
                    start);
            // get first SMS text
            if (msisdn.equals(this.MSISDN)) {
                this.SMS1 = this.procResultSMS.substring(0, end).trim();
            }
            // get second SMS text
            else {
                this.SMS1 = this.procResultSMS.substring(end).trim();
            }
        }

    }

    // gets data from xmlParser and rewrite them into collection objects
    private void rewriteUNBAToVoiceObjects(XMLParser xmlParser)
            throws Exception {

        // rewrite number of credit groups
        this.creditGroupsNo = xmlParser.getCreditGroupsNo();

        // rewrite type of MCGroup
        this.MCGroup = xmlParser.getMcGgroup();

        // rewrite charge flag
        this.charge = xmlParser.getCharge();

        // rewrite NoChargeReason description
        this.noChargeReason = xmlParser.getNoChargeReason();

        // rewrite XMLParser.costs -> VO.UNBACostsCalls
        StringBuffer sb = new StringBuffer();
        sb.append("<root>\n");
        Iterator<String> keys = xmlParser.getCosts().keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = xmlParser.getCosts().get(key);
            sb.append("<row>\n");
            sb.append("<col name=\"PARAM\">");
            sb.append(key);
            sb.append("</col>\n");
            sb.append("<col name=\"VALUE\">");
            sb.append(value);
            sb.append("</col>\n");
            sb.append("</row>\n");

        }
        sb.append("</root>\n");
        this.UNBACostsCalls = sb.toString();
        logger.debug("UNBACostsCalls:\n" + UNBACostsCalls);

        // rewrite XMLParser.creditsAvaliability -> VO.CreditsAvaliability
        sb.setLength(0);
        sb.append("<root>\n");
        keys = xmlParser.getCreditsAvaliability().keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = xmlParser.getCreditsAvaliability().get(key);
            sb.append("<row>\n");
            sb.append("<col name=\"NAME\">");
            sb.append(key);
            sb.append("</col>\n");
            sb.append("<col name=\"AVALIABLE\">");
            sb.append(value);
            sb.append("</col>\n");
            sb.append("</row>\n");
        }
        sb.append("</root>\n");
        this.UNBACreditsAvaliability = sb.toString();
        logger.debug("UNBACreditsAvaliability:\n" + UNBACreditsAvaliability);

        // rewrite XMLParser.creditsMinutes -> VO.UNBACreditsMinutesList
        rewiteCreditListToVoiceObjectsCollection(xmlParser.getCreditsMinutes(),
                sb);
        this.UNBACreditsMinutesList = sb.toString();
        logger.debug("UNBACreditsMinutesList:\n" + UNBACreditsMinutesList);

        // rewrite XMLParser.creditsSMS -> VO.UNBACreditsSMSList
        rewiteCreditListToVoiceObjectsCollection(xmlParser.getCreditsSMS(), sb);
        this.UNBACreditsSMSList = sb.toString();
        logger.debug("UNBACreditsSMSList:\n" + UNBACreditsSMSList);

        // rewrite XMLParser.creditsMMS -> VO.UNBACreditsMMSList
        rewiteCreditListToVoiceObjectsCollection(xmlParser.getCreditsMMS(), sb);
        this.UNBACreditsMMSList = sb.toString();
        logger.debug("UNBACreditsMMSList:\n" + UNBACreditsMMSList);

        // rewrite XMLParser.creditsBytes -> VO.UNBACreditsBytesList
        rewiteCreditListToVoiceObjectsCollection(xmlParser.getCreditsBytes(),
                sb);
        this.UNBACreditsBytesList = sb.toString();
        logger.debug("UNBACreditsBytesList:\n" + UNBACreditsBytesList);

        // rewrite XMLParser.creditsCurrency -> VO.UNBACreditsCurrencyList
        rewiteCreditListToVoiceObjectsCollection(
                xmlParser.getCreditsCurrency(), sb);
        this.UNBACreditsCurrencyList = sb.toString();
        logger.debug("UNBACreditsCurrencyList:\n" + UNBACreditsCurrencyList);

        // rewrite XMLParser.mcCalcParams -> VO.PWParams
        sb.setLength(0);
        sb.append("<root>\n");
        keys = xmlParser.getMCCalcParams().keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = xmlParser.getMCCalcParams().get(key);
            sb.append("<row>\n");
            sb.append("<col name=\"PARAM\">");
            sb.append(key);
            sb.append("</col>\n");
            sb.append("<col name=\"VALUE\">");
            sb.append(value);
            sb.append("</col>\n");
            sb.append("</row>\n");
        }
        sb.append("</root>\n");
        this.PWParams = sb.toString();
        logger.debug("PWParams:\n" + PWParams);

        // rewrite XMLParser.mcCalcAccessFees
        rewitePWAccessFeesListToVoiceObjectsCollection(xmlParser
                .getMcCalcAccessFees(), sb);
        this.PWAccessFeesList = sb.toString();
        logger.debug("PWAccessFeesList:\n" + PWAccessFeesList);

        // rewrite XMLParser.mcCalcDiscounts
        rewitePWDiscountsListToVoiceObjectsCollection(xmlParser
                .getMcCalcDiscounts(), sb);
        this.PWDiscountsList = sb.toString();
        logger.debug("PWDiscountsList:\n" + PWDiscountsList);

        //rewrite ScenarioCounter
        rewriteRatingScenarioToVoiceObjectsCollection(xmlParser.getRatingScenarios(), sb);
        this.ratingScenarios = sb.toString();
        logger.debug("RatingScenario:\n" + ratingScenarios);
    }

    public void rewriteRatingScenarioToVoiceObjectsCollection(List<RatingScenario> ratingScenarios, StringBuffer sb) {
        if (sb == null || sb.length() != 0) {
            sb = new StringBuffer();
        }

        sb.append("<root>\n");
        for (RatingScenario scenario : ratingScenarios) {
            sb.append("<row>\n");
            sb.append("<col name=\"scenario_id\">");
            sb.append(scenario.getScenarioId());
            sb.append("</col>\n");
            sb.append("<col name=\"scenario_des\">");
            sb.append(scenario.getScenarioDes());
            sb.append("</col>\n");

            //counters
            sb.append("<col name=\"counters\">");
            sb.append("&lt;root&gt;\n");
            for (int i = 0; i < scenario.getScenarioCounters().size(); i++) {
                sb.append("&lt;row&gt;\n");

                sb.append("&lt;col name=&quot;counter_id&quot;&gt;");
                sb.append(scenario.getScenarioCounters().get(i).getCounterId());
                sb.append("&lt;/col&gt;\n");

                sb.append("&lt;col name=&quot;counter_des&quot;&gt;");
                sb.append(scenario.getScenarioCounters().get(i).getCounterDes());
                sb.append("&lt;/col&gt;\n");

                sb.append("&lt;col name=&quot;step&quot;&gt;");
                sb.append(scenario.getScenarioCounters().get(i).getStep());
                sb.append("&lt;/col&gt;\n");

                sb.append("&lt;col name=&quot;volume_total&quot;&gt;");
                sb.append(scenario.getScenarioCounters().get(i).getVolumeTotal());
                sb.append("&lt;/col&gt;\n");

                sb.append("&lt;col name=&quot;volume_left&quot;&gt;");
                sb.append(scenario.getScenarioCounters().get(i).getVolumeLeft());
                sb.append("&lt;/col&gt;\n");

                sb.append("&lt;col name=&quot;volume_limit&quot;&gt;");
                sb.append(scenario.getScenarioCounters().get(i).getVolumeLimit());
                sb.append("&lt;/col&gt;\n");

                sb.append("&lt;/row&gt;\n");
            }
            sb.append("&lt;root&gt;\n");
            sb.append("</col>\n");
            sb.append("</row>\n");
        }
        sb.append("</root>\n");


    }

    public String getUNBACostsCalls() {
        return this.UNBACostsCalls;
    }

    public String getUNBACreditsAvaliability() {
        return this.UNBACreditsAvaliability;
    }

    public String getUNBACreditsMinutesList() {
        return this.UNBACreditsMinutesList;
    }

    public String getUNBACreditsSMSList() {
        return this.UNBACreditsSMSList;
    }

    public String getUNBACreditsMMSList() {
        return this.UNBACreditsMMSList;
    }

    public String getUNBACreditsBytesList() {
        return this.UNBACreditsBytesList;
    }

    /**
     * Gets CreditsCurrencyList (tag 'credits_curency')
     *
     * @return VoiceObjects Collection contains CreditsCurrencyList
     */
    public String getUNBACreditsCurrencyList() {
        return this.UNBACreditsCurrencyList;
    }

    /**
     * Gets Number of Cregit Groups (attribute 'credit_groups_no' of tag
     * 'msisdn')
     *
     * @return numer as String (0....N)
     */
    public String getCreditGroupsNo() {
        return this.creditGroupsNo;
    }

    /**
     * Gets information about charging customer for UNBA connection (attribute
     * 'charge' of tag 'msisdn')
     *
     * @return Y/N
     */
    public String getCharge() {
        return this.charge;
    }

    /**
     * Gets information about NoChargeReason for UNBA connection (attribute
     * 'no_charge_reason' of tag 'msisdn')
     *
     * @return Y/N
     */
    public String getNoChargeReason() {
        return this.noChargeReason;
    }

    public String getMCGroup() {
        return this.MCGroup;
    }

    public String getPWParams() {
        return PWParams;
    }

    public String getPWAccessFeesList() {
        return PWAccessFeesList;
    }

    public String getPWDiscountsList() {
        return PWDiscountsList;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
        this.logger.debug("systemName: " + systemName);
    }
}
