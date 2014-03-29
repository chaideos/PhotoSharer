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

import edu.sdsu.cs646.photosharer.data.User;
import edu.sdsu.cs646.photosharer.data.UserPhoto;

/**
 * A class which deals with creating the database and tables to be used in the
 * app.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PhotoSharerDb";

    private static final int DATABASE_VERSION = 1;

    private static final String USERS_TABLE = "users";

    private static final String PHOTOS_TABLE = "photos";

    private static final String ID = "_id";

    private static final String NAME = "name";

    private static final String LINK = "link";

    private static final String CREATE_USERS_TABLE = "create table "
	    + USERS_TABLE + " ( " + ID + " INTEGER PRIMARY KEY," + NAME
	    + " TEXT NOT NULL)";

    private static final String CREATE_PHOTOS_TABLE = "create table "
	    + PHOTOS_TABLE + " ( " + ID + " INTEGER PRIMARY KEY," + NAME
	    + "TEXT NOT NULL," + LINK + " TEXT NOT NULL)";

    public DatabaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase appDb) {
	appDb.execSQL(CREATE_USERS_TABLE);
	appDb.execSQL(CREATE_PHOTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase appDb, int oldVersion, int newVersion) {
	appDb.execSQL("drop table if exists " + USERS_TABLE);
	appDb.execSQL("drop table if exists " + PHOTOS_TABLE);
	onCreate(appDb);
    }

    public long addUser(User user) {
	ContentValues values = new ContentValues();
	values.put(NAME, user.getName());
	values.put(ID, Integer.parseInt(user.getId()));
	return addRecord(USERS_TABLE, values);
    }

    public long addPhoto(UserPhoto photo) {
	ContentValues values = new ContentValues();
	values.put(NAME, photo.getName());
	values.put(ID, Integer.parseInt(photo.getId()));
	return addRecord(PHOTOS_TABLE, values);
    }

    public long numOfUsers() {
	return getCount(USERS_TABLE);
    }

    public long numOfPhotos() {
	return getCount(PHOTOS_TABLE);
    }

    /**
     * Retrieves all the users currently present in the database.
     * 
     * @return - A list of users
     */
    public List<User> getUsers() {
	List<User> users = new ArrayList<User>();
	SQLiteDatabase db = this.getReadableDatabase();
	int nameColumn = -1, idColumn = -1;
	Cursor cursor = db.query(USERS_TABLE, new String[] { ID, NAME }, null,
		null, null, null, null);
	if (cursor != null && cursor.moveToFirst()) {
	    User user = null;
	    nameColumn = cursor.getColumnIndex(NAME);
	    idColumn = cursor.getColumnIndex(ID);
	    user = new User(String.valueOf(cursor.getInt(idColumn)),
		    cursor.getString(nameColumn));
	    users.add(user);
	    while (cursor.moveToNext()) {
		user = new User(String.valueOf(cursor.getInt(idColumn)),
			cursor.getString(nameColumn));
		users.add(user);
	    }
	    cursor.close();
	}

	db.close();
	return users;
    }

    private long getCount(String tableName) {
	SQLiteDatabase db = this.getReadableDatabase();
	String countStr = "select count(*) from " + tableName;
	SQLiteStatement statement = db.compileStatement(countStr);
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

    private long addRecord(String table, ContentValues values) {
	SQLiteDatabase db = this.getWritableDatabase();
	long retVal = db.insert(table, "", values);
	db.close();
	return retVal;
    }
}
