package persistencia;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
�*
�* @author Arthur Freire, Leandro Soares, Marcos Rodrigues, Natasha Bezerra, Tales Tenorio
�*
�*/
public class Serializador<T> {

 private XStream xStream;
 private String diretorio;
 private final String FILE_SEPARATOR = System.getProperty("file.separator");

     public Serializador() {
         this.xStream = new XStream(new DomDriver("UTF-8"));
        try {
          new File(new File(".").getCanonicalPath() + FILE_SEPARATOR + "banco").mkdirs();
          this.diretorio = new File(".").getCanonicalPath() + FILE_SEPARATOR + "banco" + FILE_SEPARATOR;
        } catch (IOException e) {
          System.out.println("Erro na persist�ncia!");
          e.printStackTrace();
      }
    }

 /**
* M�todo que escreve uma cole��o gen�ricas em um arquivo XML.
*
* @throws FileNotFoundException
 Exce��o que � lan�ada se n�o houver arquivo.
*/
      public void salvar(String nomeArquivo, T collection) {
         try {
             new File(diretorio).mkdir();
             FileOutputStream file = new FileOutputStream(diretorio + FILE_SEPARATOR + nomeArquivo + ".xml");
             BufferedOutputStream s = new BufferedOutputStream(file);
             xStream.toXML(collection, s);
             file.close();
         } catch (Exception e) {
             e.printStackTrace();
        }
    }

 /**
* M�todo que ler uma cole��o gen�rica de um arquivo XML.
*
* @throws FileNotFoundException
 Exce��o que � lan�ado se n�o houver arquivo.
*/
        @SuppressWarnings("unchecked")
        public T recuperar(String nomeArquivo) {
            try {
               XStream reader = new XStream(new DomDriver());
               FileInputStream file = new FileInputStream(diretorio + FILE_SEPARATOR + nomeArquivo + ".xml");
               BufferedInputStream s = new BufferedInputStream(file);
               T collection = (T) reader.fromXML(s);
               file.close();
               return collection;
            } catch (Exception e) {
                return null;
          }

 }
}