package IRE699;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class JSONWrite {

    public static void main(String[] args) {

        JSONParser JSONparser = new JSONParser();

        try (Reader reader = new FileReader("JSONParseIRE699/resources/orarendIRE699.json")) {

            JSONObject JSONObject = new JSONObject();

            JSONArray oraArray = new JSONArray();
            oraArray.add(createOra(new String[]{"Szoftvertesztelés", "Dr. Hornyák Olivér", "Mérnökinformatikus","hétfő","10","12","Inf 103"}));
            oraArray.add(createOra(new String[]{"Szoftvertesztelés", "Dr. Hornyák Olivér", "Mérnökinformatikus","hétfő","12","14","Inf 103"}));
            oraArray.add(createOra(new String[]{"Web technológiák 1", "Agárdi Anita", "Mérnökinformatikus","hétfő","14","16","XXX. előadó"}));
            oraArray.add(createOra(new String[]{"Web technológiák 1", "Agárdi Anita", "Mérnökinformatikus","hétfő","16","18","Inf 202"}));
            oraArray.add(createOra(new String[]{"Mesterséges intelligencia", "Kunné Dr. Tamás Judit", "Mérnökinformatikus","kedd","10","12","XXXII. előadó"}));
            oraArray.add(createOra(new String[]{"Adatkezelés XML-ben", "Dr. Bednarik László", "Mérnökinformatikus","kedd","12","14","XXXII. előadó"}));
            oraArray.add(createOra(new String[]{"Webes alkalmazások (Java)", "Selmeci Viktor", "Mérnökinformatikus","kedd","14","16","online"}));
            oraArray.add(createOra(new String[]{"Webes alkalmazások (Java)", "Selmeci Viktor", "Mérnökinformatikus","kedd","16","18","online"}));
            oraArray.add(createOra(new String[]{"Adatkezelés XML-ben", "Dr. Bednarik László", "Mérnökinformatikus","szerda","10","12","Inf 101"}));
            oraArray.add(createOra(new String[]{"Mesterséges intelligencia", "Fazekas Levente", "Mérnökinformatikus","kedd","10","12","I. előadó"}));

            for (int i = 0; i < oraArray.size() ;i++) {
                JSONObject localObject = (JSONObject) oraArray.get(i);
                System.out.println("\nÓra");
                System.out.println("  Targy: " + localObject.get("targy"));
                System.out.println("  Oktató: " + localObject.get("oktato"));
                System.out.println("  Szak: " + localObject.get("szak"));
                System.out.println("  Időpont: ");

                JSONObject time = (JSONObject) localObject.get("idopont");
                System.out.println("    Nap: " + time.get("nap"));
                System.out.println("    Tól: " + time.get("tol"));
                System.out.println("    Ig: " + time.get("ig"));

                System.out.println("  Helyszín: " + localObject.get("helyszin"));

            }

            JSONObject oraObject = new JSONObject();
            oraObject.put("ora", oraArray);
            JSONObject.put("IRE699_orarend", oraObject);

            FileWriter file = new FileWriter("JSONParseIRE699/resources/orarendIRE6991.json");
            file.write(JSONObject.toString());
            file.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static JSONObject createOra(String[] data){//Targy,Oktato,Szak,Nap,Tol,Ig,Helyszin

        JSONObject localObject = new JSONObject();

        localObject.put("targy", data[0]);
        localObject.put("oktato", data[1]);
        localObject.put("szak", data[2]);

        JSONObject timeObject = new JSONObject();
        timeObject.put("nap",data[3]);
        timeObject.put("tol",data[4]);
        timeObject.put("ig",data[5]);
        localObject.put("idopont",timeObject);

        localObject.put("helyszin", data[6]);

        return localObject;
    }
}
