/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.AutoresDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author juan
 */
public class AutoresDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<AutoresDTO> lista = new ArrayList<>();

    public void cadastrarAutor(AutoresDTO objautoresdto) {

        String sql = "insert into autores (nome, email, telefone) values (?,?,?)";
        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objautoresdto.getNome());
            pstm.setString(2, objautoresdto.getEmail());
            pstm.setString(3, objautoresdto.getTelefone());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "AutorDAO Cadastrar" + e);
        }

    }

    public ArrayList<AutoresDTO> pesquisarAutor() {
        
        String sql = "select * from autores";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                AutoresDTO objAutoresDTO = new AutoresDTO();
                objAutoresDTO.setId(rs.getInt("id"));
                objAutoresDTO.setNome(rs.getString("nome"));
                objAutoresDTO.setEmail(rs.getString("email"));
                objAutoresDTO.setTelefone(rs.getString("telefone"));

                lista.add(objAutoresDTO);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AutorDAO Pesquisar: " + e);
        }

        return lista;
    }
    
    public void alterarAutor(AutoresDTO objAutoresDTO){
        
        String sql = "update autores set nome = ?, email = ?, telefone = ? where id = ?";
        conn = new ConexaoDAO().conectaBD();
        
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objAutoresDTO.getNome());
            pstm.setString(2, objAutoresDTO.getEmail());
            pstm.setString(3, objAutoresDTO.getTelefone());
            pstm.setInt(4, objAutoresDTO.getId());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "AutorDAO Editar" + e);
        }
        
    }
    
    public void excluirAutor(AutoresDTO objAutoresDTO){
        
        String sql = "delete from autores where id = ?";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objAutoresDTO.getId());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "AutorDAO Excluir" + e);
        }
    }

}
