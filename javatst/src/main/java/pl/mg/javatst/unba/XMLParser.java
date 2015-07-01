package pl.mg.javatst.unba;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import pl.mg.javatst.unba.datasource.RatingScenario;

import javax.xml.parsers.*;

/**
 * Class provide XML Parser and other utilities for parsing and converting UNBA XML
 * to Voice Objects data types
 *
 * @author Goemant Inc. Pawel Biedronski
 */
public class XMLParser {


    // rating_scenario node labels
    public static final String RATING_SCENARIOS_LABEL = "rating_scenarios";
    public static final String RATING_SCENARIO_LABEL = "rating_scenario";
    public static final String SCENARIO_ID_LABEL = "scenario_id";
    public static final String SCENARIO_DES_LABEL = "scenario_des";
    public static final String SCENARIO_COUNTERS_LABEL = "scenario_counters";
    public static final String COUNTER_LABEL = "counter";
    public static final String COUNTER_ID_LABEL = "counter_id";
    public static final String COUNTER_DES_LABEL = "counter_des";
    public static final String STEP_LABEL = "step";
    public static final String VOLUME_TOTAL_LABEL = "volume_total";
    public static final String VOLUME_LEFT_LABEL = "volume_left";
    public static final String VOLUME_LIMIT_LABEL = "volume_limit";
    // rating_scenario node labels

    // variable holds unbaXML
    private String unbaData = null;
    // variable holds main MSISDN
    private String msisdnMain = null;
    // Logger
    private Logger logger = null;
    // tab for <costs> elements
    private HashMap<String, String> costs = new HashMap<String, String>(8);
    // tab for <mc_calculation> elements
    private HashMap<String, String> mcCalcParams = new HashMap<String, String>(5);
    // tab for Credits Avaliability
    private HashMap<String, String> creditsAvaliability = new HashMap<String, String>(4);
    // tab for MC Calculation Access Fees
    private List<AccessFee> mcCalcAccessFees = new ArrayList<AccessFee>();
    // tab for MC Calculation Discounts
    private List<Discount> mcCalcDiscounts = new ArrayList<Discount>();
    // tab for <credits_minutes>
    private List<Credit> creditsMinutes = new ArrayList<Credit>();
    // tab for <credits_sms>
    private List<Credit> creditsSMS = new ArrayList<Credit>();
    // tab for <credits_mms>
    private List<Credit> creditsMMS = new ArrayList<Credit>();
    // tab for <credits_bytes>
    private List<Credit> creditsBytes = new ArrayList<Credit>();
    // tab for <credits_currency>
    private List<Credit> creditsCurrency = new ArrayList<Credit>();

    // tab for <rating_scenarios>
    private List<RatingScenario> ratingScenarios = new ArrayList<RatingScenario>();

    // variable for credit_groups_no
    private String creditGroupsNo = "0";
    // variable for charge flag
    private String charge = "";
    // variable for 'no charge reason' description
    private String no_charge_reason = "";
    // variable for before_first_bill
    private String before_first_bill = "";
    // variable for rate_plan_change
    private String rate_plan_change = "";
    /**
     * variable for mc_group
     * Y – Active service 774 (Obsługa w grupie kart) or tariff Honsiu Company
     * N – Tariff Honsiu Basic
     * Attribute not present otherwise
     */
    private String mc_group = "";


    // data formater
    private SimpleDateFormat sdf = new SimpleDateFormat("");
    // data formats (for sdf)
    private String DATE_FORMAT_UNBA = "HH:mm yyyy-MM-dd";
    private String DATE_FORMAT_VO = "dd-MM-yyyy-HH-mm";

    /**
     * Constructor
     *
     * @param unbaLogger handler to Logger object
     * @param unbaData   XML from UNBA PL/SQL procedure
     * @param msisdnMain - main Customer Number
     */
    public XMLParser(Logger unbaLogger, String unbaData, String msisdnMain) {

        // rewrite variables
        this.unbaData = unbaData;
        this.logger = unbaLogger;
        this.msisdnMain = msisdnMain;
        // init table costs
        costs.put("COSTS_CALLS_ALL", "0");
        costs.put("COSTS_CALLS_ROAMING", "0");
        costs.put("COSTS_CALLS_PREMIUM", "0");
        costs.put("COSTS_CALLS_LASTCALL_DATETIME", "");
        costs.put("COSTS_MINIMAL_CONSUMPTION_UNUSED_AMOUNT", "");
        costs.put("COSTS_ACCESS_FEE_AMOUNT", "");
        costs.put("COSTS_DISCOUNTS_AMOUNT", "");
        costs.put("COSTS_OTHER_FEES_AMOUNT", "");


        // init table mcCalcParams
        mcCalcParams.put("MC_ROLLED", "");
        mcCalcParams.put("MC_CURRENT", "");
        mcCalcParams.put("MC_USAGE_TO_BE_INCLUDED", "");
        mcCalcParams.put("MC_USAGE_NOT_TO_BE_INCLUDED", "");
        mcCalcParams.put("MC_CALCULATED", "");
        mcCalcParams.put("MC_REMAINING", "");
        mcCalcParams.put("MC_TO_BE_PAID", "");
        mcCalcParams.put("MC_ALL_COSTS", "");
        mcCalcParams.put("MC_ACCESS_FEES_VALUE", "");
        mcCalcParams.put("MC_TOTAL", "");


        // init table credits avaliability
        creditsAvaliability.put("CREDITS_MINUTES", "false");
        creditsAvaliability.put("CREDITS_SMS", "false");
        creditsAvaliability.put("CREDITS_MMS", "false");
        creditsAvaliability.put("CREDITS_BYTES", "false");


    }

