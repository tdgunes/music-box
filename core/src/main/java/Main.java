import com.dd.plist.PropertyListFormatException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        MusicOntology musicOntology = new MusicOntology();
	    QueryEngine queryEngine = new QueryEngine("#AC/DC", musicOntology, "myACDCList");
        HashMap<String, ArrayList<String>> results = queryEngine.getResults();
        for (String key : results.keySet()) {
            System.out.println(key);
            for (String result: results.get(key)) {
                System.out.println("-"+result);
            }
            System.out.println();
        }
        musicOntology.writeToFile();
    }
}
