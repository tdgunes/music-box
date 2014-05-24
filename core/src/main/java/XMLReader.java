import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Taha Dogan Gunes
 * <tdgunes@gmail.com>
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

// Track ID    1797
//         Name    Fear Of The Dark
//         Artist    Iron Maiden
//         Album    Fear Of The Dark
//         Genre    Metal
//         Kind    MPEG audio file
//         Size    7003841
//         Total Time    436950
//         Track Number    12
//         Year    1992
//         Date Modified  <date>2013-03-19T20:02:39Z</date>
//         Date Added  <date>2012-08-19T09:34:20Z</date>
//         Bit Rate    128
//         Sample Rate    44100
//         Play Count    9
//         Play Date    3480264737
//         Play Date UTC  <date>2014-04-13T17:12:17Z</date>
//         Skip Count    1
//         Skip Date  <date>2014-04-26T12:36:41Z</date>
//         Persistent ID    8F1887443F30B481
//         Track Type    File
//         Location    file://localhost/Users/tdgunes/Music/Iron%20Maiden/Iron%20Maiden%20-%201992%20-%20Fear%20Of%20The%20Dark/12-Fear%20Of%20The%20Dark.mp3
//         File Folder Count    3
//         Library Folder Count    2

public class XMLReader {
    public static final String xmlFilePath = "/Users/tdgunes/Music/iTunes/iTunes Music Library.xml";
    private static Throwable ex;
    private ArrayList<HashMap> trackList;

    public XMLReader() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        File file = new File(xmlFilePath);
        NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(file);
//        for (String s : rootDict.keySet()) {
//            System.out.print(s + ": ");
//            System.out.println(rootDict.get(s));
//        }
        trackList = new ArrayList<HashMap>();



        NSDictionary tracks = (NSDictionary) rootDict.get("Tracks");
        int limit = 0;


        for (String s : tracks.keySet()) {
//            System.out.print(" ");
//            System.out.print(s + ": "); // id of the track
//            System.out.println(tracks.get(s));


            Random r = new Random();
            if (r.nextInt(100) == 5) {


                NSDictionary trackInfo = (NSDictionary) tracks.get(s);
                HashMap<String, NSObject> hashMap = new HashMap<String, NSObject>();
                for (String info : trackInfo.keySet()) {
                    hashMap.put(info, trackInfo.get(info));
                    // System.out.print("  ");
                    // System.out.print(info + ": ");
                    //System.out.println(trackInfo.get(info));

                }
                trackList.add(hashMap);


                if (limit == 100) break;


                limit++;
            }
        }

    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, PropertyListFormatException, ParseException {
        XMLReader xmlReader = new XMLReader();

    }

    public ArrayList<HashMap> getTrackList() {


        return trackList;

    }
}
