package mx.edu.utng.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sun.management.ConnectorAddressLink;

public class DoctoAlumnoWS {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement ps;
	
	public DoctoAlumnoWS() {
		conectar();
	}
	private void conectar(){
		try {
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://localhost:5433/wstest","postgres","root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
		e.printStackTrace();
		}
	}
	public int addDocto(DoctoAlumno docto){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("INSERT INTO docto_alumno (date_reception,date_devolution,note,activo,document) "
						+ " VALUES (?,?,?,?,?);");
				ps.setString(1, docto.getDateTest());
				ps.setString(2, docto.getDateDevolution());
				ps.setString(3, docto.getNote());
				ps.setString(4, docto.getActivo());
				ps.setString(5, docto.getDocument());
				
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//para hacer un update
	public int updateDocto(DoctoAlumno docto){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("UPDATE docto_alumno  SET date_reception= ?,date_devolution= ?,note= ?,activo= ?,document=?  WHERE id= ?;");
					
				ps.setString(1, docto.getDateTest());
				ps.setString(2, docto.getDateDevolution());
				ps.setString(3, docto.getNote());
				ps.setString(4, docto.getActivo());
				ps.setString(5, docto.getDocument());
				ps.setInt(6, docto.getId());
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}//
	//para borrar
	public int removeDocto(int id){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("DELETE FROM docto_alumno  WHERE id= ?;");
				ps.setInt(1, id);
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//lista de doctos
	public DoctoAlumno[] getDoctoAlumnos(){
		DoctoAlumno[]result=null;
		List <DoctoAlumno> doctos=new ArrayList<DoctoAlumno>();
		if(connection!=null){
			try {
				statement=connection.createStatement();
				resultSet=statement.executeQuery("SELECT * FROM docto_alumno; ");
				while(resultSet.next()){
					DoctoAlumno docto=new DoctoAlumno(
							resultSet.getInt("id"),
							resultSet.getString("date_reception"),
							resultSet.getString("date_devolution"),
							resultSet.getString("note"),
							resultSet.getString("activo"), 
							resultSet.getString("document"));
					doctos.add(docto);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		result=doctos.toArray(new DoctoAlumno[doctos.size()]);
		return result;
	}
//obtener una 
public DoctoAlumno getDoctobyId(int id){
	DoctoAlumno docto=null;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("SELECT * FROM docto_alumno WHERE id = ?;");
			ps.setInt(1, id);
			resultSet=ps.executeQuery();
			while(resultSet.next()){
				docto=new DoctoAlumno(
						resultSet.getInt("id"),
						resultSet.getString("date_reception"),
						resultSet.getString("date_devolution"),
						resultSet.getString("note"),
						resultSet.getString("activo"), 
						resultSet.getString("document"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return docto;
}
/**
public static void main(String[] args) {
	DoctoAlumnoWS ws=new DoctoAlumnoWS();
	DoctoAlumno docto=new DoctoAlumno(0, "04/15", "012/8", "hola",1,"docu");
	ws.addDocto(docto);
	
}*/

}
