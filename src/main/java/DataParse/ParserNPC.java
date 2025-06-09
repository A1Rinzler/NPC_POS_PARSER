package DataParse;

import GroupsEnum.Groups;
import Patterns.MassPointPattern;
import Patterns.SinglePointPattern;
//import XmlEngine.CreateXML;
import XmlEngine.EndOfLine;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 05.04.2025
 **/

public class ParserNPC {
    private final static String npcpos_file = "PTS_Scripts/npcpos.txt";
    //private final static String npcpos_file = "Npc_Pos_Parser/PTS_Scripts/npcpos_test.txt";
    //private final static String npcpos_file = "Npc_Pos_Parser/PTS_Scripts/npcpos.txt";
    Properties properties = new Properties();
    GetNPC_Id getNPCId = new GetNPC_Id();
    SinglePointPattern singlePointPattern = new SinglePointPattern();
    MassPointPattern massPointPattern = new MassPointPattern();
    EndOfLine endOfLine = new EndOfLine();
    List<String> npc_Pos = new ArrayList<>();
    List<String> arrTerritoryList = new ArrayList<>();
    String groupName = "";
    int respawnTime = 0;
    int respawnRandTime = 0;
    int total = 0;
    int npc_Id = 0;
    String npc_Name = "";
    String periodOfDay = "none";
    String territoryName = "";
    String eventGroupName = "";

    boolean debug;

