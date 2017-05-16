/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dadostabelas.dal;

import java.sql.*;
/**
 *
 * @author Ilton
 */
public class ModuloConexao {
    public static Connection conector() {
      
        Connection conexao;
        
        String driver = "com.mysql.jdbc.Driver" ;
        
        String url = "jdbc:mysql://10.0.24.24:3306/MassaTotal";
        
        String user = "unimed";
        
        String pwd = "Unimed,247";
                                          
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, pwd);
            return conexao;
        } catch (Exception e) {
            return null;
        }
        
    }
}
