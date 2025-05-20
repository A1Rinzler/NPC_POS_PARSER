package XmlEngine;

import Patterns.EncodingPattern;

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
    EncodingPattern xmlEncodingPattern = new EncodingPattern();
    Path directoryPath = Paths.get("Npc_Pos_Parser/XML_Out");
    //Path directoryPath = Paths.get("XML_Out");

    List<String> directoryFiles = new ArrayList<>();
    String xmlFileName = "";

     public void createXMLFile(String str, StringBuffer parsedPatternBuffer){
         xmlFileName = getName(str);
         getDirectoryFiles(directoryPath);
         writeToXmlFile(xmlEncodingPattern.getXmlPattern(), parsedPatternBuffer, xmlFileName);
     }

    private void writeToXmlFile(StringBuffer xmlEncodingPattern, StringBuffer parsedPatternBuffer, String xmlFileName){
        String adressToFile = directoryPath + "/" + xmlFileName;

            try {
                BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(adressToFile), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    if (!twinCheck()){
                        bufferedWriter.write(xmlEncodingPattern.toString()); //запись шаблона единоразовая, если файла не существовало.
                    }
                bufferedWriter.write(parsedPatternBuffer.toString());
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

//        if (xmlNameMatcher.find()){
//            matchXmlName = xmlNameMatcher.group();
//            xmlName = matchXmlName.substring(0,2) + "_" + matchXmlName.substring(2) + ".xml";
//        }
//         else {}
         switch (str){
             //giran_siege01 обработать
             //gludio_siege01
             case "[queenant_room]", "[new_queen_ant_guard_room]", "[queen_ant_guard_room]" -> xmlName ="19_23_Ant_Queen.xml";
             case "[orphen_t21_18_001]", "[orphen_t21_18_002]", "[orphen_t21_18_001_01]" -> xmlName ="21_18_Orfen.xml";
             case "[dg_20_21_03f_004_02]", "[dg_20_21_03f_005]", "[dg_20_21_03f_004]", "[dg_20_21_03f_tele]" -> xmlName ="20_21.xml";
             case "[core_cube]" -> xmlName ="20_21_Core.xml";
             case "[23_18_baium_npc]" -> xmlName ="23_18_Baium.xml";
             case "[gludio_chamberlain_etc]", "[gludio_mass_teleporter]", "[gludio_siege01]", "[gludio_siege02]", "[gludio_siege03]",
                  "[gludio_siege04]", "[gludio_siege05]", "[gludio_siege06]", "[gludio_siege07]", "[gludio_siege08]", "[gludio_siege09]", "[gludio_siege10]",
                  "[gludio_siege11]", "[gludio_siege12]", "[gludio_siege13]"  -> xmlName = "19_21_Gludio_Siege.xml";
             case "[dion_chamberlain_etc]", "[dion_mass_teleporter]", "[dion_siege01]", "[dion_siege02]", "[dion_siege03]", "[dion_siege04]",
                  "[dion_siege05]", "[dion_siege06]", "[dion_siege07]", "[dion_siege08]", "[dion_siege09]", "[dion_siege10]", "[dion_siege11]",
                  "[dion_siege12]", "[dion_siege13]" -> xmlName = "20_22_Dion_Siege.xml";
             case "[giran_chamberlain_etc]", "[giran_mass_teleporter]", "[giran_siege01]", "[giran_siege02]", "[giran_siege03]", "[giran_siege04]",
                  "[giran_siege05]", "[giran_siege06]", "[giran_siege07]", "[giran_siege08]", "[giran_siege09]", "[giran_siege10]", "[giran_siege11]",
                  "[giran_siege12]", "[giran_siege13]" -> xmlName = "23_22_Giran_Siege.xml";
             case "[oren_chamberlain_etc]", "[oren_mass_teleporter]", "[oren_siege01]", "[oren_siege02]", "[oren_siege03]", "[oren_siege04]",
                  "[oren_siege05]", "[oren_siege06]", "[oren_siege07]", "[oren_siege08]", "[oren_siege09]", "[oren_siege10]","[oren_siege11]",
                  "[oren_siege12]","[oren_siege13]"-> xmlName = "22_19_Oren_Siege.xml";
             case "[innadrile_chamberlain_etc]", "[innadrile_m_teleporter]", "[innadrile_siege01]", "[innadrile_siege02]", "[innadrile_siege03]",
                  "[innadrile_siege04]", "[innadrile_siege05]", "[innadrile_siege06]", "[innadrile_siege07]", "[innadrile_siege08]", "[innadrile_siege09]",
                  "[innadrile_siege10]", "[innadrile_siege11]", "[innadrile_siege12]", "[innadrile_siege13]"-> xmlName = "23_25_Innadril_Siege.xml";
             case "[aden_chamberlain_etc]", "[aden_mass_teleporter]", "[aden_siege01]", "[aden_siege02]", "[aden_siege03]", "[aden_siege04]", "[aden_siege05]",
                  "[aden_siege06]", "[aden_siege07]", "[aden_siege08]", "[aden_siege09]", "[aden_siege10]", "[aden_siege11]", "[aden_siege12]", "[aden_siege13]",
                  "[aden_siege14]", "[aden_siege15]", "[aden_siege16]", "[aden_siege17]", "[aden_siege18]", "[aden_siege19]", "[aden_siege092]" -> xmlName = "24_18_Aden_Siege.xml";
             case "[godard_castle2416_0201]", "[godard_castle2416_0202]", "[godard_castle2416_0301]", "[godard_castle2416_0303]", "[godard_castle2416_0401]",
                  "[godard_castle2416_0402]", "[godard_castle2416_0501]", "[godard_castle2416_0502]", "[godard_castle2416_0601]", "[godard_castle2416_0602]",
                  "[godard_castle2416_0701]", "[godard_castle2416_0702]", "[godard_castle2416_0801]", "[godard_castle2416_0802]", "[godard_castle2416_0901]",
                  "[godard_castle2416_0902]", "[godard_castle2416_1001]", "[godard_castle2416_1002]", "[godard_castle2416_1101]", "[godard_castle2416_1102]",
                  "[godard_castle2416_1201]", "[godard_castle2416_1202]", "[godard_castle2416_1301]", "[godard_castle2416_1302]", "[godard_castle2416_1401]",
                  "[godard_castle2416_1402]", "[godard_castle2416_1501]", "[godard_castle2416_1502]", "[godard_castle2416_1601]", "[godard_castle2416_1602]",
                  "[godard_castle2416_1701]", "[godard_castle2416_1702]", "[godard_castle2416_1801]", "[godard_castle2416_1802]", "[godard_castle2416_1901]",
                  "[godard_castle2416_1902]", "[godard_castle2416_2001]", "[godard_castle2416_2002]", "[godard_castle2416_2101]", "[godard_castle2416_2102]" -> xmlName = "24_16_Goddard_Siege.xml";
             case "[rune03_2016_01]", "[rune03_2016_02]", "[rune03_2016_04]", "[rune03_2016_05]", "[rune03_2016_06]", "[rune03_2016_07]", "[rune03_2016_08]",
                  "[rune03_2016_09]", "[rune03_2016_10]", "[rune03_2016_11]", "[rune03_2016_12]", "[rune03_2016_13]", "[rune03_2016_14]", "[rune03_2016_15]",
                  "[rune03_2016_16]", "[rune03_2016_17]", "[rune03_2016_18]", "[rune03_2016_19]", "[rune03_2016_20]", "[rune03_2016_21]", "[rune03_2016_22]",
                  "[rune03_2016_23]", "[rune03_2016_24]", "[rune03_2016_25]", "[rune03_2016_26]", "[rune03_2016_27]", "[rune03_2016_28]", "[rune03_2016_29]",
                  "[rune03_2016_30]", "[rune03_2016_31]", "[rune03_2016_32]", "[rune03_2016_33]", "[rune03_2016_34]", "[rune03_2016_35]" -> xmlName = "20_16_Rune_Siege.xml";
             case "[schuttgart19_2213_01]", "[schuttgart19_2213_02]", "[schuttgart19_2213_03]", "[schuttgart19_2213_04]", "[schuttgart19_2213_05]",
                  "[schuttgart19_2213_06]", "[schuttgart19_2213_07]", "[schuttgart19_2213_08]", "[schuttgart19_2213_09]", "[schuttgart19_2213_10]",
                  "[schuttgart19_2213_11]", "[schuttgart19_2213_12]", "[schuttgart19_2213_13]", "[schuttgart19_2213_14]", "[schuttgart19_2213_15]",
                  "[schuttgart19_2213_16]", "[schuttgart19_2213_17]", "[schuttgart19_2213_18]", "[schuttgart19_2213_19]", "[schuttgart19_2213_20]",
                  "[schuttgart19_2213_21]", "[schuttgart19_2213_22]", "[schuttgart19_2213_23]", "[schuttgart19_2213_24]", "[schuttgart19_2213_25]",
                  "[schuttgart19_2213_26]", "[schuttgart19_2213_27]", "[schuttgart19_2213_28]", "[schuttgart19_2213_29]", "[schuttgart19_2213_30]",
                  "[schuttgart19_2213_31]", "[schuttgart19_2213_32]", "[schuttgart19_2213_33]", "[schuttgart19_2213_34]", "[schuttgart19_2213_35]",
                  "[schuttgart19_2213_36]" -> xmlName = "22_13_Schuttgart_Siege.xml";
             default -> {
                 if (str.contains("t21_24_boss")){
                     xmlName ="21_24_Zaken.xml";
                 }else if (xmlNameMatcher.find()){
                     matchXmlName = xmlNameMatcher.group();
                     xmlName = matchXmlName.substring(0,2) + "_" + matchXmlName.substring(2) + ".xml";
                 }
                 else xmlName = "NoNameMatches.xml";
             }
         }
        return xmlName;
    }
}
