package br.edu.infnet.pedido.model.persistencia;

import br.edu.infnet.pedido.model.entidade.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends JdbcDAO<Usuario> {

    public UsuarioDAO() {
    }

    @Override
    public Boolean salvar(Usuario usuario) {

        String sqlUsuario = "insert into usuario(nome,cpf, codigo) values (?,null, null)";

        try {
            pstm = con.prepareStatement(sqlUsuario);
            pstm.setString(1, usuario.getNome());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean atualizar(Usuario usuario) {

        String sqlUsuario = "update usuario set nome = ? where codigo = ? ";

        try {
            pstm = con.prepareStatement(sqlUsuario);
            pstm.setString(1, usuario.getNome());
            pstm.setLong(2, usuario.getCodigo());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deletar(Usuario usuario) {

        String sqlUsuario = "delete  from usuario where codigo =? ";

        try {
            pstm = con.prepareStatement(sqlUsuario);
            pstm.setLong(1, usuario.getCodigo());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Usuario obter(Long codigoUsuario) {
        String sqlUsuario = "select *  from usuario where codigo =? ";
        Usuario usuario = new Usuario();
        try {
            pstm = con.prepareStatement(sqlUsuario);
            pstm.setLong(1, codigoUsuario);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                Long codigoDB = rs.getLong("codigo");
                usuario = new Usuario(codigoDB, nome);
            }
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Usuario> listarTodos() {

        String sqlUsuario = "select * from usuario ";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sqlUsuario);
            while (rs.next()) {
                String nome = rs.getString("nome");
                Long codigo = rs.getLong("codigo");
                Usuario usuario = new Usuario(codigo, nome);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
