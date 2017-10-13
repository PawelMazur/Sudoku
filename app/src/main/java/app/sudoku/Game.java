package app.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Random;

import app.sudoku.FileTxt.ReadingGame;
import app.sudoku.FileTxt.SaveGame;

/**
 * Created by Pawel on 2017-04-06.
 */
public class Game extends Activity {

    //private Widok widok;
    private String ZNACZNIK = "GRA";
    private LevelGame levelGame;

    public int modeGame = 1;
    public int level = 1;
    public static final int LEVEL_EASY = 0;
    public static final int LEVEL_NORMAL = 1;
    public static final int LEVEL_HARD = 2;
    public static final int CONTINUE = -1;

    private int [][] elementsTab = new int[9][9];
    public int [][] currElementTab = new int[9][9];
    private int [][] checkElementsTab = new int[9][9];
    //private int [][][] suporrtElementTab = new int[9][9][9];
    private boolean[][][] supportElementTab = new boolean[9][9][10];

    private boolean [] digitInBlock = new boolean[10];
    private boolean [] digitExist = new boolean[10];

    //private View sudokuView;
    private SudokuView sudokuView;
    public static Game instance;
    public static Context context;
    private Button checkButton;
    private ToggleButton editButton;
    private Chronometer chronometer;

    public int wrongFieldX = 0;
    public int wrongFieldY = 0;


    public boolean isChange = false;



    private SaveGame saveGameCurrentElement;
    private SaveGame saveGameSupportElement;
    private SaveGame saveGameTime;
    private SaveGame saveGameLastTime;
    private SaveGame saveGameCheckElement;
    private SaveGame saveGameElement;
    private ReadingGame readingGameCurrentElement;
    private ReadingGame readingGameSupportElement;
    private ReadingGame readingGameTime;
    private ReadingGame readingGameLastTime;
    private ReadingGame readingGameCheckElement;
    private ReadingGame readingGameElement;


    private long lastPause;
    private long addTime = 0;

