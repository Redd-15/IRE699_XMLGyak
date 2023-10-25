package hu.saxIRE699;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.events.StartDocument;

public class SAXEventHandler extends DefaultHandler {
    private int indent = 0;

    private String formatAttributes(Attributes attributes){
        int attrLength = attributes.getLength();
        if (attrLength == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder(", {");
        for (int i = 0; i < attrLength; i++){
            sb.append(attributes.getLocalName(i) + "=" + attributes.getValue(i));
            if (i < attrLength - 1){
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private void indent(){
        for (int i = 0; i < indent; i++){
            System.out.print(" ");
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        indent();
        System.out.print(qName);
        System.out.print(formatAttributes(attributes));
        System.out.print(" start");
        System.out.println();
        indent ++;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        indent --;
        indent();
        System.out.print(qName);
        System.out.print(" end");
        System.out.println();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        int countEmpty = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = start; i < (length+start); i++) {
            if (ch[i] == ' ' | ch[i] == '\n'){
                countEmpty ++;
            }
            sb.append(ch[i]);
        }

        if (countEmpty != length){
            indent();
            System.out.println(sb);
        }

    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }
}



