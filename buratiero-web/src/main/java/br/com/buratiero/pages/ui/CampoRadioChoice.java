package br.com.buratiero.pages.ui;

import java.util.List;
import java.util.Map;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.IOnChangeListener;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.value.IValueMap;

public class CampoRadioChoice<T> extends RadioChoice<T>
{

   private static final long serialVersionUID = 1879602974222311661L;
   private String prefix = "";
   private String suffix = "<br />\n";
   private StringBuilder css = new StringBuilder(" style=\"margin-left: 7px;margin-right: 7px;height: 5px;\"");

   private LabelPosition labelPosition = LabelPosition.AFTER;

   /**
    * Constructor
    * @param id See Component
    * @see org.apache.wicket.Component#Component(String)
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String)
    */
   public CampoRadioChoice(final String id)
   {
      super(id);
   }

   /**
    * Constructor
    * @param id See Component
    * @param choices The list of choices in the radio choice
    * @see org.apache.wicket.Component#Component(String)
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, List)
    */
   public CampoRadioChoice(final String id, final List<? extends T> choices)
   {
      super(id, choices);
   }

   /**
    * Constructor
    * @param id See Component
    * @param renderer The rendering engine
    * @param choices The list of choices in the radio choice
    * @see org.apache.wicket.Component#Component(String)
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String,
    *      List,IChoiceRenderer)
    */
   public CampoRadioChoice(final String id, final List<? extends T> choices,
      final IChoiceRenderer<? super T> renderer)
   {
      super(id, choices, renderer);
   }

   /**
    * Constructor
    * @param id See Component
    * @param model See Component
    * @param choices The list of choices in the radio choice
    * @see org.apache.wicket.Component#Component(String, IModel)
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel, List)
    */
   public CampoRadioChoice(final String id, IModel<T> model, final List<? extends T> choices)
   {
      super(id, model, choices);
   }

   /**
    * Constructor
    * @param id See Component
    * @param model See Component
    * @param choices The list of choices in the radio choice
    * @param renderer The rendering engine
    * @see org.apache.wicket.Component#Component(String, IModel)
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel,
    *      List,IChoiceRenderer)
    */
   public CampoRadioChoice(final String id, IModel<T> model, final List<? extends T> choices,
      final IChoiceRenderer<? super T> renderer)
   {
      super(id, model, choices, renderer);
   }

   /**
    * Constructor
    * @param id See Component
    * @param choices The list of choices in the radio choice
    * @see org.apache.wicket.Component#Component(String)
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel)
    */
   public CampoRadioChoice(String id, IModel<? extends List<? extends T>> choices)
   {
      super(id, choices);
   }

