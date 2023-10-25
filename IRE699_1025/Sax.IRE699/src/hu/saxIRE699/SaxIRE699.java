package hu.saxIRE699;

import javax.xml.parsers.*;
import org.xml.sax.*;
import java.io.File;
import java.io.IOException;

public class SaxIRE699 {


    public static void main(String[] args) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //factory.setValidating(true);
            SAXParser parser = factory.newSAXParser();
            SAXEventHandler handler = new SAXEventHandler();

            parser.parse(new File("resources/IRE699_kurzusfelvetel.xml"), handler);



        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }

}
