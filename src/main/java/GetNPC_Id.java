import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GetNPC_Id {
    public Map<String, Integer> allNPCId = new HashMap<>();
    private File npc_pch = new File("src/main/resources/npc_pch.txt");

    public int getNPC_Id(String NPCName){
        return allNPCId.get(NPCName);
    }

    public void getAllNPCID(){

        try {
            String str = null;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(npc_pch));
            while ((str = bufferedReader.readLine()) != null) {
                String[] array = str.split("=");
                String onePart = array[0].trim();
                Integer twoPart = Integer.valueOf(array[1].substring(2).replaceFirst("0", ""));
                //System.out.println(twoPart);
                allNPCId.put(onePart, twoPart);
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
