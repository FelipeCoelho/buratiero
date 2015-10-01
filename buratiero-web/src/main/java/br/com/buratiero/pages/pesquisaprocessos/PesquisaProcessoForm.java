package br.com.buratiero.pages.pesquisaprocessos;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Generics;
import org.odlabs.wiquery.ui.datepicker.DatePicker;
import br.com.buratiero.enuns.EnumMessagens;
import br.com.buratiero.enuns.EnumProcessoNovoImpugnacao;
import br.com.buratiero.enuns.EnumSImNao;
import br.com.buratiero.enuns.EnumTipoProcesso;
import br.com.buratiero.model.Cidade;
import br.com.buratiero.model.Colaborador;
import br.com.buratiero.model.Estado;
import br.com.buratiero.model.Processo;
import br.com.buratiero.pages.cadastroprocessos.CadastroProcessoPage;
import br.com.buratiero.pages.inicial.FormularioBase;
import br.com.buratiero.pages.inicial.InicioPage;
import br.com.buratiero.pages.ui.ActionButtonPanel;
import br.com.buratiero.pages.ui.AjaxDownload;
import br.com.buratiero.pages.ui.CampoRadioChoice;
import br.com.buratiero.pages.ui.JasperUtil;
import br.com.buratiero.pages.ui.Mascara;
import br.com.buratiero.pages.ui.ModalConfirmacao;
import br.com.buratiero.pages.ui.gridgenerica.DataGridGenerica;
import br.com.buratiero.pages.ui.provider.ProviderGenerico;
import br.com.buratiero.service.ProcessoService;
import br.com.buratiero.util.ParametrosOrdenacao;

public abstract class PesquisaProcessoForm extends FormularioBase<Processo> {

	private static final long serialVersionUID = -5694572929727874117L;

	@SpringBean
	private ProcessoService processoService;

	private FeedbackPanel feedBack;

	private AutoCompleteTextField<String> campoAutNuProcesso;
	private DropDownChoice<Estado> campoUf;
	private AutoCompleteTextField<String> campoAutCidade;
	private AutoCompleteTextField<String> campoDsVara;
	private DatePicker<Date> campoDtpublicacao;
	private AutoCompleteTextField<String> campoDsReclamante;
	private AutoCompleteTextField<String> campoDsReclamada;
	private AutoCompleteTextField<String> campoAutColaborador;
	private DatePicker<Date> campoDtCarga;
	private DatePicker<Date> campoDtPraso;
	private DatePicker<Date> campoDtDevolucaoCarga;
	private DatePicker<Date> campoDtProtocolo;
	private Button btnVoltar;
	private Button btnLimpar;
	private AjaxButton btnPesquisar;
	private CampoRadioChoice<EnumProcessoNovoImpugnacao> campoNovoImpugnacao;
	private CampoRadioChoice<EnumTipoProcesso> campoTipoProcesso;
	private CampoRadioChoice<EnumSImNao> campoRecebimento;
	private TextField<Double> campoValorHonorarioSolicitado;
	private Estado estadoSelecionado;
	private List<Estado> listaEstado;
	private TextField<Double> campoValorEfetivamenteRecebido;
	private TextField<Double> campoValorHonorarioFixado;
	private DatePicker<Date> campoDtRecebimento;
	private WebMarkupContainer informacaoVazia;
	private List<Processo> listProcesso;
	private DataGridGenerica<Processo, String> gridGenerica;
	private ProviderGenerico<Processo, String> providerGenerico;
	private Processo filtro;
	private ModalConfirmacao modalConfirmacao;
	private Processo processoSelecionado;
	private AjaxDownload<Void> downloadRelatorio;

