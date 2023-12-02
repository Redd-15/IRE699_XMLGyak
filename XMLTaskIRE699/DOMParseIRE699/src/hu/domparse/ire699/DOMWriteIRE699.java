package hu.domparse.ire699;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

public class DOMWriteIRE699 {

    public static void main(String[] args) {
        try {
            File xml = new File("XMLIRE699.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(xml);
            doc.getDocumentElement().normalize();

            DomRead reader = new DomRead();
            reader.printDocument(doc);

            DomWrite writer = new DomWrite();
            writer.writeToFile(doc, "XMLIRE6991.xml");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DomWrite {

    public void writeToFile(Document doc, String file) {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            FileOutputStream output = new FileOutputStream(file);
            StreamResult result = new StreamResult(output);

            transformer.transform(source, result);

            System.out.println("\nFile written!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
