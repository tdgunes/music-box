import com.dd.plist.PropertyListFormatException;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.vocabulary.XSD;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

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

public class MusicOntology {
    public static final String baseURI = "http://www.github.com/tdgunes/music-bot/";
    public String ns;

    public OntModel ontModel;
    public Ontology onto;

    public OntClass track;

    public DatatypeProperty hasID;
    public DatatypeProperty hasName;
    public DatatypeProperty releasedYear;
    public DatatypeProperty hasTotalTime;
    public DatatypeProperty hasBitRate;
    public DatatypeProperty playCount;
    public DatatypeProperty playDateUTC;
    public DatatypeProperty dateAdded;
    public DatatypeProperty hasLocation;



    public OntClass genre;
    public ObjectProperty hasGenre;

    public OntClass album;
    public ObjectProperty onAlbum;

    public OntClass artist;
    //TODO expand artist class from dbpedia

    public ObjectProperty playedBy;


    public MusicOntology() throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {

        ns = baseURI + "#";

        //    Reasoner reasoner = PelletReasonerFactory.theInstance().create();
//        OntModelSpec ontModelSpec = OntModelSpec.OWL_LITE_MEM;

//        ontModelSpec.setReasoner(reasoner);
        ontModel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);

        onto = ontModel.createOntology(baseURI);

        track = ontModel.createClass(ns + "Track");
        genre = ontModel.createClass(ns + "Genre");
        artist = ontModel.createClass(ns + "Artist");
        album = ontModel.createClass(ns + "Album");


        onAlbum = ontModel.createObjectProperty(ns + "onAlbum");
        onAlbum.addDomain(track);
        onAlbum.addRange(album);


        hasGenre = ontModel.createObjectProperty(ns + "hasGenre");
        hasGenre.addDomain(track);
        hasGenre.addRange(genre);


        playedBy = ontModel.createObjectProperty(ns + "playedBy");
        playedBy.addDomain(track);
        playedBy.addRange(artist);



        hasID = ontModel.createDatatypeProperty(ns + "hasID");
        hasID.addRange(XSD.integer);

        hasName = ontModel.createDatatypeProperty(ns + "hasName");
        hasName.addRange(XSD.xstring);

        releasedYear = ontModel.createDatatypeProperty(ns + "releasedYear");
        releasedYear.addRange(XSD.gYear);

        hasTotalTime = ontModel.createDatatypeProperty(ns + "hasTotalTime");
        hasTotalTime.addRange(XSD.xlong);

        hasBitRate = ontModel.createDatatypeProperty(ns + "hasBitRate");
        hasBitRate.addRange(XSD.integer);

        playCount = ontModel.createDatatypeProperty(ns + "playCount");
        playCount.addRange(XSD.integer);

        playDateUTC = ontModel.createDatatypeProperty(ns + "playDateUTC");
        playDateUTC.addRange(XSD.dateTime);

        dateAdded = ontModel.createDatatypeProperty(ns + "dateAdded");
        dateAdded.addRange(XSD.dateTime);

        hasLocation= ontModel.createDatatypeProperty(ns + "hasLocation");
        hasLocation.addRange(XSD.xstring);



        addLibraryToOntology();
    }

    public void writeToFile() throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        FileWriter out = new FileWriter("model.xml");
        ontModel.write(out);
        System.out.println("Done");
    }

    private void addLibraryToOntology() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        XMLReader xmlReader = new XMLReader();
        ArrayList<HashMap> tracks = xmlReader.getTrackList();

        int count = 0;
        for (HashMap hashMap : tracks) {
            try {
                String rawTrack = Utils.cleanStringForOnto(hashMap.get("Name").toString());
                String rawArtist = Utils.cleanStringForOnto(hashMap.get("Artist").toString());
                String rawTrackID = hashMap.get("Track ID").toString();
                String rawLocation = hashMap.get("Location").toString();
                String rawTotalTime = hashMap.get("Total Time").toString();
                String rawGenre = Utils.cleanStringForOnto(hashMap.get("Genre").toString());
                String rawAlbum = Utils.cleanStringForOnto(hashMap.get("Album").toString());

                System.out.println("Track: " + rawTrack);
                System.out.println("Artist: " + rawArtist);
                System.out.println("Track ID: " + rawTrackID);
                System.out.println("Location: " + rawLocation);
                System.out.println("Total time: "+ rawTotalTime);
                System.out.println("Genre: "+ rawGenre);
                System.out.println();

                //preparing trackIndividual
                Individual trackIndividual = track.createIndividual(ns + rawTrack);
                Individual artistIndividual = artist.createIndividual(ns + rawArtist);
                Individual albumIndividual = album.createIndividual(ns + rawAlbum);
                Individual genreIndividual = genre.createIndividual(ns + rawGenre);


                //trackIndividual :hasName "songName"
                Literal trackName = ontModel.createTypedLiteral(rawTrack, XSDDatatype.XSDstring);
                Statement trackHasName = ontModel.createStatement(trackIndividual, hasName, trackName);
                ontModel.add(trackHasName);


                //artistIndividual :hasName "artistName"
                Literal artistName = ontModel.createTypedLiteral(rawArtist, XSDDatatype.XSDstring);
                Statement artistHasName = ontModel.createStatement(artistIndividual, hasName, artistName);
                ontModel.add(artistHasName);


                //genreIndividual :hasName "genreName"
                Literal genreName = ontModel.createTypedLiteral(rawGenre, XSDDatatype.XSDstring);
                Statement genreHasName = ontModel.createStatement(genreIndividual, hasName, genreName);
                ontModel.add(genreHasName);


                //trackIndividual :playedBy artistIndividual
                ontModel.add(ontModel.createStatement(trackIndividual, playedBy, artistIndividual));

                //trackIndividual :onAlbum albumIndividual
                ontModel.add(ontModel.createStatement(trackIndividual, onAlbum, albumIndividual));

                //trackIndividiual :hasGenre genreIndividual
                ontModel.add(ontModel.createStatement(trackIndividual, hasGenre, genreIndividual));

                //trackIndividual :hasID "Track ID"
                Literal trackID = ontModel.createTypedLiteral(rawTrackID, XSDDatatype.XSDpositiveInteger);
                Statement hasTrackID = ontModel.createStatement(trackIndividual, hasID, trackID);
                ontModel.add(hasTrackID);

                //trackIndividual :hasLocation "/somepath/path/"
                Literal location = ontModel.createTypedLiteral(rawLocation, XSDDatatype.XSDstring);
                Statement trackHasLocation = ontModel.createStatement(trackIndividual, hasLocation, location);
                ontModel.add(trackHasLocation);

                //trackIndividual :hasTotalTime 123123
                Literal totalTime = ontModel.createTypedLiteral(Long.parseLong(rawTotalTime), XSDDatatype.XSDlong);
                Statement trackHasTotalTime = ontModel.createStatement(trackIndividual, hasTotalTime, totalTime);
                ontModel.add(trackHasTotalTime);

                count++;
            } catch (NullPointerException exception) {
                continue;
            }
        }
        System.out.println("Total tracks: " + count);
    }




    public static void main(String[] args) throws IOException, ParserConfigurationException, ParseException, SAXException, PropertyListFormatException {
        MusicOntology musicOntology = new MusicOntology();

        musicOntology.writeToFile();


    }
}