	public PesquisaProcessoForm(String id, Processo processo) {
		super(id, new CompoundPropertyModel<Processo>(processo));

		setOutputMarkupPlaceholderTag(true);
		criarCampoFeedBack();
		criarCampoAutoCompNuProcesso();
		criarCampoEstado();
		criarCampoAutoCompCidade();
		criarCampoVara();
		criarCampoDataPublicacao();
		criarCampoReclamante();
		criarCampoReclamada();
		criarCampoProcessoNovoImpugnacao();
		criarCampoTipoProcesso();
		criarCampoColaborador();
		criarCampoDtCarga();
		criarCampoDtPraso();
		criarCampoDtDevolucaoCarga();
		criarCampoDtProtocolo();
		criarCampoRecebimento();
		criarCampoDtRecebimento();
		criarCampoValorHorarioSolicitado();
		criarCampoValorHorarioFixado();
		criarCampoValorHorarioRecebido();
		criarBotaoVoltar();
		criarBotaoLimpar();
		criarBotaoPesquisar();
		criarLinkRelatorio();
		criaInformacao();
		criarModalConfirmacao();
		criarGridProcesso();
	}

	private void criarCampoFeedBack() {
		feedBack = new FeedbackPanel(ID_FEEDBACK);
		feedBack.setOutputMarkupPlaceholderTag(true);
		addOrReplace(feedBack);
	}

	private void criarCampoAutoCompNuProcesso() {
		campoAutNuProcesso = new AutoCompleteTextField<String>("nuProcesso") {
			private static final long serialVersionUID = -2146246963759431344L;

			@Override
			protected Iterator<String> getChoices(String input) {
				List<String> choices = recuperaNumProcesso(input);
				if (choices.isEmpty()) {
					choices = new ArrayList<String>(10);
				}
				return choices.iterator();
			}
		};
		campoAutNuProcesso.setOutputMarkupPlaceholderTag(true);
		campoAutNuProcesso.setLabel(new Model<String>("Número do processo"));
		campoAutNuProcesso.add(new Mascara("9999999-99.9999.9.99.9999"));
		addOrReplace(campoAutNuProcesso);

	}

