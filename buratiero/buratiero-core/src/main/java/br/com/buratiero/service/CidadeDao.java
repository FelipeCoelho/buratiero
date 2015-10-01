package br.com.buratiero.service;

import java.util.List;
import br.com.buratiero.dao.ScpDao;
import br.com.buratiero.model.Cidade;
import br.com.buratiero.model.Estado;

public interface CidadeDao extends ScpDao<Cidade>
{

   List<String> recuperaNomeCidade(String input, Estado estado);

   Cidade recuperaCidadePorUfNome(String nome, Estado estado);

}
