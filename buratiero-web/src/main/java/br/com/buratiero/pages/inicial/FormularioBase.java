package br.com.buratiero.pages.inicial;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Url;

public class FormularioBase<T> extends Form<T>
{

   private static final long serialVersionUID = 6474359486313380209L;

   public final static String ID_FEEDBACK = "feedback";

   public FormularioBase(String id)
   {
      super(id);
   }

   public FormularioBase(String id, IModel<T> model)
   {
      super(id, model);
   }
   
   protected String recuperaCaminhoJasper(String nomeRelatorio) {
      String arquivo = "/reports/".concat(nomeRelatorio);
      Url relativo = Url.parse(getRequest().getContextPath());
      String caminho = getRequestCycle().getUrlRenderer().renderFullUrl(relativo).concat(arquivo);
      if (caminho.contains("wicket//")) {
         caminho = caminho.replaceAll("wicket//", "");
      }
      return caminho;
   }

}
