/*
 * EraPremiaConfiguration.java
 *
 * Created on 24 maj 2007, 23:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mg.javatst.unba.osp.vo.connectors;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Michal Janusz
 */

/** Configuration class for EPBuildMenu connector.
 *This class shuld be pluged-in into vtk
 */
public class EraPremiaConfiguration {
    /** Parameter to be read by EPBuildMenu connector.
     */
    static public int minGrp;
    /** Parameter to be read by EPBuildMenu connector.
     */
    static public String grpRules;
    /** Parameter to be read by EPBuildMenu connector. File name with log4j properties.
     */
    static public String logConfFile;
    
    
    
    /** Creates a new instance of EraPremiaConfiguration */
    public EraPremiaConfiguration() {
    }
    
    /** sets minGrp configuration parameter
     */
    public void setMinGrp(int newMinGrp){
        minGrp = newMinGrp;
    }
    
    /** sets grpRules configuration parameter
     */
    public void setGrpRules(String newGrpRules){
        grpRules = newGrpRules;
    }
    
    /** sets logConfFile configuration parameter
     */
    public void setLogConfFile(String aLogConfFile) {
        logConfFile = aLogConfFile;
            if(logConfFile != null && !logConfFile.equals(""))
                PropertyConfigurator.configure(logConfFile);

    }
    
}
