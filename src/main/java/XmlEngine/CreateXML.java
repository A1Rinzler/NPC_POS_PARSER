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
    //Path directoryPath = Paths.get("Npc_Pos_Parser/XML_Out");
    Path directoryPath = Paths.get("XML_Out");

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
             case "[queenant_room]", "[new_queen_ant_guard_room]", "[queen_ant_guard_room]" -> xmlName ="19_23_Ant_Queen.xml";
             case "[orphen_t21_18_001]", "[orphen_t21_18_002]", "[orphen_t21_18_001_01]", "[orphen_t21_18_002];[orphen_t21_18_001]" -> xmlName ="21_18_Orfen.xml";
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
             case "[t121_24_071]", "[t21_244_009]" -> xmlName = "21_24.xml";
             case "[23_normal]", "[26_normal]", "[26_pccafe]", "[33_normal]", "[36_normal]", "[36_pccafe]", "[43_normal]", "[46_normal]", "[46_pccafe]",
                  "[53_normal]", "[56_normal]", "[56_pccafe]", "[63_normal_1]", "[66_normal_1]", "[66_pccafe]", "[73_normal_1]", "[63_normal_2]",
                  "[66_normal_2]", "[73_normal_2]", "[obelisk_territory_a]", "[53_normal_buha]", "[66_pccafe_buha]"-> xmlName = "Kamaloka.xml";
             case "[inzone03_2011_25]", "[inzone03_2011_30]", "[inzone03_2011_35]", "[inzone03_2011_40]", "[inzone03_2011_50]", "[inzone03_2011_55]",
                  "[inzone03_2011_60]", "[inzone03_2011_65]", "[inzone03_2011_70]", "[inzone03_2011_75]" -> xmlName = "Rim_Kamaloka.xml";
             case "[oren_t20_14_018]", "[oren_t20_14_19]", "[oren_t20_14_20]", "[oren_t20_14_21]" -> xmlName = "20_14.xml";
             case "[lyonn07_18_14_01]", "[lyonn07_18_14_02]", "[lyonn07_18_14_03]", "[lyonn07_18_14_04]", "[lyonn07_18_14_05]", "[lyonn07_18_14_06]",
                  "[lyonn07_18_14_07]", "[lyonn07_18_14_08]", "[lyonn07_18_14_09]", "[lyonn07_18_14_10]", "[lyonn07_18_14_11]", "[lyonn07_18_14_12]",
                  "[lyonn07_18_14_13]", "[lyonn07_18_14_14]", "[lyonn07_18_14_15]", "[lyonn07_18_14_16]", "[lyonn07_18_14_17]", "[lyonn07_18_14_18]",
                  "[lyonn07_18_14_19]", "[lyonn07_18_14_20]", "[lyonn07_18_14_21]", "[lyonn07_18_14_22]", "[lyonn07_18_14_23]" -> xmlName = "18_14.xml";
