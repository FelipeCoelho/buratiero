package br.com.buratiero.pages.cadastroprocessos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.ui.datepicker.DatePicker;
import br.com.buratiero.enuns.EnumMessagens;
import br.com.buratiero.enuns.EnumProcessoNovoImpugnacao;
import br.com.buratiero.enuns.EnumSImNao;
import br.com.buratiero.enuns.EnumTipoProcesso;
import br.com.buratiero.model.Cidade;
import br.com.buratiero.model.Colaborador;
import br.com.buratiero.model.Estado;
import br.com.buratiero.model.Processo;
import br.com.buratiero.pages.inicial.FormularioBase;
import br.com.buratiero.pages.inicial.InicioPage;
import br.com.buratiero.pages.ui.CampoRadioChoice;
import br.com.buratiero.pages.ui.Mascara;

public abstract class CadastroProcessoForm extends FormularioBase<Processo>
{

   private static final long serialVersionUID = 5295396202009958225L;

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
   private AjaxButton btnSalvar;
   private CampoRadioChoice<EnumProcessoNovoImpugnacao> campoNovoImpugnacao;
   private CampoRadioChoice<EnumTipoProcesso> campoTipoProcesso;
   private CampoRadioChoice<EnumSImNao> campoRecebimento;
   private TextArea<String> campoHistorico;
   private TextField<Double> campoValorHonorarioSolicitado;
   private Estado estadoSelecionado;
   private List<Estado> listaEstado;
   private TextField<Double> campoValorEfetivamenteRecebido;
   private TextField<Double> campoValorHonorarioFixado;
   private DatePicker<Date> campoDtRecebimento;
   private ModalConfirmacao modalConfirmaCriarColaborador;
   private Colaborador colaboradorSelecionado;

   public CadastroProcessoForm(String id, Processo processo)
   {
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
      criarCampoHistorico();
      criarBotaoVoltar();
      criarBotaoSalvar();
      criarModalConfirmaCriarColaborador();
   }

   private void criarModalConfirmaCriarColaborador()
   {
      modalConfirmaCriarColaborador = new ModalConfirmacao("modalConfirmaCriarColaborador")
      {
         private static final long serialVersionUID = 9212963871811757557L;

         @Override
         protected void confirmaCriar(AjaxRequestTarget target, Form<?> form)
         {
            colaboradorSelecionado = new Colaborador();
            colaboradorSelecionado.setDsNome(campoAutColaborador.getModelObject());
            salvaColaborador(colaboradorSelecionado);
            success(EnumMessagens.MS_3.getMenssagem());
            close(target);
            target.add(feedBack);
         }

      };
      modalConfirmaCriarColaborador.setOutputMarkupPlaceholderTag(true);
      addOrReplace(modalConfirmaCriarColaborador);
   }

   private void criarCampoDtRecebimento()
   {
      campoDtRecebimento = new DatePicker<Date>("dtRecebimento");
      campoDtRecebimento.setLabel(new Model<String>("Data de recebimento"));
      campoDtRecebimento.add(new Mascara("99/99/9999"));
      campoDtRecebimento.setOutputMarkupPlaceholderTag(true);

      addOrReplace(campoDtRecebimento);
   }

