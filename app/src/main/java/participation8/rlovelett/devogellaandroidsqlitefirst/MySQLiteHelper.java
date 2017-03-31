package participation8.rlovelett.devogellaandroidsqlitefirst;

/**
 * Created by rlovelett on 3/31/2017.
 *
 * Helps create and update the database for this Comment application.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments"; //A string of comments on the database table itself
    public static final String COLUMN_ID = "_id"; //The id for a column in the database table
    public static final String COLUMN_COMMENT = "comment"; //A comment for a corresponding column in the database table

    private static final String DATABASE_NAME = "commments.db"; //Name of the database
    private static final int DATABASE_VERSION = 1; //The version of the database, to be incremented with updates

    // Database SQL statement to Create Database for app
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    /**
     *     Class constructor for MySQLiteHelper class. Creates an object of the parent class.
     *
     *     @param context - Some context object of the imported Context class.
     */
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *     Creates a new SQLiteDatabase.
     *
     *     @param database - some SQLiteDatabase object that's utilized as a database.
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    /**
     *     Updates the SQLiteDatabase.
     *
     *     @param db - some SQLiteDatabase object that's utilized as a database.
     *     @param oldVersion - The current version of the app database.
     *     @param newVersion - The desired new version of the database that corresponds with this update.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db); //recreate database using new newVersion of DATABASE_VERSION
    }

}