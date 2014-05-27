import com.dd.plist.PropertyListFormatException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


class SExpr{
    public String value;
    public ArrayList<Track> tracks;
    SExpr(String value) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        this.value = value;
        tracks = new ArrayList<Track>();

    }

}

public class Language {

    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input
        // (playedBy:Metallica and playedBy:ACDC)

        // print LISP-style tree }
        String test = "(playedBy:Metallica | playedBy:AC/DC)";
        String test2 = "(playedBy:Metallica | (playedBy:Metallica & playedBy:ACDC))";

        Language l = new Language();
        MusicboxParser parser = l.parse(test);
        //l.print_AST(parser);
        SExpr sExpr = l.eval(parser.start());
        System.out.println("COUNT: "+sExpr.tracks.size());
        for (Track track : sExpr.tracks) {
            System.out.println(track);
        }


        //Language l2 = new Language();
        //MusicboxParser parser2 = l2.parse(test2);
        //l2.print_AST(parser2);
        //l.eval(parser.start());


    }

    public <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public SExpr eval (ParseTree tree) throws Exception{
        System.out.println(tree.getClass());
//        System.out.println(tree.getChild(1).getClass());
//        System.out.println(tree.getChild(2).getClass());
//        System.out.println(tree.getChild(3).getClass());

        if (tree instanceof TerminalNodeImpl){
            SExpr sexpr =  new SExpr(tree.toString());
            String[] tokens = sexpr.value.split(":");
            String action = tokens[0];
            String term = tokens[1];
            System.out.println("Searching relation: "+action );
            System.out.println("With term: "+ term);
            QueryEngine queryEngine = new QueryEngine(term,new MusicOntology());
            ArrayList<Track> tracks = queryEngine.getResults();
            System.out.println("GOT: "+tracks.size());
            sexpr.tracks = tracks;
            return sexpr;
        }
        else {
            if (tree.getChildCount() == 1){
                System.out.println("the start tag");
                return (eval(tree.getChild(0)));
            }
            if (tree.getChild(2).toString().equalsIgnoreCase("&")){
                //and operation
                SExpr left_evaled = eval(tree.getChild(1));
                SExpr right_evaled = eval(tree.getChild(3));

                SExpr sexpr = new SExpr(tree.getChild(1).toString() + " and " + tree.getChild(1).toString());
                sexpr.tracks = (ArrayList<Track>) intersection(left_evaled.tracks, right_evaled.tracks);

                return sexpr;
            }
            else if (tree.getChild(2).toString().equalsIgnoreCase("|")){
                //or operation

                SExpr left_evaled = eval(tree.getChild(1));
                SExpr right_evaled = eval(tree.getChild(3));

                SExpr sexpr = new SExpr(tree.getChild(1).toString() + " or " + tree.getChild(1).toString());
                sexpr.tracks = (ArrayList<Track>) union(left_evaled.tracks, right_evaled.tracks);

                return sexpr;

            }

        }

        return null;
    }

    public void print_AST (MusicboxParser parser){
        ParseTree tree = parser.start(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser));
    }

    public MusicboxParser parse(String query){

        // create a lexer that feeds off of input CharStream
        MusicboxLexer lexer = new MusicboxLexer(new ANTLRInputStream(query));
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        MusicboxParser parser = new MusicboxParser(tokens);
        return parser;


    }


}
