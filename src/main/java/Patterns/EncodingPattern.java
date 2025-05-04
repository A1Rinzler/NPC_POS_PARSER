package Patterns;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 03.05.2025
 **/

public class EncodingPattern {
    public StringBuffer getXmlPattern() {
        StringBuffer encodingPatten = new StringBuffer();
            encodingPatten.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\n")
                    .append("<!DOCTYPE list SYSTEM \"spawn.dtd\">").append("\n")
                    .append("<list>").append("\n");
        return encodingPatten;
    }
}
