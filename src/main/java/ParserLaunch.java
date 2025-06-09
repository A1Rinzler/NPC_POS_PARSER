import DataParse.ParserNPC;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 05.04.2025
 **/

public class ParserLaunch {

    public static void main(String[] args) {
       //long start = System.currentTimeMillis();
       ParserNPC parserNPC = new ParserNPC();
       parserNPC.parse();
       //long stop = System.currentTimeMillis();
       //System.out.println(stop - start);

    }
}
