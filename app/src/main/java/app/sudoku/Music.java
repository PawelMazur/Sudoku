package app.sudoku;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Pawel on 2017-04-11.
 */
public class Music {

    private static MediaPlayer mp = null;

    public static void play(Context context, int zasob) {
        stop(context);
        if (Settings.UstawieniaMuzyka(context)) {
            mp = MediaPlayer.create(context, zasob);
            mp.setLooping(true);
            mp.start();
        }
    }

    public static void stop(Context context){
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

}
