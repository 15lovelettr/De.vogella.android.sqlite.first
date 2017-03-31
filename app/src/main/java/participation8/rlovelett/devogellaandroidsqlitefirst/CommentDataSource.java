package participation8.rlovelett.devogellaandroidsqlitefirst;

/**
 * Created by rlovelett on 3/31/2017.
 *
 * Directly edits the database and the objects it contains.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentDataSource {

    // Database fields
    private SQLiteDatabase database; //The app database
    private MySQLiteHelper dbHelper; //The creator object of the app database
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT, MySQLiteHelper.COLUMN_RATING }; //The columns in the database

    /**
     * Constructor of the CommentDataSource class, it creates a new MySQLiteHelper object to create the database
     *
     * @param context - some Context object of the imported Context class
     */
    public CommentDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    /**
     * Creates new database using the SQLHelper class
     *
     * @throws SQLException - in case database creation fails?
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Closes down database.
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Creates a new Comment object of the Comment class and adds it into the database
     *
     * @param comment - a string that will hold a comment that will be saved in the Comment object
     * @return - Comment object with the new comment
     */
    public Comment createComment(String comment, String rating) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment); //puts new desired value into Database using MySQLiteHelper class
        values.put(MySQLiteHelper.COLUMN_RATING, rating); //puts new desired value into Database using MySQLiteHelper class
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values); //finds new insert location in database using an insert statement
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                null, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null); //Cursor of the database is set to find the location of the next determined insert point
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor); //adds new comment at cursor location
        cursor.close();
        return newComment; //returns the new comment in the database
    }

    /**
     * Deletes a comment object of the Comment class from the database
     *
     * @param comment - comment to be deleted. Set to delete the first comment from the list/database
     */
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id); //deletes comment that matches the current id
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null); //deletes comment matching the selected Comment id
    }

    /**
     * Returns an ArrayList containing all the comments in the database
     *
     * @return List<Comment> - ArrayList of all the comments in the database
     **/
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                null, null, null, null, null, null); //Database query to select all columns in database

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    /**
     * Uses the cursor object to determine where to put Comment object in the database
     *
     * @param cursor - object that marks the current available position in the database
     * @return Comment - return new comment object
     */
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment(); //creates new Comment object
        comment.setId(cursor.getLong(0)); //sets comment id for the database
        comment.setComment(cursor.getString(1)); //Sets the string comment in the Object
        comment.setRating(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_RATING)));
        return comment;
    }
}