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

// respawn_random="0" period_of_day="none"

public class ParserNPC {
    final String npcpos_file = "src/main/resources/npcpos.txt";
    //private final Charset charset = Charset.forName("UTF-16LE");
    GetNPC_Id getNPCId = new GetNPC_Id();
    List<String> Npc_Pos = new ArrayList<>();
    StringBuffer npcStringBuffer = new StringBuffer();
    String groupName = "";
    //List<String> territory = new ArrayList<>();
    int respawnTime = 0;
    int total = 0;
    int npc_id = 0;
    String npc_Name = "";

     void parse(){
        getNPCId.getAllNPCID();
         try {
             BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npcpos_file), StandardCharsets.UTF_16LE));
             String str;
             String[] arrTerritory;

                while ((str = bufferedReader.readLine()) !=null  ) {
                    if (str.startsWith("territory_begin")) {
                        String[] arrTerritory_begin = str.split("\t");

                        CreateXML.createXMLFile(arrTerritory_begin[1]);

                        String coordTerritory = arrTerritory_begin[2];

                         arrTerritory = coordTerritory.replaceAll("[{}]", "").split(";"); //координаты Anywhere

                    }
                    if (str.startsWith("npcmaker_begin")) {
                        String[] arrNpcmaker_begin = str.split("\t");
                        String nextLine;
                        int count = 0;
                        while(!(nextLine = bufferedReader.readLine()).contains("npcmaker_end"))
                            //while((nextLine = bufferedReader.readLine()) !=null)
                            {

                            if (nextLine.startsWith("npc_begin")) {
                                String[] anywhere = nextLine.split("\t");
                                if (anywhere[2].contains("anywhere")){
                                    System.out.println("anywhere found");
                                    parseDataLine(nextLine);
                                    outPatternAnywherePoint();

                                    //System.out.println(nextLine);
                                }

                                else {
                                    parseDataLine(nextLine);
                                    outPatternSinglePoint();

                                }

//                                System.out.println("привет");

                           }
//                            else if (nextLine.contains("npcmaker_end")){
//                                    count++;
//                                System.out.println(count);
//                                }

                        }

                    }
                    else if (str.startsWith("npcmaker_ex_begin")) {
//                                    parseDataLine(nextLine);
//                                    outPatternMassPoint();

                        //System.out.println("yahoo");
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

        void parseDataLine(String nextLine){
            String[] arrNpc_Pos;

            String[] arrNpc_begin = nextLine.split("\t");
            npc_Name = arrNpc_begin[1].replace("[","").replace("]","").trim();
            npc_id = getNPCId.getNPC_Id(arrNpc_begin[1]);

            String[] splitPos = arrNpc_begin[2].split("=");

            if (!splitPos[1].equals("anywhere")){
                arrNpc_Pos = splitPos[1].replaceAll("[{}]", "").split(";");
            }
            else arrNpc_Pos = null;

            if (arrNpc_Pos != null){
                Npc_Pos.addAll(Arrays.asList(arrNpc_Pos));
            }

            String totalStr = (arrNpc_begin[3].replaceAll("[^0-9]",""));
            total = Integer.parseInt(totalStr);
            //int respawnTimeOrigin = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]",""));
            if (arrNpc_begin[4].contains("hour")){
                respawnTime = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]","")) * 3600;
            } else if (arrNpc_begin[4].contains("min")){
                respawnTime = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]","")) * 60;
            } else respawnTime = Integer.parseInt(arrNpc_begin[4].replaceAll("[^0-9]",""));
        }


    //                                	<spawn group="devastated_castle_guards" count="1" respawn="300" respawn_random="0" period_of_day="none">
//                                          <point x="178222" y="-14884" z="-2200" h="0" />
//                                        <npc id="35412" /><!--Doom Guard-->
//                                      </spawn>

//                                  	<spawn count="1" respawn="60" respawn_random="0" period_of_day="none">
//		                                        <point x="-100332" y="238019" z="-3573" h="36864" />
//		                                    <npc id="30311" /><!--Sir Collin Windawood-->
//	                                    </spawn>

    void outPatternAnywherePoint(){

    }


    void outPatternSinglePoint(){
        if (!Npc_Pos.isEmpty()) {
            int npcCoord = 0;
            for (int i = 0; i < Npc_Pos.size(); i+=4) {
                if (!groupName.isEmpty()){
                    npcStringBuffer.append("<spawn group=\"").append(groupName).append("\"\t").append("count=\"").append(total)
                            .append("\" respawn=\"").append(respawnTime).append("\" respawn_random=\"0\" period_of_day=\"none\">")
                            .append("\t\t<point x=\"").append(Npc_Pos.get(npcCoord++)).append("\" y=\"").append(Npc_Pos.get(npcCoord++))
                            .append("\" z=\"").append(Npc_Pos.get(npcCoord++)).append("\" h=\"").append(Npc_Pos.get(npcCoord++)).append("\" />\n")
                            .append("\t<npc id=\"").append(npc_id).append("\" /><!--").append(npc_Name).append("-->")
                            .append("\n").append("</spawn>\n\n");
                }
                 else   npcStringBuffer.append("<spawn count=\"").append(total)
                            .append("\" respawn=\"").append(respawnTime).append("\" respawn_random=\"0\" period_of_day=\"none\">\n")
                            .append("\t\t<point x=\"").append(Npc_Pos.get(npcCoord++)).append("\" y=\"").append(Npc_Pos.get(npcCoord++))
                            .append("\" z=\"").append(Npc_Pos.get(npcCoord++)).append("\" h=\"").append(Npc_Pos.get(npcCoord++)).append("\" />\n")
                            .append("\t<npc id=\"").append(npc_id).append("\" /><!--").append(npc_Name).append("-->")
                            .append("\n").append("</spawn>\n\n");


                System.out.print(npcStringBuffer);

                Npc_Pos.clear();
                npcStringBuffer.setLength(0);
            }
        }

    }

     void outPatternMassPoint(){
         if (!Npc_Pos.isEmpty()) {
             int npcCoord = 0;
             for (int i = 0; i < Npc_Pos.size(); i+=4) {
                 npcStringBuffer.append("<spawn count=\"").append(total)
                         .append("\" respawn=\"").append(respawnTime).append("\" respawn_random=\"0\" period_of_day=\"none\">")
                         .append("\n").append("\t\t <territory>").append("\n")
                         .append("\t\t\t").append("<add x=\"").append(Npc_Pos.get(npcCoord++)).append("\" y=\"").append(Npc_Pos.get(npcCoord++)).append("\" zmin=\"")
                         .append(Npc_Pos.get(npcCoord++)).append("\" zmax=\"").append(Npc_Pos.get(npcCoord++)).append("\" />")
                         .append("\n").append("\t\t</territory>").append("\n")
                         .append("\t<npc id=\"").append(npc_id).append("\" /><!--").append(npc_Name).append("-->")
                         .append("\n").append("</spawn>\n\n");
             }
             System.out.print(npcStringBuffer);
             Npc_Pos.clear();
             npcStringBuffer.setLength(0);
         }
     }
}
