package br.com.buratiero.pages.cadastroprocessos;

import java.util.List;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import br.com.buratiero.model.Cidade;
import br.com.buratiero.model.Colaborador;
import br.com.buratiero.model.Estado;
import br.com.buratiero.model.Processo;
import br.com.buratiero.pages.inicial.BasePage;
import br.com.buratiero.service.ProcessoService;

@MountPath("cadastro_processo")
public class CadastroProcessoPage extends BasePage {

	private static final long serialVersionUID = 3445955510717842433L;

	@SpringBean
	private ProcessoService processoService;

	private CadastroProcessoForm formulario;

	public CadastroProcessoPage() {
		criarFormulario(new Processo());
	}

	public CadastroProcessoPage(Processo processo) {
		criarFormulario(processo);
	}

	private void criarFormulario(Processo processo) {
		formulario = new CadastroProcessoForm("formulario", processo) {
			private static final long serialVersionUID = 6127943810800686014L;

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

		};
		formulario.setOutputMarkupPlaceholderTag(true);
		addOrReplace(formulario);
	}
}
