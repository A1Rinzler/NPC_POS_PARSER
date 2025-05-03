import Patterns.EncodingPattern;
import Patterns.XmlPattern;

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
    XmlPattern xmlEncodingPattern = new EncodingPattern();
    Path directoryPath = Paths.get("Npc_Pos_Parser/XML_Out");
    List<String> directoryFiles = new ArrayList<>();
    String xmlFileName = "";

    //todo добавить в конец каждого файла после парса скрипта.
    String endOfFile = "</list>";

     void createXMLFile(String str, StringBuffer stringBuffer){
         xmlFileName = getName(str);
         getDirectoryFiles(directoryPath);
         writeToXmlFile(directoryFiles, xmlEncodingPattern.getXmlPattern(), stringBuffer, xmlFileName);
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
        String xmlName;
        String matchXmlName;
        Pattern xmlNamePattern = Pattern.compile("\\d{4}");
        Matcher xmlNameMatcher = xmlNamePattern.matcher(str);

        if (xmlNameMatcher.find()){
            matchXmlName = xmlNameMatcher.group();
            xmlName = matchXmlName.substring(0,2) + "_" + matchXmlName.substring(2) + ".xml";
        }
         else {
             switch (str){
                 case "[queenant_room]", "[new_queen_ant_guard_room]", "[queen_ant_guard_room]" -> xmlName ="19_23_Ant_Queen.xml";
                 case "[orphen_t21_18_001]", "[orphen_t21_18_002]", "[orphen_t21_18_001_01]" -> xmlName ="21_18_Orfen.xml";
                 case "[dg_20_21_03f_004_02]", "[dg_20_21_03f_005]", "[dg_20_21_03f_004]", "[dg_20_21_03f_tele]" -> xmlName ="20_21.xml";
                 case "[core_cube]" -> xmlName ="20_21_Core.xml";
                 case "[23_18_baium_npc]" -> xmlName ="23_18_Baium.xml";
                 default -> {
                     if (str.contains("t21_24_boss")){
                         xmlName ="21_24_Zaken.xml";
                     }else xmlName = "NoNameMatches.xml";
                 }
             }
         }
        return xmlName;
    }
}
