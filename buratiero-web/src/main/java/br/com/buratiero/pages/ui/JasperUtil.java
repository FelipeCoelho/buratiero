package br.com.buratiero.pages.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperUtil implements Serializable
{
   private static final long serialVersionUID = 7116932312847790690L;

   private JasperUtil()
   {
   }

   public static byte[] gerarJasperParaByte(InputStream is, Map<String, Object> parametros, List<?> lista)
   {
      try
      {
         JasperPrint jp = JasperFillManager.fillReport(is, parametros, new JRBeanCollectionDataSource(lista, false));
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         JasperExportManager.exportReportToPdfStream(jp, baos);
         baos.close();
         is.close();
         return baos.toByteArray();
      }
      catch (JRException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return null;
   }
}