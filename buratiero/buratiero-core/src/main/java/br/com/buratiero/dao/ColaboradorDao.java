package br.com.buratiero.dao;

import java.util.List;
import br.com.buratiero.model.Colaborador;

public interface ColaboradorDao extends ScpDao<Colaborador>
{

   List<String> recuperaNomeColaborador(String input);

   Colaborador recuperaColaboradorPorNome(String nome);

   boolean existeColaborador(Colaborador colaboradorSelecionado);

}
