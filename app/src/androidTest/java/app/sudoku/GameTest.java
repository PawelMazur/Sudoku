package app.sudoku;

import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import junit.framework.TestCase;

public class GameTest extends TestCase {

    private String ZNACZNIK = "GraTest";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        /*
        gra = new Gra();
        element = gra.fromMarkPuzzle(puzzleLatwe1);
        gra.initCurrentTab(element);


        gra1 = new Gra();
        element1 = gra1.fromMarkPuzzle(puzzleDoSprawdzenia);
        gra1.initCurrentTab(element1);
        */



    }

    LevelGame levelGame = new LevelGame();
    Game gamePuzzle = new Game();
    private int[][] puzzle = new int[9][9];

    @SmallTest
    public void testpuzzleLatwe0(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(0));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleLatwe1(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(1));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));

    }

    @SmallTest
    public void testpuzzleLatwe2(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(2));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));

    }

    @SmallTest
    public void testpuzzleLatwe3(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(3));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));

    }

    @SmallTest
    public void testpuzzleLatwe4(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(4));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));

    }

    @SmallTest
    public void testpuzzleLatwe5(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(5));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));

    }

    @SmallTest
    public void testpuzzleLatwe6(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(6));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));

    }

    @SmallTest
    public void testpuzzleLatwe7(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(7));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));

    }

    @SmallTest
    public void testpuzzleLatwe8(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(8));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleLatwe9(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.easyLevel.get(9));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(0));
        gamePuzzle.initCurrentTab(puzzle);
        //assertEquals(false, errorFieldTest(puzzle));
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
        //assertEquals(false, graPuzzle.checkErrorField(7, 7, graPuzzle.currElementTab[8][8]));
    }

    @SmallTest
    public void testpuzzleSrednie1(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(1));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie2(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(2));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie3(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(3));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie4(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(4));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie5(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(5));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie6(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(6));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie7(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(7));
        gamePuzzle.initCurrentTab(puzzle);
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie8(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(8));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleSrednie9(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.normalLevel.get(9));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne0(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(0));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne1(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(1));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne2(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(2));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne3(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(3));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne4(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(4));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne5(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(5));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne6(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(6));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne7(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(7));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne8(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(8));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }

    @SmallTest
    public void testpuzzleTrudne9(){
        puzzle = gamePuzzle.fromMarkPuzzle(levelGame.hardLevel.get(9));
        gamePuzzle.initCurrentTab(puzzle);
        displayCurrentTab();
        assertEquals(false, errorFieldTest2(puzzle));
    }




    public void displayCurrentTab(){
        for (int y = 0; y < gamePuzzle.currElementTab.length ; y++){
            for (int x = 0; x < gamePuzzle.currElementTab[y].length; x++){
                Log.d(ZNACZNIK, "" + gamePuzzle.currElementTab[x][y]);
            }
            Log.d(ZNACZNIK, " ");
        }
    }


    /*
    public boolean errorFieldTest(int [][] currElementTab){
        for (int y = 0; y < graPuzzle.currElementTab.length; y++) {
            for (int x = 0; x < graPuzzle.currElementTab[y].length; x++) {
                return graPuzzle.checkErrorField(x, y, currElementTab[x][y]);

            }
        }
        return false;
    }
    */

    public boolean errorFieldTest2(int [][] currElementTab){
        boolean temp = false;
        for (int y = 0; y < gamePuzzle.currElementTab.length; y++) {
            for (int x = 0; x < gamePuzzle.currElementTab[y].length; x++) {
                //return graPuzzle.checkErrorField(x, y, currElementTab[x][y]);
                if (gamePuzzle.checkErrorField(x, y, gamePuzzle.currElementTab[x][y])){
                    temp = true;
                }
            }
        }
        return temp;
    }

























    //------------------------------------------
    /*
    private final String puzzleDoSprawdzenia =
            "123456789"+
                    "456789123"+
                    "789123456"+
                    "234567891"+
                    "567891234"+
                    "891234567"+
                    "345678912"+
                    "678912345"+
                    "912345678";

    private int[][] element = new int[9][9];
    private int[][] element1 = new int[9][9];

    Gra gra;
    Gra gra1;



    @SmallTest
    public void testEndGame(){
        //assertEquals(true, gra1.checkEndGame(element1));
    }


    @SmallTest
    public void testObjectGra(){
        assertNotNull(gra);
    }

    @SmallTest
    public void testSurfaceView(){
        //SurfaceView sudokuView = (SudokuView) gra.findViewById(R.id.sudoku_view);
        //assertNotNull(sudokuView);
    }

    private final String puzzleLatwe1 =
             "360000000" +
             "004250800" +
             "000004200"+
             "070460003" +
             "820000014" +
             "500013020"+
             "001900000" +
             "007080000" +
             "000000045";



    @SmallTest
    public void testField(){
        /*
        gra.setField(2, 2, 3);
        assertEquals( 3, gra.getField(2, 2));

        gra.setField(1, 7, 9);
        assertEquals(9,gra.getField(1, 7));

        gra.setField(4, 0, 5);
        assertEquals(5, gra.getField(4, 0));

        assertEquals(3, gra.getField(0,0));

        assertEquals(6, gra.getField(1,0));
        //


    }


    @SmallTest
    public void testCheckErrorFieldForFirstBlock() throws Exception {
        gra.setField(2, 2, 3);
        assertEquals(3, gra.getField(2, 2));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(true, gra.checkErrorField(2, 2, 3));

    }

    @SmallTest
    public void testCheckErrorFieldForFirst2Block() throws Exception {
        gra.setField(2, 2, 5);
        assertEquals(5, gra.getField(2, 2));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(false, gra.checkErrorField(2, 2, 5));

    }

    @SmallTest
    public void testCheckErrorFieldForSecondBlock() throws Exception {
        gra.setField(3, 2, 5);
        assertEquals(5, gra.getField(3, 2));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(true, gra.checkErrorField(3, 2, 5));

    }

    @SmallTest
    public void testCheckErrorFieldForThirtBlock() throws Exception {

        gra.setField(8, 0, 8);
        assertEquals(8, gra.getField(8, 0));
        assertEquals(8, gra.getField(6, 1));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(true, gra.checkErrorField(8, 0, 8));

    }

    @SmallTest
    public void testCheckErrorFieldForFourthBlock() throws Exception {

        gra.setField(0, 3, 8);
        assertEquals(8, gra.getField(0, 3));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(true, gra.checkErrorField(0, 3, 8));

    }

    @SmallTest
    public void testCheckErrorFieldForFifthBlock() throws Exception {

        gra.setField(4, 4, 6);
        assertEquals(6, gra.getField(4, 4));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(true, gra.checkErrorField(4, 4, 6));

    }

    @SmallTest
    public void testCheckErrorFieldForSixthBlock() throws Exception {

        gra.setField(6, 3, 6);
        assertEquals(6, gra.getField(6, 3));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(false, gra.checkErrorField(6, 3, 9));

    }


    @SmallTest
    public void testCheckErrorFieldForSeventhBlock() throws Exception {

        gra.setField(0, 8, 1);
        assertEquals(1, gra.getField(0, 8));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(true, gra.checkErrorField(0, 8, 1));

    }




    @SmallTest
    public void testCheckErrorFieldForFourth2Block() throws Exception {
        gra.setField(0, 3, 10);
        assertEquals(10, gra.getField(0, 3));
        gra.errorField(element);
        //wynik dla pierwszego bloku
        assertEquals(false, gra.checkErrorField(0, 3, 10));

    }
    */


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}