package proj2.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public abstract class Communicator {
	
	public abstract void update(String command);
	
	public abstract CachedRowSet query(String command);
	
	public static Object conn(DataSource source, Function<Connection, Object> func) {
		try(Connection conn = source.getConnection()){
			return func.apply(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object stat(Connection conn, Function<Statement, Object> func) {
		try(Statement stat = conn.createStatement()){
			return func.apply(stat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public abstract DataSource getSource();
	
	public static CachedRowSet query(Communicator comm, String command) {
		return (CachedRowSet) conn(comm.getSource(), conn -> {return stat(conn, stat -> {try {
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet cacheset = factory.createCachedRowSet();
			cacheset.populate(stat.executeQuery(command));
			return cacheset;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		});});
	}
	
	public static int update(Communicator comm, String command) {
		return (int) conn(comm.getSource(), conn -> {return stat(conn, stat -> {try {
			return stat.executeUpdate(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(command);
			e.printStackTrace();
			return 0;
		}});});
	}
	
	public static String commandConversion(Object obj) {
		if(obj instanceof String) {
			return "'" + obj + "'";
		}else if(obj instanceof Boolean) {
			return (((Boolean) obj).booleanValue() ? 1 : 0) + "";
		}else if(obj instanceof Integer || obj instanceof Long || obj instanceof Short) {
			return obj + "";
		}
		System.out.println(obj);
		return obj.toString();
	}

}
