package XmlEngine;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 03.05.2025
 **/

public class EndOfLine {
    Path directoryPath = Paths.get("Npc_Pos_Parser/XML_Out");
    List<String> directoryFiles = new ArrayList<>();
    String endOfFile = "</list>";

    public void writeEndOfLine(){
        getDirectoryFiles(directoryPath);
        writeToXmlFile(endOfFile);
    }

    private void writeToXmlFile(String endOfFile){
        String adressToFile;
        for(String xmlFileName : directoryFiles){
            adressToFile = directoryPath + "/" + xmlFileName;
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(adressToFile), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                if (adressToFile.endsWith(".xml")){
                bufferedWriter.write(endOfFile);
                }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
    }

    private void getDirectoryFiles(Path directoryPath){
        directoryFiles.clear();
        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)){
            try {
                Stream<Path> checkFilesStream = Files.list(directoryPath);
                checkFilesStream.forEach(path -> {
                    if (Files.isRegularFile(path)){
                        directoryFiles.add(path.getFileName().toString());
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
