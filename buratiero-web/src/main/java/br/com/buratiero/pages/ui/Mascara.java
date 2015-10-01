package br.com.buratiero.pages.ui;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

public class Mascara extends Behavior
{

   private static final long serialVersionUID = -21858635008236591L;
   private String mascara;
   
   public Mascara(String mascara)
   {
      this.mascara = mascara;
   }
   
   @Override
   public void renderHead(Component component, IHeaderResponse response)
   {
      super.renderHead(component, response);
      response.render(OnDomReadyHeaderItem.forScript(String.format("$('#%s').mask(\"%s\")", component.getMarkupId(), mascara)));
   }

}
