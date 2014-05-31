import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class TrackListView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList list1;
    private DefaultListModel<String> trackListModel;
    private ArrayList<Track> tracklist;

    public TrackListView(ArrayList<Track> tracklist) {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.tracklist = tracklist;

        int count = this.tracklist.size();
        this.setTitle("Number of the tracks found: "+ count );

        for (Track track : tracklist) {
            trackListModel.addElement(track.getTitle() + " :playedBy " + track.getArtist());
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {


        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(TrackListView.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                String filePath = file.getAbsolutePath();
                if(!filePath.endsWith(".m3u")) {
                    file = new File(filePath + ".m3u");
                }

                PlaylistWriter playlistWriter = new PlaylistWriter(file.getAbsolutePath());
                int count = this.tracklist.size();
                for (Track track : this.tracklist) {
                    playlistWriter.addSong(track.getTitle(), track.getPath(), track.getSeconds());
                }
                ;
                playlistWriter.write();


                System.out.println("Saving: " + file.getAbsolutePath() + "");
              //  JOptionPane.showMessageDialog(null, "" + count + " track(s) found. And stored to\n" + file.getAbsolutePath());
            } catch (Exception e1) {

                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "An unexpected error has occurred while writing the playlist, \n" +
                        "please check the console for more details.");
            }


        } else {
            System.out.println("Save command cancelled by user.");
        }
        // add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        TrackListView dialog = new TrackListView();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
    }

    private void createUIComponents() {
        trackListModel = new DefaultListModel<String>();

        list1 = new JList<String>(trackListModel);

    }
}
