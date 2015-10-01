package br.com.buratiero.dao;

import java.util.List;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.model.Processo;
import br.com.buratiero.util.ParametrosOrdenacao;

public interface ProcessoDao extends ScpDao<Processo>
{

   List<String> recuperaNumeroProcesso(String input);

   List<String> recuperaNomeReclamada(String input);

   List<String> recuperaNomeReclamante(String input);

   List<String> recuperaNomeVara(String input);

   List<Processo> buscaListaGrid(Entidade entidade, long first, long count,
      ParametrosOrdenacao ordernar);

   int contadorListaGrid(Entidade entidade);

   List<Processo> recuperaListaProcesso(Processo filtro);

}
