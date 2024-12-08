package appDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import app.MyConnection;
import appModels.Activo;
import appModels.Cripto;
import appModels.Fiat;
import appModels.Moneda;



public class ActivoDAOjdbc implements ActivoDAO{
	
	public List<Activo> listarActivosCripto(){
		String sql = "SELECT * FROM ACTIVO_CRIPTO ";
		Connection con = null;
      	List<Activo> lista = new LinkedList<Activo>();
      	try {
        	con = MyConnection.getCon();
          	Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);    
        	while(resul.next()) {
          		String nomenclatura = resul.getString("NOMENCLATURA");
          		double cantidad = resul.getDouble("CANTIDAD");
          		Activo activo = new Activo(nomenclatura, cantidad);
          		lista.add(activo);
          	} 
        	resul.close();
       		sent.close();
        	return lista; // La función finalizó con éxito          	
        } catch (SQLException e) {
//      		System.out.println(e.getMessage());
      		return null; // error 2: SQLException
      	}
	}
	public List<Activo> listarActivosFiat(){
		String sql = "SELECT * FROM ACTIVO_FIAT ";
		Connection con = null;
      	List<Activo> lista = new LinkedList<Activo>();
      	try {
        	con = MyConnection.getCon();
          	Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);    
        	while(resul.next()) {
          		String nomenclatura = resul.getString("NOMENCLATURA");
          		double cantidad = resul.getDouble("CANTIDAD");
          		Activo activo = new Activo(nomenclatura, cantidad);
          		lista.add(activo);
          	} 
        	resul.close();
       		sent.close();
        	return lista; // La función finalizó con éxito          	
        } catch (SQLException e) {
//      		System.out.println(e.getMessage());
      		return null; // error 2: SQLException
      	}
	}
	public List<Activo> listarActivos(){
		String sql = "SELECT * FROM ACTIVO_CRIPTO UNION ALL SELECT * FROM ACTIVO_FIAT ";
      	Connection con = null;
      	List<Activo> lista = new LinkedList<Activo>();
      	try {
        	con = MyConnection.getCon();
          	Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);    
        	while(resul.next()) {
          		String nomenclatura = resul.getString("NOMENCLATURA");
          		double cantidad = resul.getDouble("CANTIDAD");
          		Activo activo = new Activo(nomenclatura, cantidad);
          		lista.add(activo);
          	} 
        	resul.close();
       		sent.close();
        	return lista; // La función finalizó con éxito          	
        } catch (SQLException e) {
//      		System.out.println(e.getMessage());
      		return null; // error 2: SQLException
      	}
	}
	
	public boolean actualizarActivo(Activo activo, Moneda moneda) {
		String sql = null;
		if(moneda instanceof Cripto) {
			sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD='"+ activo.getCantidad()+"' WHERE NOMENCLATURA=\""+activo.getNomenclatura()+"\"";
		}
		else if(moneda instanceof Fiat) {
			sql = "UPDATE ACTIVO_FIAT SET CANTIDAD='"+ activo.getCantidad()+"' WHERE NOMENCLATURA=\""+activo.getNomenclatura()+"\"";
		} else {
			return false;
		}
		Connection con = null;
		try {
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
	    	stmt.close();
	    	return true;
	    	} 
		catch (SQLException e){
	    	return false;
		}
	}
	public boolean crearActivo(Activo activo, Moneda moneda) {
		String sql = null;
		if(moneda instanceof Cripto) {
			sql = "INSERT INTO ACTIVO_CRIPTO VALUES (\""+ moneda.getNomenclatura()+"\", '"+activo.getCantidad()+ "')";
		}
		else if(moneda instanceof Fiat) {
			sql = "INSERT INTO ACTIVO_FIAT VALUES (\""+ moneda.getNomenclatura()+"\", '"+activo.getCantidad()+ "')";
		} else {
			return false;
		}
		Connection con = null;
		try {
			con = MyConnection.getCon();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
	    	stmt.close();
	    	return true;
	    	} 
		catch (SQLException e){
	    	return false;
		}
	}
	public Activo existeActivo(Moneda moneda) {
		String table= null;
		if(moneda instanceof Cripto)
			table = "ACTIVO_CRIPTO";
		else if (moneda instanceof Fiat)
			table = "ACTIVO_FIAT";
		
		String sql = "SELECT * FROM " + table +" WHERE NOMENCLATURA=\""+moneda.getNomenclatura()+"\"";
		Connection con = null;
		try {
        	con = MyConnection.getCon();

            Statement sent = con.createStatement();
        	ResultSet resul = sent.executeQuery(sql);
            if (!resul.next()){
          		resul.close();
          		sent.close();
          		return null;
        	}
            String nomenclatura = resul.getString("NOMENCLATURA");
          	double cantidad = resul.getDouble("CANTIDAD");
          	Activo activo = new Activo(nomenclatura, cantidad);
          	return activo;
		}
		catch(SQLException e){
//			System.out.println("code:" +e.getErrorCode() + " - " + e.hashCode() + "\n mensaje:" + e.getMessage());
	    	return null;
		}
	}
}
