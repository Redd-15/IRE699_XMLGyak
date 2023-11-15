package domIRE6991115;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

public class DomModifyIRE699 {

    public static void main(String[] args) {

        try {
            File xml = new File("IRE699_1115/DomQueryIRE699/resources/orarendIRE699.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(xml);
            doc.getDocumentElement().normalize();

            DomMod mod = new DomMod();

            mod.printDocument(doc);
            System.out.println("-------------------------------------------------------------------------------------------");
            mod.modifDocument(doc);
            mod.printDocument(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            FileOutputStream output = new FileOutputStream("IRE699_1115/DomQueryIRE699/resources/orarend_I_IRE699.xml");
            StreamResult result = new StreamResult(output);

            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class DomMod {

    private int indent = 0;

    public void printDocument(Document doc) {

        Element root = doc.getDocumentElement();

        System.out.println("\n" + root.getNodeName());
        indent++;
        NodeList lessons = root.getElementsByTagName("ora");

        for (int i = 0; i < lessons.getLength(); i++) {

            Element lesson = (Element) lessons.item(i);

            printWithIndent("Ora" + formatAttributes(lesson.getAttributes()));
            indent++;

            Element targy = (Element) lesson.getElementsByTagName("targy").item(0);
            Element idopont = (Element) lesson.getElementsByTagName("idopont").item(0);
            Element nap = (Element) idopont.getElementsByTagName("nap").item(0);
            Element tol = (Element) idopont.getElementsByTagName("tol").item(0);
            Element ig = (Element) idopont.getElementsByTagName("ig").item(0);
            Element hely = (Element) lesson.getElementsByTagName("helyszin").item(0);
            Element okt = (Element) lesson.getElementsByTagName("oktato").item(0);
            Element szak = (Element) lesson.getElementsByTagName("szak").item(0);
            Element oraado = (Element) lesson.getElementsByTagName("oraado").item(0);

            printWithIndent("Targy");
            indent++;
            printWithIndent(targy.getTextContent());
            indent--;
            printWithIndent("Targy");

            printWithIndent("Idopont");
            indent++;

            printWithIndent("nap");
            indent++;
            printWithIndent(nap.getTextContent());
            indent--;
            printWithIndent("nap");

            printWithIndent("tól");
            indent++;
            printWithIndent(tol.getTextContent());
            indent--;
            printWithIndent("tól");

            printWithIndent("ig");
            indent++;
            printWithIndent(ig.getTextContent());
            indent--;
            printWithIndent("ig");

            indent--;
            printWithIndent("Idopont");

            printWithIndent("Helyszín");
            indent++;
            printWithIndent(hely.getTextContent());
            indent--;
            printWithIndent("Helyszín");

            printWithIndent("Oktató");
            indent++;
            printWithIndent(okt.getTextContent());
            indent--;
            printWithIndent("Oktató");

            if (oraado != null){

            printWithIndent("Óraadó");
            indent++;
            printWithIndent(oraado.getTextContent());
            indent--;
            printWithIndent("Óraadó");

            }

            printWithIndent("Szak");
            indent++;
            printWithIndent(szak.getTextContent());
            indent--;
            printWithIndent("Szak");

            indent--;
            printWithIndent("Ora");
        }

        System.out.println(root.getNodeName());

    }

    public void modifDocument(Document doc) {

        Element root = doc.getDocumentElement();

        NodeList lessons = root.getElementsByTagName("ora");

        for (int i = 0; i < lessons.getLength(); i++) {

            Element lesson = (Element) lessons.item(i);

            Element targy = (Element) lesson.getElementsByTagName("targy").item(0);
            Element idopont = (Element) lesson.getElementsByTagName("idopont").item(0);
            Element tol = (Element) idopont.getElementsByTagName("tol").item(0);
            Element ig = (Element) idopont.getElementsByTagName("ig").item(0);
            Element okt = (Element) lesson.getElementsByTagName("oktato").item(0);

            if (targy.getTextContent().equals("Szoftvertesztelés") | targy.getTextContent().equals("Web technológiák 1") ){

                Element child = doc.createElement("oraado");
                child.setTextContent("Óraadó_neve");
                lesson.appendChild(child);
            }

            if (lesson.getAttributes().item(1).getNodeValue().equals("angol")){
                lesson.getAttributes().item(1).setNodeValue("német");
            }

            if (targy.getTextContent().equals("Mesterséges intelligencia") & okt.getTextContent().equals("Fazekas Levente")){
                ig.setTextContent(tol.getTextContent());
            }
            
        }

    }

    private String formatAttributes(NamedNodeMap attributes) {
        int attrLength = attributes.getLength();
        if (attrLength == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(": {");
        for (int i = 0; i < attrLength; i++) {
            sb.append(attributes.item(i).getNodeName() + "=" + attributes.item(i).getNodeValue());
            if (i < attrLength - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private void printWithIndent(String in) {
        indent();
        System.out.println(in);
    }

    private void indent() {
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
    }
}