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
import java.util.ArrayList;

public class DOMReadIRE699 {

    public static void main(String[] args) {

        try {
            File xml = new File("XMLIRE699.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(xml);
            doc.getDocumentElement().normalize();

            DomRead reader = new DomRead();
            reader.printDocument(doc);
            reader.writeToFile(doc, "XMLIRE699S.xml");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DomRead {

    private int indent = 0;

    public void printDocument(Document doc){

        //Getting the root element from the document
        Element root = doc.getDocumentElement();

        //Get the nodeLists from the root element
        NodeList soforok = root.getElementsByTagName("Sofor");
        NodeList vezeti = root.getElementsByTagName("Vezeti");
        NodeList buszok = root.getElementsByTagName("Busz");
        NodeList jaratok = root.getElementsByTagName("Jarat");
        NodeList utvonalak = root.getElementsByTagName("Utvonal");
        NodeList megallasok = root.getElementsByTagName("Megallas");
        NodeList megallok = root.getElementsByTagName("Megallo");
        NodeList helyszinek = root.getElementsByTagName("Helyszin");

        //Print root node Start
        System.out.println("\n<" + root.getNodeName() + ">" );
        indent++;

        //Print Sofor nodes
        System.out.println();
        for (int i = 0; i < soforok.getLength(); i++){

            Element element = (Element)soforok.item(i);

            Element szul_ev = (Element)element.getElementsByTagName("szul_ev").item(0);
            ArrayList<Element> tel_szamok = new ArrayList<>();
            for (int x = 0; x < element.getElementsByTagName("tel_szam").getLength() ;x++ ) {
                tel_szamok.add( (Element) element.getElementsByTagName("tel_szam").item(x) );
            }
            Element nev = (Element)element.getElementsByTagName("nev").item(0);

            printElementStartWithAttributes(element);
            indent++;

            printWithIndent("<szul_ev>" + szul_ev.getTextContent() + "</szul_ev>");
            for (Element tel_szam:tel_szamok) {
                printWithIndent("<tel_szam>" + tel_szam.getTextContent() + "</tel_szam>");
            }
            printWithIndent("<nev>" + nev.getTextContent() + "</nev>");

            indent--;
            printElementEndWithAttributes(element);


        }

        //Print vezeti nodes
        System.out.println();
        for (int i = 0; i < vezeti.getLength(); i++){

            printElementOneLinerWithAttributes((Element)vezeti.item(i));
        }

        //Print Busz nodes
        System.out.println();
        for (int i = 0; i < buszok.getLength(); i++){

            Element element = (Element)buszok.item(i);

            Element tipus = (Element)element.getElementsByTagName("tipus").item(0);
                Element marka = (Element)tipus.getElementsByTagName("marka").item(0);
                Element uzemanyag = (Element)tipus.getElementsByTagName("uzemanyag").item(0);
            Element ferohely = (Element)element.getElementsByTagName("ferohely").item(0);
            Element rendszam = (Element)element.getElementsByTagName("rendszam").item(0);
            Element uzemanyag_szint = (Element)element.getElementsByTagName("uzemanyag_szint").item(0);
            Element fogyasztas = (Element)element.getElementsByTagName("fogyasztas").item(0);

            printElementStartWithAttributes(element);
            indent++;

            printWithIndent("<tipus>");
            indent++;
            printWithIndent("<marka>" + marka.getTextContent() + "</marka>");
            printWithIndent("<uzemanyag>" + uzemanyag.getTextContent() + "</uzemanyag>");
            indent--;
            printWithIndent("</tipus>");

            printWithIndent("<ferohely>" + ferohely.getTextContent() + "</ferohely>");
            printWithIndent("<rendszam>" + rendszam.getTextContent() + "</rendszam>");
            printWithIndent("<uzemanyag_szint>" + uzemanyag_szint.getTextContent() + "</uzemanyag_szint>");
            printWithIndent("<fogyasztas>" + fogyasztas.getTextContent() + "</fogyasztas>");

            indent--;
            printElementEndWithAttributes(element);
        }

        //Print Jarat nodes
        System.out.println();
        for (int i = 0; i < jaratok.getLength(); i++){

            Element element = (Element) jaratok.item(i);

            Element indulasi_ido = (Element)element.getElementsByTagName("indulasi_ido").item(0);

            printElementStartWithAttributes(element);
            indent++;

            printWithIndent("<indulasi_ido>" + indulasi_ido.getTextContent() + "</indulasi_ido>");

            indent--;
            printElementEndWithAttributes(element);
        }

        //Print Utvonal nodes
        System.out.println();
        for (int i = 0; i < utvonalak.getLength(); i++){

            Element element = (Element) utvonalak.item(i);

            Element utvonal_nev = (Element)element.getElementsByTagName("utvonal_nev").item(0);

            printElementStartWithAttributes(element);
            indent++;

            printWithIndent("<utvonal_nev>" + utvonal_nev.getTextContent() + "</utvonal_nev>");

            indent--;
            printElementEndWithAttributes(element);
        }

        //Print Megallas nodes
        System.out.println();
        for (int i = 0; i < megallasok.getLength(); i++){

            Element element = (Element) megallasok.item(i);

            Element tav_elo = (Element)element.getElementsByTagName("tav_elo").item(0);
            Element sorszam = (Element)element.getElementsByTagName("sorszam").item(0);

            printElementStartWithAttributes(element);
            indent++;

            printWithIndent("<tav_elo>" + tav_elo.getTextContent() + "</tav_elo>");
            printWithIndent("<sorszam>" + sorszam.getTextContent() + "</sorszam>");

            indent--;
            printElementEndWithAttributes(element);
        }

        //Print Megallo nodes
        System.out.println();
        for (int i = 0; i < megallok.getLength(); i++){

            Element element = (Element) megallok.item(i);

            Element fedett = (Element)element.getElementsByTagName("fedett").item(0);
            Element m_ferohely = (Element)element.getElementsByTagName("m_ferohely").item(0);

            printElementStartWithAttributes(element);
            indent++;

            printWithIndent("<fedett>" + fedett.getTextContent() + "</fedett>");
            printWithIndent("<m_ferohely>" + m_ferohely.getTextContent() + "</m_ferohely>");

            indent--;
            printElementEndWithAttributes(element);
        }

        //Print Helzsyinek nodes
        System.out.println();
        for (int i = 0; i < helyszinek.getLength(); i++){

            Element element = (Element) helyszinek.item(i);

            Element varos = (Element)element.getElementsByTagName("varos").item(0);
            Element utca = (Element)element.getElementsByTagName("utca").item(0);
            Element hazszam = (Element)element.getElementsByTagName("hazszam").item(0);

            printElementStartWithAttributes(element);
            indent++;

            printWithIndent("<varos>" + varos.getTextContent() + "</varos>");
            printWithIndent("<utca>" + utca.getTextContent() + "</utca>");
            printWithIndent("<hazszam>" + hazszam.getTextContent() + "</hazszam>");

            indent--;
            printElementEndWithAttributes(element);
        }

        //Print root node End
        System.out.println("\n<" + root.getNodeName() + ">" );

    }

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

    private void printElementStartWithAttributes(Element element){
        printWithIndent("<" + element.getNodeName() + formatAttributes(element.getAttributes()));
    }

    private void printElementEndWithAttributes(Element element){
        printWithIndent("<" + element.getNodeName() + "/>");
    }

    private void printElementOneLinerWithAttributes(Element element){
        printWithIndent("<" + element.getNodeName() + formatAttributes(element.getAttributes()) + "/>");
    }

    //Formatting the Attributes
    private String formatAttributes(NamedNodeMap attributes){
        int attrLength = attributes.getLength();
        if (attrLength == 0){
            return ">";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attrLength; i++){
            sb.append(" ");
            sb.append(attributes.item(i).getNodeName()).append("=").append(attributes.item(i).getNodeValue());
        }
        sb.append(">");
        return sb.toString();
    }

    //Print text with indentation
    private void printWithIndent(String in){
        indent();
        System.out.println(in);
    }

    //Printing the indentation
    private void indent(){
        for (int i = 0; i < indent; i++){
            System.out.print("   ");
        }
    }
}


