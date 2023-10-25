package hu.saxIRE699;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XsdIRE699 {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("resources/IRE699_kurzusfelvetel.xsd"));
            factory.setSchema(schema);
            Validator validator = schema.newValidator();

            SAXParser parser = factory.newSAXParser();
            //SAXEventHandler handler = new SAXEventHandler();

            //parser.parse(new File("resources/IRE699_kurzusfelvetel.xml"), handler);
            parser.parse(new File("resources/IRE699_kurzusfelvetel.xml"), new DefaultHandler());
            try {
                validator.validate(new StreamSource(new File("resources/IRE699_kurzusfelvetel.xml")));
                System.out.println("\nXSD Validation succesful!");
            }catch (SAXException e){
                System.out.println("\nXSD Validation unsuccesful!");
            }





        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}