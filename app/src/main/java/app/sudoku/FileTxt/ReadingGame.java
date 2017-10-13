package app.sudoku.FileTxt;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Pawel on 2017-04-29.
 */
public class ReadingGame {

    private String ZNACZNIK = "Odczyt";
    private String text = "";
    private String s ;
    private String pathname = "data/data/app.sudoku/cache/";

    private String suffix = ".txt";
    private String string = "";

    public String odczytTextu(String filename){


        File file = null;
        String[] elementy = new File(pathname).list();
        for (String element: elementy){
            Log.d(ZNACZNIK , "pathname : " + element.substring(0, filename.length()));
            if (element.substring(0, filename.length()).equals(filename)) {
                file = new File(pathname + element);
            }
        }



        //File file = new File(pathname + filename + suffix);

        BufferedInputStream fileInputStream = null;
        //Log.d(ZNACZNIK, " File : " + file);
       // Log.d(ZNACZNIK, " File : R " + file.canRead() + " W : " + file.canWrite());

        if (file != null) {
            try {
                fileInputStream = new BufferedInputStream(new FileInputStream(file));
                BufferedReader r = new BufferedReader(new InputStreamReader(fileInputStream));

                while ((s = r.readLine()) != null) {
                    text += s;
                }

                r.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String[] el = new File(pathname).list();
        for (String element: el){
            //if (element.substring(0, filename.length()).equals(filename));
            //f = new File(pathname + element);
            Log.d(ZNACZNIK , "pathname : " + element.substring(0, filename.length()));
        }

        return text;
    }
}
