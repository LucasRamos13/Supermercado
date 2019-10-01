/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Supermercado;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class DaoSupermercado {
     public static boolean inserir(Supermercado objeto) {
        String sql = "INSERT INTO Supermercado (valor, fundacao, funcionarios, razao_social, nome_fantasia) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, objeto.getValor());
            ps.setDate(2, Date.valueOf(objeto.getFundacao()));
            ps.setInt(3, objeto.getFuncionarios());
            ps.setString(4, objeto.getRazao_social());
            ps.setString(5, objeto.getNome_fantasia());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
       public static boolean alterar(Supermercado objeto) {
        String sql = "UPDATE Supermercado SET valor = ?, fundacao = ?, funcionarios = ?, razao_social = ?, nome_fantasia = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, objeto.getValor());
            ps.setDate(2, Date.valueOf(objeto.getFundacao()));
            ps.setInt(3, objeto.getFuncionarios());
            ps.setString(4, objeto.getRazao_social());
            ps.setString(5, objeto.getNome_fantasia());
            ps.setInt(6, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       
         public static boolean excluir(Supermercado objeto) {
        String sql = "DELETE FROM Supermercado WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         
      public static List<Supermercado> consultar() {
        List<Supermercado> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome_fantasia, razao_social, funcionarios, valor, fundacao FROM Supermercado";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supermercado objeto = new Supermercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome_fantasia(rs.getString("nome_fantasia"));
                objeto.setRazao_social(rs.getString("razao_social"));
                objeto.setFuncionarios(rs.getInt("funcionarios"));
                objeto.setValor(rs.getDouble("valor"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
      
       public static Supermercado consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome_fantasia, razao_social, funcionarios, valor, fundacao FROM Supermercado WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supermercado objeto = new Supermercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome_fantasia(rs.getString("nome_fantasia"));
                objeto.setRazao_social(rs.getString("razao_social"));
                objeto.setFuncionarios(rs.getInt("funcionarios"));
                objeto.setValor(rs.getDouble("valor"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
       
}
