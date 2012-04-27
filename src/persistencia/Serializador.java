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
á*
á* @author Arthur Freire, Leandro Soares, Marcos Rodrigues, Natasha Bezerra, Tales Tenorio
á*
á*/
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
          System.out.println("Erro na persistÛncia!");
          e.printStackTrace();
      }
    }

 /**
* MÚtodo que escreve uma coleþÒo genÚricas em um arquivo XML.
*
* @throws FileNotFoundException
 ExceþÒo que Ú lanþada se nÒo houver arquivo.
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
* MÚtodo que ler uma coleþÒo genÚrica de um arquivo XML.
*
* @throws FileNotFoundException
 ExceþÒo que Ú lanþado se nÒo houver arquivo.
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