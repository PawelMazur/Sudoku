package app.sudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String ZNACZNIK = "MainActivity";

    public static MainActivity instance;
    private int level;
    private String pathname = "data/data/app.sudoku/cache/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sudoku);

        instance = this;

        View przyciskKontynuacja = findViewById(R.id.przycisk_kontynuacja);
        przyciskKontynuacja.setOnClickListener(this);
        View przyciskNowa = findViewById(R.id.przycisk_nowa);
        przyciskNowa.setOnClickListener(this);
        View przyciskInformacja = findViewById(R.id.przycisk_informacje);
        przyciskInformacja.setOnClickListener( this);
        View przyciskWyjscie = findViewById(R.id.przycisk_wyjscie);
        przyciskWyjscie.setOnClickListener(this);


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
            case R.id.action_settings:
                //startActivity(new Intent(this, Preferencje.class));
                return true;
        }
        /*
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
        */
        return false;

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.przycisk_informacje:
                startActivity(new Intent(this, InfoGame.class));
                break;
            case R.id.przycisk_nowa:
                otworzDialogNowaGra();
                //startActivity(new Intent(this, Gra.class));
                break;
            case R.id.przycisk_wyjscie:
                finish();
                break;
            case R.id.przycisk_kontynuacja:
                String filename = "currentElement";
                String[] elementy = new File(pathname).list();
                File file = null;
                for (String element: elementy) {
                    if (element.substring(0, filename.length()).equals(filename)) {
                        file = new File(pathname + element);
                    }
                }
                if (file != null){
                    uruchomGre(-1);
                } else {
                    Toast.makeText(getApplicationContext(), "Nie została zapisa żadna gra", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void otworzDialogNowaGra(){
        new AlertDialog.Builder(this).setTitle(R.string.tytul_nowa_gra)
                .setItems(R.array.poziomtrudnosci, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int i){
                        uruchomGre(i);
                    }
                }).show();
    }

    private void uruchomGre(int i){
        Log.d(ZNACZNIK, "kliknięto " + i);
        //Intent intent = new Intent(MainActivity.this, Gra.class);
        Intent intent = new Intent(MainActivity.this, Game.class);
        setLevel(i);
        //intent.putExtra("", i);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Muzyka.play(this, R.raw.nature);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //Muzyka.stop(this);
    }
}
