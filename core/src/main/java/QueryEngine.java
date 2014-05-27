import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import java.util.ArrayList;

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
public class QueryEngine {

    private String query;
    private MusicOntology musicOntology;

    public QueryEngine(String query, MusicOntology musicOntology) {
        this.query = query;
        this.musicOntology = musicOntology;

    }

    public ArrayList<Track> getResults(){
        ArrayList<Track> results = new ArrayList<Track>();
        String[] queryTokens = query.split("\\s+");


        System.out.println("------------------");
        for (String queryToken : queryTokens) {
            //validate


                String queryString = "" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX : <" + musicOntology.ns + ">\n" +
                        "SELECT DISTINCT ?track WHERE {" +
                        "?track rdf:type :Track.\n" +
                        "?artist rdf:type :Artist.\n" +
                        "?track :playedBy ?artist.\n" +
                        "?artist :hasName \""+queryToken+"\"." +
                        "}";
                System.out.println(queryString);
                System.out.println("------------------");

                Dataset dataset = DatasetFactory.create(musicOntology.ontModel);
                Query query = QueryFactory.create(queryString);

                QueryExecution qexec = SparqlDLExecutionFactory.create(query, dataset);
                ResultSet resultSet = qexec.execSelect();


                while (resultSet.hasNext()){
                    QuerySolution row = resultSet.next();

                    RDFNode track = row.get("track");

                    System.out.println("-> "+track.asResource().getLocalName());


                    ExtendedIterator iterator = track.asResource().listProperties();

                    Track song = new Track(track.asResource().getLocalName());
                    while(iterator.hasNext()){
                        StatementImpl mp = (StatementImpl) iterator.next();

                        String relation = mp.getPredicate().getLocalName();
                        String destination;

                        System.out.print(":" + relation + " ");


                        RDFNode object = mp.getObject();

                        if (object instanceof Literal) {
                            // object is a literal
                            Literal o = (Literal) object;
                            destination = o.getString();


                        } else {

                            destination = object.toString();

                        }
                        System.out.print(" \"" + destination + "\"");
                        System.out.println();

                        if (relation.equalsIgnoreCase("hasLocation")){
                            song.setPath(destination);
                        }
                        else if (relation.equalsIgnoreCase("hasTotalTime")){
                            song.setSeconds(destination);
                        }

                    }
                    results.add(song);
                }

            }






        return results;
    }
}
