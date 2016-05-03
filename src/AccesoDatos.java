import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AccesoDatos {
	
	public AccesoDatos(){
		
	}
	
	private String usuario="T103-PC16\\Privada";
	private String clave1="";
	private Connection con;
	private String clave;
	private String bd = "";

	public void ConexionSql() {
		try {
			con= DriverManager.getConnection("jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;integratedSecurity=true;databaseName=AdventureWorks2014",usuario,clave1);
			System.out.println("Conexion Establecida a Sql");
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		// System.out.println("Conexion establecida con la bd " + bd);
	}
	

//	public void getConexion() {
//		try {
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/information_schema", usuario, clave);
//			System.out.println("Conexion Establecida a MySql");
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			System.exit(-1);
//		}
//		// System.out.println("Conexion establecida con la bd " + bd);
//	}  
	
	public ArrayList<ArrayList<String>> getRegistrosTablaBD1(String tabla, String bd) {
		ArrayList<ArrayList<String>> lista = new ArrayList<>();
		this.ConexionSql();
		try {
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM " + bd + "." + tabla);
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> registro = new ArrayList<>();
				ArrayList<String> nombreCol = new ArrayList<>();
				for (int i = 1; i <= col; i++) {
					if (lista.size() == 0) {
						nombreCol.add((rsmd.getColumnName(i)));
					}
					registro.add(rs.getString(i));
				}
				if (lista.size() == 0) {
					lista.add(nombreCol);
				}
				lista.add(registro);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public void mostrarLista(ArrayList<ArrayList<String>> lista){
		
	for (ArrayList<String>datos : lista){
		
		for (String dato : datos){
			
			System.out.print(dato + "\t");
			
		}
	
			System.out.print("\n");
	}
	
		
	}

	public static void main(String[] args) {
	
		AccesoDatos accesoDatos = new AccesoDatos();

		accesoDatos.mostrarLista(accesoDatos.getRegistrosTablaBD1("HumanResources.department", "AdventureWorks2014"));
		
//		accesoDatos.getConexion();

	}

}

