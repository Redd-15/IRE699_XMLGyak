package hu.domparse.ire699;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class DOMModifyIRE699 {

    public static void main(String[] args) {
        try {
            File xml = new File("XMLIRE699.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(xml);
            doc.getDocumentElement().normalize();

            DomRead reader = new DomRead();

            System.out.println("\n---------------------------------------------------Original document:---------------------------------------------------");
            reader.printDocument(doc);

            System.out.println("\n-----------------------------------------------------Modifications:-----------------------------------------------------");            DomModifyDoc(doc);

            System.out.println("---------------------------------------------------Modified document:---------------------------------------------------");
            reader.printDocument(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void DomModifyDoc(Document doc) {

        Element root = doc.getDocumentElement();

        //Get the nodeLists from the root element
        NodeList soforok = root.getElementsByTagName("Sofor");
        NodeList buszok = root.getElementsByTagName("Busz");
        NodeList jaratok = root.getElementsByTagName("Jarat");
        NodeList megallok = root.getElementsByTagName("Megallo");
        NodeList helyszinek = root.getElementsByTagName("Helyszin");

        for (int i = 0; i < soforok.getLength(); i++){

            Element element = (Element)soforok.item(i);

            Element nev = (Element)element.getElementsByTagName("nev").item(0);

            if(nev.getTextContent().equals("Nagy József")){
                nev.setTextContent("Nagy Sándor");
                break;
            }
        }
        System.out.println("Modified: Nagy József --> Nagy Sándor");

        for (int i = 0; i < buszok.getLength(); i++){

            Element element = (Element)buszok.item(i);

            if(element.getAttribute("bkod").equals("1")){

                Element uzemanyag_szint = (Element)element.getElementsByTagName("uzemanyag_szint").item(0);
                uzemanyag_szint.setTextContent(String.valueOf(Integer.parseInt(uzemanyag_szint.getTextContent())*0.9).split("\\.")[0]);
                break;
            }
        }
        System.out.println("Modified: Busz (id=1) reduced fuel by 10%");

        for (int i = 0; i < jaratok.getLength(); i++){

            Element element = (Element)jaratok.item(i);

            if(element.getAttribute("jkod").equals("1")){

                Element indulasi_ido = (Element)element.getElementsByTagName("indulasi_ido").item(0);
                indulasi_ido.setTextContent("10:25");
                break;
            }
        }
        System.out.println("Modified: Járat (id=1) delayed to 10:25");

        for (int i = 0; i < megallok.getLength(); i++){

            Element element = (Element)megallok.item(i);

            if(element.getAttribute("mkod").equals("6")){

                Element fedett = (Element)element.getElementsByTagName("fedett").item(0);
                fedett.setTextContent("I");
                break;
            }
        }
        System.out.println("Modified: In megallo (id=6) built roof --> fedett=I ");

        System.out.println("Modified: Járat (id=1) delayed to 10:25");

        for (int i = 0; i < helyszinek.getLength(); i++){

            Element element = (Element)helyszinek.item(i);

            if(element.getAttribute("hkod").equals("3")){

                Element hazszam = (Element)element.getElementsByTagName("hazszam").item(0);
                hazszam.setTextContent("35");
                break;
            }
        }
        System.out.println("Modified: Helyszin (id=3) misadministrated: address number 34 --> 35 ");
    }
}
