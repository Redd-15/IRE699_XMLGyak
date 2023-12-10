package hu.domparse.ire699;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMWriteIRE699 {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();

            DomRead reader = new DomRead();
            DomCreate creater = new DomCreate(doc);

            //Creating root element
            Element root = doc.createElement("MVK_database");
            doc.appendChild(root);

            //Creating Sofor elements
            root.appendChild(creater.createSofor(new String[]{"1", "1", "1980", "Nagy József", "+36301234566", "+36701234566"}));
            root.appendChild(creater.createSofor(new String[]{"2", "2", "1976", "Kiss János", "+36301235466", "+36401232366"}));
            root.appendChild(creater.createSofor(new String[]{"3", "3", "1999", "Adorján István", "+363012764566", "+36301267566"}));

            //Creating Vezeti elements
            root.appendChild(creater.createVezeti(new String[]{"2", "1"}));
            root.appendChild(creater.createVezeti(new String[]{"3", "2"}));
            root.appendChild(creater.createVezeti(new String[]{"1", "3"}));

            //Creating Busz elements
            root.appendChild(creater.createBusz(new String[]{"1", "3", "BYD", "Elektromos", "145", "AABC-123", "80", "30"}));
            root.appendChild(creater.createBusz(new String[]{"2", "2", "BYD", "Elektromos", "145", "AABC-133", "74", "30"}));
            root.appendChild(creater.createBusz(new String[]{"3", "1", "MAN", "Dízel", "110", "AABC-143", "20", "12"}));

            //Creating Jarat elements
            root.appendChild(creater.createJarat(new String[]{"1", "3", "10:00"}));
            root.appendChild(creater.createJarat(new String[]{"2", "2", "15:00"}));
            root.appendChild(creater.createJarat(new String[]{"3", "1", "18:20"}));

            //Creating Utvonal elements
            root.appendChild(creater.createUtvonal(new String[]{"1", "1A"}));
            root.appendChild(creater.createUtvonal(new String[]{"2", "2"}));
            root.appendChild(creater.createUtvonal(new String[]{"3", "3A"}));

            //Creating Megallas elements for 3A
            root.appendChild(creater.createMegallas(new String[]{"3", "3", "10", "1"}));
            root.appendChild(creater.createMegallas(new String[]{"1", "3", "5", "2"}));
            root.appendChild(creater.createMegallas(new String[]{"4", "3", "1", "3"}));

            //Creating Megallas elements for 2
            root.appendChild(creater.createMegallas(new String[]{"2", "2", "8", "1"}));
            root.appendChild(creater.createMegallas(new String[]{"6", "2", "25", "2"}));
            root.appendChild(creater.createMegallas(new String[]{"3", "2", "30", "3"}));

            //Creating Megallas elements for 1A
            root.appendChild(creater.createMegallas(new String[]{"5", "1", "15", "1"}));
            root.appendChild(creater.createMegallas(new String[]{"3", "1", "19", "2"}));
            root.appendChild(creater.createMegallas(new String[]{"2", "1", "8", "3"}));

            //Creating Megallo elements
            root.appendChild(creater.createMegallo(new String[]{"4", "1", "I", "10"}));
            root.appendChild(creater.createMegallo(new String[]{"9", "2", "N", "50"}));
            root.appendChild(creater.createMegallo(new String[]{"6", "3", "N", "70"}));
            root.appendChild(creater.createMegallo(new String[]{"5", "4", "I", "12"}));
            root.appendChild(creater.createMegallo(new String[]{"7", "5", "I", "55"}));
            root.appendChild(creater.createMegallo(new String[]{"8", "6", "N", "30"}));

            //Creating Helyszin elements
            root.appendChild(creater.createHelyszin(new String[]{"1", "Miskolc", "Ady Endre", "10"}));
            root.appendChild(creater.createHelyszin(new String[]{"2", "Mályi", "Fő", "17"}));
            root.appendChild(creater.createHelyszin(new String[]{"3", "Miskolc", "Széchenyi János", "34"}));
            root.appendChild(creater.createHelyszin(new String[]{"4", "Miskolc", "Balaton", "170"}));
            root.appendChild(creater.createHelyszin(new String[]{"5", "Miskolc", "Balaton", "1"}));
            root.appendChild(creater.createHelyszin(new String[]{"6", "Miskolc", "Tiszai-PU", "1"}));
            root.appendChild(creater.createHelyszin(new String[]{"7", "Miskolc", "Egyetem", "1"}));
            root.appendChild(creater.createHelyszin(new String[]{"8", "Miskolc", "Reptéri", "11"}));
            root.appendChild(creater.createHelyszin(new String[]{"9", "Miskolc", "Széchenyi János", "1"}));

            //Writing the DOM tree to console, then to file
            reader.printDocument(doc);
            reader.writeToFile(doc, "XMLIRE6991.xml");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DomCreate {

    Document doc;

    public DomCreate(Document document){
        doc = document;
    }

    //Creating Sofor Element
    public Element createSofor(String[] data){ //lakhely, skod, szulev, nev, tel_szam[]
        Element element = doc.createElement("Sofor");

        element.setAttribute("lakhely", data[0]);
        element.setAttribute("skod", data[1]);

        element.appendChild(newTextElement("szul_ev", data[2]));
        element.appendChild(newTextElement("nev", data[3]));

        for (int i = 4; i < data.length; i++){
            element.appendChild(newTextElement("tel_szam", data[i]));
        }
        return element;
    }

    //Creating Vezeti Element
    public Element createVezeti(String[] data){ //busz, sofor
        Element element = doc.createElement("Vezeti");

        element.setAttribute("busz", data[0]);
        element.setAttribute("sofor", data[1]);

        return element;
    }

    //Creating Busz Element
    public Element createBusz(String[] data){ //bkod, jarat, marka, uzemanyag, ferohely, rendszam, uzemanyagszint, fogyasztas
        Element element = doc.createElement("Busz");

        element.setAttribute("bkod", data[0]);
        element.setAttribute("jarat", data[1]);

        Element tipus = doc.createElement("tipus");
        tipus.appendChild(newTextElement("marka", data[2]));
        tipus.appendChild(newTextElement("uzemanyag", data[3]));

        element.appendChild(tipus);

        element.appendChild(newTextElement("ferohely", data[4]));
        element.appendChild(newTextElement("rendszam", data[5]));
        element.appendChild(newTextElement("uzemanyag_szint", data[6]));
        element.appendChild(newTextElement("fogyasztas", data[7]));

        return element;
    }

    //Creating Jarat Element
    public Element createJarat(String[] data){ //jkod, utvonal, indulasi_ido
        Element element = doc.createElement("Jarat");

        element.setAttribute("jkod", data[0]);
        element.setAttribute("utvonal", data[1]);

        element.appendChild(newTextElement("indulasi_ido", data[2]));

        return element;
    }

    //Creating Utvonal Element
    public Element createUtvonal(String[] data){ //utkod, utvonal_nev
        Element element = doc.createElement("Utvonal");

        element.setAttribute("utkod", data[0]);

        element.appendChild(newTextElement("utvonal_nev", data[1]));

        return element;
    }

    //Creating Megallas Element
    public Element createMegallas(String[] data){ //megallo, utvonal, tav_elo, sorszam
        Element element = doc.createElement("Megallas");

        element.setAttribute("megallo", data[0]);
        element.setAttribute("utvonal", data[1]);

        element.appendChild(newTextElement("tav_elo", data[2]));
        element.appendChild(newTextElement("sorszam", data[3]));

        return element;
    }

    //Creating Megallo Element
    public Element createMegallo(String[] data){ //helyszin, mkod, fedett, m_ferohely
        Element element = doc.createElement("Megallo");

        element.setAttribute("helyszin", data[0]);
        element.setAttribute("mkod", data[1]);

        element.appendChild(newTextElement("fedett", data[2]));
        element.appendChild(newTextElement("m_ferohely", data[3]));

        return element;
    }

    //Creating Helyszin Element
    public Element createHelyszin(String[] data){ //hkod, varos, utca, hazszam
        Element element = doc.createElement("Helyszin");

        element.setAttribute("hkod", data[0]);

        element.appendChild(newTextElement("varos", data[1]));
        element.appendChild(newTextElement("utca", data[2]));
        element.appendChild(newTextElement("hazszam", data[3]));

        return element;
    }

    //Creating text node
    private Element newTextElement(String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        return element;
    }
}
