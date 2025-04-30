import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.List;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 05.04.2025
 **/

public class CreateXML {
    StringBuffer xmlOutPattern = new StringBuffer();
    Path directoryPath = Paths.get("/home/dataXml"); //адрес для теста, далее пусть от папки Out читать
    List<String> directoryFiles = new ArrayList<>();

    //todo добавить в конец каждого файла после парса скрипта.
    String endOfFile = "</list>";

     void createXMLFile(String str, StringBuffer stringBuffer){
         getXmlOutPattern();
        //System.out.println(getName(str));
        //System.out.println(stringBuffer);
         //getDirectoryFiles(directoryPath);
         writeToXmlFile(directoryFiles,xmlOutPattern, stringBuffer, getName(str));

     }

    private void writeToXmlFile(List<String> files, StringBuffer pattern, StringBuffer parsedPattern, String fileName){
        String adressToFile = directoryPath + "/" +fileName;
        //System.out.println(adressToFile);

        //todo проверка на существующий файл по полученному имени.
        //if (){}
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(adressToFile), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            //bufferedWriter.write(pattern.toString()); //запись шаблона единоразовая, если файла не существовало.
            bufferedWriter.write(parsedPattern.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void checkList(){
         for (String a : directoryFiles){
             System.out.println(a);
         }
     }

    private void getDirectoryFiles(Path directoryPath){
         if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)){
             try {
                 Stream<Path> checkFilesStream = Files.list(directoryPath);
                 checkFilesStream.forEach(path -> {
                     if (Files.isRegularFile(path)){
                         directoryFiles.add(path.getFileName().toString());
                     }
                 });
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
             /* Чтение директории, туда проверку на имя файла.
              * если файл существует записать в него, если файл не существует,
              * получить имя заполнить его getXmlOutPattern() и записать в него.
              * */
         }
         checkList();
     }


     private String getName(String str){
        String xmlName = "";
        String matchXmlName = "";
        Pattern xmlNamePattern = Pattern.compile("\\d{4}");
        Matcher xmlNameMatcher = xmlNamePattern.matcher(str);

        if (xmlNameMatcher.find()){
            matchXmlName = xmlNameMatcher.group();
            xmlName = matchXmlName.substring(0,2) + "_" + matchXmlName.substring(2) + ".xml";
        }
        //todo не все имена территорий работают по такому шаблону, некоторые просто текстовые названия без координат. Захардкодить отдельно, их немного.
        //например такие названия dg_20_21_03f_004, core_cube, 23_18_baium_npc, t21_24_boss1f_008. Они в конце скрипта, может еще где, но не видел.
        else System.out.println("Ну нет такого закона!"); //не нашло совпадений

        return xmlName;

    }

    private void getXmlOutPattern(){
         xmlOutPattern.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\n")
                 .append("<!DOCTYPE list SYSTEM \"spawn.dtd\">").append("\n")
                 .append("<list>").append("\n");
    }
}
