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
        //QueryEngine queryEngine = new QueryEngine(value,new MusicOntology());
        //tracks = queryEngine.getResults();
    }

}

public class Language {

    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input
        // (playedBy:Metallica and playedBy:ACDC)

        // print LISP-style tree }
        String test = "(playedBy:Metallica & playedBy:ACDC)";
        String test2 = "(playedBy:Metallica | (playedBy:Metallica & playedBy:ACDC))";

        Language l = new Language();
        MusicboxParser parser = l.parse(test);
        //l.print_AST(parser);
        l.eval(parser.start());

        Language l2 = new Language();
        MusicboxParser parser2 = l2.parse(test2);
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
        if (tree instanceof TerminalNodeImpl){
            return new SExpr(tree.toString());
        }
        else {
            if (tree.getChild(2).toString().equalsIgnoreCase("&")){
                //and operation


            }
            else if (tree.getChild(2).toString().equalsIgnoreCase("|")){
                //or operation

            }

        }


        System.out.println(tree.getChildCount());
        System.out.println(tree.getChild(1).getClass());
        System.out.println(tree.getChild(2).getClass());
        System.out.println(tree.getChild(3).getClass());

        System.out.println(tree.getChild(1).toString());
        System.out.println(tree.getChild(2).toString());
        System.out.println(tree.getChild(3).toString());

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
