package examples;

import com.dd.plist.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

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
public class XMLExample {
    public static final String xmlFilePath = "/Users/tdgunes/Music/iTunes/iTunes Music Library.xml";
    private static Throwable ex;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, PropertyListFormatException, ParseException {
        //File xmlFile = new File(xmlFilePath);

        File file = new File(xmlFilePath);
        NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(file);
        for (String s : rootDict.keySet()) {
            System.out.print(s + ": ");
            System.out.println(rootDict.get(s));
        }

        NSDictionary tracks = (NSDictionary) rootDict.get("Tracks");
        for (String s : tracks.keySet()) {
            System.out.print(" ");
            System.out.print(s + ": "); // id of the track
            System.out.println(tracks.get(s));
            NSDictionary trackInfo = (NSDictionary) tracks.get(s);
            for (String info : trackInfo.keySet()) {
                System.out.print("  ");
                System.out.print(info + ": ");
                System.out.println(trackInfo.get(info));

            }
            break;
        }




    }
}