    /**
     * Method parse XML UNBA from PL/SQL procedure
     *
     * @throws Exception
     */
    public void parse() throws Exception {
        if (unbaData == null || unbaData.trim().equals(""))
            throw new Exception("unbaData can't be null or empty");
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        document = db.parse(new InputSource(new StringReader(this.unbaData)));
        Element root = document.getDocumentElement();
        if (root.hasAttributes()) {
            NamedNodeMap nmp = root.getAttributes();
            for (int i = 0; i < nmp.getLength(); i++) {
                // System.out.println("  "+i+"\t"+nmp.item(i).getNodeName()+" = "+nmp.item(i).getNodeValue());
                // TODO: pobranie atrubutów ?
                // .....
            }
        }
        // if <unba2> has childes
        if (root.hasChildNodes()) {
            NodeList nodeList = root.getChildNodes();
            // go throu childes of <unba2> (<msisdn> element)
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodeMSISDN = nodeList.item(i);
                if (nodeMSISDN.getNodeName().equals("msisdn")) {
                    if (nodeMSISDN.hasAttributes()) {
                        NamedNodeMap nmp = nodeMSISDN.getAttributes();
                        for (int j = 0; j < nmp.getLength(); j++) {
                            String paramName = nmp.item(j).getNodeName();
                            // take 'id' param and compare with 'msisdnMain'
                            if (paramName.equals("id")) {
                                String paramValue = nmp.item(j).getNodeValue();
                                if (paramValue.equals(msisdnMain)) {
                                    // parse <msisdn>
                                    getMSISDNData(nodeMSISDN);
                                    // if found msisdnMain - ignore others
                                    break;
                                }
                            } // end <msisdn>.id == msisdnMain
                        } // end <msisdn>.getAtributes()
                    } // end <msisdn>.hasAttributes()
                } // end  <msisdn>
            } // end <root>.chileds()
        } // <root>.getChildNodes()        
        else {
            logger.error("node '<root>' has no childs?!");
        }
    }

    /**
     * gets data from <msisdn> node
     *
     * @param nodeMSISDN - Node from XML
     * @throws Exception
     */
    private void getMSISDNData(Node nodeMSISDN) throws Exception {
        // get credit_groups_no
        NamedNodeMap nmp = nodeMSISDN.getAttributes();
        for (int j = 0; j < nmp.getLength(); j++) {
            String paramName = nmp.item(j).getNodeName();
            // gets attribute 'credit_groups_no'
            if (paramName.equals("credit_groups_no")) {
                this.creditGroupsNo = nmp.item(j).getNodeValue();
            }
            // gets attribute 'charge'
            else if (paramName.equals("charge")) {
                this.charge = nmp.item(j).getNodeValue();
            }
            // gets attribute 'no_charge_reason'
            else if (paramName.equals("no_charge_reason")) {
                this.no_charge_reason = nmp.item(j).getNodeValue();
            }
            // gets attribute 'before_first_bill'
            else if (paramName.equals("before_first_bill")) {
                this.before_first_bill = nmp.item(j).getNodeValue();
            }
            // gets attribute 'rate_plan_change'
            else if (paramName.equals("rate_plan_change")) {
                this.rate_plan_change = nmp.item(j).getNodeValue();
            }
            // gets attribute 'mc_group'
            else if (paramName.equals("mc_group")) {
                this.mc_group = nmp.item(j).getNodeValue();
            }
        }
        // get data from <msisdn> childes
        NodeList nodeList = nodeMSISDN.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            // take data from tag <costs>
            if (child.getNodeName().equals("costs")) {
                NodeList nodeListCosts = child.getChildNodes();
                for (int iCosts = 0; iCosts < nodeListCosts.getLength(); iCosts++) {
                    Node childCosts = nodeListCosts.item(iCosts);
                    // calls
                    if (childCosts.getNodeName().equals("calls")) {
                        getDataCostsCalls(childCosts);
                    }
                    // minimal_consumption
                    else if (childCosts.getNodeName().equals("minimal_consumption")) {
                        getDataCostsMinimalConsumption(childCosts);
                    }
                    // access_fee
                    else if (childCosts.getNodeName().equals("mc_formula")) {
                        getDataCostsAccessFee(childCosts);
                    }
                    // discounts
                    else if (childCosts.getNodeName().equals("discounts")) {
                        getDataCostsDiscounts(childCosts);
                    }
                    // other_fees
                    else if (childCosts.getNodeName().equals("other_fees")) {
                        getDataCostsOtherFees(childCosts);
                    }
                    // mc_calculation
                    else if (childCosts.getNodeName().equals("mc_calculation")) {
                        getDataMCCalculation(childCosts);
                    }
//            		 total amount
                    else if (childCosts.getNodeName().equals("total")) {
                        getDataMCCalculationTotalAmount(childCosts, "MC_ALL_COSTS");
                    }

                }
            } //costs

            //rating_scenarios
            else if (child.getNodeName().equals("rating_scenarios")) {
                parseRatingScenarios(child);
            }
            //rating_scenarios

            // take data from tag <credits>
            else if (child.getNodeName().equals("credits")) {
                NodeList nodeListCredits = child.getChildNodes();
                for (int iCredits = 0; iCredits < nodeListCredits.getLength(); iCredits++) {
                    Node childCredits = nodeListCredits.item(iCredits);
                    // credits_minutes
                    if (childCredits.getNodeName().equals("credits_minutes")) {
                        getDataCredits(childCredits, CreditType.MINUTES);
                    }
                    // credits_smses
                    else if (childCredits.getNodeName().equals("credits_smses")) {
                        getDataCredits(childCredits, CreditType.SMS);
                    }
                    // credits_mmses
                    else if (childCredits.getNodeName().equals("credits_mmses")) {
                        getDataCredits(childCredits, CreditType.MMS);
                    }
                    // credits_bytes
                    else if (childCredits.getNodeName().equals("credits_bytes")) {
                        getDataCredits(childCredits, CreditType.BYTES);
                    }
                    // credits_currency
                    else if (childCredits.getNodeName().equals("credits_currency")) {
                        getDataCredits(childCredits, CreditType.CURRENCY);
                    }
                }
                // put status of credits to creditsAvaliability
                if (this.creditsMinutes.size() > 0)
                    creditsAvaliability.put("CREDITS_MINUTES", "true");
                if (this.creditsSMS.size() > 0)
                    creditsAvaliability.put("CREDITS_SMS", "true");
                if (this.creditsMMS.size() > 0)
                    creditsAvaliability.put("CREDITS_MMS", "true");
                if (this.creditsBytes.size() > 0)
                    creditsAvaliability.put("CREDITS_BYTES", "true");
            }//credits
        }
    }

    /**
     * Get data for rating_scenarios
     *
     * @param ratingSceanriosNode node with rating scenarios list
     */
    private void parseRatingScenarios(Node ratingSceanriosNode) {
        NodeList nodeListScenarios = ratingSceanriosNode.getChildNodes();
        for (int iScenarios = 0; iScenarios < nodeListScenarios.getLength(); iScenarios++) {
            //rating_scenario
            RatingScenario scenario = new RatingScenario();
            Node ratingScenario = nodeListScenarios.item(iScenarios);
            NodeList ratingScenarioList = ratingScenario.getChildNodes();
            if (ratingSceanriosNode != null) {
                for (int j = 0; j < ratingScenarioList.getLength(); j++) {
                    Node ratingScenarioAttribute = ratingScenarioList.item(j);
//                    System.out.println(ratingScenarioAttribute.getNodeName());
                    switch (ratingScenarioAttribute.getNodeName()) {
                        case SCENARIO_ID_LABEL:
                            scenario.setScenarioId(Integer.parseInt(ratingScenarioAttribute.getTextContent()));
                            break;
                        case SCENARIO_DES_LABEL:
                            scenario.setScenarioDes(ratingScenarioAttribute.getTextContent());
                            break;
                        case SCENARIO_COUNTERS_LABEL:
                            NodeList counters = ratingScenarioAttribute.getChildNodes();
                            if (counters != null) {
                                for (int counterI = 0; counterI < counters.getLength(); counterI++) {
                                    Node counter = counters.item(counterI);
                                    if (counter != null) {
                                        ScenarioCounter scenarioCounter = new ScenarioCounter();
                                        NodeList counterAttributes = counter.getChildNodes();
                                        if (counterAttributes != null) {
//                                            System.out.println(counterAttributes.getLength());
                                            for (int counterAttributeI = 0; counterAttributeI < counterAttributes.getLength(); counterAttributeI++) {
                                                Node counterAttribute = counterAttributes.item(counterAttributeI);
                                                switch (counterAttribute.getNodeName()) {
                                                    case COUNTER_ID_LABEL:
                                                        scenarioCounter.setCounterId(Integer.parseInt(counterAttribute.getTextContent()));
                                                        break;
                                                    case COUNTER_DES_LABEL:
                                                        scenarioCounter.setCounterDes(counterAttribute.getTextContent());
                                                        break;
                                                    case STEP_LABEL:
                                                        scenarioCounter.setStep(Integer.parseInt(counterAttribute.getTextContent()));
                                                        break;
                                                    case VOLUME_TOTAL_LABEL:
                                                        scenarioCounter.setVolumeTotal(Integer.parseInt(counterAttribute.getTextContent()));
                                                        break;
                                                    case VOLUME_LEFT_LABEL:
                                                        scenarioCounter.setVolumeLeft(Integer.parseInt(counterAttribute.getTextContent()));
                                                        break;
                                                    case VOLUME_LIMIT_LABEL:
                                                        scenarioCounter.setVolumeLimit(Integer.parseInt(counterAttribute.getTextContent()));
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                            scenario.getScenarioCounters().add(scenarioCounter);
                                        }

                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            this.ratingScenarios.add(scenario);
            //rating_scenario
        }
    }

    /**
     * Get Data of Cost Calls
     *
     * @param node handler to <calls> node
     * @throws Exception
     */
    private void getDataCostsCalls(Node node) throws Exception {
        NamedNodeMap nmp = node.getAttributes();
        for (int i = 0; i < nmp.getLength(); i++) {
            String attrName = nmp.item(i).getNodeName();
            String attrValue = nmp.item(i).getNodeValue();
            // get COSTS_CALLS_ALL
            if (attrName.equals("all")) {
                costs.put("COSTS_CALLS_ALL", attrValue);
            }
            // get COSTS_CALLS_ROAMING
            else if (attrName.equals("roaming")) {
                costs.put("COSTS_CALLS_ROAMING", attrValue);
            }
            // get COSTS_CALLS_PREMIUM
            else if (attrName.equals("premium")) {
                costs.put("COSTS_CALLS_PREMIUM", attrValue);
            }
            // get COSTS_CALLS_LASTCALL_DATETIME
            else if (attrName.equals("last_call_date")) {
                if (!isEmpty(attrValue)) {
                    // convertion of date format UNBA -> VO
                    sdf.applyPattern(DATE_FORMAT_UNBA);
                    Date tmp = sdf.parse(attrValue);
                    sdf.applyPattern(DATE_FORMAT_VO);
                    attrValue = sdf.format(tmp);
                    // put result (converted date) to table
                    costs.put("COSTS_CALLS_LASTCALL_DATETIME", attrValue);
                }
            }
        }
    }

    /**
     * Get Data of Costs Minimal Consumption
     *
     * @param node handler to <minimal_consumption> node
     * @throws Exception
     */
    private void getDataCostsMinimalConsumption(Node node) {
        NamedNodeMap nmp = node.getAttributes();
        for (int i = 0; i < nmp.getLength(); i++) {
            String attrName = nmp.item(i).getNodeName();
            String attrValue = nmp.item(i).getNodeValue();
            // get COSTS_MINIMAL_CONSUMPTION_UNUSED_AMOUNT
            if (attrName.equals("unused_amount")) {
                costs.put("COSTS_MINIMAL_CONSUMPTION_UNUSED_AMOUNT", attrValue);
                break;
            }
        }
    }

    /**
     * Get Data of Costs Access Fee
     *
     * @param node handler to <access_fee> node
     * @throws Exception
     */
    private void getDataCostsAccessFee(Node node) {
        NamedNodeMap nmp = node.getAttributes();
        for (int i = 0; i < nmp.getLength(); i++) {
            String attrName = nmp.item(i).getNodeName();
            String attrValue = nmp.item(i).getNodeValue();
            // get COSTS_ACCESS_FEE_AMOUNT
            if (attrName.equals("remaining")) {
                costs.put("COSTS_ACCESS_FEE_AMOUNT", attrValue);

                break;
            }
        }
    }

    /**
     * Get Data of Costs Discounts
     *
     * @param node handler to <discounts> node
     * @throws Exception
     */
    private void getDataCostsDiscounts(Node node) {
        NamedNodeMap nmp = node.getAttributes();
        for (int i = 0; i < nmp.getLength(); i++) {
            String attrName = nmp.item(i).getNodeName();
            String attrValue = nmp.item(i).getNodeValue();
            // get COSTS_DISCOUNTS_AMOUNT
            if (attrName.equals("amount")) {
                costs.put("COSTS_DISCOUNTS_AMOUNT", attrValue);
                break;
            }
        }
    }

    /**
     * Get Data of Costs Other Fees
     *
     * @param node handler to <other_fees> node
     * @throws Exception
     */
    private void getDataCostsOtherFees(Node node) {
        NamedNodeMap nmp = node.getAttributes();
        for (int i = 0; i < nmp.getLength(); i++) {
            String attrName = nmp.item(i).getNodeName();
            String attrValue = nmp.item(i).getNodeValue();
            // get COSTS_OTHER_FEES
            if (attrName.equals("amount")) {
                costs.put("COSTS_OTHER_FEES_AMOUNT", attrValue);
                //mcCalcParams.put("MC_REMAINING", attrValue);
                break;
            }
        }
    }

    /**
     * Get data from mc_calculation node
     *
     * @param mcCalc
     */
    private void getDataMCCalculationElementAmount(Node mcCalc, String NODE_KEY) {
        NamedNodeMap nmp = mcCalc.getAttributes();
        for (int i = 0; i < nmp.getLength(); i++) {
            String attrName = nmp.item(i).getNodeName();
            String attrValue = nmp.item(i).getNodeValue();
            // get amount
            if (attrName.equals("amount")) {
                mcCalcParams.put(NODE_KEY, attrValue);
            }
        }
    }

    private void getDataMCCalculationFormulaAmount(Node mcCalc) {
        NamedNodeMap nmp = mcCalc.getAttributes();
        for (int i = 0; i < nmp.getLength(); i++) {
            String attrName = nmp.item(i).getNodeName();
            String attrValue = nmp.item(i).getNodeValue();
            // get amount
            if (attrName.equals("remaining")) {
                mcCalcParams.put("MC_REMAINING", attrValue);
            }
            if (attrName.equals("to_be_paid")) {
                mcCalcParams.put("MC_TO_BE_PAID", attrValue);
            }
        }
    }

    /**
     * Get data from total node
     *
     * @param mcCalc
     */
    private void getDataMCCalculationTotalAmount(Node mcCalc, String NODE_KEY) {
        NamedNodeMap nmp = mcCalc.getAttributes();
        for (int i = 0; i < nmp.getLength(); i++) {
            String attrName = nmp.item(i).getNodeName();
            String attrValue = nmp.item(i).getNodeValue();
            // get amount
            if (attrName.equals("amount")) {
                mcCalcParams.put(NODE_KEY, attrValue);
            }
        }
    }

    /**
     * Get list Access Fees
     *
     * @param mcCalc
     */
    private void getDataMCCalculationAccessFees(Node node) {
        NodeList nodeListAccessFees = node.getChildNodes();
        for (int iAF = 0; iAF < nodeListAccessFees.getLength(); iAF++) {
            Node nodeAF = nodeListAccessFees.item(iAF);
            // get mc_rolled
            if (nodeAF.getNodeName().equals("access_fee")) {
                AccessFee accessFeee = new AccessFee();
                NamedNodeMap nmp = nodeAF.getAttributes();
                for (int i = 0; i < nmp.getLength(); i++) {
                    String attrName = nmp.item(i).getNodeName();
                    String attrValue = nmp.item(i).getNodeValue();
                    // get amount
                    if (attrName.equals("des")) {
                        accessFeee.setDes(attrValue);
                    }
                    // get amount
                    else if (attrName.equals("advance")) {
                        accessFeee.setAdvance(attrValue);
                    }
                    // get amount
                    else if (attrName.equals("past")) {
                        accessFeee.setPast(attrValue);
                    }
                    // get amount
                    else if (attrName.equals("correction")) {
                        accessFeee.setCorrection(attrValue);
                    }
                } // access_fee attributes
                this.mcCalcAccessFees.add(accessFeee);
            } // if access_fee
        } // for nodeListAccessFees
    }

    /**
     * Get list discounts in mc_ccalculation
     *
     * @param mcCalc
     */
    private void getDataMCCalculationDiscounts(Node node) {
        NodeList nodeListAccessFees = node.getChildNodes();
        for (int iAF = 0; iAF < nodeListAccessFees.getLength(); iAF++) {
            Node nodeAF = nodeListAccessFees.item(iAF);
            // get mc_rolled
            if (nodeAF.getNodeName().equals("discount")) {
                Discount discount = new Discount();
                NamedNodeMap nmp = nodeAF.getAttributes();
                for (int i = 0; i < nmp.getLength(); i++) {
                    String attrName = nmp.item(i).getNodeName();
                    String attrValue = nmp.item(i).getNodeValue();
                    // get amount
                    if (attrName.equals("des")) {
                        discount.setDes(attrValue);
                    }
                    // get amount
                    else if (attrName.equals("amount")) {
                        discount.setAmount(attrValue);
                    }

                } // access_fee attributes
                this.mcCalcDiscounts.add(discount);
            } // if access_fee
        } // for nodeListAccessFees
    }


    /**
     * Get Data of MC Calculation
     *
     * @param node handler to <mc_calculation> node
     * @throws Exception
     */
    private void getDataMCCalculation(Node node) {
        NodeList nodeListMCCalculation = node.getChildNodes();
        for (int iMCCalc = 0; iMCCalc < nodeListMCCalculation.getLength(); iMCCalc++) {
            Node mcCalc = nodeListMCCalculation.item(iMCCalc);
            // get mc_rolled
            if (mcCalc.getNodeName().equals("mc_rolled")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationElementAmount(mcCalc, "MC_ROLLED");
            }
            // get mc_rolled
            else if (mcCalc.getNodeName().equals("mc_current")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationElementAmount(mcCalc, "MC_CURRENT");
            }
            // get usage_to_be_included
            else if (mcCalc.getNodeName().equals("usage_to_be_included")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationElementAmount(mcCalc, "MC_USAGE_TO_BE_INCLUDED");
            }
            // get usage_to_be_included
            else if (mcCalc.getNodeName().equals("usage_not_to_be_included amount")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationElementAmount(mcCalc, "MC_USAGE_NOT_TO_BE_INCLUDED");
            }
            // get mc_calculated
            else if (mcCalc.getNodeName().equals("mc_calculated")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationElementAmount(mcCalc, "MC_CALCULATED");
            }
            // get mc_remaining
//    		else if (mcCalc.getNodeName().equals("total")){
            // take <mcmc_rolled> attributes: amount
//    			getDataMCCalculationElementAmount(mcCalc,"MC_TOTAL");
//    		}


            else if (mcCalc.getNodeName().equals("mc_formula")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationFormulaAmount(mcCalc);
            }
            // get access_fees list
            else if (mcCalc.getNodeName().equals("access_fees")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationAccessFees(mcCalc);
            }
//    		 get access_fees amount
            else if (mcCalc.getNodeName().equals("access_fees_to_be_included")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationElementAmount(mcCalc, "MC_ACCESS_FEES_VALUE");
            }
            // get discounts
            else if (mcCalc.getNodeName().equals("discounts")) {
                // take <mcmc_rolled> attributes: amount
                getDataMCCalculationDiscounts(mcCalc);
            }
        }
    }

    /**
     * function gets data of <credit> from node and put them into proper List:  creditsMinutes,creditsSMS,creditsMMS,creditsBytes,creditsCurrency
     *
     * @param node       node of: <credits_minutes> or <credits_sms> or <credits_mms> or <credits_bytes> or <credits_currency>
     * @param creditType type of credits from class CreditType
     * @see CreditType
     */
    private void getDataCredits(Node node, short creditType) {
        // parse XML Node - gets all <credit>
        NodeList nodeListCredits = node.getChildNodes();
        for (int iCredit = 0; iCredit < nodeListCredits.getLength(); iCredit++) {
            Node credit = nodeListCredits.item(iCredit);
            Credit creditPackage = new Credit();
            // check name and take only <credit>
            if (credit.getNodeName().equals("credit")) {
                // take <credit> attributes: name, id, amount
                NamedNodeMap nmp = credit.getAttributes();
                for (int i = 0; i < nmp.getLength(); i++) {
                    String attrName = nmp.item(i).getNodeName();
                    String attrValue = nmp.item(i).getNodeValue();
                    // get id
                    if (attrName.equals("id")) {
                        creditPackage.setID(attrValue);
                    }
                    // get name
                    else if (attrName.equals("name")) {
                        creditPackage.setName(maskXML(attrValue));
                    }
                    // get value
                    else if (attrName.equals("amount")) {
                        // if credit_minutes
                        if (creditType == CreditType.MINUTES) {
                            // convert value UNBA (5min 59s) -> VO (5.59)
                            if (!isEmpty(attrValue)) {
                                attrValue = attrValue.replaceAll(" ", "");
                                if (attrValue.endsWith("min")) {
                                    attrValue = attrValue.replaceAll("min", "");
                                } else {
                                    attrValue = attrValue.replaceAll("min", ".");
                                    attrValue = attrValue.replaceAll("sec", "");
                                    attrValue = attrValue.replaceAll("s", "");
                                }
                            } else {
                                attrValue = "0";
                            }
                        } // end credit_minutes
                        // if credit_bytes
                        else if (creditType == CreditType.BYTES) {
                            // take unit (KB or MB)
                            if (!isEmpty(attrValue)) {
                                attrValue = attrValue.trim();
                                if (attrValue.toUpperCase().endsWith("KB")) {
                                    creditPackage.setUnit("KB");
                                } else if (attrValue.endsWith("MB")) {
                                    creditPackage.setUnit("MB");
                                }
                                attrValue = attrValue.toUpperCase().replaceAll("KB", "");
                                attrValue = attrValue.toUpperCase().replaceAll("MB", "");
                            } else {
                                attrValue = "0";
                                creditPackage.setUnit("MB");
                            }
                        }// credit bytes
                        // if credit_smses
                        else if (creditType == CreditType.SMS) {
                            // take unit (SMS)
                            if (!isEmpty(attrValue)) {
                                attrValue = attrValue.trim();
                                if (attrValue.endsWith("SMS")) {
                                    attrValue = attrValue.replaceAll("SMS", "");
                                }
                            } else {
                                attrValue = "0";
                            }
                        }// credit smses
                        // if credit_mmses
                        else if (creditType == CreditType.MMS) {
                            // take unit (SMS)
                            if (!isEmpty(attrValue)) {
                                attrValue = attrValue.trim();
                                if (attrValue.endsWith("MMS")) {
                                    attrValue = attrValue.replaceAll("MMS", "");
                                }
                            } else {
                                attrValue = "0";
                            }
                        }// credit mmses
                        creditPackage.setAmount(attrValue);
                    }
                    // unexpected attribute name
                    else {
                        logger.warn("Unexpexted <credit> attribute: '" + attrName + " ' in <" + CreditType.getNameXML(creditType) + ">");
                    }
                }
            }
            // ignore all others
            else {
                logger.warn("Unexpexted <" + credit.getNodeName() + "> in <" + CreditType.getNameXML(creditType) + ">");
                continue;
            }
            // check if <credit> id complete
            if (!creditPackage.isComplete()) {
                logger.warn("incomplete <credit> node: " + creditPackage.toString() + "in <" + CreditType.getNameXML(creditType) + ">");
                continue;
            }

            // put to proper table
            switch (creditType) {
                case CreditType.MINUTES:
                    creditsMinutes.add(creditPackage);
                    break;
                case CreditType.SMS:
                    creditsSMS.add(creditPackage);
                    break;
                case CreditType.MMS:
                    creditsMMS.add(creditPackage);
                    break;
                case CreditType.BYTES:
                    creditsBytes.add(creditPackage);
                    break;
                case CreditType.CURRENCY:
                    creditsCurrency.add(creditPackage);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * replace XML tags '<' to (lt) , '>' to (gt)
     *
     * @param text with XML tags
     * @return text wich replaced XML tags
     */
    public static String maskXML(String text) {
        String result = text;
        result = result.replaceAll("<", "(lt)");
        result = result.replaceAll(">", "(gt)");
        return result;
    }

    /**
     * Function check id provided parameter id NULL or empty
     *
     * @param data text to verify
     * @return true id text is empty or false if not
     */
    public static boolean isEmpty(String data) {
        if (data == null || data.equals(""))
            return true;
        else
            return false;
    }

    /**
     * get HashMap with definiotion of <costs> node. <BR>
     * Keys (String class) on HashMap:<BR>
     * - "COSTS_CALLS_ALL" contains - attribute 'all' of <calls> in <costs> <BR>
     * - "COSTS_CALLS_ROAMING" - attribute 'roaming' of <calls> in <costs> <BR>
     * - "COSTS_CALLS_PREMIUM" - attribute 'premium' of <calls> in <costs> <BR>
     * - "COSTS_CALLS_LASTCALL_DATETIME" - attribute 'last_call_date' of <calls> in <costs> <BR>
     * - "COSTS_MINIMAL_CONSUMPTION_UNUSED_AMOUNT" - attribute 'unused_amount' of <minimal_consumption> in <costs> <BR>
     * - "COSTS_ACCESS_FEE_AMOUNT" - attribute 'amount' of <access_fee> in <costs> <BR>
     * All values in HashMap are String class
     *
     * @return tab with <costs> definition
     */
    public HashMap<String, String> getCosts() {
        return costs;
    }

    /**
     * get HashMap with definiotion of <mc_calculation> node. <BR>
     * Keys (String class) on Hashmap:<BR>
     * - "MC_ROLLED"
     * - "MC_CURRENT"
     * - "MC_USAGE_TO_BE_INCLUDED"
     * - "MC_CALCULATED"
     * - "MC_REMAINING"
     *
     * @return tab with <mc_calculation> definiotion
     */
    public HashMap<String, String> getMCCalcParams() {
        return this.mcCalcParams;
    }

    /**
     * get HashMap with information of Credit Avability <BR>
     * Keys (String class) on HashMap:<BR>
     * - "NAME" contains: name of Credit: CREDITS_MINUTES,CREDITS_SMS,CREDITS_MMS,CREDITS_BYTES
     * All values in HashMap are String class (two values possible: "true"/"false")
     *
     * @return tab with Credit Avability definition
     */
    public HashMap<String, String> getCreditsAvaliability() {
        return creditsAvaliability;
    }

    /**
     * get List of Credit objects - from <credits_minutes><BR>
     *
     * @return list of Credit
     */
    public List<Credit> getCreditsMinutes() {
        return creditsMinutes;
    }

    /**
     * get List of Credit objects - from <credits_smses><BR>
     *
     * @return list of Credit
     */
    public List<Credit> getCreditsSMS() {
        return creditsSMS;
    }

    /**
     * get List of Credit objects - from <credits_mmses><BR>
     *
     * @return list of Credit
     */
    public List<Credit> getCreditsMMS() {
        return creditsMMS;
    }

    /**
     * get List of Credit objects - from <credits_bytes><BR>
     *
     * @return list of Credit
     */
    public List<Credit> getCreditsBytes() {
        return creditsBytes;
    }

    /**
     * get List of Credit objects - from <credits_currency><BR>
     *
     * @return list of Credit
     */
    public List<Credit> getCreditsCurrency() {
        return creditsCurrency;
    }

    /**
     * Gets number of Credit Groups attribute 'credit_groups_no' of 'msisdn'
     *
     * @return numer of Credit Groups
     */
    public String getCreditGroupsNo() {
        return this.creditGroupsNo;
    }

    /**
     * Gets 'Charge' - attribute 'charge' of 'msisdn'
     *
     * @return charge ("Y"/"N")
     */
    public String getCharge() {
        return this.charge;
    }

    /**
     * Gets 'No Charge Reason' attribute 'no_charge_reason' of 'msisdn'
     *
     * @return text description of reason
     */
    public String getNoChargeReason() {
        return this.no_charge_reason;
    }

    /**
     * Gets 'Before First Bill' attribute 'before_first_bill' of 'msisdn'
     *
     * @return text description of reason
     */
    public String getBeforeFirstBill() {
        return before_first_bill;
    }

    /**
     * Gets 'Rate Plan Change' attribute 'rate_plan_change' of 'msisdn'
     *
     * @return text description of reason
     */
    public String getRatePlanChange() {
        return rate_plan_change;
    }

    /**
     * Gets 'MC Group' attribute 'mc_group' of 'msisdn'
     *
     * @return text description of reason
     */
    public String getMcGgroup() {
        return mc_group;
    }

    /**
     * Gets 'Access Fees' in mc_calcultaion
     *
     * @return List of AccessFee objects
     */
    public List<AccessFee> getMcCalcAccessFees() {
        return mcCalcAccessFees;
    }

    /**
     * Gets 'Discounts' in mc_calcultaion
     *
     * @return List of Discount objects
     */
    public List<Discount> getMcCalcDiscounts() {
        return mcCalcDiscounts;
    }


    /**
     * Gets 'ratingScenario' list
     *
     * @return List of rating_scenario objects
     */
    public List<RatingScenario> getRatingScenarios() {
        return ratingScenarios;
    }

    @Override
    public String toString() {
        return "XMLParser{" +
                "unbaData='" + unbaData + '\'' +
                ", msisdnMain='" + msisdnMain + '\'' +
                ", costs=" + costs +
                ", mcCalcParams=" + mcCalcParams +
                ", creditsAvaliability=" + creditsAvaliability +
                ", mcCalcAccessFees=" + mcCalcAccessFees +
                ", mcCalcDiscounts=" + mcCalcDiscounts +
                ", creditsMinutes=" + creditsMinutes +
                ", creditsSMS=" + creditsSMS +
                ", creditsMMS=" + creditsMMS +
                ", creditsBytes=" + creditsBytes +
                ", creditsCurrency=" + creditsCurrency +
                ", ratingScenarios=" + ratingScenarios +
                ", creditGroupsNo='" + creditGroupsNo + '\'' +
                ", charge='" + charge + '\'' +
                ", no_charge_reason='" + no_charge_reason + '\'' +
                ", before_first_bill='" + before_first_bill + '\'' +
                ", rate_plan_change='" + rate_plan_change + '\'' +
                ", mc_group='" + mc_group + '\'' +
                ", sdf=" + sdf +
                ", DATE_FORMAT_UNBA='" + DATE_FORMAT_UNBA + '\'' +
                ", DATE_FORMAT_VO='" + DATE_FORMAT_VO + '\'' +
                '}';
    }
}