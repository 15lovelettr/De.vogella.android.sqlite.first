package participation8.rlovelett.devogellaandroidsqlitefirst;

/**
 * Created by rlovelett on 3/31/2017.
 *
 * The main activity of the app. Creates the app interface and the app database upon app creation.
 */

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.Random;

public class MainActivity extends ListActivity {
    private CommentDataSource datasource; //creates a new database that can be edited and potentially deleted/recreated

    /**
     * onCreate - creates the entire app layout, and creates a new database of the class
     * CommentDataSource, also sets up an onClick method for the buttons of the app.
     *
     * @param savedInstanceState - current saved instance
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new CommentDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()) {
            case R.id.add: //if button ID id R.id.add, then add a new Randomized Comment object to the list of comment
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                comment = datasource.createComment(comments[nextInt]);
                adapter.add(comment);
                break;
            case R.id.delete: //if button ID id R.id.delete, then delete the first Comment object from the list of comments
                if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * Resumes app function once the database is finished being edited
     */
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    /**
     * Temporarily pauses app function as database is being edited. Time period waiting should be practically unnoticable.
     */
    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}
