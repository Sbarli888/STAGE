package example.stage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDatabaseOperation 
{
	private SQLiteDatabase ldb;
	private Context lcontext;
	private LoginDatabaseHelper ldh;

	public LoginDatabaseOperation(Context context) 
	{
		this.lcontext = context;
	}

	public void createWriteModeDatabase() throws SQLException 
	{
		ldh = new LoginDatabaseHelper(lcontext, LoginDatabaseHelper.db_name,null, 1);
		ldb = ldh.getWritableDatabase();
	}

	public void close() throws SQLException 
	{
		ldh.close();
	}

	public void insertRow(String uname, String mobno, String passwd,String confpasswd) throws SQLException 
	{
		ContentValues values = new ContentValues();

		values.put(LoginDatabaseHelper.username, uname);
		values.put(LoginDatabaseHelper.mobileno, mobno);
		values.put(LoginDatabaseHelper.password, passwd);
		values.put(LoginDatabaseHelper.confpassword, confpasswd);

		ldb.insert(LoginDatabaseHelper.table_name, null, values);
	}

	public void updateRow(String uname, String mobno, String password,String confpassword)throws SQLException
	{
		ContentValues values = new ContentValues();
		values.put(LoginDatabaseHelper.username, uname);
		values.put(LoginDatabaseHelper.mobileno, mobno);
		values.put(LoginDatabaseHelper.password, password);
		values.put(LoginDatabaseHelper.confpassword, confpassword);
		ldb.insert(LoginDatabaseHelper.table_name, null, values);
	}

	public Cursor fetchLine(String username, String password)throws SQLException 
	{
		String q= "SELECT "+  LoginDatabaseHelper.password + " FROM " + LoginDatabaseHelper.table_name 
                + "  WHERE " + LoginDatabaseHelper.mobileno + " = '" + username  +"'"+  " AND "+ LoginDatabaseHelper.password + "= '" + password +"'";
		

		return ldb.rawQuery(q, null);

	}

}
