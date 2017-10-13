package app.sudoku.FileTxt;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import app.sudoku.Game;

/**
 * Created by Pawel on 2017-04-29.
 */
public class SaveGame {

    private String ZNACZNIK = "Zapis";
    //private String filename = "ZapisSudoku.txt";
    private String suffix = ".txt";
    private String prefix = "ZapisSudoku";
    private String pathname = "data/data/app.sudoku/cache/";

    public void zapisTextu(String text, String filename){

        String[] elementy = new File(pathname).list();
        for (String element: elementy){
            if (element.substring(0, filename.length()).equals(filename)) {
                File f = new File(pathname + element);
                f.delete();
            }
        }



        File file = new File(pathname + filename);
        if (!file.exists()){
            try {
                file = File.createTempFile(filename, suffix, Game.instance.getCacheDir());

            } catch (IOException e){
                e.printStackTrace();
            }

        }
        Log.d(ZNACZNIK,  " File : " + file);
        Log.d(ZNACZNIK, " File : R " + file.canRead() + " W : " + file.canWrite());

        if (file.exists()){
            OutputStream outputStream = null;
            Writer out;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(file));
                out = new OutputStreamWriter(outputStream);
                out.write(text);
                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }

    }
}
