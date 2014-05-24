import com.dd.plist.PropertyListFormatException;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashSet;

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
public class MusicBoxView extends JFrame {
    protected JList<String> albumList;
    protected JList<String> artistList;

    private JTextField playedByMetallicaACDCTextField;
    private JButton generateButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPanel contentPane;
    private JList list1;
    private JList list2;
    private JList list3;
    private JButton aboutButton;
    private JComboBox comboBox3;


    public DefaultListModel<String> onAlbumModel;
    public DefaultListModel<String> playedByModel;
    public DefaultListModel<String> hasGenreModel;



    public MusicBoxView() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {

        MusicOntology ontology = new MusicOntology();

        LinkedHashSet<String> hashSet = new LinkedHashSet<String>();

        ExtendedIterator<Individual> artistIterator = (ExtendedIterator<Individual>) ontology.artist.listInstances();

        while (artistIterator.hasNext()){
            Individual ind = artistIterator.next();
            if (hashSet.add(ind.getLocalName().trim()))
                playedByModel.addElement(ind.getLocalName());
            System.out.println(ind.getLocalName());
        }

        hashSet = new LinkedHashSet<String>();
        ExtendedIterator<Individual> albumIterator = (ExtendedIterator<Individual>) ontology.album.listInstances();


        while (albumIterator.hasNext()){
            Individual ind = albumIterator.next();
            if (hashSet.add(ind.getLocalName().trim()))
                onAlbumModel.addElement(ind.getLocalName());
            System.out.println(ind.getLocalName());
        }

        hashSet = new LinkedHashSet<String>();
        ExtendedIterator<Individual> genreIterator = (ExtendedIterator<Individual>) ontology.genre.listInstances();


        while (genreIterator.hasNext()){

            Individual ind = genreIterator.next();
            if (hashSet.add(ind.getLocalName().trim()))
                hasGenreModel.addElement(ind.getLocalName());
            System.out.println(ind.getLocalName());
        }


        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println();
                //artist playedBy
                for (Object o : list1.getSelectedValuesList()) {
                    System.out.println(o);
                }

                //album
                for (Object o : list2.getSelectedValuesList()) {
                    System.out.println(o);
                }

                //genre
                for (Object o : list3.getSelectedValuesList()) {
                    System.out.println(o);
                }
            }
        });


    }

    public static void main(String[] args) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        JFrame frame = new JFrame("MusicBox");
        frame.setContentPane(new MusicBoxView().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        onAlbumModel = new DefaultListModel<String>();
        playedByModel = new DefaultListModel<String>();
        hasGenreModel = new DefaultListModel<String>();

        list2 = new JList<String>(onAlbumModel);
        list1 = new JList<String>(playedByModel);
        list3 = new JList<String>(hasGenreModel);

    }
}
