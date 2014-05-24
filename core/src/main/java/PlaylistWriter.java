import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

//
//#EXTM3U
//        #EXTINF:405,Cry for the Moon (The Embrace - Epica
//        /Users/tdgunes/Music/EPICA - DISCOGRAPHY [CHANNEL NEO]/[2004] We Will Take You With Us/04 - Cry for the Moon (The Embrace That Smothers, Part IV).mp3
//        #EXTINF:422,Rest Calm - Nightwish
//        /Users/tdgunes/Music/Nightwish - Imaginaerum [ChattChitto RG]/Imaginaerum/09 Rest Calm.mp3
//        #EXTINF:187,Grand Theft Auto IV: Soviet Connection - London Philharmonic Orchestra & Andrew Skeet
//        /Users/tdgunes/Music/iTunes/iTunes Media/Music/London Philharmonic Orchestra & Andrew Skeet/The Greatest Video Game Music (Bonus Track Edition)/08 Grand Theft Auto IV_ Soviet Connection.m4a
//        #EXTINF:190,Narcissistic Cannibal (feat. Skrillex & Kill the Noise) - Korn
//        /Users/tdgunes/Music/iTunes/iTunes Media/Music/Korn/Narcissistic Cannibal (feat. Skrillex & Kill the Noise) - Single/01 Narcissistic Cannibal (feat. Skrillex & Kill the Noise).m4a



public class PlaylistWriter {

    public String fileName;
    public ArrayList<HashMap> songs;
    public PlaylistWriter(String fileName) {
        this.fileName = fileName;
        songs = new ArrayList<HashMap>();


    }
    public void addSong(String title, String path, String seconds){
        HashMap<String,String> song = new HashMap<String, String>();
        song.put("title", title);
        song.put("seconds", seconds);
        song.put("path", path);
        songs.add(song);
    }

    public void write(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write("#EXTM3U" + "\n");
            for (HashMap song : songs) {
                out.write("#EXTINF:"+song.get("seconds")+","+song.get("title") + "\n");
                out.write(song.get("path")+"\n");
            }

            out.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception " + e.getLocalizedMessage());
        }
    }
    public static void main(String[] args)  {

    }


}
