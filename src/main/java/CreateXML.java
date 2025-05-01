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
    //Path directoryPath = Paths.get("NPC_POS_PARSER","Npc_Pos_Parser","PTS_Scritps", "XML_Out"); //адрес для теста, далее путь от папки Out читать
    Path directoryPath = Paths.get("Npc_Pos_Parser/XML_Out");
    List<String> directoryFiles = new ArrayList<>();
    String xmlFileName = "";

    //todo добавить в конец каждого файла после парса скрипта.
    String endOfFile = "</list>";

     void createXMLFile(String str, StringBuffer stringBuffer){
         getXmlOutPattern();
         xmlFileName = getName(str);
        //System.out.println(getName(str));
        //System.out.println(stringBuffer);
         getDirectoryFiles(directoryPath);
         writeToXmlFile(directoryFiles,xmlOutPattern, stringBuffer, xmlFileName);
     }

    private void writeToXmlFile(List<String> files, StringBuffer pattern, StringBuffer parsedPattern, String fileName){
        String adressToFile = directoryPath + "/" +fileName;

            try {
                BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(adressToFile), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    if (!twinCheck()){
                        bufferedWriter.write(pattern.toString()); //запись шаблона единоразовая, если файла не существовало.
                     }
                bufferedWriter.write(parsedPattern.toString());
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private boolean twinCheck(){
         boolean twin = false;
         for (String file : directoryFiles){
            if (file.equals(xmlFileName)){
                twin = true;
                break;
            }else twin = false;
         }
         return twin;
    }

    private void getDirectoryFiles(Path directoryPath){
        directoryFiles.clear();
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
         }
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

    //todo вынести отдельно,чтобы не костылять с чисткой. Каждый раз дописывает, т.к. вызова метода много.
    private void getXmlOutPattern(){
         xmlOutPattern.setLength(0);
         xmlOutPattern.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\n")
                 .append("<!DOCTYPE list SYSTEM \"spawn.dtd\">").append("\n")
                 .append("<list>").append("\n");
    }
}
