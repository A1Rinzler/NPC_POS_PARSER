import GroupsEnum.CastleSiegeGroup;

import java.io.*;
//import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 05.04.2025
 **/

public class ParserNPC {
    final String npcpos_file = "src/main/resources/npcpos_test.txt";
    GroupsName groupsName = new GroupsName();
    GetNPC_Id getNPCId = new GetNPC_Id();
    List<String> Npc_Pos = new ArrayList<>();
    List<String> arrTerritoryList = new ArrayList<>();
    StringBuffer npcStringBuffer = new StringBuffer();
    String groupName = "";
    int respawnTime = 0;
    int respawnRandTime = 0;
    int total = 0;
    int npc_id = 0;
    String npc_Name = "";
    String periodOfDay = "none";

     void parse(){
        getNPCId.getAllNPCID();
         try {
             BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npcpos_file)));//, StandardCharsets.UTF_16LE));
             String str;

                while ((str = bufferedReader.readLine()) !=null  ) {
                    if (str.startsWith("territory_begin")) {
                        String[] arrTerritory_begin = str.split("\t");

                        CreateXML.createXMLFile(arrTerritory_begin[1]);

                        String coordTerritory = arrTerritory_begin[2];

                        String[] arrTerritory = coordTerritory.replaceAll("[{}]", "").split(";"); //координаты Anywhere
                        arrTerritoryList.addAll(Arrays.asList(arrTerritory));
                    }
                    if (str.startsWith("npcmaker_begin")) {
                        //String[] arrNpcmaker_begin = str.split("\t");
                        String nextLine;
                        while(!(nextLine = bufferedReader.readLine()).contains("npcmaker_end"))
                            {

                            if (nextLine.startsWith("npc_begin")) {
                                String[] npcBeginLine = nextLine.split("\t");

                                String dbName = npcBeginLine[5];

                                groupName = groupsName.getGroupName(dbName);

                                if (npcBeginLine[2].contains("anywhere")){
                                    //System.out.println("anywhere found");
                                    parseDataLine(nextLine);
                                    outPatternAnywherePoint();
                                }
                                else {
                                    parseDataLine(nextLine);
                                    outPatternSinglePoint();
                                }
                           }
                        }
                    }
                    else if (str.startsWith("npcmaker_ex_begin")) {
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
                                String[] anywhere = nextLine.split("\t");
                                if (anywhere[2].contains("anywhere")){
                                    parseDataLine(nextLine);
                                    outPatternAnywherePoint();
                                }
                                else {
                                    parseDataLine(nextLine);
                                    outPatternSinglePoint();
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
     }

     void parseDataLine(String nextLine) {
        String[] arrNpc_Pos;

        String[] arrNpc_begin = nextLine.split("\t");
        npc_Name = arrNpc_begin[1].replace("[", "").replace("]", "").trim();
        npc_id = getNPCId.getNPC_Id(arrNpc_begin[1]);

        String[] splitPos = arrNpc_begin[2].split("=");

        if (!splitPos[1].equals("anywhere")) {
            arrNpc_Pos = splitPos[1].replaceAll("[{}]", "").split(";");
        } else arrNpc_Pos = null;

        if (arrNpc_Pos != null) {
            Npc_Pos.addAll(Arrays.asList(arrNpc_Pos));
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


    //одна координата для спавна нпц
    void outPatternSinglePoint(){
        if (!Npc_Pos.isEmpty()) {
            int npcCoord = 0;
            for (int i = 0; i < Npc_Pos.size(); i+=4) {
                    npcStringBuffer.append("\t<spawn ");
                if (!groupName.isEmpty()){
                    npcStringBuffer.append("group=\"").append(groupName).append("\" ");
                }
                    npcStringBuffer.append("count=\"").append(total).append("\" ")
                                   .append("respawn=\"").append(respawnTime).append("\" ")
                                   .append("respawn_random=\"").append(respawnRandTime).append("\" ")
                                   .append("period_of_day=\"").append(periodOfDay).append("\">\n")
                                   .append("\t\t<point x=\"").append(Npc_Pos.get(npcCoord++)).append("\" y=\"").append(Npc_Pos.get(npcCoord++)).append("\" ")
                                   .append("z=\"").append(Npc_Pos.get(npcCoord++)).append("\" h=\"").append(Npc_Pos.get(npcCoord++)).append("\" />\n")
                                   .append("\t\t<npc id=\"").append(npc_id).append("\" /><!--").append(npc_Name).append("-->").append("\n")
                                   .append("\t</spawn>\n\n");

                System.out.print(npcStringBuffer);

                Npc_Pos.clear();
                npcStringBuffer.setLength(0);
                periodOfDay = "none";
                respawnRandTime = 0;
            }
        }

    }

    //множественные координаты для спавна нпц
     void outPatternAnywherePoint(){
         if (!arrTerritoryList.isEmpty()) {
             int npcCoord = 0;
                 npcStringBuffer.append("\t<spawn count=\"").append(total).append("\" ")
                                .append("respawn=\"").append(respawnTime).append("\" ")
                                .append("respawn_random=\"").append(respawnRandTime).append("\" ");

                    if (!periodOfDay.isEmpty()){
                        npcStringBuffer.append("period_of_day=\"").append(periodOfDay).append("\">").append("\n")
                                       .append("\t\t<territory>").append("\n");
                    }
                    else npcStringBuffer.append("\n").append("\t\t<territory>").append("\n");

                 for (int i = 0; i < arrTerritoryList.size()/4; i++) {
                     npcStringBuffer.append("\t\t\t<add")
                                    .append(" x=\"").append(arrTerritoryList.get(npcCoord++))
                                    .append("\" y=\"").append(arrTerritoryList.get(npcCoord++))
                                    .append("\" zmin=\"").append(arrTerritoryList.get(npcCoord++))
                                    .append("\" zmax=\"").append(arrTerritoryList.get(npcCoord++)).append("\" />")
                                    .append("\n");
                 }

                 npcStringBuffer.append("\t\t</territory>").append("\n")
                                .append("\t\t<npc id=\"").append(npc_id).append("\" /><!--").append(npc_Name).append("-->")
                                .append("\n").append("\t</spawn>\n\n");


             System.out.print(npcStringBuffer);
             npcStringBuffer.setLength(0);
             periodOfDay = "none";
             respawnRandTime = 0;
         }
     }
}
