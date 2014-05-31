import com.dd.plist.PropertyListFormatException;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        MusicOntology musicOntology = new MusicOntology();
	    QueryEngine queryEngine = new QueryEngine("AC/DC", "playedBy", musicOntology);
        PlaylistWriter playlistWriter = new PlaylistWriter("myACDCList.m3u");
        for (Track track : queryEngine.getResults()) {
            playlistWriter.addSong(track.getTitle(),track.getPath(), track.getSeconds());
        };
        playlistWriter.write();
        musicOntology.writeToFile();
    }
}