	private void criarCampoEstado() {
		campoUf = new DropDownChoice<Estado>("uf", getListaEstado(), new ChoiceRenderer<Estado>("uf", "codigo"));
		campoUf.setLabel(new Model<String>("UF:"));
		campoUf.setModel(new Model<Estado>());
		campoUf.setOutputMarkupPlaceholderTag(true);
		campoUf.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = -7694805472994638144L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (null != campoUf.getModelObject()) {
					campoAutCidade.setEnabled(campoUf.getModelObject() != null);
					estadoSelecionado = campoUf.getModelObject();
				} else {
					campoAutCidade.setEnabled(false);
				}

				target.add(campoAutCidade);
			}
		});
		addOrReplace(campoUf);
	}

	private void criarCampoAutoCompCidade() {
		campoAutCidade = new AutoCompleteTextField<String>("cidade.nome") {
			private static final long serialVersionUID = -2414822763637484667L;

			@Override
			protected Iterator<String> getChoices(String input) {
				List<String> choices = recuperaNomeCidade(input, getEstadoSelecionado());
				if (choices.isEmpty()) {
					choices = new ArrayList<String>(10);
				}

				return choices.iterator();
			}

		};
		campoAutCidade.setEnabled(false);
		campoAutCidade.setLabel(new Model<String>("Cidade"));
		campoAutCidade.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoAutCidade);
	}

	private void criarCampoVara() {
		campoDsVara = new AutoCompleteTextField<String>("dsVara") {
			private static final long serialVersionUID = -5375125479131794426L;

			@Override
			protected Iterator<String> getChoices(String input) {
				List<String> choices = recuperaNomeVara(input);
				if (choices.isEmpty()) {
					choices = new ArrayList<String>(10);
				}

				return choices.iterator();
			}

		};
		campoDsVara.setLabel(new Model<String>("Vara"));
		campoDsVara.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoDsVara);
	}

	private void criarCampoDataPublicacao() {
		campoDtpublicacao = new DatePicker<Date>("dtpublicacao");
		campoDtpublicacao.setLabel(new Model<String>("Data da publicação"));
		campoDtpublicacao.add(new Mascara("99/99/9999"));
		campoDtpublicacao.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoDtpublicacao);
	}

	private void criarCampoReclamante() {
		campoDsReclamante = new AutoCompleteTextField<String>("dsReclamante") {
			private static final long serialVersionUID = -2618793839614300508L;

			@Override
			protected Iterator<String> getChoices(String input) {
				List<String> choices = recuperaNomeReclamante(input);
				if (choices.isEmpty()) {
					choices = new ArrayList<String>(10);
				}

				return choices.iterator();
			}

		};
		campoDsReclamante.setLabel(new Model<String>("Reclamente"));
		campoDsReclamante.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoDsReclamante);
	}

	private void criarCampoReclamada() {
		campoDsReclamada = new AutoCompleteTextField<String>("dsReclamada") {
			private static final long serialVersionUID = -272749770735109050L;

			@Override
			protected Iterator<String> getChoices(String input) {
				List<String> choices = recuperaNomeReclamada(input);
				if (choices.isEmpty()) {
					choices = new ArrayList<String>(10);
				}

				return choices.iterator();
			}

		};
		campoDsReclamada.setLabel(new Model<String>("Reclamada"));
		campoDsReclamada.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoDsReclamada);
	}

	private void criarCampoProcessoNovoImpugnacao() {
		campoNovoImpugnacao = new CampoRadioChoice<EnumProcessoNovoImpugnacao>("stNovoImpugnacao", Arrays.asList(EnumProcessoNovoImpugnacao.values()),
				new ChoiceRenderer<EnumProcessoNovoImpugnacao>("descricao", "codigo"));
		campoNovoImpugnacao.setLabel(new Model<String>("Processo Novo/Impugnação"));
		campoNovoImpugnacao.setOutputMarkupPlaceholderTag(true);
		campoNovoImpugnacao.setSuffix("&nbsp");
		addOrReplace(campoNovoImpugnacao);
	}

	private void criarCampoTipoProcesso() {
		campoTipoProcesso = new CampoRadioChoice<EnumTipoProcesso>("stTipoProcesso", Arrays.asList(EnumTipoProcesso.values()), new ChoiceRenderer<EnumTipoProcesso>("descricao", "codigo"));
		campoTipoProcesso.setLabel(new Model<String>("Tipo de processo"));
		campoTipoProcesso.setOutputMarkupPlaceholderTag(true);
		campoTipoProcesso.setSuffix("&nbsp");

		addOrReplace(campoTipoProcesso);
	}

	private void criarCampoColaborador() {
		campoAutColaborador = new AutoCompleteTextField<String>("colaborador.dsNome") {
			private static final long serialVersionUID = -2414822763637484667L;

			@Override
			protected Iterator<String> getChoices(String input) {
				List<String> choices = recuperaNomeColaborador(input);
				if (choices.isEmpty()) {
					choices = new ArrayList<String>(10);
				}

				return choices.iterator();
			}

		};
		campoAutColaborador.setLabel(new Model<String>("Colaborador"));
		campoAutColaborador.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoAutColaborador);
	}

	private void criarCampoDtCarga() {
		campoDtCarga = new DatePicker<Date>("dtCarga");
		campoDtCarga.setLabel(new Model<String>("Data da Carga"));
		campoDtCarga.add(new Mascara("99/99/9999"));
		campoDtCarga.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoDtCarga);
	}

	private void criarCampoDtPraso() {
		campoDtPraso = new DatePicker<Date>("dtPraso");
		campoDtPraso.setLabel(new Model<String>("Data do prazo"));
		campoDtPraso.add(new Mascara("99/99/9999"));
		campoDtPraso.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoDtPraso);
	}

	private void criarCampoDtDevolucaoCarga() {
		campoDtDevolucaoCarga = new DatePicker<Date>("dtDevolucaoCarga");
		campoDtDevolucaoCarga.setLabel(new Model<String>("Data da devolução da carga"));
		campoDtDevolucaoCarga.add(new Mascara("99/99/9999"));
		campoDtDevolucaoCarga.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoDtDevolucaoCarga);
	}

	private void criarCampoDtProtocolo() {
		campoDtProtocolo = new DatePicker<Date>("dtProtocolo");
		campoDtProtocolo.setLabel(new Model<String>("Data do protocolo"));
		campoDtProtocolo.add(new Mascara("99/99/9999"));
		campoDtProtocolo.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoDtProtocolo);
	}

	private void criarCampoRecebimento() {
		campoRecebimento = new CampoRadioChoice<EnumSImNao>("tpRecebido", Arrays.asList(EnumSImNao.values()), new ChoiceRenderer<EnumSImNao>("descricao", "codigo"));
		campoRecebimento.setLabel(new Model<String>("Recebimento"));
		campoRecebimento.setOutputMarkupPlaceholderTag(true);
		campoRecebimento.setSuffix("&nbsp");
		addOrReplace(campoRecebimento);
	}

	private void criarCampoDtRecebimento() {
		campoDtRecebimento = new DatePicker<Date>("dtRecebimento");
		campoDtRecebimento.setLabel(new Model<String>("Data de recebimento"));
		campoDtRecebimento.add(new Mascara("99/99/9999"));
		campoDtRecebimento.setOutputMarkupPlaceholderTag(true);

		addOrReplace(campoDtRecebimento);
	}

	private void criarCampoValorHorarioSolicitado() {
		campoValorHonorarioSolicitado = new TextField<Double>("vlHonorarioSolicitado");
		campoValorHonorarioSolicitado.setLabel(new Model<String>("Valor dos honorários solicitados"));
		campoValorHonorarioSolicitado.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoValorHonorarioSolicitado);
	}

	private void criarCampoValorHorarioFixado() {
		campoValorHonorarioFixado = new TextField<Double>("vlHonorarioFixado");
		campoValorHonorarioFixado.setLabel(new Model<String>("Valor dos honorários solicitados"));
		campoValorHonorarioFixado.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoValorHonorarioFixado);
	}

	private void criarCampoValorHorarioRecebido() {
		campoValorEfetivamenteRecebido = new TextField<Double>("vlEfetivamenteRecebido");
		campoValorEfetivamenteRecebido.setLabel(new Model<String>("Valor dos honorários solicitados"));
		campoValorEfetivamenteRecebido.setOutputMarkupPlaceholderTag(true);
		addOrReplace(campoValorEfetivamenteRecebido);
	}

	private void criarBotaoVoltar() {
		btnVoltar = new Button("btnVoltar") {
			private static final long serialVersionUID = 1720604726133878663L;

			@Override
			public void onSubmit() {
				setResponsePage(new InicioPage());
			}
		};
		btnVoltar.setOutputMarkupPlaceholderTag(true);
		addOrReplace(btnVoltar);
	}

	private void criarBotaoPesquisar() {
		btnPesquisar = new AjaxButton("btnPesquisar") {
			private static final long serialVersionUID = -68541459308773758L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				filtro = PesquisaProcessoForm.this.getModelObject();

				if (getEstadoSelecionado() != null) {
					Cidade cidadeSelecionada = recuperaCidadePorUfNome(campoAutCidade.getModelObject(), getEstadoSelecionado());
					if (cidadeSelecionada != null) {
						filtro.setCidade(cidadeSelecionada);
					}
				}
				if (campoAutColaborador.getModelObject() != null && !campoAutColaborador.getModelObject().equals("")) {
					Colaborador colaboradorSelecionado = recuperaColaboradorPorNome(campoAutColaborador.getModelObject());
					if (colaboradorSelecionado.getCodigo() != null) {
						filtro.setColaborador(colaboradorSelecionado);
					}
				}
				providerGenerico = null;
				criarGridProcesso();
				target.add(gridGenerica);

			}

			@Override
			public void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedBack);
			}
		};
		btnPesquisar.setOutputMarkupPlaceholderTag(true);
		addOrReplace(btnPesquisar);
	}

	private void criarLinkRelatorio() {
		add(new AjaxLink<Void>("imprimir") {
			private static final long serialVersionUID = 3591707367903577336L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				filtro = PesquisaProcessoForm.this.getModelObject();

				if (getEstadoSelecionado() != null) {
					Cidade cidadeSelecionada = recuperaCidadePorUfNome(campoAutCidade.getModelObject(), getEstadoSelecionado());
					if (cidadeSelecionada != null) {
						filtro.setCidade(cidadeSelecionada);
					}
				}
				if (campoAutColaborador.getModelObject() != null && !campoAutColaborador.getModelObject().equals("")) {
					Colaborador colaboradorSelecionado = recuperaColaboradorPorNome(campoAutColaborador.getModelObject());
					if (colaboradorSelecionado.getCodigo() != null) {
						filtro.setColaborador(colaboradorSelecionado);
					}
				}
				downloadRelatorio.iniciar(target);
			}
		});

		downloadRelatorio = new AjaxDownload<Void>() {
			private static final long serialVersionUID = -3249685931594863019L;

			@Override
			protected void escrever(OutputStream output) throws IOException {
				List<Processo> listaProcesso = recuperaListaProcesso(filtro);
				Map<String, Object> parametros = Generics.newHashMap(0);
				output.write(JasperUtil.gerarJasperParaByte(new URL(recuperaCaminhoJasper("RelatorioProcesso.jasper")).openStream(), parametros, listaProcesso));
			}
		};
		add(downloadRelatorio);
	}

	private void criarBotaoLimpar() {
		btnLimpar = new Button("btnLimpar") {
			private static final long serialVersionUID = -6909426890562775268L;

			@Override
			public void onSubmit() {
				setResponsePage(new PesquisaProcessoPage());
			}
		};
		btnLimpar.setOutputMarkupPlaceholderTag(true);
		addOrReplace(btnLimpar);
	}

	private void criarGridProcesso() {
		final List<IColumn<Processo, String>> columns = new ArrayList<IColumn<Processo, String>>();
		final List<AjaxLink<Processo>> listaBotoes = new ArrayList<AjaxLink<Processo>>();

		listaBotoes.add(criarBotaoExcluir());
		listaBotoes.add(criarBotaoEditar());
		criarColunas(columns, listaBotoes);

		gridGenerica = new DataGridGenerica<Processo, String>("table", columns, getProviderGenerico(), 5) {
			private static final long serialVersionUID = -2837712007974126400L;

			@Override
			protected void onConfigure() {
				// setVisibilityAllowed(getItemCount() > 0);
				informacaoVazia.setVisibilityAllowed(getItemCount() == 0);
			}
		};
		gridGenerica.setOutputMarkupPlaceholderTag(true);
		addOrReplace(gridGenerica);
	}

	private void criarColunas(final List<IColumn<Processo, String>> columns, final List<AjaxLink<Processo>> listaBotoes) {
		columns.add(DataGridGenerica.criaColunar("Número do processo", "nuProcesso", true, 10));
		columns.add(DataGridGenerica.criaColunar("Cidade", "cidade.nome", true, 10));
		columns.add(DataGridGenerica.criaColunar("Vara", "dsVara", true, 10));
		columns.add(DataGridGenerica.criaColunar("Data da publicação", "dtpublicacao", true, 10));
		columns.add(DataGridGenerica.criaColunar("Reclamante", "dsReclamante", true, 5));
		columns.add(DataGridGenerica.criaColunar("Reclamada", "dsReclamada", true, 5));
		columns.add(DataGridGenerica.criaColunar("Processo Novo/Impugnação", "stNovoImpugnacao", true, 5));
		columns.add(DataGridGenerica.criaColunar("Tipo de Processo", "stTipoProcesso", true, 5));
		columns.add(DataGridGenerica.criaColunar("Colaborador", "colaborador.dsNome", true, 5));
		columns.add(DataGridGenerica.criaColunar("Data da Carga", "dtCarga", true, 5));
		columns.add(DataGridGenerica.criaColunar("Data do prazo", "dtPraso", true, 5));
		columns.add(DataGridGenerica.criaColunar("Data da devolução da carga", "dtDevolucaoCarga", true, 5));
		columns.add(DataGridGenerica.criaColunar("Data do protocolo", "dtProtocolo", true, 5));
		columns.add(DataGridGenerica.criaColunar("Valor dos honorários solicitados", "vlHonorarioSolicitado", true, 5));
		columns.add(DataGridGenerica.criaColunar("Valor dos honorários fixados", "vlHonorarioFixado", true, 5));
		columns.add(DataGridGenerica.criaColunar("Recebimento", "tpRecebido", true, 5));
		columns.add(DataGridGenerica.criaColunar("Data de recebimento", "dtRecebimento", true, 5));
		columns.add(DataGridGenerica.criaColunar("Valor efetivamente recebido", "vlEfetivamenteRecebido", true, 5));
		columns.add(DataGridGenerica.criaColunar("Histórico do processo", "dsHistoricoProcesso", true, 5));
		columns.add(new AbstractColumn<Processo, String>(new Model<String>("Opções")) {
			private static final long serialVersionUID = -3102670641136395641L;

			@Override
			public String getCssClass() {
				return "tam5";
			}

			@Override
			public void populateItem(Item<ICellPopulator<Processo>> cellItem, String componentId, IModel<Processo> entidade) {
				cellItem.add(new ActionButtonPanel<Processo>(componentId, entidade, listaBotoes, true, true));

			}
		});
	}

	private AjaxLink<Processo> criarBotaoEditar() {
		return new AjaxLink<Processo>("fa fa-pencil") {
			private static final long serialVersionUID = -2007593370707695822L;

			@Override
			public void onClick(final AjaxRequestTarget target) {
				setResponsePage(new CadastroProcessoPage(getModelObject()));
			}
		};
	}

	private AjaxLink<Processo> criarBotaoExcluir() {
		return new AjaxLink<Processo>("fa fa-trash") {
			private static final long serialVersionUID = -2007593370707695822L;

			@Override
			public void onClick(final AjaxRequestTarget target) {
				processoSelecionado = getModelObject();
				modalConfirmacao.inicializar();
				modalConfirmacao.show(target);
			}
		};
	}

	public ProviderGenerico<Processo, String> getProviderGenerico() {
		if (providerGenerico == null) {
			providerGenerico = new ProviderGenerico<Processo, String>(processoService, filtro);
			providerGenerico.setOrdernar(new ParametrosOrdenacao("nuProcesso", true));
		}
		return providerGenerico;
	}

	private void criaInformacao() {
		informacaoVazia = new WebMarkupContainer("containerVazio");
		informacaoVazia.setOutputMarkupPlaceholderTag(true);
		addOrReplace(informacaoVazia);

	}

	private void criarModalConfirmacao() {
		modalConfirmacao = new ModalConfirmacao("modalConfirmacao") {
			private static final long serialVersionUID = 2414350397084701093L;

			@Override
			protected void confirmaExclusao(AjaxRequestTarget target, Form<?> form) {
				excluirProcesso(processoSelecionado);
				success(EnumMessagens.MS_4.getMenssagem());
				close(target);
				target.add(feedBack);
			}
		};
		modalConfirmacao.setOutputMarkupPlaceholderTag(true);
		addOrReplace(modalConfirmacao);
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public List<Estado> getListaEstado() {
		if (listaEstado == null) {
			listaEstado = recuperarListaEstado();
		}
		return listaEstado;
	}

	public List<Processo> getListProcesso() {
		if (listProcesso == null) {
			listProcesso = recuperaListaProcesso();
		}
		return listProcesso;
	}

	public void setListProcesso(List<Processo> listProcesso) {
		this.listProcesso = listProcesso;
	}

	protected abstract List<Estado> recuperarListaEstado();

	protected abstract List<String> recuperaNumProcesso(String input);

	protected abstract List<String> recuperaUfEstado(String input);

	protected abstract List<String> recuperaNomeCidade(String input, Estado estado);

	protected abstract List<String> recuperaNomeColaborador(String colaborador);

	protected abstract List<String> recuperaNomeReclamada(String input);

	protected abstract List<String> recuperaNomeReclamante(String input);

	protected abstract List<String> recuperaNomeVara(String input);

	protected abstract Estado recuperaEstadoPorUf(String uf);

	protected abstract Cidade recuperaCidadePorUfNome(String nome, Estado estado);

	protected abstract Colaborador recuperaColaboradorPorNome(String nome);

	protected abstract void salvarProcesso(Processo processo);

	protected abstract void salvaColaborador(Colaborador colaborador);

	protected abstract boolean existeColaborador(Colaborador colaboradorSelecionado);

	protected abstract List<Processo> recuperaListaProcesso();

	protected abstract void excluirProcesso(Processo processo);

	protected abstract List<Processo> recuperaListaProcesso(Processo filtro);

}
