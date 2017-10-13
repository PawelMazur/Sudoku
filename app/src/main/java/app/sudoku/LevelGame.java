package app.sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 2017-04-27.
 */
public class LevelGame {

    int level;

    protected List<String> easyLevel = new ArrayList<>();
    protected List<String> normalLevel = new ArrayList<>();
    protected List<String> hardLevel = new ArrayList<>();


    public LevelGame(int level) {
        this.level = level;
        init();
    }

    public LevelGame() {
        init();
    }

    private void init(){


        easyLevel.add(easyPuzzle0);
        easyLevel.add(easyPuzzle1);
        easyLevel.add(easyPuzzle2);
        easyLevel.add(easyPuzzle3);
        easyLevel.add(easyPuzzle4);

        easyLevel.add(easyPuzzle5);

        easyLevel.add(easyPuzzle6);

        easyLevel.add(easyPuzzle7);
        easyLevel.add(easyPuzzle8);
        easyLevel.add(easyPuzzle9);




        normalLevel.add(normalPuzzle0);
        normalLevel.add(normalPuzzle1);
        normalLevel.add(normalPuzzle2);
        normalLevel.add(normalPuzzle3);
        normalLevel.add(normalPuzzle4);
        normalLevel.add(normalPuzzle5);
        normalLevel.add(normalPuzzle6);

        normalLevel.add(normalPuzzle7);

        normalLevel.add(normalPuzzle8);

        normalLevel.add(normalPuzzle9);


        hardLevel.add(hardPuzzle0);
        hardLevel.add(hardPuzzle1);
        hardLevel.add(hardPuzzle2);
        hardLevel.add(hardPuzzle3);
        hardLevel.add(hardPuzzle4);
        hardLevel.add(hardPuzzle5);
        hardLevel.add(hardPuzzle6);
        hardLevel.add(hardPuzzle7);
        hardLevel.add(hardPuzzle8);
        hardLevel.add(hardPuzzle9);


    }

    private final String easyPuzzle0 =
            "000060489" +
            "604309100" +
            "000000000" +
            "530090801" +
            "000213600" +
            "006700903" +
            "005000300" +
            "800002000" +
            "240600010";


    private final String easyPuzzle1 =
            "300002070" +
            "410603080" +
            "000000009" +
            "852000006" +
            "960250107" +
            "100009008" +
            "040900005" +
            "000704003" +
            "630000704";

    private final String easyPuzzle2 =
            "208069007" +
            "300001208" +
            "107200006" +
            "704005603" +
            "000000004" +
            "906007805" +
            "003700502" +
            "470502069" +
            "500000001";

    private final String easyPuzzle3 =
            "000710082" +
            "400502009" +
            "082003071" +
            "000309047" +
            "360401058" +
            "107200006" +
            "020900714" +
            "700025603" +
            "006040005";

    private final String easyPuzzle4 =
            "000090001" +
            "470000009" +
            "600704002" +
            "005030004" +
            "700820603" +
            "906047025"+
            "050309007" +
            "040000906" +
            "300400208";

    private final String easyPuzzle5 =
            "740000039" +
            "609010508" +
            "800600701" +
            "170050903" +
            "960000050" +
            "280003070" +
            "400085090" +
            "090004205" +
            "500090400";

    private final String easyPuzzle6 =
            "407080390" +
            "006070005" +
            "520300407" +
            "104000903" +
            "205000174" +
            "060040800" +
            "001008030" +
            "630000008" +
            "850009040";

    private final String easyPuzzle7 =
            "740008609" +
            "802009001" +
            "600407008" +
            "005003004" +
            "960001052" +
            "100850063" +
            "410000306" +
            "520300400" +
            "306000205";

    private final String easyPuzzle8 =
            "000080302" +
            "900400005" +
            "340050010" +
            "706090403" +
            "003060000" +
            "120530700" +
            "890010500" +
            "500070201" +
            "201605007";

    private final String easyPuzzle9 =
            "360000000" +
            "004230800" +
            "000004200" +
            "070460003" +
            "820000014" +
            "500013020" +
            "001900000" +
            "007048300" +
            "000000045";


    private final String normalPuzzle0 =
            "103006089" +
            "006009000" +
            "709020056" +
            "204000091" +
            "060000004" +
            "090000060" +
            "305070000" +
            "608010300" +
            "002040678";

    private final String normalPuzzle1 =
            "030100058" +
            "820006047" +
            "700000906" +
            "600010805" +
            "500090004" +
            "470080603" +
            "309001082" +
            "008009071" +
            "100250009";


    private final String normalPuzzle2 =
            "100200069" +
            "309401082" +
            "058300001" +
            "900100208" +
            "004020036" +
            "020900047" +
            "600704025" +
            "002003000" +
            "470502093";

