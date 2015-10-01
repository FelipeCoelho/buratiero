package br.com.buratiero.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.buratiero.dao.GenericoDao;
import br.com.buratiero.dao.ProcessoDao;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.model.Cidade;
import br.com.buratiero.model.Colaborador;
import br.com.buratiero.model.Estado;
import br.com.buratiero.model.Processo;
import br.com.buratiero.util.ParametrosOrdenacao;
import br.com.buratiero.util.Provider;

@Service("processoService")
public class ProcessoService extends GenericoService<Processo> implements Provider<Processo>
{

   private static final long serialVersionUID = 4277888698084457532L;
   @Autowired
   @Qualifier("processoDao")
   private ProcessoDao processoDao;

   @Autowired
   private CidadeService cidadeService;

   @Autowired
   private EstadoService estadoService;

   @Autowired
   private ColaboradorService colaboradorService;

   @Override
   protected GenericoDao<Processo> getDao()
   {
      return processoDao;
   }

   public List<String> recuperaNumeroProcesso(String input)
   {
      return processoDao.recuperaNumeroProcesso(input);
   }

   public List<String> recuperaNomeCidade(String input, Estado estado)
   {
      return cidadeService.recuperaNomeCidade(input, estado);
   }

   public List<String> recuperaUfEstado(String input)
   {
      return estadoService.recuperaUfEstado(input);
   }

   public Estado recuperaEstadoPorUf(String uf)
   {
      return estadoService.recuperaEstadoPorUf(uf);
   }

   public List<Estado> recuperaListaEstado()
   {
      return estadoService.recuperaListaEstado();
   }

   public List<String> recuperaNomeColaborador(String input)
   {
      return colaboradorService.recuperaNomeColaborador(input);
   }

   public Cidade recuperaCidadePorUfNome(String nome, Estado estado)
   {
      return cidadeService.recuperaCidadePorUfNome(nome, estado);
   }

   public Colaborador recuperaColaboradorPorNome(String nome)
   {
      return colaboradorService.recuperaColaboradorPorNome(nome);
   }

   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void salvarProcesso(Processo processo)
   {
      processoDao.incluir(processo);
   }

   public void salvaColaborador(Colaborador colaborador)
   {
      colaboradorService.salvaColaborador(colaborador);
   }

   public boolean existeColaborador(Colaborador colaboradorSelecionado)
   {
      return colaboradorService.existeColaborador(colaboradorSelecionado);
   }

   public List<String> recuperaNomeReclamada(String input)
   {
      return processoDao.recuperaNomeReclamada(input);
   }

   public List<String> recuperaNomeReclamante(String input)
   {
      return processoDao.recuperaNomeReclamante(input);
   }

   public List<String> recuperaNomeVara(String input)
   {
      return processoDao.recuperaNomeVara(input);
   }

   public List<Processo> recuperaListaProcesso()
   {
      return processoDao.findAll();
   }

   @Override
   public List<Processo> buscaListaGrid(Entidade entidade, long first, long count,
      ParametrosOrdenacao ordernar)
   {
      return processoDao.buscaListaGrid(entidade, first, count, ordernar);
   }

   @Override
   public int contadorListaGrid(Entidade entidade)
   {
      return processoDao.contadorListaGrid(entidade);
   }

   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void excluirProcesso(Processo processo)
   {
      processoDao.delete(processo.getCodigo());
   }

   public List<Processo> recuperaListaProcesso(Processo filtro)
   {
      return processoDao.recuperaListaProcesso(filtro);
   }

}
