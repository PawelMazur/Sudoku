package app.sudoku;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Pawel on 2017-04-16.
 */
public class SudokuView extends SurfaceView implements SurfaceHolder.Callback{

    private String ZNACZNIK = "SudokuView";

    private int width;
    private int height;

    private int selectedX;
    private int selectedY;

    private final Rect choiseRect = new Rect();
    private final Rect wrongRect = new Rect();

    //trzeba dodac trzy konstruktory
    public SudokuView(Context context) {

        super(context);
        //setFocusable(true);
        //setFocusableInTouchMode(true);
    }

    public SudokuView(Context context, AttributeSet attrs) {

        super(context, attrs);
        //setFocusable(true);
        //setFocusableInTouchMode(true);
    }

    public SudokuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //setFocusable(true);
        //setFocusableInTouchMode(true);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);

        displayChoiseRect((int ) (event.getX()/width) , (int ) (event.getY()/ height));
        if (!Game.instance.isChange) {
            Game.instance.displayKeyBoard();
        } else if (Game.instance.isChange){
            Game.instance.displayKeyBoardSupport();
        }

        Game.instance.setWrongFieldX(-1);
        Game.instance.setWrongFieldY(-1);

        //Gra.instance.checkEndGame();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w/9;
        height = h/9;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint background = new Paint();
        Paint yellow = new Paint();
        Paint backgroundDark = new Paint();
        //yellow.setColor(Color.YELLOW);
        yellow.setColor(getResources().getColor(R.color.yellow_light));
        backgroundDark.setColor(Color.DKGRAY);

        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tlo3), 0, 0, background);


        //getResourses oznacza ze bedziemy kozystac z ustawien jakie sa w kolor.xml
        //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.notification_template_icon_low_bg), 0, 0, null);

        if (Game.instance.getModeGame() == 1) {
            background.setColor(getResources().getColor(R.color.puzzle_background));
            canvas.drawRect(0, 0, getWidth(), getHeight(), background);
            for (int i = 0; i < 3; i++){
                canvas.drawRect( getWidth()/3 , 0 , getWidth()/3 * 2, getHeight()/3, yellow);
                canvas.drawRect(0 , getHeight()/3 , getWidth()/3, getHeight()/3 * 2, yellow);
                canvas.drawRect( getWidth()/3 * 2, getHeight()/3 , getWidth(), getHeight()/3 * 2, yellow);
                canvas.drawRect( getWidth()/3 , getHeight()/3 * 2 , getWidth()/3 * 2, getHeight(), yellow);
            }
        } else if (Game.instance.getModeGame() == 2){
            background.setColor(getResources().getColor(R.color.background_night));
            canvas.drawRect(0,0,getWidth(),getHeight(), background);
            for (int i = 0; i < 3; i++){
                canvas.drawRect( getWidth()/3 , 0 , getWidth()/3 * 2, getHeight()/3, backgroundDark);
                canvas.drawRect(0 , getHeight()/3 , getWidth()/3, getHeight()/3 * 2, backgroundDark);
                canvas.drawRect( getWidth()/3 * 2, getHeight()/3 , getWidth(), getHeight()/3 * 2, backgroundDark);
                canvas.drawRect( getWidth()/3 , getHeight()/3 * 2 , getWidth()/3 * 2, getHeight(), backgroundDark);
            }
        }

        //Co bedzie trzeba zrobic
        //Narysowac plansze
        //Narysowac cyfry
        //Narysowac podpowiedzi
        //Narysowac wybrana wartosc

        //Rysuje plansze
        //definuje kolory odpowiedzialne za linie siatki
        Paint czarny = new Paint();
        czarny.setColor(Color.BLACK);

        Paint ciemny = new Paint();
        ciemny.setColor(getResources().getColor(R.color.puzzle_dark));

        Paint podswietlenie = new Paint();
        podswietlenie.setColor(getResources().getColor(R.color.puzzle));

        Paint jasny = new Paint();
        jasny.setColor(getResources().getColor(R.color.puzzle_light));

        //rysowanie linie
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i * height, getWidth(), i * height, ciemny );
            canvas.drawLine(0, i * height + 1, getWidth(), i * height, jasny);
            canvas.drawLine(i * width, 0, i * width, getHeight(), ciemny);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), jasny);
        }

        //rysowanie pogrubionych lini
        for (int i = 0; i < 9 ; i++) {
            if (i % 3 == 0){
                canvas.drawLine(0, i * height, getWidth(), i * height, new Paint(Color.BLACK) );
                canvas.drawLine(0, i * height + 2, getWidth(), i * height + 2, new Paint(Color.BLACK));
                canvas.drawLine(i * width, 0, i * width, getHeight(), new Paint(Color.BLACK));
                canvas.drawLine(i * width + 2, 0, i * width + 2, getHeight(), new Paint(Color.BLACK));
            }
        }

        //create digit
        Paint digit = new Paint(Paint.ANTI_ALIAS_FLAG);
        digit.setColor(Color.BLACK);
        digit.setStyle(Paint.Style.FILL);

        digit.setTextSize(height * 0.75f);
        digit.setTextScaleX(width / height);
        digit.setTextAlign(Paint.Align.CENTER);

        //create digit on center field
        Paint.FontMetrics fontMetrics = digit.getFontMetrics();
        //Centering in axles X:
        float x = width /2;
        //Centering in axles Y:
        float y = height / 2 + (fontMetrics.ascent + fontMetrics.ascent) /2;


        Paint digitNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        digitNormal.setColor(Color.GRAY);
        digitNormal.setStyle(Paint.Style.FILL);

        digitNormal.setTextSize(height * 0.70f);
        digitNormal.setTextScaleX(width / height);
        digitNormal.setTextAlign(Paint.Align.CENTER);

        //create digit on center field
        Paint.FontMetrics fontMetricsNormal = digit.getFontMetrics();
        //Centering in axles X:
        float xNormal = width /2;
        //Centering in axles Y:
        float yNormal = height / 2 + (fontMetrics.ascent + fontMetrics.ascent) /2;


        //supporting digit
        Paint supportDigit = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
        supportDigit.setColor(Color.GRAY);
        supportDigit.setStyle(Paint.Style.STROKE);
        supportDigit.setTextSize(height * 0.20f);
        supportDigit.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics1 = supportDigit.getFontMetrics();
        float supX = - (fontMetrics1.ascent ) / 2;
        float supY = - (fontMetrics1.ascent ) * 1.5f;


        //rysowanie cyfr
        for (int i = 0; i < 9  ; i++){
            for (int j = 0; j < 9; j++) {

                if (Game.instance.getCheckElemet(i, j) == 1) {

                    canvas.drawText("" + Game.instance.convertDigit(i, j), i * width + x, height + j * height + y, digit);
                } if (Game.instance.getCheckElemet(i, j) == 0) {
                    /*
                    if (i == 2 && j == 2) {
                        canvas.drawRect(i * width, j * height, i * width + width, j * height + height, yellow );
                    }
                    */
                    canvas.drawText("" + Game.instance.convertDigit(i, j), i * width + x, height + j * height + y, digitNormal);

                    /*
                    if (i == 2 && j == 2) {
                        canvas.drawRect(i * width, j * height, i * width + width, j * height + height, yellow );
                    }
                    canvas.drawRect(0,0,getWidth(),getHeight(), background);
                    for (int i = 0; i < 3; i++){
                        canvas.drawRect( getWidth()/3 , 0 , getWidth()/3 * 2, getHeight()/3, yellow);
                        canvas.drawRect(0 , getHeight()/3 , getWidth()/3, getHeight()/3 * 2, yellow);
                        canvas.drawRect( getWidth()/3 * 2, getHeight()/3 , getWidth(), getHeight()/3 * 2, yellow);
                        canvas.drawRect( getWidth()/3 , getHeight()/3 * 2 , getWidth()/3 * 2, getHeight(), yellow);
                    }*/
                }

                for (int k = 1 ; k < 10; k++){
                    if (Game.instance.getSupportDigit(i,j, k) ){

                        if (Game.instance.currElementTab[i][j] == 0) {
                            canvas.drawText("  " + k , i * width + supX * (1 + ((k - 1) % 3) * 1.8f), j * height + supY * (1 + (k - 1)/ 3) , supportDigit );

                        }





                        //canvas.drawText("  " + k , i * width + supX * (1 + ((k - 1) % 3) * 1.8f), j * height + supY * (1 + (k - 1)/ 3) , supportDigit );

                    }
                }
            }
        }



        //setWrongRect(canvas);

        Paint selectedWrogRec = new Paint();
        selectedWrogRec.setColor(getResources().getColor(R.color.transparentRed));
        displayWrongRec();
        canvas.drawRect(wrongRect, selectedWrogRec);

        //Rysuje zaznaczenie
        Paint selected = new Paint();
        selected.setColor(getResources().getColor(R.color.transparentBlue));
        canvas.drawRect(choiseRect, selected);
    }

    private void displayWrongRec(){
        invalidate(wrongRect);
        wrongRect.set((int) Game.instance.getWrongFieldX() * width, (int) Game.instance.getWrongFieldY() * height,
                (int) Game.instance.getWrongFieldX() * width + width, (int) Game.instance.getWrongFieldY() * height + height);
        invalidate(wrongRect);
    }


    private void displayChoiseRect(int x, int y){
        invalidate(choiseRect);
        selectedX = x;
        selectedY = y;
        choiseRect.set((int) (x * width),(int) ( y * height) , (int) (x * width + width), (int ) (y *  height + height) );
        Log.d(ZNACZNIK, "X : " + x + " Y : " + y);
        invalidate(choiseRect);
    }


    public void setDigit(int digit){
        //Log.d(ZNACZNIK,"SelectX" + selectedX + " " + selectedY + "Digit : " + digit);
        //Gra.instance.setField(selectedX, selectedY, digit);
        Game.instance.setDigitGame(selectedX, selectedY, digit);
    }

}
