package domIRE6991108;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DomWriteIRE699 {

    public static void main(String[] args) {

        try {
            File xml = new File("resources/orarendIRE699.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(xml);
            doc.getDocumentElement().normalize();

            DomRead reader = new DomRead();
            reader.printDocument(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            FileOutputStream output = new FileOutputStream("resources/orarend1IRE699.xml");
            StreamResult result = new StreamResult(output);

            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}