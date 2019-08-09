package Utilities;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import java.io.File;

public class XMLReader {
    public static String readXML(String filePath, String node) throws DocumentException {
        File inputFile = new File(filePath);
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputFile);
        return document.selectSingleNode(node).getText();
    }

    public static String readPropertiesFile(String node) throws DocumentException {
        return readXML(pathHelpers.returnPropertiesPackagePath(), node);
    }
}
