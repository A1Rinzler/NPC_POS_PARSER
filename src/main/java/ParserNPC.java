import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserNPC {
    private String npcpos_file = "src/main/resources/npcpos.txt";
    private Charset charset = Charset.forName("UTF-16LE");

     void parse(){
        StringBuffer npcStr = new StringBuffer();

         try {
             BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(new FileInputStream(npcpos_file), charset));
             String str;
             List<String> territory = new ArrayList<>();
                while ((str = bufferedReader.readLine()) !=null  ) {
                    if (str.startsWith("territory_begin")) {
                        String[] arrTerritory_begin = str.split("\t");

                        CreateXML.createXMLFile(arrTerritory_begin[1]);

                        String coordTerritory = arrTerritory_begin[2];

                        String[] arrTerritory = coordTerritory.replaceAll("[{}]", "").split(";"); //координаты Anywhere

                    } else if (str.startsWith("npcmaker_begin")) {
                        String[] arrNpcmaker_begin = str.split("\t");
                    } else if (str.startsWith("npc_begin")) {
                        String[] arrNpc_begin = str.split("\t");

                    }
                    if (str.startsWith("npcmaker_begin")){break;}
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
