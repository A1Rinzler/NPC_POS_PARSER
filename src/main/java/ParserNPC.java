import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// respawn_random="0" period_of_day="none"

public class ParserNPC {
     final String npcpos_file = "src/main/resources/npcpos.txt";
    private final Charset charset = Charset.forName("UTF-16LE");
    GetNPC_Id getNPCId = new GetNPC_Id();


     void parse(){
        getNPCId.getAllNPCID();
        StringBuffer npcStringBuffer = new StringBuffer();
        List<String> Npc_Pos = new ArrayList<>();

         try {
             BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npcpos_file), charset));
             String str;
             int npc_id = 0;
             String[] arrTerritory;
             String[] arrNpc_Pos;
             int respawnTime = 0;
             int total = 0;
             String npc_Name = null;
             //List<String> territory = new ArrayList<>();
                while ((str = bufferedReader.readLine()) !=null  ) {
                    if (str.startsWith("territory_begin")) {
                        String[] arrTerritory_begin = str.split("\t");

                        CreateXML.createXMLFile(arrTerritory_begin[1]);

                        String coordTerritory = arrTerritory_begin[2];

                         arrTerritory = coordTerritory.replaceAll("[{}]", "").split(";"); //координаты Anywhere

                    } else if (str.startsWith("npcmaker_begin")) {
                        String[] arrNpcmaker_begin = str.split("\t");
                    } else if (str.startsWith("npc_begin")) {
                        String[] arrNpc_begin = str.split("\t");
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

                        if (arrNpc_begin[4].contains("hour")){
                            String respawnTimeStr = (arrNpc_begin[4].replaceAll("[^0-9]",""));
                            respawnTime = Integer.parseInt(respawnTimeStr) * 60;
                        }
                        else respawnTime = Integer.parseInt(arrNpc_begin[4]);

                    }
                    /*
                    <spawn count="1" respawn="60" respawn_random="0" period_of_day="none">
		                <territory>
		                	<add x="47995" y="127717" zmin="-3767" zmax="-2967" />
		                	<add x="48579" y="129316" zmin="-3767" zmax="-2967" />
		                	<add x="50391" y="129435" zmin="-3767" zmax="-2967" />
		                	<add x="49791" y="127264" zmin="-3767" zmax="-2967" />
		                </territory>
		              <npc id="20063" /><!--Ol Mahum Shooter-->
	                </spawn>
                     */

                    if (str.startsWith("npcmaker_end")){break;}
                }

             if (Npc_Pos.size() != 0) {
                 int cout = 0;
                 int npcCoord = 0;
                 for (int i = 0; i < Npc_Pos.size(); i+=4) {
                     //for (int j = 0; j < 4 ; j++) {
                                npcStringBuffer.append("<spawn count=\"").append(total)
                                        .append("\" respawn=\"")
                                        .append(respawnTime)
                                        .append("\" respawn_random=\"0\" period_of_day=\"none\">")
                                        .append("\n")
                                        .append("\t\t <territory>").append("\n")
                                        .append("\t\t\t")
                                        .append("<add x=\"")
                                        .append(Npc_Pos.get(npcCoord++))
                                        .append("\" y=\"")
                                        .append(Npc_Pos.get(npcCoord++))
                                        .append("\" zmin=\"")
                                        .append(Npc_Pos.get(npcCoord++))
                                        .append("\" zmax=\"")
                                        .append(Npc_Pos.get(npcCoord++))
                                        .append("\" />")
                                        .append("\n").append("\t\t")
                                        .append("</territory>").append("\n")
                                        .append("\t")
                                        .append("<npc id=\"")
                                        .append(npc_id).append("\" /><!--")
                                        .append(npc_Name).append("-->").append("\n").append("</spawn>").append("\n\n");
//                     cout++;

//                     System.out.println(cout);
//
//                     System.out.print("npc_id " + npc_id);
//                     System.out.print(" x = " + Npc_Pos.get(npcCoord++));
//                     System.out.print(" y = " + Npc_Pos.get(npcCoord++));
//                     System.out.print(" zmin = " + Npc_Pos.get(npcCoord++));
//                     System.out.print(" zmax = " + Npc_Pos.get(npcCoord++));
//                     System.out.println(" total = " + total + " respawnTime = " + respawnTime);
                     //}
                 }
                 System.out.print(npcStringBuffer);


             }
                bufferedReader.close();
         } catch (FileNotFoundException e) {
             System.out.println("npcpos.txt not found");
             //throw new RuntimeException(e);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }



     }
}