    int tab [][] = new int[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        sudokuView = (SudokuView) findViewById(R.id.sudoku_view);
        this.context = getApplicationContext();

        //sudokuView = new SudokuView(this);
        //sudokuView.requestFocus();


        Log.d(ZNACZNIK, "Level : " + MainActivity.instance.getLevel());
        levelGame = new LevelGame(MainActivity.instance.getLevel());
        instance = this;

        checkButton = (Button) findViewById(R.id.checkButton);
        editButton = (ToggleButton) findViewById(R.id.edit_button);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        //widok = new Widok(this);
        //setContentView(widok);

        saveGameCurrentElement = new SaveGame();
        saveGameSupportElement = new SaveGame();
        saveGameTime = new SaveGame();
        saveGameLastTime = new SaveGame();
        saveGameCheckElement = new SaveGame();
        saveGameElement = new SaveGame();

        readingGameCurrentElement = new ReadingGame();
        readingGameSupportElement = new ReadingGame();
        readingGameTime = new ReadingGame();
        readingGameLastTime = new ReadingGame();
        readingGameCheckElement = new ReadingGame();
        readingGameElement = new ReadingGame();

        setWrongFieldX(-1);
        setWrongFieldY(-1);

        initSupportTab();
        initClock();


        difficultyLevel(MainActivity.instance.getLevel());


        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorField(currElementTab);
                if (getWrongFieldY() == -1 && getWrongFieldX() == -1) {
                    Toast.makeText(getApplicationContext(), "Nie ma błędu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    isChange = true;
                } else {
                    isChange = false;
                }
            }
        });

    }


    public int getCheckElemet(int x, int y){
        return checkElementsTab[x][y];
    }


    private void initClock(){
        if (MainActivity.instance.getLevel() != -1) {
            if (lastPause != 0) {
                chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
            } else {
                chronometer.setBase(SystemClock.elapsedRealtime());
            }
            chronometer.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sudoku, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.menu_trybDzienny:
                setModeGame(1);
                break;
            case R.id.menu_trybNocny:
                setModeGame(2);
                break;
            case R.id.menu_NowaGra:
                finish();
                break;
            case R.id.menu_Wyczysc:
                //clearCurrentTable();
                //initCurrentTab(tab);
                tab = fromMarkPuzzle(readingGameElement.odczytTextu("Element"));
                initCheckElement(tab);
                initCurrentTab(tab);
                break;
            case R.id.menu_Wyjdz:
                finish();
                break;
            /*
            case R.id.menu_ListaPlikow:
                startActivity(new Intent(getApplicationContext(), ActivityFile.class));
                break;
            */

            case R.id.menu_Ustawienia_Muzyka:
                startActivity(new Intent(getApplicationContext(), Settings.class));
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    private void backToNewGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Gratulacje\n Czas : " + chronometer.getText() )
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        finish();
                    }
                });
        // Create the AlertDialog object and return it
        builder.show();
    }


    public int getModeGame() {
        return modeGame;
    }

    public void setModeGame(int modeGame) {
        this.modeGame = modeGame;
    }

    public void clearCurrentTable(){
        for (int i = 0; i < currElementTab.length; i++){
            for (int j = 0; j < currElementTab[i].length; j++){
                currElementTab[i][j] = 0;
            }
        }
    }

    protected void displayKeyBoard(){
        Dialog v = new Klawiatura(this, digitExist ,sudokuView);
        v.show();
    }

    protected void displayKeyBoardSupport(){
        Dialog v = new Klawiatura(this, digitInBlock ,sudokuView);
        v.show();
    }

    private void difficultyLevel(int level){
        String puzzle = null;
        Random random = new Random();
        //int r = random.nextInt(5);
        int r = random.nextInt(9);

        switch (level){

            case LEVEL_EASY:
                puzzle = levelGame.easyLevel.get(r);
                tab = fromMarkPuzzle(puzzle);
                initCheckElement(tab);
                initCurrentTab(tab);
                break;
            case LEVEL_NORMAL:
                puzzle = levelGame.normalLevel.get(r);
                tab = fromMarkPuzzle(puzzle);
                initCheckElement(tab);
                initCurrentTab(tab);
                break;
            case LEVEL_HARD:
                puzzle = levelGame.hardLevel.get(r);
                fromMarkPuzzle(puzzle);
                tab = fromMarkPuzzle(puzzle);
                initCheckElement(tab);
                initCurrentTab(tab);
                break;

            case CONTINUE:

                fromMarkSupportTab(readingGameSupportElement.odczytTextu("supportElement"));
                addTime = fromMarkTime(readingGameTime.odczytTextu("Time"));
                Long l = fromMarkTime(readingGameLastTime.odczytTextu("LastTime"));
                chronometer.setBase(addTime + SystemClock.elapsedRealtime() - l);

                puzzle = readingGameCurrentElement.odczytTextu("currentElement");
                initCheckElement(fromMarkPuzzle(readingGameCheckElement.odczytTextu("checkElementsTab")));


                tab = fromMarkPuzzle(puzzle);
                initCurrentTab(tab);

                //String currentElement = odczytCurrentElement.odczytTextu("currentElement");


                // /tu dodac
                Log.d(ZNACZNIK, "Time continue : " + addTime);
                break;


        }

    }
    ///---------------------------------------------------------

    public int[][] fromMarkPuzzle(String elements) {
        elementsTab = new int[9][9];
        int x = 0;
        int y = 0;
        for (int i = 0; i < elements.length(); i++){
            if (i % 9 == 0 && i != 0){
                x = 0;
                y++;
                //Log.d(ZNACZNIK , i + "----------------------------------");
            }
            elementsTab[x][y] = elements.charAt(i) - '0';
            //Log.d(ZNACZNIK , "X : " + x + " Y : " + y + " Value : " + elementsTab[x][y]);
            x++;

        }

        return elementsTab;

    }


    public void initCheckElement(int [][] elementsTab){
        for (int x = 0; x < elementsTab.length; x++) {
            for (int y = 0; y < elementsTab[x].length; y++) {
                if(elementsTab[x][y] == 0){
                    checkElementsTab[x][y] = 0;
                } else {
                    checkElementsTab[x][y] = 1;
                }
            }
        }
    }

    // inicjowanie tabel {aktualnej, pomocniczej}
    public void initCurrentTab(int [][] elementsTab){
        for (int x = 0; x < elementsTab.length; x++){
            for (int y = 0; y < elementsTab[x].length; y++){
                currElementTab[x][y] = elementsTab[x][y];
            }
        }
    }

    private void initSupportTab(){
        for (int y = 0; y < supportElementTab.length; y++){
            for (int x = 0; x < supportElementTab[y].length; x++){
                for (int z = 0; z < supportElementTab[x][y].length ; z++){
                    supportElementTab[x][y][z] = false;
                }
            }
        }
    }

    // wyswietlanie tabel {aktualnej, pomocniczej}
    private void displaySupportTab(){
        for (int y = 0; y < supportElementTab.length; y++){
            for (int x = 0; x < supportElementTab[y].length; x++){
                for (int z = 0; z < supportElementTab[x][y].length ; z++){
                    Log.d(ZNACZNIK , " sup " + z + " " + supportElementTab[x][y][z]);
                }
                Log.d(ZNACZNIK, ",");

            }
            Log.d(ZNACZNIK, "|");
        }
    }

    public void displayCurrentTab(){
        for (int y = 0; y < currElementTab.length ; y++){
            for (int x = 0; x < currElementTab[y].length; x++){
                Log.d(ZNACZNIK, "" + currElementTab[x][y]);
            }
            Log.d(ZNACZNIK, " ");
        }
    }



    public void setSupportDigit(int x, int y, int z,boolean value){
        if (currElementTab[x][y] == 0){

            if (supportElementTab[x][y][z] == value ) {
                supportElementTab[x][y][z] = !value;
            } else {
                supportElementTab[x][y][z] = value;
            }
            //supportElementTab[x][y][z] = value;
        }

    }

    public boolean getSupportDigit(int x, int y, int z){
        return supportElementTab [x][y][z];
    }


    private String toMarkSupportTab(boolean [][][] supElementTab){
        StringBuffer stringBuffer = new StringBuffer();
        for (int y = 0; y < supElementTab.length; y++){
            for (int x = 0; x < supElementTab[y].length; x++){
                for (int z = 0; z < supElementTab[x][y].length; z++){
                    if (supElementTab[x][y][z]) {
                        stringBuffer.append(z);
                    }
                }
                stringBuffer.append(",");
            }
            stringBuffer.append("|");
        }
        return stringBuffer.toString();

    }

    private void fromMarkSupportTab(String supElement){
        int y = 0;
        int x = 0;
        int tmp = 0;
        for (int i = 0; i < supElement.length(); i++){


            String a = String.valueOf(supElement.charAt(i));
            Log.d(ZNACZNIK , " X: " + x + " Y: " + y + " znak : " + a);
            if (Character.isDigit(supElement.charAt(i))) {
                tmp = Integer.parseInt(a);
                if (tmp != 0) {
                    setSupportDigit(x, y, tmp, true);
                    Log.d(ZNACZNIK , " X: " + x + " Y: " + y + " tmp: " + tmp );
                }
            }
            if ("|".equals(a)) {
                y++;
                x = 0;
            }
            if (",".equals(a)) {
                x++;
            }

        }
        displaySupportTab();
    }


    public int getField(int x, int y){
        return currElementTab[x][y];
    }

    public String convertDigit(int x, int y) {
        int a = getField(x, y);
        if (a == 0){
            return "";
        } else {
            return String.valueOf(a);
        }
    }

    public void setField(int x, int y, int value) {
        //if (elementsTab[x][y] == 0) {
        if (checkElementsTab[x][y] == 0) {
            currElementTab[x][y] = value;
        }
        //}
    }


    // ustanie cyfr w grze {główna metoda }
    public void setDigitGame(int x, int y, int value){

        if (!isChange) {
            setField(x, y, value);
            checkDigidExist(currElementTab);
            //checkDigitInBlock(value);
        } else if (isChange) {
            setSupportDigit(x, y, value, true);
        }
        checkEndGame(currElementTab);

    }


    //spawdzenie czy gra sie zakończyła
    public void checkEndGame(int [][] currElementTab){
        int amount = 0;
        for (int y = 0; y < currElementTab.length ; y++) {
            for (int x = 0; x < currElementTab[y].length; x++) {
                if(currElementTab[x][y] != 0 && !checkErrorField2(x, y, currElementTab[x][y])){
                    amount++;
                    if (amount == 81){
                        backToNewGame();
                        Log.d(ZNACZNIK, "amount" + amount);
                    }
                }
            }
        }
    }

    // spawdzenie ilosci cyfr
    private void checkDigidExist(int [][] currElementTab) {
        int [] amount = new int[10];
        for (int i = 1; i < 10; i++){
            for (int y = 0; y < currElementTab.length; y++) {
                for (int x = 0; x < currElementTab[y].length; x++) {
                    if (i == getField(x, y)) {
                        amount[i] += 1;
                        if (amount[i] >= 9) {
                            digitExist[i] = true;
                        }
                        if (amount[i] < 9) {
                            digitExist[i] = false;
                        }
                    }
                }
            }
        }
    }


    //sprawdzenie błedów
    public void errorField(int [][] currElementTab){
        for (int y = 0; y < currElementTab.length; y++) {
            for (int x = 0; x < currElementTab[y].length; x++) {
                checkErrorField(x, y, currElementTab[x][y]);
            }
        }
    }


    public boolean checkErrorField(int x, int y, int value){
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (getField(x, y) != 0) {
                    if ((x < 3 && y < 3) && (i < 3 && j < 3)) {
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    if (( (3 <= x && x < 6) && y < 3 ) && ((3 <= i && i < 6) && j < 3)){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    if (( (6 <= x && x < 9) && y < 3 ) && ((6 <= i && i < 9) && j < 3)){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    //------------------------------------------------------------------
                    if ((x < 3  && (3 <= y && y < 6)) && (i < 3  && (3 <= j && j < 6))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    if (((3 <= x && x < 6)  && (3 <= y && y < 6)) && ((3 <= i && i < 6)  && (3 <= j && j < 6))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    if (((6 <= x && x < 9)  && (3 <= y && y < 6)) && ((6 <= i && i < 9)  && (3 <= j && j < 6))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    //-----------------------------------------------------------
                    if ((x < 3  && (6 <= y && y < 9)) && (i < 3  && (6 <= j && j < 9))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    if (((3 <= x && x < 6)  && (6 <= y && y < 9)) && ((3 <= i && i < 6)  && (6 <= j && j < 9))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    if (((6 <= x && x < 9)  && (6 <= y && y < 9)) && ((6 <= i && i < 9)  && (6 <= j && j < 9))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            return true;
                        }
                    }
                    //pionowo
                    if (getField(x, i) == value) {
                        if (i != y) {
                            Log.d(ZNACZNIK, "bład wystąpil pionowo : " + value);
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            Log.d(ZNACZNIK, "X : " + getWrongFieldX() + " Y : " + getWrongFieldY());
                            return true;
                        }
                    }

                    //poziomo
                    if (getField(i, y) == value) {
                        if (i != x) {
                            Log.d(ZNACZNIK, "bład wystąpił poziomo ");
                            setWrongFieldX(x);
                            setWrongFieldY(y);
                            Log.d(ZNACZNIK, "X : " + getWrongFieldX() + " Y : " + getWrongFieldY());
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    public boolean checkErrorField2(int x, int y, int value){
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (getField(x, y) != 0) {
                    if ((x < 3 && y < 3) && (i < 3 && j < 3)) {
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    if (( (3 <= x && x < 6) && y < 3 ) && ((3 <= i && i < 6) && j < 3)){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    if (( (6 <= x && x < 9) && y < 3 ) && ((6 <= i && i < 9) && j < 3)){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    //------------------------------------------------------------------
                    if ((x < 3  && (3 <= y && y < 6)) && (i < 3  && (3 <= j && j < 6))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    if (((3 <= x && x < 6)  && (3 <= y && y < 6)) && ((3 <= i && i < 6)  && (3 <= j && j < 6))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    if (((6 <= x && x < 9)  && (3 <= y && y < 6)) && ((6 <= i && i < 9)  && (3 <= j && j < 6))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    //-----------------------------------------------------------
                    if ((x < 3  && (6 <= y && y < 9)) && (i < 3  && (6 <= j && j < 9))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    if (((3 <= x && x < 6)  && (6 <= y && y < 9)) && ((3 <= i && i < 6)  && (6 <= j && j < 9))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    if (((6 <= x && x < 9)  && (6 <= y && y < 9)) && ((6 <= i && i < 9)  && (6 <= j && j < 9))){
                        if ((getField(i, j) == getField(x, y)) && (((i != x) || (j != y))) ) {

                            return true;
                        }
                    }
                    //pionowo
                    if (getField(x, i) == value) {
                        if (i != y) {

                            return true;
                        }
                    }

                    //poziomo
                    if (getField(i, y) == value) {
                        if (i != x) {

                            return true;
                        }
                    }

                }
            }
        }

        return false;
    }
    //----------------------

    //pola błedów
    public int getWrongFieldX() {
        return wrongFieldX;
    }

    public void setWrongFieldX(int wrongFieldX) {
        this.wrongFieldX = wrongFieldX;
    }

    public int getWrongFieldY() {
        return wrongFieldY;
    }

    public void setWrongFieldY(int wrongFieldY) {
        this.wrongFieldY = wrongFieldY;
    }
    //------------------------


    private String toMarkPuzzle(int [][] cElementTab){
        StringBuffer buffer = new StringBuffer();
        for (int y = 0; y < currElementTab.length ; y++){
            for (int x = 0; x < currElementTab[y].length; x++){
                buffer.append(cElementTab[x][y]);
            }
        }
        return buffer.toString();
    }

    private String toMarkTime(Long time){
        return String.valueOf(time);
    }

    private Long fromMarkTime(String text){
        return Long.parseLong(text);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.gra);
        chronometer.setBase(chronometer.getBase());
        chronometer.start();
    }

    @Override
    protected void onPause() {

        Log.d(ZNACZNIK, "Pause: Zapamietana tablica");

        saveGameCurrentElement.zapisTextu(toMarkPuzzle(currElementTab),"currentElement");
        saveGameSupportElement.zapisTextu(toMarkSupportTab(supportElementTab), "supportElement");
        saveGameCheckElement.zapisTextu(toMarkPuzzle(checkElementsTab), "checkElementsTab");
        saveGameElement.zapisTextu(toMarkPuzzle(tab), "Element");

        chronometer.stop();
        lastPause = SystemClock.elapsedRealtime();
        saveGameTime.zapisTextu(toMarkTime(chronometer.getBase()), "Time");
        saveGameLastTime.zapisTextu(toMarkTime(lastPause), "LastTime");
        //Log.d(ZNACZNIK , "Time Pause : " + lastPause);
        super.onPause();
        Music.stop(this);
    }



}
