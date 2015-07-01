package pl.mg.javatst.unba;

import org.apache.log4j.Logger;
import pl.mg.javatst.unba.osp.vo.connectors.AbstractDbBean;

/**
 * Basic Class for Unba Connector
 * 
 * @author Geomant Inc. Pawel Biedronski
 */
public class AbstractUnbaBean extends AbstractDbBean {

	public AbstractUnbaBean() {
		this.logger = Logger.getLogger("UNBA2Logger");
		this.connectionPool = UnbaConfiguration.connectionPool;
		this.transcode2En = UnbaConfiguration.transcode2En;
		this.functionName = "get_unbill_data";
		this.systemName = "BSCS IX";
		this.packageName = UnbaConfiguration.packageName;
	}

}
