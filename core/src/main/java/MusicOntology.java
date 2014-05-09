import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import org.mindswap.pellet.jena.PelletReasonerFactory;

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
    public static final String baseURI = "http://www.github.com/tdgunes/music-box/";
    public String ns;

    public OntModel ontModel;
    public Ontology onto;

    public OntClass track;

    public DatatypeProperty hasID;
    public DatatypeProperty hasName;
    public DatatypeProperty releaseYear;
    public DatatypeProperty hasTotalTime;
    public DatatypeProperty hasBitRate;
    public DatatypeProperty playCount;
    public DatatypeProperty playDateUTC;
    public DatatypeProperty playDateAdded;




    public OntClass genre;



    public OntClass artist;
    public ObjectProperty playedBy;

    public MusicOntology() {
        ns = baseURI + "#";

        Reasoner reasoner = PelletReasonerFactory.theInstance().create();
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;

        ontModelSpec.setReasoner(reasoner);
        ontModel = ModelFactory.createOntologyModel(ontModelSpec);

        onto = ontModel.createOntology(baseURI);

        track = ontModel.createClass(ns+"Track");
        genre = ontModel.createClass(ns+"Genre");
        artist = ontModel.createClass(ns+"Artist");

        track.addSubClass(genre);

        playedBy = ontModel.createObjectProperty(ns+"playedBy");
        playedBy.addDomain(track);
        playedBy.addRange(artist);

        



    }
}