    private final String normalPuzzle3 =
            "000000000" +
            "401502090" +
            "502003004" +
            "000000000" +
            "005036007" +
            "704000906" +
            "300401502" +
            "107008009" +
            "000000000";

    private final String normalPuzzle4 =
            "000076001" +
            "200043060" +
            "007200030" +
            "000705009" +
            "109000056" +
            "050090403" +
            "012000078" +
            "070000045" +
            "600907002";

    private final String normalPuzzle5 =
            "400060009" +
            "706090403" +
            "009030700" +
            "010600008" +
            "605000302" +
            "908020005" +
            "200003007" +
            "807009000" +
            "500870001";

    private final String normalPuzzle6 =
            "807010034" +
            "000070001" +
            "091040060" +
            "750000003" +
            "180402706" +
            "000705000" +
            "070300640" +
            "302000078" +
            "640900302";

    private final String normalPuzzle7 =
            "700090000" +
            "000500704" +
            "802700006" +
            "017060500" +
            "508001093" +
            "006008000" +
            "205007009" +
            "070030208" +
            "900805007";

    private final String normalPuzzle8 =
            "000000006" +
            "609080014" +
            "000690005" +
            "085040009" +
            "170030200" +
            "900020000" +
            "400009082" +
            "008071003" +
            "390000001";

    private final String normalPuzzle9 =
            "650000070" +
            "000506000" +
            "014000005" +
            "007009000" +
            "002317000" +
            "000000800" +
            "500000630" +
            "000201000" +
            "030000097";

    private final String hardPuzzle0 =
            "300470000" +
            "008300401" +
            "107000000" +
            "006107000" +
            "020906000" +
            "010820006" +
            "090010005" +
            "580000000" +
            "470000600";


    private final String hardPuzzle1 =
            "000070000" +
            "500010806" +
            "870500010" +
            "000087004" +
            "600000900" +
            "907000000" +
            "402008005" +
            "008065002" +
            "700400108";

    private final String hardPuzzle2 =
            "040000006" +
            "000503019" +
            "000806000" +
            "002008705" +
            "090700402" +
            "060400108" +
            "001007004" +
            "600020087" +
            "007600001";


    private final String hardPuzzle3 =
            "002108060" +
            "760030008" +
            "100700002" +
            "007050300" +
            "050020907" +
            "300080054" +
            "000500000" +
            "040010076" +
            "009076003";

    private final String hardPuzzle4 =
            "800002006" +
            "003605009" +
            "506008043" +
            "005007030" +
            "000500008" +
            "708001000" +
            "004000901" +
            "001003080" +
            "600120054";

    private final String hardPuzzle5 =
            "680009300" +
            "304080901" +
            "900050000" +
            "809040006" +
            "500900203" +
            "240605019" +
            "005090100" +
            "700030005" +
            "130004098";

    private final String hardPuzzle6 =
            "506010003" +
            "800302006" +
            "003600800" +
            "021003600" +
            "300000021" +
            "600009304" +
            "700201005" +
            "002060008" +
            "460000102";

    private final String hardPuzzle7 =
            "800107036" +
            "000309000" +
            "903050047" +
            "300000071" +
            "407090502" +
            "205000009" +
            "000700600" +
            "701030000" +
            "600800704";

    private final String hardPuzzle8 =
            "630820000" +
            "700906005" +
            "508010003" +
            "007600002" +
            "005000309" +
            "006080401" +
            "003208007" +
            "050100006" +
            "070300058";

    private final String hardPuzzle9 =
            "009000000" +
            "080605020" +
            "501078000" +
            "000000700" +
            "706040102" +
            "004000000" +
            "000000903" +
            "090301080" +
            "000000600";

    /*
    private final String puzzleTrudne1 =
            "123456789"+
                    "456789123"+
                    "789123456"+
                    "234567891"+
                    "567891234"+
                    "891234567"+
                    "345678912"+
                    "678912345"+
                    "912345670";

    private final String puzzleTrudne2 =
            "123456789123456789123456789"+
                    "123456789123456789123456789"+
                    "123456789123456789123456789";


    */
    /*
    private final String puzzleTrudne1 =
            "009000000080605020501078000"+
            "000000700706040102004000000"+
            "000720903090301080000000600";

    private final String puzzleTrudne2 =
            "009000000080605020501078000"+
                    "000000700706040102004000000"+
                    "000720903090301080000000600";

    private final String puzzleTrudne3 =
            "009000000080605020501078000"+
                    "000000700706040102004000000"+
                    "000720903090301080000000600";

    private final String puzzleTrudne4 =
            "009000000080605020501078000"+
                    "000000700706040102004000000"+
                    "000720903090301080000000600";

    private final String puzzleTrudne5 =
            "009000000080605020501078000"+
                    "000000700706040102004000000"+
                    "000720903090301080000000600";
                    */
}
