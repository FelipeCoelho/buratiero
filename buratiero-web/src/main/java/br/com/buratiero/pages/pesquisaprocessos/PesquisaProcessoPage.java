package br.com.buratiero.pages.pesquisaprocessos;

import java.util.List;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import br.com.buratiero.model.Cidade;
import br.com.buratiero.model.Colaborador;
import br.com.buratiero.model.Estado;
import br.com.buratiero.model.Processo;
import br.com.buratiero.pages.inicial.BasePage;
import br.com.buratiero.service.ProcessoService;

@MountPath("pesquisa_processo")
public class PesquisaProcessoPage extends BasePage {
	private static final long serialVersionUID = -7503861008537584081L;

	private PesquisaProcessoForm formulario;

	@SpringBean
	private ProcessoService processoService;

	public PesquisaProcessoPage() {
		this(new Processo());
	}

	public PesquisaProcessoPage(Processo processo) {
		formulario = new PesquisaProcessoForm(ID_FORMULARIO, processo) {
			private static final long serialVersionUID = -7239237848930033126L;

			@Override
			protected List<String> recuperaNumProcesso(String input) {
				return processoService.recuperaNumeroProcesso(input);
			}

			@Override
			protected List<String> recuperaUfEstado(String input) {
				return processoService.recuperaUfEstado(input);
			}

			@Override
			protected List<String> recuperaNomeCidade(String input, Estado estado) {
				return processoService.recuperaNomeCidade(input, estado);
			}

			@Override
			protected Estado recuperaEstadoPorUf(String uf) {
				return processoService.recuperaEstadoPorUf(uf);
			}

			@Override
			protected List<Estado> recuperarListaEstado() {
				return processoService.recuperaListaEstado();
			}

			@Override
			protected List<String> recuperaNomeColaborador(String input) {
				return processoService.recuperaNomeColaborador(input);
			}

			@Override
			protected Cidade recuperaCidadePorUfNome(String nome, Estado estado) {
				return processoService.recuperaCidadePorUfNome(nome, estado);
			}

			@Override
			protected Colaborador recuperaColaboradorPorNome(String nome) {
				return processoService.recuperaColaboradorPorNome(nome);
			}

			@Override
			protected void salvarProcesso(Processo processo) {
				processoService.salvarProcesso(processo);
			}

			@Override
			protected void salvaColaborador(Colaborador colaborador) {
				processoService.salvaColaborador(colaborador);
			}

			@Override
			protected boolean existeColaborador(Colaborador colaboradorSelecionado) {
				return processoService.existeColaborador(colaboradorSelecionado);
			}

			@Override
			protected List<String> recuperaNomeReclamada(String input) {
				return processoService.recuperaNomeReclamada(input);
			}

			@Override
			protected List<String> recuperaNomeReclamante(String input) {
				return processoService.recuperaNomeReclamante(input);
			}

			@Override
			protected List<String> recuperaNomeVara(String input) {
				return processoService.recuperaNomeVara(input);
			}

			@Override
			protected List<Processo> recuperaListaProcesso() {
				return processoService.recuperaListaProcesso();
			}

			@Override
			protected void excluirProcesso(Processo processo) {
				processoService.excluirProcesso(processo);
			}

			@Override
			protected List<Processo> recuperaListaProcesso(Processo filtro) {
				return processoService.recuperaListaProcesso(filtro);
			}

		};
		formulario.setOutputMarkupPlaceholderTag(true);
		addOrReplace(formulario);
	}
}
