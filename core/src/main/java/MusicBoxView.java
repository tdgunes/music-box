import com.dd.plist.PropertyListFormatException;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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


    private JButton generateButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPanel contentPane;
    private JList list1;
    private JList list2;
    private JList list3;
    private JButton aboutButton;
    private JComboBox comboBox3;
    private JButton leftParButton;
    private JButton rigthParButton;
    private JButton andButton;
    private JButton orButton;
    private JTextArea requestTextArea;
    private JButton clearButton;
    private MusicOntology ontology;

    public DefaultListModel<String> onAlbumModel;
    public DefaultListModel<String> playedByModel;
    public DefaultListModel<String> hasGenreModel;


    public MusicBoxView(JFrame frame) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {

        ontology =new MusicOntology();

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


        generateButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {



                Language l = new Language(ontology);
                MusicboxParser parser = l.parse(requestTextArea.getText());
                SExpr sExpr = null;
                try {
                    sExpr = l.eval(parser.start());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An unexpected error has occurred while parsing your request, \n" +
                            "please check the console for more details.");
                    e1.printStackTrace();
                }

                int count = sExpr.tracks.size();
                System.out.println("COUNT: "+sExpr.tracks.size());
                if (count != 0) {
                    TrackListView dialog = new TrackListView(sExpr.tracks);
                    dialog.pack();
                    dialog.setVisible(true);


                }
                else {
                    JOptionPane.showMessageDialog(null,"Unable to find any songs according to your request! \n" );
                }
            }


        });

        requestTextArea.setCaretPosition(1);
        leftParButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestTextArea.insert(" (", requestTextArea.getCaretPosition());

            }
        });
        rigthParButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestTextArea.insert(" )", requestTextArea.getCaretPosition());
            }
        });


        andButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestTextArea.insert(" &", requestTextArea.getCaretPosition());
            }
        });
        orButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestTextArea.insert(" |", requestTextArea.getCaretPosition());
            }
        });


        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL aboutPage = new URL("https://github.com/tdgunes/music-bot");
                    openWebpage(aboutPage);

                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        JFrame frame = new JFrame("MusicBox");
        MusicBoxView musicBoxView = new MusicBoxView(frame);
        frame.setContentPane(musicBoxView.contentPane);
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
        list2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    System.out.println(onAlbumModel.getElementAt(index));
                    requestTextArea.insert(" " + "onAlbum:" + onAlbumModel.getElementAt(index), requestTextArea.getCaretPosition());

                }
            }
        });

        list1 = new JList<String>(playedByModel);
        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    System.out.println(playedByModel.getElementAt(index));
                    requestTextArea.insert(" " + "playedBy:" + playedByModel.getElementAt(index), requestTextArea.getCaretPosition());

                }
            }
        });
        list3 = new JList<String>(hasGenreModel);
        list3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    System.out.println(hasGenreModel.getElementAt(index));
                    requestTextArea.insert(" " + "hasGenre:" + hasGenreModel.getElementAt(index), requestTextArea.getCaretPosition());
                }
            }
        });

    }
}
