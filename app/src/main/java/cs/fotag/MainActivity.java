package cs.fotag;


import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.Toast;
import cs.fotag.model.Model;

public class MainActivity extends AppCompatActivity {
    GridView gridview;
    Model model;
    ImageAdapter imageAdapter;
    RatingBar filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model=new Model();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridview = (GridView) findViewById(R.id.gridview);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("MVC", "save state");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("MVC", "restore state");
        super.onRestoreInstanceState(savedInstanceState);
        gridview.setAdapter(new ImageAdapter(this,model));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem filter = menu.findItem(R.id.action_rating);
        RatingBar ratingBar = (RatingBar) MenuItemCompat.getActionView(filter);
        ratingBar.setStepSize(1);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                model.setFilter((int) rating);
                Toast.makeText(getApplicationContext(), "" + model.getFilter(), Toast.LENGTH_SHORT).show();
                model.image.clear();
                gridview.removeAllViewsInLayout();
                gridview.setAdapter(new ImageAdapter(getApplicationContext(), model));
                imageAdapter.notifyDataSetChanged();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.action_load){
            imageAdapter=new ImageAdapter(this,model);
            gridview.setAdapter(imageAdapter);
            return true;
        }
        if(id==R.id.action_clear){
            gridview.removeAllViewsInLayout();
        }

        return super.onOptionsItemSelected(item);
    }
}

