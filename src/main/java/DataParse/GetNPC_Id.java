package DataParse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 05.04.2025
 **/

public class GetNPC_Id {

    public Map<String, Integer> allNPCId = new HashMap<>();

    //private final String npc_pch = "Npc_Pos_Parser/PTS_Scripts/npc_pch.txt";

    private final String npc_pch = "PTS_Scripts/npc_pch.txt";

    public int getNPC_Id(String NPCName){
        return allNPCId.get(NPCName);
    }

    public void getAllNPCID(){

        try {
            String str;
            BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npc_pch), StandardCharsets.UTF_16LE));

            while ((str = bufferedReader.readLine()) != null) {
                String[] array = str.split("=");
                String onePart = array[0].trim();
                Integer twoPart = Integer.valueOf(array[1].substring(2).replaceFirst("0", ""));
                allNPCId.put(onePart, twoPart);
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
