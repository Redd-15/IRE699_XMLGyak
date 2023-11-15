package domIRE6991115;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomQueryIRE699 {

    public static void main(String[] args) {

        try {
            File xml = new File("IRE699_1115/DomQueryIRE699/resources/orarendIRE699.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(xml);
            doc.getDocumentElement().normalize();




            DomQuery reader = new DomQuery();
            reader.printCourses(doc);
            System.out.println("\n");
            reader.printDocumentFirstInstance(doc);
            System.out.println("\n");
            reader.printTeachers(doc);
            System.out.println("\n");
            reader.printLessonsTeacher(doc, "Szoftvertesztelés");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DomQuery {

    private int indent = 0;

    public void printCourses(Document doc){

        Element root = doc.getDocumentElement();
        NodeList lessons = root.getElementsByTagName("ora");
        List<String> courses = new ArrayList<String>();

        for (int i = 0; i < lessons.getLength(); i++){

            Element lesson = (Element)lessons.item(i);
            Element targy = (Element)lesson.getElementsByTagName("targy").item(0);
            courses.add(targy.getTextContent());

        }

        System.out.print("Tárgyak: [");
        for (int i = 0; i < courses.size()-1; i++) {

            System.out.print(courses.get(i) + ", ");
        }
        System.out.print(courses.get(courses.size()-1) + " ]");

    }

    public void printDocumentFirstInstance(Document doc){

        Element root = doc.getDocumentElement();

        NodeList lessons = root.getElementsByTagName("ora");

        int i = 1;

        Element lesson = (Element)lessons.item(i);


        printWithIndent("\nOra" + formatAttributes(lesson.getAttributes()));
        indent++;

        Element targy = (Element)lesson.getElementsByTagName("targy").item(0);
        Element idopont = (Element)lesson.getElementsByTagName("idopont").item(0);
        Element nap = (Element)idopont.getElementsByTagName("nap").item(0);
        Element tol = (Element)idopont.getElementsByTagName("tol").item(0);
        Element ig = (Element)idopont.getElementsByTagName("ig").item(0);
        Element hely = (Element)lesson.getElementsByTagName("helyszin").item(0);
        Element okt = (Element)lesson.getElementsByTagName("oktato").item(0);
        Element szak = (Element)lesson.getElementsByTagName("szak").item(0);

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

        printWithIndent("Szak");
        indent++;
        printWithIndent(szak.getTextContent());
        indent--;
        printWithIndent("Szak");

        indent--;
        printWithIndent("Ora");

    }

    public void printTeachers(Document doc){

        Element root = doc.getDocumentElement();
        NodeList lessons = root.getElementsByTagName("ora");
        List<String> teachers = new ArrayList<String>();

        for (int i = 0; i < lessons.getLength(); i++){

            Element lesson = (Element)lessons.item(i);
            Element teacher = (Element)lesson.getElementsByTagName("oktato").item(0);
            teachers.add(teacher.getTextContent());

        }

        System.out.print("Oktatók: [");
        for (int i = 0; i < teachers.size()-1; i++) {

            System.out.print(teachers.get(i) + ", ");
        }
        System.out.print(teachers.get(teachers.size()-1) + "]");

    }

    public void printLessonsTeacher(Document doc, String lessonName){

        Element root = doc.getDocumentElement();
        NodeList lessons = root.getElementsByTagName("ora");
        List<String> courses = new ArrayList<String>();
        List<String> teachers = new ArrayList<String>();

        for (int i = 0; i < lessons.getLength(); i++){

            Element lesson = (Element)lessons.item(i);
            Element course = (Element)lesson.getElementsByTagName("targy").item(0);
            Element teacher = (Element)lesson.getElementsByTagName("oktato").item(0);
            courses.add(course.getTextContent());
            teachers.add(teacher.getTextContent());
        }

        List<String> output = new ArrayList<String>();

        for (int i = 0; i < courses.size(); i++) {

            if (courses.get(i).equals(lessonName)) {
                output.add(teachers.get(i));
            }
        }

        System.out.print("A " + lessonName + " óra oktatói: [");

        for (int i = 0; i < output.size()-1; i++) {

            System.out.print(output.get(i) + ", ");
        }
        if (output.size() != 0) {
            System.out.print(output.get(output.size() - 1));
        }
        System.out.println("]");


    }

    private String formatAttributes(NamedNodeMap attributes){
        int attrLength = attributes.getLength();
        if (attrLength == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder(": {");
        for (int i = 0; i < attrLength; i++){
            sb.append(attributes.item(i).getNodeName() + "=" + attributes.item(i).getNodeValue());
            if (i < attrLength - 1){
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private void printWithIndent(String in){
        indent();
        System.out.println(in);
    }

    private void indent(){
        for (int i = 0; i < indent; i++){
            System.out.print(" ");
        }
    }
}

