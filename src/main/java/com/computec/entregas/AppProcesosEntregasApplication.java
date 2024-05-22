package com.computec.entregas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppProcesosEntregasApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(AppProcesosEntregasApplication.class, args);
	}
	
	@Autowired
	@Qualifier("sqlServerDataSource")
	DataSource sqlServerDS;

	@Override
	public void run(String... args) throws Exception {
		
		String query = "SELECT C.id_cliente, C.nombre_cliente, P.id_producto, P.nombre_producto, SP.id_subproducto, SP.nombre_subproducto"
				+ " FROM clientes AS C INNER JOIN cliente_producto AS CP ON (C.id_cliente = CP.id_cliente)"
				+ "	INNER JOIN productos AS P ON (CP.id_producto = P.id_producto)"
				+ "	INNER JOIN producto_subproducto AS PS ON (PS.id_producto = P.id_producto)"
				+ "	INNER JOIN subproductos AS SP ON (PS.id_subproducto = SP.id_subproducto)"
				+ " WHERE C.id_cliente = ?";
		
		Connection sqlServerConn = sqlServerDS.getConnection();
		PreparedStatement preparedStatement = sqlServerConn.prepareStatement(query);
		preparedStatement.setInt(1, 1);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			System.out.println(String.format("[id_cliente: %d -> nombre_cliente: %s]",
					resultSet.getInt(1),
					resultSet.getString(2)));
		}
		
		sqlServerConn.close();
		
	}

}
