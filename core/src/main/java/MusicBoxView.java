import com.dd.plist.PropertyListFormatException;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.io.File;

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

    private JTextField playedByTextField;
    private JButton generateButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPanel contentPane;
    private JList list1;
    private JList list2;
    private JList list3;
    private JButton aboutButton;
    private JComboBox comboBox3;
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
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(MusicBoxView.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    Language l = new Language(ontology);
                    try {
                        MusicboxParser parser = l.parse(playedByTextField.getText());
                        SExpr sExpr = l.eval(parser.start());
                        int count = sExpr.tracks.size();
                        System.out.println("COUNT: "+sExpr.tracks.size());


                        File file = fc.getSelectedFile();

                        PlaylistWriter playlistWriter = new PlaylistWriter(file.getAbsolutePath());
                        for (Track track : sExpr.tracks) {
                            playlistWriter.addSong(track.getTitle(),track.getPath(), track.getSeconds());
                        };
                        playlistWriter.write();


                        System.out.println("Saving: " + file.getAbsolutePath() + "" );
                        JOptionPane.showMessageDialog(null, ""+count+" track(s) found. And stored to\n"+ file.getAbsolutePath() );
                    } catch (Exception e1) {

                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "An unexpected error has occurred, \n" +
                                "please check the console for more details.");
                    }


                } else {
                    System.out.println("Save command cancelled by user." );
                }

            }
        });

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
                    playedByTextField.setText(playedByTextField.getText() + " " + "playedBy:" + onAlbumModel.getElementAt(index) + " |");
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
                    playedByTextField.setText(playedByTextField.getText()+" "+"playedBy:"+playedByModel.getElementAt(index) + " |");
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
                    playedByTextField.setText(playedByTextField.getText()+" "+"hasGenre:"+hasGenreModel.getElementAt(index) + " |");
                }
            }
        });

    }
}
