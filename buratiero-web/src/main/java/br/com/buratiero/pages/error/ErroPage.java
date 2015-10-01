package br.com.buratiero.pages.error;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

public class ErroPage extends WebPage
{

   private static final long serialVersionUID = -7786797700571866325L;

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      super.renderHead(response);
      response.render(CssHeaderItem.forUrl("comum/css/estilo.css"));
   }

}
