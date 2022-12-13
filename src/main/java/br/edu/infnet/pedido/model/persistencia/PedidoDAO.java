package br.edu.infnet.pedido.model.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;
import br.edu.infnet.pedido.model.entidade.Produto;
import br.edu.infnet.pedido.model.entidade.Usuario;

public class PedidoDAO extends JdbcDAO<Pedido>{

	@Override
	public Boolean salvar(Pedido obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean atualizar(Pedido obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deletar(Pedido obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obter(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> listarTodos() {
		String sql = "select p.codigo, p.data, c.nome, pr.descricao, pr.preco from pedido p "
				+ "	join cliente c"
				+ "	join itens_pedido i"
				+ "	join produto pr"
				+ "	on p.cliente_cod = c.codigo"
				+ "	and p.codigo = i.pedido_cod"
				+ "	and pr.codigo = i.produto_cod";
		Map<Long, Pedido> pedidos = new TreeMap<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long codigo = rs.getLong("codigo");
				LocalDate data = rs.getDate("data").toLocalDate();
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Pedido pedido = null;
				if(pedidos.get(codigo) == null) {
					pedido = new Pedido(codigo, data, new Cliente(nome));
					pedido.setProdutos(new ArrayList<>());
					pedidos.put(codigo, pedido);
				}
				Produto produto = new Produto(null, descricao, preco);
				pedido = pedidos.get(codigo);
				pedido.getProdutos().add(produto);
			}
			return new ArrayList<Pedido>(pedidos.values());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
