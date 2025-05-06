import GroupsEnum.Groups;
import Patterns.MassPointPattern;
import Patterns.SinglePointPattern;
import XmlEngine.CreateXML;
import XmlEngine.EndOfLine;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 05.04.2025
 **/

public class ParserNPC {
    final String npcpos_file = "Npc_Pos_Parser/PTS_Scripts/npcpos_test.txt";
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

     public void parse(){
        getNPCId.getAllNPCID();
         try {
             BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npcpos_file)));//, StandardCharsets.UTF_16LE));
             String str;

                while ((str = bufferedReader.readLine()) !=null  ) {
                    if (str.startsWith("territory_begin")) {
                        arrTerritoryList.clear();

                        String[] arrTerritory_begin = str.split("\t");
                        territoryName = arrTerritory_begin[1];
                        String coordTerritory = arrTerritory_begin[2];
                        String[] arrTerritory = coordTerritory.replaceAll("[{}]", "").split(";"); //координаты Anywhere
                        arrTerritoryList.addAll(Arrays.asList(arrTerritory));
                    }
                    if (str.startsWith("npcmaker_begin")) {
                        periodOfDay = "none";
                        //String[] arrNpcmaker_begin = str.split("\t");
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
                        String nextLine;
                        String[] arrNpcmaker_ex_begin= str.split("\t");
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
                 territoryName);
     }
    private void callMassPointPattern(){
         massPointPattern.massPattern(
                 arrTerritoryList,
                 total,
                 respawnTime,
                 respawnRandTime,
                 periodOfDay,
                 npc_Id,
                 npc_Name,
                 territoryName);
     }

    private void parseDataLine(String nextLine) {
        String[] arrNpc_Pos;
        String[] arrNpc_begin = nextLine.split("\t");
        npc_Name = arrNpc_begin[1].replace("[", "").replace("]", "").trim();
        npc_Id = getNPCId.getNPC_Id(arrNpc_begin[1]);
        String[] splitPos = arrNpc_begin[2].split("=");

        if (!splitPos[1].equals("anywhere")) {
            arrNpc_Pos = splitPos[1].replaceAll("[{}]", "").split(";");
        } else arrNpc_Pos = null;

        if (arrNpc_Pos != null) {
            npc_Pos.addAll(Arrays.asList(arrNpc_Pos));
        }

        String totalStr = (arrNpc_begin[3].replaceAll("[^0-9]", ""));
        total = Integer.parseInt(totalStr);

        if (arrNpc_begin[4].contains("hour")) {
            respawnTime = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]", "")) * 3600;
        } else if (arrNpc_begin[4].contains("min")) {
            respawnTime = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]", "")) * 60;
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
