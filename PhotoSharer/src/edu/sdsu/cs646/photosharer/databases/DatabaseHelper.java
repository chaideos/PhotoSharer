package edu.sdsu.cs646.photosharer.databases;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * A class which deals with creating the database and tables to be used in the
 * app.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PhotoSharerDb";

    private static final int DATABASE_VERSION = 1;

    private static final String USERS_TABLE = "users";

    private static final String ID = "_id";

    private static final String NAME = "name";

    private static final String CREATE_USERS_TABLE = "create table "
	    + USERS_TABLE + " ( " + ID + " INTEGER PRIMARY KEY," + NAME
	    + " TEXT NOT NULL)";

    private static final String USER_COUNT_QUERY = "select count(*) from "
	    + USERS_TABLE;

    public DatabaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase appDb) {
	appDb.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase appDb, int oldVersion, int newVersion) {
	appDb.execSQL("drop table if exists " + USERS_TABLE);
	onCreate(appDb);
    }

    public long addUser(String user) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(NAME, user);
	long retVal = db.insert(USERS_TABLE, "", values);
	db.close();
	return retVal;
    }

    public long numOfUsers() {
	SQLiteDatabase db = this.getReadableDatabase();
	SQLiteStatement statement = db.compileStatement(USER_COUNT_QUERY);
	long retVal = 0;
	try {
	    retVal = statement.simpleQueryForLong();
	} catch (SQLiteDoneException e) {

	} finally {
	    statement.close();
	    db.close();
	}
	return retVal;
    }

    /**
     * Retrieves all the users currently present in the database.
     * 
     * @return - A list of users
     */
    public List<String> getUsers() {
	List<String> users = new ArrayList<String>();
	SQLiteDatabase db = this.getReadableDatabase();
	int columnIndex = -1;
	Cursor cursor = db.query(USERS_TABLE, new String[] { NAME }, null,
		null, null, null, null);
	if (cursor != null && cursor.moveToFirst()) {
	    columnIndex = cursor.getColumnIndex(NAME);
	    users.add(cursor.getString(columnIndex));
	    while (cursor.moveToNext()) {
		users.add(cursor.getString(columnIndex));
	    }
	    cursor.close();
	}

	db.close();
	return users;
    }
}