     public void parse(){
        getNPCId.getAllNPCID();

         try {
             //BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npcpos_file)));//, StandardCharsets.UTF_16LE));
             BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npcpos_file), StandardCharsets.UTF_16LE));

             //properties.load(new FileInputStream("Npc_Pos_Parser/debug.properties"));
             properties.load(new FileInputStream("debug.properties"));

             String debugKey = properties.getProperty("debug");
             if (!debugKey.isEmpty()){
             debug = Boolean.parseBoolean(debugKey);
             }else debug = false;

             String str;

                while ((str = bufferedReader.readLine()) !=null  ) {
                    if (str.startsWith("territory_begin")) {
                        arrTerritoryList.clear();

                        String[] arrTerritory_begin = str.split("\t");
                        //territoryName = arrTerritory_begin[1];
                        String coordTerritory = arrTerritory_begin[2];
                        String[] arrTerritory = coordTerritory.replaceAll("[{}]", "").split(";"); //координаты Anywhere
                        arrTerritoryList.addAll(Arrays.asList(arrTerritory));
                    }
                    if (str.startsWith("npcmaker_begin")) {
                        periodOfDay = "none";
                        //eventGroupName = "";
                        String[] arrNpcmaker_begin = str.split("\t");
                        territoryName = arrNpcmaker_begin[1];
                        String nextLine;
                        while(!(nextLine = bufferedReader.readLine()).contains("npcmaker_end")){
                            if (nextLine.startsWith("npc_begin")) {
                                resetValues();
                                String[] npcBeginLine = nextLine.split("\t");

                                String dbName = npcBeginLine[5];

                                groupName = Groups.getGroupByLine(dbName);
                                parseDataLine(nextLine);

                                if (npcBeginLine[2].contains("anywhere")){
                                    callMassPointPattern();
                                }else {
                                    callSinglePointPattern();
                                }

                           }
                        }
                    }
                    else if (str.startsWith("npcmaker_ex_begin")) {
                        periodOfDay = "none";
                        eventGroupName = "";
                        String nextLine;
                        String[] arrNpcmaker_ex_begin= str.split("\t");
                        territoryName = arrNpcmaker_ex_begin[1];

                        if (arrNpcmaker_ex_begin[4].contains("IsNight")){
                           int dayMarker =  Integer.parseInt(arrNpcmaker_ex_begin[4].replaceAll("[^0-9]",""));

                           switch (dayMarker){
                               case 0: periodOfDay = "day";
                               break;
                               case 1: periodOfDay = "night";
                               break;
                               default: periodOfDay = "none";
                           }
                        }

                        if (arrNpcmaker_ex_begin[4].contains("[EventName]")){
                            String[] eventNameArr = arrNpcmaker_ex_begin[4].replaceAll("[{}\\[\\]]", "").split("=");
                            String eventPTSName = eventNameArr[2];
                            eventGroupName = Groups.getGroupByLine(eventPTSName);
                            //System.out.println(eventPTSName);
                            //System.out.println(eventGroupName);
                        }

                        while(!(nextLine = bufferedReader.readLine()).contains("npcmaker_ex_end")){
                            if (nextLine.startsWith("npc_ex_begin")) {
                                resetValues();
                                String[] anywhere = nextLine.split("\t");
                                parseDataLine(nextLine);
                                if (anywhere[2].contains("anywhere")){
                                    callMassPointPattern();
                                }else {
                                    callSinglePointPattern();
                                }
                            }
                        }
                    }
                }
                bufferedReader.close();
         } catch (FileNotFoundException e) {
             System.out.println("npcpos.txt not found");
             //throw new RuntimeException(e);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         endOfLine.writeEndOfLine();
     }

     private void resetValues(){
         npc_Pos.clear();
         respawnRandTime = 0;
         groupName = "";
     }
    private void callSinglePointPattern(){
         singlePointPattern.singlePattern(
                 npc_Pos,
                 groupName,
                 total,
                 respawnTime,
                 respawnRandTime,
                 periodOfDay,
                 npc_Id,
                 npc_Name,
                 territoryName,
                 eventGroupName);
     }
    private void callMassPointPattern(){
         massPointPattern.massPattern(
                 arrTerritoryList,
                 groupName,
                 total,
                 respawnTime,
                 respawnRandTime,
                 periodOfDay,
                 npc_Id,
                 npc_Name,
                 territoryName,
                 eventGroupName);
     }

    private void swap(String[] arr, int index){
            if (arr.length != 0 ){
                String temp = arr[index];
                for (int i = index; i < arr.length - 1; i ++) {
                   arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = temp;
            }
            else throw new IllegalArgumentException("Массив пуст");
    }

    private void parseDataLine(String nextLine) {
        String[] arrNpc_Pos;
        String[] arrNpc_begin = nextLine.split("\t");

        //свап индексов, если на второй позиции стоит nickname, а не координаты. Перемещается в конец массива.
        if (arrNpc_begin[2].contains("nickname")){
            swap(arrNpc_begin, 2);
        }
        npc_Name = arrNpc_begin[1].replaceAll("[\\[\\]]", "").trim();
        npc_Id = getNPCId.getNPC_Id(arrNpc_begin[1]);
        String[] splitPos = arrNpc_begin[2].split("=");

        //быстро посмотреть, где стопорится парс
        if (debug){
            System.out.println(territoryName + " " + npc_Name);
        }

        //Бывает несколько координат с процентом спавна на точку. У оверов это не работает, берем первую точку.
        //Сделал, коммент оставлю, мож подправлю на стороне сервера процент появления на точке.
        //Хотя это скорее где-то в спайн менеджере эпик босса прописано
        if (!splitPos[1].equals("anywhere")) {
            String[] arrNpc_Pos_Temp = splitPos[1].replaceAll("[{}]", "").split(";");
            arrNpc_Pos = Arrays.copyOfRange(arrNpc_Pos_Temp, 0, 4);
            //arrNpc_Pos = splitPos[1].replaceAll("[{}]", "").split(";", 4);
        } else arrNpc_Pos = null;

        if (arrNpc_Pos != null) {
            npc_Pos.addAll(Arrays.asList(arrNpc_Pos));
        }

        //свап индексов, если на третьей позиции стоит on_delete_create, а не total. Перемещается в конец массива.
        if (arrNpc_begin[3].contains("on_delete_create")){
            swap(arrNpc_begin, 3);
        }

        String totalStr = (arrNpc_begin[3].replaceAll("[^0-9]", ""));
        total = Integer.parseInt(totalStr);

        if (arrNpc_begin[4].contains("hour")) {
            respawnTime = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]", "")) * 3600;
        } else if (arrNpc_begin[4].contains("min")) {
            respawnTime = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]", "")) * 60;
        } else if (arrNpc_begin[4].contains("no")) {
                respawnTime = 0;
        } else respawnTime = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]", ""));

        if (arrNpc_begin[5].contains("respawn_rand")){
            if (arrNpc_begin[5].contains("hour")) {
                respawnRandTime = Integer.parseInt(arrNpc_begin[5].replaceAll("[^0-9]", "")) * 3600;
            } else if (arrNpc_begin[5].contains("min")) {
                respawnRandTime = Integer.parseInt(arrNpc_begin[5].replaceAll("[^0-9]", "")) * 60;
            } else respawnRandTime = Integer.parseInt(arrNpc_begin[5].replaceAll("[^0-9]", ""));
        }
     }
}