//             раскомментировать, если решили закомментировать ивенты
//             case "[20_18_net_cafe_event_1_01]", "[20_18_net_cafe_event_1_02]" -> xmlName = "20_18.xml";
//             case "[20_22_net_cafe_event_1_01]", "[20_22_net_cafe_event_1_02]", "[20_22_net_cafe_event_1_03]" -> xmlName = "20_22.xml";
//             case "[20_23_net_cafe_event_1_01]" -> xmlName = "20_23.xml";
//             case "[21_19_net_cafe_event_1_01]", "[21_19_net_cafe_event_1_02]" -> xmlName = "21_19.xml";
//             case "[22_19_net_cafe_event_1_01]", "[22_19_net_cafe_event_1_02]", "[22_19_net_cafe_event_1_03]" -> xmlName = "22_19.xml";
//             case "[22_22_net_cafe_event_1_01]", "[22_22_net_cafe_event_1_02]", "[22_22_net_cafe_event_1_03]", "[22_22_net_cafe_event_1_04]" -> xmlName = "22_22.xml";
//             case "[23_12_net_cafe_event_1_01]", "[23_12_net_cafe_event_1_02]" -> xmlName = "23_12.xml";
//             case "[17_22_net_cafe_event_1_01]", "[17_22_net_cafe_event_1_02]", "[17_22_net_cafe_event_1_03]" -> xmlName = "17_22.xml";
//             case "[23_20_net_cafe_event_1_01]", "[23_20_net_cafe_event_1_02]", "[23_20_net_cafe_event_1_03]" -> xmlName = "23_20.xml";
//             case "[17_25_net_cafe_event_1_01]", "[17_25_net_cafe_event_1_02]" -> xmlName = "17_25.xml";
//             case "[24_18_net_cafe_event_1_01]", "[24_18_net_cafe_event_1_02]" -> xmlName = "24_18.xml";
//             case "[23_24_net_cafe_event_1_01]", "[23_24_net_cafe_event_1_02]", "[23_24_net_cafe_event_1_03]" -> xmlName = "23_24.xml";
//             case "[24_16_net_cafe_event_1_01]" -> xmlName = "24_16.xml";
//             case "[21_16_net_cafe_event_1_01]" -> xmlName = "21_16.xml";
//             case "[schuttgart20_npc2213_nc01]", "[schuttgart20_npc2213_nc02]" -> xmlName = "22_13.xml";

             case "[gludio_dominion03]", "[gludio_dominion08]", "[gludio_dominion12]", "[gludio_dominion11]", "[gludio_dominion_fort01]" -> xmlName = "Gludio_Dominion.xml";
             case "[dion_dominion03]", "[dion_dominion08]", "[dion_dominion11]", "[dion_dominion12]", "[dion_dominion_fort01]" -> xmlName = "Dion_Dominion.xml";
             case "[giran_dominion03]", "[giran_dominion08]", "[giran_dominion11]", "[giran_dominion12]", "[giran_dominion_fort01]" -> xmlName = "Giran_Dominion.xml";
             case "[oren_dominion03]","[oren_dominion08]","[oren_dominion11]","[oren_dominion12]", "[oren_dominion_fort01]" -> xmlName = "Oren_Dominion.xml";
             case "[innadrile_dominion03]","[innadrile_dominion08]","[innadrile_dominion11]","[innadrile_dominion12]", "[innadrille_dominion_fort01]" -> xmlName = "Innadril_Dominion.xml";
             case "[aden_dominion092]", "[aden_dominion093]", "[aden_dominion12]", "[aden_dominion_fort01]" -> xmlName = "Aden_Dominion.xml";
             case "[godard_dominion2416_0201]", "[godard_dominion2416_0202]", "[godard_dominion2416_0601]", "[godard_dominion2416_602]", "[godard_dominion2416_0801]",
                  "[godard_dominion2416_0802]", "[godard_dominion2416_1601]", "[godard_dominion2416_1501]", "[godard_dominion2416_1502]", "[godad_dominion_fort01]" -> xmlName = "Goddard_Dominion.xml";
             case "[rune03_dominion2016_02]", "[rune03_dominion2016_04]", "[rune03_dominion2016_05]", "[rune03_dominion2016_16]", "[rune03_dominion2016_17]", "[rune03_dominion2016_18]",
                  "[rune03_dominion2016_12]", "[rune03_dominion2016_13]", "[rune_dominion_fort01]" -> xmlName = "Rune_Dominion.xml";
             case "[schuttgart19_dominion2213_01]", "[schuttgart19_dominion2213_02]", "[schuttgart19_dominion2213_09]", "[schuttgart19_dominion2213_10]", "[schuttgart19_dominion2213_13]",
                  "[schuttgart19_dominion2213_14]", "[schuttgart19_dominion2213_15]", "[schuttgart19_dominion2213_27]", "[schuttgart19_dominion2213_28]", "[schuttgart_dominion_fort01]" -> xmlName = "Schuttgar_Dominion.xml";
             case "[aden05_agit2517_01]", "[aden05_agit2517_02]", "[aden05_agit2517_03]", "[aden05_agit2517_04]", "[aden05_agit2517_05]", "[aden05_agit2517_06]", "[aden05_agit2517_07]",
                  "[aden05_agit2517_08]", "[aden05_agit2517_09]", "[aden05_agit2517_10]", "[aden05_agit2517_11]", "[aden05_agit2517_12]" -> xmlName = "25_17_Devastated_Castle.xml";
             case "[partisan_agit_2121_01]", "[partisan_agit_2121_02]", "[partisan_agit_2121_03]" -> xmlName = "21_21_Fortress_of_Resistance.xml";
             case "banditsagit_2217_01]", "banditsagit_2217_02]" -> xmlName = "22_17_Bandit_Stronghold.xml";
             case "[rune07_azit2115_01]", "[rune07_azit2115_02]", "[rune07_azit2115_03]", "[rune07_azit2115_04]", "[rune07_azit2115_05]", "[rune07_azit2115_06]", "[rune07_azit2115_07]" -> xmlName = "21_15_Wild_Beast_Reserve.xml";
             case "[rune11_2117_01]", "[rune11_2117_02]", "[rune11_2117_03]", "[rune11_2117_04]", "[rune11_2117_05]", "[rune11_2117_06]", "[rune11_2117_07]", "[rune11_2117_08]", "[rune11_2117_09]",
                  "[rune11_2117_10]", "[rune11_2117_11]", "[rune11_2117_12]", "[rune11_2117_13]"  -> xmlName = "21_17_Fortress of the Dead.xml";
             case "[godard06_2414_01]", "[godard06_2414_02]", "[godard06_2414_21]", "[godard06_2414_22]", "[godard06_2414_31]", "[godard06_2414_32]", "[godard06_2414_41]", "[godard06_2414_42]" -> xmlName = "24_14_Rainbow_Springs_Chateau.xml";
             case "[1710_olympiad1]", "[1710_olympiad2]", "[1710_olympiad3]", "[1710_olympiad4]", "[1710_olympiad5]", "[1710_olympiad6]", "[1710_olympiad7]", "[1710_olympiad8]", "[1710_olympiad9]",
                  "[1710_olympiad10]", "[1710_olympiad11]", "[1710_olympiad12]", "[1710_olympiad13]", "[1710_olympiad14]", "[1710_olympiad15]", "[1710_olympiad16]", "[1710_olympiad17]", "[1710_olympiad18]", "[1710_olympiad19]",
                  "[1710_olympiad20]", "[1710_olympiad21]", "[1710_olympiad22]" -> xmlName = "17_10_olympiad.xml";
             //Ивенты
             case "[lyonn03_npc1814_lc01]", "[lyonn03_npc1814_lc02]", "[gludio08_npc1921_lc01]", "[gludio08_npc1921_lc02]", "[gludio08_npc1921_lc03]", "[oren09_npc2018_lc01]", "[oren09_npc2018_lc02]",
                  "[dion09_npc2022_lc01]", "[dion09_npc2022_lc02]", "[dion09_npc2022_lc03]", "[dion10_npc2023_lc01]", "[oren04_npc2119_lc01]", "[oren04_npc2119_lc02]", "[oren17_npc2219_lc01]",
                  "[oren17_npc2219_lc02]", "[oren17_npc2219_lc03]", "[giran11_npc2222_lc01]", "[giran11_npc2222_lc02]", "[giran11_npc2222_lc03]", "[giran11_npc2222_lc04]", "[schuttgart03_npc2312_lc01]",
                  "[schuttgart03_npc2312_lc02]", "[gludio06_npc1722_lc01]", "[gludio06_npc1722_lc02]", "[gludio06_npc1722_lc03]", "[aden14_npc2320_lc01]", "[aden14_npc2320_lc02]", "[aden14_npc2320_lc03]",
                  "[gludio25_npc1725_lc01]", "[gludio25_npc1725_lc02]", "[aden13_npc2418_lc01]", "[aden13_npc2418_lc02]", "[innadril09_npc2324_lc01]", "[innadril09_npc2324_lc02]", "[innadril09_npc2324_lc03]",
                  "[godard02_npc2416_lc01]", "[godard02_npc2416_lc02]", "[rune02_npc2116_lc01]", "[rune02_npc2116_lc02]", "[schuttgart20_npc2213_lc01]", "[schuttgart20_npc2213_lc02]",
                  "[aden45_npc1619__lc01]" -> xmlName = "event_letter_collector.xml";
             case "[lyonn03_npc1814_ht01]", "[lyonn03_npc1814_ht02]", "[gludio08_npc1921_ht01]", "[gludio08_npc1921_ht02]", "[gludio08_npc1921_ht03]", "[oren09_npc2018_ht01]", "[oren09_npc2018_ht02]",
                  "[dion09_npc2022_ht01]", "[dion09_npc2022_ht02]", "[dion09_npc2022_ht03]", "[dion10_npc2023_ht01]", "[oren04_npc2119_ht01]", "[oren04_npc2119_ht02]", "[oren17_npc2219_ht01]", "[oren17_npc2219_ht02]",
                  "[oren17_npc2219_ht03]", "[giran11_npc2222_ht01]", "[giran11_npc2222_ht02]", "[giran11_npc2222_ht03]", "[giran11_npc2222_ht04]", "[schuttgart03_npc2312_ht01]", "[schuttgart03_npc2312_ht02]",
                  "[gludio06_npc1722_ht01]", "[gludio06_npc1722_ht02]", "[gludio06_npc1722_ht03]", "[aden14_npc2320_ht01]", "[aden14_npc2320_ht02]", "[aden14_npc2320_ht03]", "[gludio25_npc1725_ht01]", "[gludio25_npc1725_ht02]",
                  "[aden13_npc2418_ht01]", "[aden13_npc2418_ht02]", "[innadril09_npc2324_ht01]", "[innadril09_npc2324_ht02]", "[innadril09_npc2324_ht03]", "[godard02_npc2416_ht01]", "[godard02_npc2416_ht02]",
                  "[rune02_npc2116_ht01]", "[rune02_npc2116_ht02]", "[schuttgart20_npc2213_ht01]", "[schuttgart20_npc2213_ht02]", "[aden45_npc1619__ht01]" -> xmlName = "heart_event.xml";
             case "[lyonn03_npc1814_bk01]", "[lyonn03_npc1814_bk02]", "[gludio08_npc1921_bk01]", "[gludio08_npc1921_bk02]", "[gludio08_npc1921_bk03]", "[oren09_npc1921_bk01]", "[oren09_npc1921_bk02]",
                  "[dion09_npc2022_bk01]", "[dion09_npc2022_bk02]", "[dion09_npc2022_bk03]", "[dion10_npc2023_bk01]", "[oren04_npc2119_bk01]", "[oren04_npc2119_bk02]", "[oren17_npc2219_bk01]",
                  "[oren17_npc2219_bk02]", "[oren17_npc2219_bk03]", "[giran11_npc2222_bk01]", "[giran11_npc2222_bk02]", "[giran11_npc2222_bk03]", "[giran11_npc2222_bk04]", "[schuttgart03_npc2312_bk01]",
                  "[schuttgart03_npc2312_bk02]", "[gludio06_npc1722_bk01]", "[gludio06_npc1722_bk02]", "[gludio06_npc1722_bk03]", "[aden14_npc2320_bk01]", "[aden14_npc2320_bk02]", "[aden14_npc2320_bk03]",
                  "[gludio25_npc1725_bk01]", "[gludio25_npc1725_bk02]", "[aden13_npc2418_bk01]", "[aden13_npc2418_bk02]", "[innadril09_npc2324_bk01]", "[innadril09_npc2324_bk02]", "[innadril09_npc2324_bk03]",
                  "[godard02_npc2416_bk01]", "[godard02_npc2416_bk02]", "[rune02_npc2116_bk01]", "[rune02_npc2116_bk02]", "[schuttgart20_npc2213_bk01]", "[schuttgart20_npc2213_bk02]", "[aden45_npc1619__bk01]" -> xmlName = "bak_event.xml";
             case "[lyonn03_npc1814_md01]", "[lyonn03_npc1814_md02]", "[gludio08_npc1921_md01]", "[gludio08_npc1921_md02]", "[gludio08_npc1921_md03]", "[oren09_npc2018_md01]", "[oren09_npc2018_md02]",
                  "[dion09_npc2022_md01]", "[dion09_npc2022_md02]", "[dion09_npc2022_md03]", "[dion10_npc2023_md01]", "[oren04_npc2119_md01]", "[oren04_npc2119_md02]", "[oren17_npc2219_md01]", "[oren17_npc2219_md02]",
                  "[oren17_npc2219_md03]", "[giran11_npc2222_md01]", "[giran11_npc2222_md02]", "[giran11_npc2222_md03]", "[giran11_npc2222_md04]", "[schuttgart03_npc2312_md01]", "[schuttgart03_npc2312_md02]",
                  "[gludio06_npc1722_md01]", "[gludio06_npc1722_md02]", "[gludio06_npc1722_md03]", "[aden14_npc2320_md01]", "[aden14_npc2320_md02]", "[aden14_npc2320_md03]", "[gludio25_npc1725_md01]",
                  "[gludio25_npc1725_md02]", "[aden13_npc2418_md01]", "[aden13_npc2418_md02]", "[innadril09_npc2324_md01]", "[innadril09_npc2324_md02]", "[innadril09_npc2324_md03]", "[godard02_npc2416_md01]",
                  "[godard02_npc2416_md02]", "[rune02_npc2116_md01]", "[rune02_npc2116_md02]", "[lyonn03_npc1814_mdtel03]", "[schuttgart20_npc2213_md01]", "[schuttgart20_npc2213_md02]", "[aden45_npc1619__md01]" -> xmlName = "event_medal.xml";
             case "[lyonn03_npc1814_fi01]", "[gludio08_npc1921_fi01]", "[gludio08_npc1921_fi02]", "[gludio08_npc1921_fi03]", "[oren09_npc2018_fi01]", "[dion09_npc2022_fi01]", "[dion09_npc2022_fi02]",
                  "[dion10_npc2023_fi01]", "[oren04_npc2119_fi01]", "[oren17_npc2219_fi01]", "[oren17_npc2219_fi02]", "[oren17_npc2219_fi03]", "[giran11_npc2222_fi01]", "[giran11_npc2222_fi02]",
                  "[giran11_npc2222_fi03]", "[schuttgart03_npc2312_fi01]", "[gludio06_npc1722_fi01]", "[gludio06_npc1722_fi02]", "[aden14_npc2320_fi01]", "[aden14_npc2320_fi02]",
                  "[gludio25_npc1725_fi01]", "[aden13_npc2418_fi01]", "[aden13_npc2418_fi02]", "[aden13_npc2418_fi03]", "[innadril09_npc2324_fi01]", "[innadril09_npc2324_fi02]", "[godard02_npc2416_fi01]",
                  "[godard02_npc2416_fi02]", "[rune02_npc2116_fi01]", "[rune02_npc2116_fi02]", "[schuttgart20_npc2213_fi01]", "[schuttgart20_npc2213_fi02]", "[aden45_npc1619__fi01]" -> xmlName = "fire_event.xml";
             case "[gludio06_npc1722_tk01]", "[gludio25_npc1725_tk01]", "[gludio08_npc1921_tk01]", "[schuttgart03_npc2312_tk01]", "[lyonn03_npc1814_tk01]", "[oren09_npc2018_tk01]", "[oren04_npc2119_tk01]",
                  "[oren17_npc2219_tk01]", "[dion09_npc2022_tk01]", "[dion10_npc2023_tk02]", "[dion10_npc2023_tk03]", "[innadril09_npc2324_tk01]", "[giran11_npc2222_tk01]",
                  "[aden13_npc2418_tk01]", "[aden14_npc2320_tk01]", "[godard02_npc2416_tk01]", "[rune02_npc2116_tk01]", "[schuttgart20_npc2213_tk01]", "[aden45_npc1619__tk01]" -> xmlName = "event_18age.xml";
             case "[lyonn03_npc2418_2c01]", "[lyonn03_npc2418_2c02]", "[innadril09_npc2324_2c01]", "[aden14_npc2320_2c01]", "[schutgart03_npc2312_2c01]", "[schutgart03_npc2312_2c02]",
                  "[giran11_npc2222_2c01]", "[oren17_npc2219_2c01]", "[gludio08_npc2119_2c01]", "[gludio08_npc2119_2c02]", "[dion10_npc2023_2c01]", "[dion09_npc2022_2c01]", "[oren09_npc2018_2c01]",
                  "[oren09_npc2018_2c02]", "[gludio08_npc1921_2c01]", "[lyonn03_npc1814_2c01]", "[gludio25_npc1725_2c01]", "[gludio25_npc1725_2c02]", "[gludio06_npc1722_2c01]", "[godard02_npc2416_2c01]",
                  "[godard02_npc2416_2c02]", "[rune02_npc2116_2c01]", "[rune02_npc2116_2c02]", "[schuttgart20_npc2213_2c01]", "[schuttgart20_npc2213_2c02]", "[aden45_npc1619__2c01]" -> xmlName = "test_2nd_class.xml";
             case "[schuttgart03_npc2312_sw01]", "[schuttgart03_npc2312_sw02]", "[oren04_npc2119_sw01]", "[oren04_npc2119_sw02]", "[oren09_npc2018_sw01]", "[oren09_npc2018_sw02]", "[gludio08_npc1921_sw01]",
                  "[lyonn03_npc1814_sw01]", "[gludio25_npc1725_sw01]", "[gludio25_npc1725_sw02]", "[gludio06_npc1722_sw01]", "[schuttgart20_npc2213_sw01]", "[aden45_npc1619__sw01]" -> xmlName = "event_start_weapon.xml";
             case "[18_14_net_cafe_event_1_01]", "[18_14_net_cafe_event_1_02]", "[19_21_net_cafe_event_1_01]", "[19_21_net_cafe_event_1_02]", "[19_21_net_cafe_event_1_03]",
                  "[20_18_net_cafe_event_1_01]", "[20_18_net_cafe_event_1_02]", "[20_22_net_cafe_event_1_01]", "[20_22_net_cafe_event_1_02]", "[20_22_net_cafe_event_1_03]",
                  "[20_23_net_cafe_event_1_01]", "[21_19_net_cafe_event_1_01]", "[21_19_net_cafe_event_1_02]", "[22_19_net_cafe_event_1_01]", "[22_19_net_cafe_event_1_02]", "[22_19_net_cafe_event_1_03]",
                  "[22_22_net_cafe_event_1_01]", "[22_22_net_cafe_event_1_02]", "[22_22_net_cafe_event_1_03]", "[22_22_net_cafe_event_1_04]", "[23_12_net_cafe_event_1_01]", "[23_12_net_cafe_event_1_02]",
                  "[17_22_net_cafe_event_1_01]", "[17_22_net_cafe_event_1_02]", "[17_22_net_cafe_event_1_03]", "[23_20_net_cafe_event_1_01]", "[23_20_net_cafe_event_1_02]", "[23_20_net_cafe_event_1_03]",
                  "[17_25_net_cafe_event_1_01]", "[17_25_net_cafe_event_1_02]", "[24_18_net_cafe_event_1_01]", "[24_18_net_cafe_event_1_02]", "[23_24_net_cafe_event_1_01]", "[23_24_net_cafe_event_1_02]",
                  "[23_24_net_cafe_event_1_03]", "[24_16_net_cafe_event_1_01]", "[21_16_net_cafe_event_1_01]", "[schuttgart20_npc2213_nc01]", "[schuttgart20_npc2213_nc02]" -> xmlName = "net_cafe_event_1.xml";
             case "[schuttgart20_npc2213_so01]", "[schuttgart20_npc2213_so02]" -> xmlName = "worldcup_event.xml";
             case "[aden45_npc1619__we01]" -> xmlName = "event_welcome_to_lineage2.xml";
             case "godard29_npc2316_pg1]", "godard29_npc2316_pg2]", "[godard13_npc2415_pg1]", "[godard13_npc2415_pg2]", "[aden18_npc2419_pg1]", "[aden18_npc2419_pg2]", "[aden18_npc2419_pg3]",
                  "[aden18_npc2419_pg4]", "[aden18_npc2419_pg5]", "[aden18_npc2419_pg6]", "[aden18_npc2419_pg7]", "[aden14_npc2320_pg1]", "[aden14_npc2320_pg2]", "[aden14_npc2320_pg3]",
                  "[aden14_npc2320_pg4]", "[aden14_npc2320_pg5]", "[aden14_npc2320_pg6]", "[aden14_npc2320_pg7]", "[aden13_npc2418_pg1]", "[aden13_npc2418_pg2]", "[aden13_npc2418_pg3]", "[aden13_npc2418_pg4]",
                  "[godard06_npc2414_pg1]", "[godard06_npc2414_pg2]", "[schuttgart20_npc2213_pg1];[schuttgart20_npc2213_pg2];[schuttgart20_npc2213_pg3]", "[schuttgart20_npc2213_pg4]", "[schuttgart20_npc2213_pg5]",
                  "[schuttgart20_npc2213_pg6]", "[schuttgart03_npc2312_pg1]", "[schuttgart03_npc2312_pg2]", "[oren29_npc2218_pg1]", "[oren29_npc2218_pg2]", "[oren23_npc2018_pg1]", "[oren23_npc2018_pg2]",
                  "[godard02_npc2416_pg1]", "[godard02_npc2416_pg2]", "[godard02_npc2416_pg3]", "[godard02_npc2416_pg4]", "[oren17_npc2219_pg1]", "[oren17_npc2219_pg2]", "[oren17_npc2219_pg3]", "[oren17_npc2219_pg4]",
                  "[oren20_npc2219_pg1]", "[oren20_npc2219_pg2]", "[oren04_npc2119_pg1]", "[oren04_npc2119_pg2]", "[oren04_npc2119_pg3]", "[oren04_npc2119_pg4]", "[oren04_npc2119_pg5]", "[oren04_npc2119_pg6]",
                  "[oren04_npc2119_pg7]", "[aden05_npc2517_pg2]", "[aden05_npc2517_pg5]", "[oren15_npc2217_pg2]", "[oren15_npc2217_pg5]", "[lyonn03_npc1814_pg1]", "[lyonn03_npc1814_pg2]", "[gludio_1722_pg01];[gludio_1722_pg02]",
                  "[gludio_1722_pg03]", "[gludio_1722_pg04]", "[gludio_1725_pg01]", "[gludio_1725_pg02]" ,"[gludio_1725_pg03]", "[gludio_1725_pg01];[gludio_1725_pg02]", "[gludio_npc1725_pg01]",
                  "[gludio_npc1725_pg02]", "[gludio_npc1725_pg03]", "[gludio_1921_pg01];[gludio_1921_pg02]", "[gludio_1921_pg03]", "[dion_2022_pg01]", "[dion_2022_pg02]", "[dion_2022_pg03]",
                  "[dion_2022_pg04]", "[dion_2022_pg05]", "[dion_2022_pg06]", "[dion_2121_pg01]", "[dion_2023_pg01]", "[giran_2222_pg01]", "[giran_2222_pg02]", "[giran_2222_pg03]", "[giran_2222_pg04]",
                  "[giran_2222_pg05]","[giran_2222_pg06]", "[giran_2222_pg07]", "[giran_2222_pg08]", "[giran_2222_pg09]", "[giran_2322_pg01]", "[giran_2322_pg02]", "[rune_2016_pg01];[rune_2016_pg02];[rune_2016_pg03]",
                  "[rune_2016_pg04];[rune_2016_pg05]", "[rune_2016_pg06]", "[rune_2016_pg01];[rune_2016_pg02];[rune_2016_pg03];[rune_2016_pg04];[rune_2016_pg05];[rune_2016_pg06]", "[rune_2116_pg01]", "[rune_2116_pg02]",
                  "[rune_2116_pg03]", "[rune_2116_pg04]", "[rune_2116_pg05]", "[rune_2116_pg06]", "[rune_2116_pg07]", "[rune_2117_pg02]", "[rune_2117_pg04]", "[rune_2117_pg05];[rune_2117_pg06]", "[rune_npc2117_pg03]",
                  "[innadril_2324_pg01]", "[innadril_2324_pg02]", "[innadril_2324_pg03]", "[innadril_2324_pg01];[innadril_2324_pg02]", "[innadril_2325_pg01]", "[aden45_npc1619__pg01]", "[aden45_npc1619__pg02]",
                  "[aden45_npc1619__pg03]", "[aden45_npc1619__pg04]" -> xmlName = "event_mutant_pig.xml";
             case "[gludio06_npc1722_tb01]", "[gludio06_npc1722_tb02]", "[gludio06_npc1722_tb03]", "[gludio25_npc1725_tb01]", "[gludio25_npc1725_tb02]", "[lyonn03_npc1814_tb01]", "[lyonn03_npc1814_tb02]",
                  "[gludio08_npc1921_tb01]", "[gludio08_npc1921_tb02]", "[gludio08_npc1921_tb03]", "[oren09_npc2018_tb01]", "[oren09_npc2018_tb02]", "[dion09_npc2022_tb01]", "[dion09_npc2022_tb02]", "[dion09_npc2022_tb03]",
                  "[dion10_npc2023_tb01]", "[rune02_npc2116_tb01]", "[rune02_npc2116_tb02]", "[oren04_npc2119_tb01]", "[oren04_npc2119_tb02]", "[oren17_npc2219_tb01]", "[oren17_npc2219_tb02]", "[oren17_npc2219_tb03]",
                  "[giran11_npc2222_tb01]", "[giran11_npc2222_tb02]", "[giran11_npc2222_tb03]", "[giran11_npc2222_tb04]", "[schuttgart03_npc2312_tb01]", "[schuttgart03_npc2312_tb02]",
                  "[aden14_npc2320_tb01]", "[aden14_npc2320_tb02]", "[aden14_npc2320_tb03]", "[innadril09_npc2324_tb01]", "[innadril09_npc2324_tb02]", "[innadril09_npc2324_tb03]",
                  "[godard02_npc2416_tb01]", "[godard02_npc2416_tb02]", "[aden13_npc2418_tb01]", "[aden13_npc2418_tb02]", "[schuttgart20_npc2213_tb01]", "[schuttgart20_npc2213_tb02]",
                  "[aden45_npc1619__tb01]" -> xmlName = "event_treasure_box.xml";
             case "[etc08_npc1816_manager01]", "[etc08_npc1715_search01]", "[etc08_npc1815_search02]", "[etc08_npc1716_search03]", "[etc08_npc1816_search04]", "[gludio08_npc1921_sch01]",
                  "[gludio08_npc1921_sch02]", "[gludio08_npc1921_sch03]", "[gludio06_npc1722_sch01]", "[gludio06_npc1722_sch02]", "[gludio06_npc1722_sch03]", "[dion09_npc2022_sch01]",
                  "[dion09_npc2022_sch02]", "[dion09_npc2022_sch03]", "[giran11_npc2222_sch01]", "[giran11_npc2222_sch02]", "[giran11_npc2222_sch03]", "[giran11_npc2222_sch04]",
                  "[oren17_npc2219_sch01]", "[oren17_npc2219_sch02]", "[oren17_npc2219_sch03]", "[aden14_npc2320_sch01]", "[aden14_npc2320_sch02]", "[aden14_npc2320_sch03]",
                  "[aden13_npc2418_sch01]", "[aden13_npc2418_sch02]", "[innadril09_npc2324_sch01]", "[innadril09_npc2324_sch02]", "[innadril09_npc2324_sch03]", "[oren04_npc2119_sch01]", "[oren04_npc2119_sch02]",
                  "[godard02_npc2416_sch01]", "[godard02_npc2416_sch02]", "[rune02_npc2116_sch01]", "[rune02_npc2116_sch02]", "[schuttgart20_npc2213_sch01]", "[schuttgart20_npc2213_sch02]",
                  "[gludio25_npc1725_sch01]", "[gludio25_npc1725_sch02]", "[oren09_npc2018_sch01]", "[oren09_npc2018_sch02]", "[dion10_npc2023_sch01]", "[lyonn03_npc1814_sch01]", "[lyonn03_npc1814_sch02]",
                  "[schuttgart03_npc2312_sch01]", "[schuttgart03_npc2312_sch02]", "[aden45_npc1619__sch01]", "[aden45_npc1619__sch02]" -> xmlName = "event_search.xml";
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
