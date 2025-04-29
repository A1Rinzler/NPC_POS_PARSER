import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 05.04.2025
 **/

public class CreateXML {
    StringBuffer xmlOutPattern = new StringBuffer();
    String xmlName = "";
    String directoryName = "/home/dataXml"; //адрес для теста, далее пусть от папки Out читать

    Path path = Paths.get(directoryName);


    //todo не все имена территорий работают по такому шаблону, некоторые просто текстовые названия без координат. Захардкодить отдельно, их немного.
    //например такие названия dg_20_21_03f_004, core_cube, 23_18_baium_npc, t21_24_boss1f_008. Они в конце скрипта, может еще где, но не видел.
     void createXMLFile(String str){

        System.out.println(getName(str));

         if (Files.exists(path) && Files.isDirectory(path)){

             /* Чтение директории, туда проверку на имя файла.
              * если файл существует записать в него, если файл не существует,
              * получить имя заполнить его getXmlOutPattern() и записать в него.
              * */
         }
    }

     private String getName(String str){
        String matchXmlName = "";
        Pattern xmlNamePattern = Pattern.compile("\\d{4}");
        Matcher xmlNameMatcher = xmlNamePattern.matcher(str);

        if (xmlNameMatcher.find()){
            matchXmlName = xmlNameMatcher.group();}

        xmlName = matchXmlName.substring(0,2) + "_" + matchXmlName.substring(2) + ".xml";
        return xmlName;
    }

    private void getXmlOutPattern(){
         xmlOutPattern.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\n")
                 .append("<!DOCTYPE list SYSTEM \"spawn.dtd\">").append("\n")
                 .append("<list>").append("\n");
    }

    //todo добавить в конец каждого файла после парса скрипта.
    String endOfFile = "</list>";
}