   private void criarCampoDtProtocolo()
   {
      campoDtProtocolo = new DatePicker<Date>("dtProtocolo");
      campoDtProtocolo.setLabel(new Model<String>("Data do protocolo"));
      campoDtProtocolo.add(new Mascara("99/99/9999"));
      campoDtProtocolo.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoDtProtocolo);
   }

   private void criarCampoDtDevolucaoCarga()
   {
      campoDtDevolucaoCarga = new DatePicker<Date>("dtDevolucaoCarga");
      campoDtDevolucaoCarga.setLabel(new Model<String>("Data da devolução da carga"));
      campoDtDevolucaoCarga.add(new Mascara("99/99/9999"));
      campoDtDevolucaoCarga.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoDtDevolucaoCarga);
   }

   private void criarCampoDtCarga()
   {
      campoDtCarga = new DatePicker<Date>("dtCarga");
      campoDtCarga.setLabel(new Model<String>("Data da Carga"));
      campoDtCarga.add(new Mascara("99/99/9999"));
      campoDtCarga.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoDtCarga);
   }

   private void criarCampoDtPraso()
   {
      campoDtPraso = new DatePicker<Date>("dtPraso");
      campoDtPraso.setLabel(new Model<String>("Data do prazo"));
      campoDtPraso.add(new Mascara("99/99/9999"));
      campoDtPraso.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoDtPraso);
   }

   private void criarCampoColaborador()
   {
      campoAutColaborador = new AutoCompleteTextField<String>("colaborador.dsNome")
      {
         private static final long serialVersionUID = -2414822763637484667L;

         @Override
         protected Iterator<String> getChoices(String input)
         {
            List<String> choices = recuperaNomeColaborador(input);
            if (choices.isEmpty())
            {
               choices = new ArrayList<String>(10);
            }

            return choices.iterator();
         }

      };
      campoAutColaborador.setLabel(new Model<String>("Colaborador"));
      campoAutColaborador.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoAutColaborador);
   }

   private void criarCampoReclamada()
   {
      campoDsReclamada = new AutoCompleteTextField<String>("dsReclamada"){
         private static final long serialVersionUID = -272749770735109050L;

         @Override
         protected Iterator<String> getChoices(String input)
         {
            List<String> choices = recuperaNomeReclamada(input);
            if (choices.isEmpty())
            {
               choices = new ArrayList<String>(10);
            }

            return choices.iterator();
         }
         
      };
      campoDsReclamada.setLabel(new Model<String>("Reclamada"));
      campoDsReclamada.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoDsReclamada);
   }

   private void criarCampoReclamante()
   {
      campoDsReclamante = new AutoCompleteTextField<String>("dsReclamante"){
         private static final long serialVersionUID = -2618793839614300508L;

         @Override
         protected Iterator<String> getChoices(String input)
         {
            List<String> choices = recuperaNomeReclamante(input);
            if (choices.isEmpty())
            {
               choices = new ArrayList<String>(10);
            }

            return choices.iterator();
         }
         
      };
      campoDsReclamante.setLabel(new Model<String>("Reclamente"));
      campoDsReclamante.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoDsReclamante);
   }

   private void criarCampoVara()
   {
      campoDsVara = new AutoCompleteTextField<String>("dsVara"){
         private static final long serialVersionUID = -5375125479131794426L;

         @Override
         protected Iterator<String> getChoices(String input)
         {
            List<String> choices = recuperaNomeVara(input);
            if (choices.isEmpty())
            {
               choices = new ArrayList<String>(10);
            }

            return choices.iterator();
         }
         
      };
      campoDsVara.setLabel(new Model<String>("Vara"));
      campoDsVara.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoDsVara);
   }

   private void criarCampoEstado()
   {
      campoUf =
         new DropDownChoice<Estado>("uf", getListaEstado(), new ChoiceRenderer<Estado>("uf",
            "codigo"));
      campoUf.setLabel(new Model<String>("UF:"));
      campoUf.setModel(new Model<Estado>());
      campoUf.setOutputMarkupPlaceholderTag(true);
      campoUf.add(new AjaxFormComponentUpdatingBehavior("onchange")
      {
         private static final long serialVersionUID = -7694805472994638144L;

         @Override
         protected void onUpdate(AjaxRequestTarget target)
         {
            if (null != campoUf.getModelObject())
            {
               campoAutCidade.setEnabled(campoUf.getModelObject() != null);
               estadoSelecionado = campoUf.getModelObject();
            }
            else
            {
               campoAutCidade.setEnabled(false);
            }

            target.add(campoAutCidade);
         }
      });
      addOrReplace(campoUf);
   }

   private void criarCampoAutoCompCidade()
   {
      campoAutCidade = new AutoCompleteTextField<String>("cidade.nome")
      {
         private static final long serialVersionUID = -2414822763637484667L;

         @Override
         protected Iterator<String> getChoices(String input)
         {
            List<String> choices = recuperaNomeCidade(input, getEstadoSelecionado());
            if (choices.isEmpty())
            {
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

   private void criarCampoValorHorarioRecebido()
   {
      campoValorEfetivamenteRecebido = new TextField<Double>("vlEfetivamenteRecebido");
      campoValorEfetivamenteRecebido
         .setLabel(new Model<String>("Valor dos honorários solicitados"));
      campoValorEfetivamenteRecebido.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoValorEfetivamenteRecebido);
   }

   private void criarCampoValorHorarioFixado()
   {
      campoValorHonorarioFixado = new TextField<Double>("vlHonorarioFixado");
      campoValorHonorarioFixado.setLabel(new Model<String>("Valor dos honorários solicitados"));
      campoValorHonorarioFixado.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoValorHonorarioFixado);
   }

   private void criarCampoValorHorarioSolicitado()
   {
      campoValorHonorarioSolicitado = new TextField<Double>("vlHonorarioSolicitado");
      campoValorHonorarioSolicitado.setLabel(new Model<String>("Valor dos honorários solicitados"));
      campoValorHonorarioSolicitado.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoValorHonorarioSolicitado);
   }

   private void criarCampoHistorico()
   {
      campoHistorico = new TextArea<String>("dsHistoricoProcesso");
      campoHistorico.setOutputMarkupPlaceholderTag(true);
      campoHistorico.setLabel(new Model<String>("Historico"));
      addOrReplace(campoHistorico);

   }

   private void criarCampoDataPublicacao()
   {
      campoDtpublicacao = new DatePicker<Date>("dtpublicacao");
      campoDtpublicacao.setLabel(new Model<String>("Data da publicação"));
      campoDtpublicacao.add(new Mascara("99/99/9999"));
      campoDtpublicacao.setOutputMarkupPlaceholderTag(true);
      addOrReplace(campoDtpublicacao);
   }

   private void criarCampoTipoProcesso()
   {
      campoTipoProcesso =
         new CampoRadioChoice<EnumTipoProcesso>("stTipoProcesso", Arrays.asList(EnumTipoProcesso
            .values()), new ChoiceRenderer<EnumTipoProcesso>("descricao", "codigo"));
      campoTipoProcesso.setLabel(new Model<String>("Tipo de processo"));
      campoTipoProcesso.setOutputMarkupPlaceholderTag(true);
      campoTipoProcesso.setSuffix("&nbsp");

      addOrReplace(campoTipoProcesso);
   }

   private void criarCampoProcessoNovoImpugnacao()
   {
      campoNovoImpugnacao =
         new CampoRadioChoice<EnumProcessoNovoImpugnacao>("stNovoImpugnacao",
            Arrays.asList(EnumProcessoNovoImpugnacao.values()),
            new ChoiceRenderer<EnumProcessoNovoImpugnacao>("descricao", "codigo"));
      campoNovoImpugnacao.setLabel(new Model<String>("Processo Novo/Impugnação"));
      campoNovoImpugnacao.setOutputMarkupPlaceholderTag(true);
      campoNovoImpugnacao.setSuffix("&nbsp");
      addOrReplace(campoNovoImpugnacao);
   }

   private void criarCampoRecebimento()
   {
      campoRecebimento =
         new CampoRadioChoice<EnumSImNao>("tpRecebido", Arrays.asList(EnumSImNao.values()),
            new ChoiceRenderer<EnumSImNao>("descricao", "codigo"));
      campoRecebimento.setLabel(new Model<String>("Recebimento"));
      campoRecebimento.setOutputMarkupPlaceholderTag(true);
      campoRecebimento.setSuffix("&nbsp");
      addOrReplace(campoRecebimento);
   }

   private void criarCampoAutoCompNuProcesso()
   {
      campoAutNuProcesso = new AutoCompleteTextField<String>("nuProcesso")
      {
         private static final long serialVersionUID = -2146246963759431344L;

         @Override
         protected Iterator<String> getChoices(String input)
         {
            List<String> choices = recuperaNumProcesso(input);
            if (choices.isEmpty())
            {
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

   private void criarCampoFeedBack()
   {
      feedBack = new FeedbackPanel(ID_FEEDBACK);
      feedBack.setOutputMarkupPlaceholderTag(true);
      addOrReplace(feedBack);
   }

   private void criarBotaoVoltar()
   {
      btnVoltar = new Button("btnVoltar")
      {
         private static final long serialVersionUID = 1720604726133878663L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new InicioPage());
         }
      };
      btnVoltar.setOutputMarkupPlaceholderTag(true);
      addOrReplace(btnVoltar);
   }

   private void criarBotaoSalvar()
   {
      btnSalvar = new AjaxButton("btnSalvar")
      {
         private static final long serialVersionUID = -68541459308773758L;

         @Override
         public void onSubmit(AjaxRequestTarget target, Form<?> form)
         {
            Cidade cidadeSelecionada =
               recuperaCidadePorUfNome(campoAutCidade.getModelObject(), getEstadoSelecionado());
            if (cidadeSelecionada.getCodigo() != null)
            {
               Colaborador colaboradorSelecionado =
                  recuperaColaboradorPorNome(campoAutColaborador.getModelObject());
               if (colaboradorSelecionado != null)
               {
                  if (colaboradorSelecionado.getId() != null)
                  {
                     Processo processoSelecionado = CadastroProcessoForm.this.getModelObject();
                     processoSelecionado.setCidade(cidadeSelecionada);
                     processoSelecionado.setColaborador(colaboradorSelecionado);
                     salvarProcesso(processoSelecionado);
                     getSession().success(EnumMessagens.MS_2.getMenssagem());
                     target.add(feedBack, CadastroProcessoForm.this);
                     setResponsePage(new CadastroProcessoPage());
                  }
                  else
                  {
                     modalConfirmaCriarColaborador.inicializar();
                     modalConfirmaCriarColaborador.show(target);
                  }
               }
               else
               {
                  error(EnumMessagens.ME_4.getMenssagem());
                  target.add(feedBack);
               }
            }
            else
            {
               error(EnumMessagens.ME_2.getMenssagem());
               target.add(feedBack);
            }
         }

         @Override
         public void onError(AjaxRequestTarget target, Form<?> form)
         {
            target.add(feedBack);
         }
      };
      btnSalvar.setOutputMarkupPlaceholderTag(true);
      addOrReplace(btnSalvar);

   }

   public Estado getEstadoSelecionado()
   {
      return estadoSelecionado;
   }

   public void setEstadoSelecionado(Estado estadoSelecionado)
   {
      this.estadoSelecionado = estadoSelecionado;
   }

   public List<Estado> getListaEstado()
   {
      if (listaEstado == null)
      {
         listaEstado = recuperarListaEstado();
      }
      return listaEstado;
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

}
