import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// respawn_random="0" period_of_day="none"

public class ParserNPC {
    private final String npcpos_file = "src/main/resources/npcpos.txt";
    private final Charset charset = Charset.forName("UTF-16LE");
    GetNPC_Id getNPCId = new GetNPC_Id();


     void parse(){
        getNPCId.getAllNPCID();
        StringBuffer npcStr = new StringBuffer();
        List<String> Npc_Pos = new ArrayList<>();

         try {
             BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npcpos_file), charset));
             String str;
             int npc_id = 0;
             String[] arrTerritory;
             String[] arrNpc_Pos;
             int respawnTime = 0;
             int total = 0;
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
                        npc_id = getNPCId.getNPC_Id(arrNpc_begin[1]);

                        String[] splitPos = arrNpc_begin[2].split("=");

                        if (!splitPos[1].equals("anywhere")){
                            arrNpc_Pos = splitPos[1].replaceAll("[{}]", "").split(";");
                        }
                        else arrNpc_Pos = null;

                        Npc_Pos = Arrays.asList(arrNpc_Pos);

                        String totalStr = (arrNpc_begin[3].replaceAll("[^0-9]",""));
                        total = Integer.parseInt(totalStr);

                        if (arrNpc_begin[4].contains("hour")){
                            String respawnTimeStr = (arrNpc_begin[4].replaceAll("[^0-9]",""));
                            respawnTime = Integer.parseInt(respawnTimeStr) * 60;
                        }
                        else respawnTime = Integer.parseInt(arrNpc_begin[4]);

                    }
                    if (Npc_Pos != null) {
                        for (int i = 0; i < Npc_Pos.size(); i+=4) {
                            for (int j = 0; j < 4 ; j++) {
                                System.out.print("npc_id " + npc_id);
                                System.out.print(" x = " + Npc_Pos.get(0));
                                System.out.print(" y = " + Npc_Pos.get(1));
                                System.out.print(" zmin = " + Npc_Pos.get(2));
                                System.out.print(" zmax = " + Npc_Pos.get(3));
                                System.out.println(" total = " + total + " respawnTime = " + respawnTime);

                            }
                        }

                    }
                    if (str.startsWith("npcmaker_end")){break;}
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
