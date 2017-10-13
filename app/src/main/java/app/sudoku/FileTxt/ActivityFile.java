package app.sudoku.FileTxt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.sudoku.R;

/**
 * Created by Pawel on 2017-04-29.
 */
public class ActivityFile extends Activity {


    private String ZNACZNIK = "ActivityFile";
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private String pathname = "data/data/app.sudoku/cache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pliki);
        listView = (ListView) findViewById(R.id.listView);


        File file = new File(pathname);

        if (file.exists()){

            String[] elements = new File(pathname).list();

            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(elements));

            arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                    R.layout.elementy_listy, list);

            Log.d(ZNACZNIK, "List : " + list + " Adapter : " + arrayAdapter + " Context : " + getApplicationContext());
            listView.setAdapter(arrayAdapter);

        }

    }


}
