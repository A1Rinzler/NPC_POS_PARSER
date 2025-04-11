import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 05.04.2025
 **/

public class GetNPC_Id {

    public Map<String, Integer> allNPCId = new HashMap<>();

    private final String npc_pch = "src/main/resources/npc_pch.txt";
    //private final Charset charset = Charset.forName("UTF-16LE");
    //private File npc_pch = new File("src/main/resources/npc_pch.txt");

    public int getNPC_Id(String NPCName){
        try {
            return allNPCId.get(NPCName);
        }
    catch (NullPointerException e){
        System.out.println("NullPointerException");
        return 0;
        }
    }

    public void getAllNPCID(){

        try {
            String str;
            BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npc_pch), StandardCharsets.UTF_16LE));

            //BufferedReader bufferedReader = new BufferedReader(new FileReader(npc_pch));
            while ((str = bufferedReader.readLine()) != null) {
                String[] array = str.split("=");
                String onePart = array[0].trim();
                Integer twoPart = Integer.valueOf(array[1].substring(2).replaceFirst("0", ""));
                //System.out.println(twoPart);
                allNPCId.put(onePart, twoPart);
                //System.out.println(onePart + " " + twoPart);
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(allNPCId);
    }

}
