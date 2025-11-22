package pl.mg.javatst.geopdf;

import com.google.gson.Gson;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.gdal.GDALParser;
import org.gdal.gdal.gdal;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GeoPdfTest {

  @Test
  public void pureGdalTest() {
    System.out.println(gdal.VersionInfo());
  }

  @Test
  public void apacheTikaGeoPdfParsingTest() throws IOException {
    String pdf1FileName = "geo/GeoPDF1.pdf";

    ClassLoader classLoader = getClass().getClassLoader();
    InputStream resourceAsStream = classLoader.getResourceAsStream(pdf1FileName);
    Metadata metadata = new Metadata();
    GDALParser parser = new GDALParser();
    try {
      parser.parse(resourceAsStream, new Cos2ContentHandler(), metadata);
    } catch (SAXException | TikaException e) {
      e.printStackTrace();
    }
    Gson gson = new Gson();
    System.out.println("metadata: " + gson.toJson(metadata));
  }

  @Test
  public void apacheTikaGeoTiffParsingTest() throws IOException {
    String pdf1FileName = "geo/sample1.tif";

    ClassLoader classLoader = getClass().getClassLoader();
    InputStream resourceAsStream = classLoader.getResourceAsStream(pdf1FileName);
    Metadata metadata = new Metadata();
    GDALParser parser = new GDALParser();
    try {
      parser.parse(resourceAsStream, new Cos2ContentHandler(), metadata);
    } catch (SAXException | TikaException e) {
      e.printStackTrace();
    }
    Gson gson = new Gson();
    System.out.println("metadata: " + gson.toJson(metadata));
  }

  @Test
  public void apacheTikaGeoTiffLargeParsingTest() throws IOException {
    String pdf1FileName = "geo/sample2.tif";

    ClassLoader classLoader = getClass().getClassLoader();
    InputStream resourceAsStream = classLoader.getResourceAsStream(pdf1FileName);
    Metadata metadata = new Metadata();
    GDALParser parser = new GDALParser();
    try {
      parser.parse(resourceAsStream, new Cos2ContentHandler(), metadata);
    } catch (SAXException | TikaException e) {
      e.printStackTrace();
    }
    Gson gson = new Gson();
    System.out.println("metadata: " + gson.toJson(metadata));
  }

  @Test
  public void apacheTikaGeospatialPdfParsingTest() throws IOException {
    String pdf1FileName = "geo/GeospatialPDF1.pdf";

    ClassLoader classLoader = getClass().getClassLoader();
    InputStream resourceAsStream = classLoader.getResourceAsStream(pdf1FileName);
    Metadata metadata = new Metadata();
    GDALParser parser = new GDALParser();
    try {
      parser.parse(resourceAsStream, new Cos2ContentHandler(), metadata);
    } catch (SAXException | TikaException e) {
      e.printStackTrace();
    }
    Gson gson = new Gson();
    System.out.println("metadata of the geospatial PDF: " + gson.toJson(metadata));
  }

  private String readFromInputStream(InputStream inputStream) throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }

  class Cos2ContentHandler implements ContentHandler {
    @Override
    public void setDocumentLocator(Locator locator) {
      System.out.println("locator: " + locator.toString());
    }

    @Override
    public void startDocument() throws SAXException {
      System.out.println("start document");
    }

    @Override
    public void endDocument() throws SAXException {
      System.out.println("end document");
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
      System.out.println("start prefix=" + prefix);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
      System.out.println("end prefix=" + prefix);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts)
        throws SAXException {
      System.out.println("startElement=" + localName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      System.out.println("endElement=" + localName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
      System.out.println("characters=" + length);
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
      System.out.println("whitespace=" + length);
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
      System.out.println("instruction=" + target);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
      System.out.println("skippedEntty=" + name);
    }
  }
}
