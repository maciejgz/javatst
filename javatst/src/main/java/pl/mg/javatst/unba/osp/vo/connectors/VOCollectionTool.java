package pl.mg.javatst.unba.osp.vo.connectors;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

public class VOCollectionTool {

	public static List<Map<String, String>> parse(String collection)
			throws ParseCollectionException {
		try {
			List<Map<String, String>> list = new LinkedList<Map<String, String>>();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(collection.getBytes("utf-8")),
					"utf-8"));
			try {
				InputSource is = new InputSource(reader);
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(is);
				Element root = (Element) doc.getElementsByTagName("root").item(
						0);
				NodeList rows = root.getElementsByTagName("row");
				for (int i = 0; i < rows.getLength(); i++) {
					Element row = (Element) rows.item(i);
					NodeList cols = row.getElementsByTagName("col");
					Map<String, String> parsedRow = new LinkedHashMap<String, String>();

					for (int j = 0; j < cols.getLength(); j++) {
						String name = null;
						String value = "";

						Element col = (Element) cols.item(j);
						name = col.getAttribute("name");
						if (name == null) {
							throw new IOException(
									"Malformed collection: col without name attribute.");
						}
						Text text = (Text) col.getFirstChild();
						if (text != null) {
							value = (text).getData();
						}
						parsedRow.put(name, value);
					}
					list.add(parsedRow);
				}
			} finally {
				reader.close();
			}
			return list;
		} catch (Exception e) {
			throw new ParseCollectionException(e);
		}
	}

	/**
	 * TEST
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String collection = "<root>"
				+ "<row><col name='col1'>val1</col><col name='col2'>val2</col></row>"
				+ "<row><col name='col1'>val3</col><col name='col2'>val4</col></row>"
				+ "</root>";

		try {
			System.out.println(parse(collection));
		} catch (ParseCollectionException e) {
			e.printStackTrace();
		}
	}

	public static class ParseCollectionException extends Exception {

		private static final long serialVersionUID = 1L;

		public ParseCollectionException() {
			super();
		}

		public ParseCollectionException(String arg0, Throwable arg1) {
			super(arg0, arg1);
		}

		public ParseCollectionException(String arg0) {
			super(arg0);
		}

		public ParseCollectionException(Throwable arg0) {
			super(arg0);
		}

	}

}
