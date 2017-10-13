package app.sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Pawel on 2017-04-11.
 */
public class Klawiatura extends Dialog {

    protected  static final String ZNACZNIK = "Klawiatura";
    private static Klawiatura instace;

    SudokuView sudokuView;

    private final View keyboard[] = new View[10];
    private View keyboardView;
    private boolean existDigit[];

    public Klawiatura(Context context,boolean [] existDigit , SudokuView sudokuView) {
        super(context);
        instace = this;

        this.sudokuView = sudokuView;
        this.existDigit = existDigit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.klawiatura);

        findView();
        initListenerObject();


        for (int i = 0; i < keyboard.length - 1; i++){
            if (existDigit[i + 1] == true){
                keyboard[i].setVisibility(View.INVISIBLE);
            }
        }


    }


    private void findView(){
        keyboardView = findViewById(R.id.keyboard);
        keyboard[0] = findViewById(R.id.keyboard1);
        keyboard[1] = findViewById(R.id.keyboard2);
        keyboard[2] = findViewById(R.id.keyboard3);
        keyboard[3] = findViewById(R.id.keyboard4);
        keyboard[4] = findViewById(R.id.keyboard5);
        keyboard[5] = findViewById(R.id.keyboard6);
        keyboard[6] = findViewById(R.id.keyboard7);
        keyboard[7] = findViewById(R.id.keyboard8);
        keyboard[8] = findViewById(R.id.keyboard9);
        keyboard[9] = findViewById(R.id.keyboard0);
    }

    private void returnScore(int digit) {
        sudokuView.setDigit(digit);
    }

    private void initListenerObject(){

        for (int i = 0; i < keyboard.length - 1; i++){

            final  int t = i + 1;
            keyboard[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    returnScore(t);
                    Klawiatura.instace.onBackPressed();

                }
            });
        }

        keyboardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnScore(0);
                Klawiatura.instace.onBackPressed();
            }
        });

        keyboard[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnScore(0);
                Klawiatura.instace.onBackPressed();
            }
        });

    }

}
