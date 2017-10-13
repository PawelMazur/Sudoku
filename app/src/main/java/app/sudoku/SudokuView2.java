package app.sudoku;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Pawel on 2017-04-28.
 */
public class SudokuView2 extends SurfaceView implements SurfaceHolder.Callback {


    public SudokuView2(Context context) {
        super(context);
    }

    public SudokuView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SudokuView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