   /**
    * Constructor
    * @param id See Component
    * @param model The model that is updated with changes in this component. See Component
    * @param choices The list of choices in the radio choice
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel,IModel)
    * @see org.apache.wicket.Component#Component(String, IModel)
    */
   public CampoRadioChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices)
   {
      super(id, model, choices);
   }

   /**
    * Constructor
    * @param id See Component
    * @param choices The list of choices in the radio choice
    * @param renderer The rendering engine
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String,
    *      IModel,IChoiceRenderer)
    * @see org.apache.wicket.Component#Component(String)
    */
   public CampoRadioChoice(String id, IModel<? extends List<? extends T>> choices,
      IChoiceRenderer<? super T> renderer)
   {
      super(id, choices, renderer);
   }

   /**
    * Constructor
    * @param id See Component
    * @param model The model that is updated with changes in this component. See Component
    * @param choices The list of choices in the radio choice
    * @param renderer The rendering engine
    * @see org.apache.wicket.Component#Component(String, IModel)
    * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel,
    *      IModel,IChoiceRenderer)
    */
   public CampoRadioChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices,
      IChoiceRenderer<? super T> renderer)
   {
      super(id, model, choices, renderer);
   }

   protected void appendOptionHtml(final AppendingStringBuffer buffer, final T choice, int index,
      final String selected)
   {
      Object displayValue = getChoiceRenderer().getDisplayValue(choice);
      Class<?> objectClass = (displayValue == null ? null : displayValue.getClass());

      // Get label for choice
      String label = "";

      if (objectClass != null && objectClass != String.class)
      {
         @SuppressWarnings("rawtypes")
         final IConverter converter = getConverter(objectClass);
         label = converter.convertToString(displayValue, getLocale());
      }
      else if (displayValue != null)
      {
         label = displayValue.toString();
      }

      // If there is a display value for the choice, then we know that the
      // choice is automatic in some way. If label is /null/ then we know
      // that the choice is a manually created radio tag at some random
      // location in the page markup!
      if (label != null)
      {
         // Append option suffix
         buffer.append(getPrefix(index, choice));

         String id = getChoiceRenderer().getIdValue(choice, index);
         final String idAttr = getMarkupId() + "-" + id;

         boolean enabled = isEnabledInHierarchy() && !isDisabled(choice, index, selected);

         // Add label for radio button
         String display = label;
         if (localizeDisplayValues())
         {
            display = getLocalizer().getString(label, this, label);
         }

         CharSequence escaped = display;
         if (getEscapeModelStrings())
         {
            escaped = Strings.escapeMarkup(display);
         }

         // Allows user to add attributes to the <label..> tag
         IValueMap labelAttrs = getAdditionalAttributesForLabel(index, choice);
         StringBuilder extraLabelAttributes = new StringBuilder();
         if (labelAttrs != null)
         {
            for (Map.Entry<String, Object> attr : labelAttrs.entrySet())
            {
               extraLabelAttributes.append(' ').append(attr.getKey()).append("=\"")
                  .append(attr.getValue()).append('"');
            }
         }

         switch (labelPosition)
         {
            case BEFORE:

               buffer.append("<label for=\"").append(idAttr).append('"').append(css)
                  .append(extraLabelAttributes).append('>').append(escaped).append("</label>");
               break;
            case WRAP_BEFORE:
               buffer.append("<label").append(extraLabelAttributes).append('>').append(escaped)
                  .append(' ');
               break;
            case WRAP_AFTER:
               buffer.append("<label").append(extraLabelAttributes).append('>');
               break;
         }

         // Add radio tag
         buffer.append("<input name=\"").append(getInputName()).append('"')
            .append(" type=\"radio\"")
            .append((isSelected(choice, index, selected) ? " checked=\"checked\"" : ""))
            .append((enabled ? "" : " disabled=\"disabled\"")).append(" value=\"").append(id)
            .append("\" id=\"").append(idAttr).append('"');

         // Should a roundtrip be made (have onSelectionChanged called)
         // when the option is clicked?
         if (wantOnSelectionChangedNotifications())
         {
            CharSequence url = urlFor(IOnChangeListener.INTERFACE, new PageParameters());

            Form<?> form = findParent(Form.class);
            if (form != null)
            {
               buffer.append(" onclick=\"").append(form.getJsForInterfaceUrl(url)).append(";\"");
            }
            else
            {
               // NOTE: do not encode the url as that would give
               // invalid JavaScript
               buffer.append(" onclick=\"window.location.href='").append(url)
                  .append((url.toString().indexOf('?') > -1 ? '&' : '?') + getInputName())
                  .append('=').append(id).append("';\"");
            }
         }

         // Allows user to add attributes to the <input..> tag
         {
            IValueMap attrs = getAdditionalAttributes(index, choice);
            if (attrs != null)
            {
               for (Map.Entry<String, Object> attr : attrs.entrySet())
               {
                  buffer.append(' ').append(attr.getKey()).append("=\"").append(attr.getValue())
                     .append('"');
               }
            }
         }

         if (getApplication().getDebugSettings().isOutputComponentPath())
         {
            CharSequence path = getPageRelativePath();
            path = Strings.replaceAll(path, "_", "__");
            path = Strings.replaceAll(path, ":", "_");
            buffer.append(" wicketpath=\"").append(path).append("_input_").append(index)
               .append('"');
         }

         buffer.append("/>");

         switch (labelPosition)
         {
            case AFTER:
               buffer.append("<label for=\"").append(idAttr).append('"').append(css)
                  .append(extraLabelAttributes).append('>').append(escaped).append("</label>");
               break;
            case WRAP_BEFORE:
               buffer.append("</label>");
               break;
            case WRAP_AFTER:
               buffer.append(' ').append(escaped).append("</label>");
               break;
         }

         // Append option suffix
         buffer.append(getSuffix(index, choice));
      }
   }

}
