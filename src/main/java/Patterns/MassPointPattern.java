package Patterns;

import XmlEngine.CreateXML;

import java.util.List;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 06.05.2025
 **/

public class MassPointPattern {
    CreateXML createXML = new CreateXML();

    public void massPattern(List<String> arrTerritoryList, int total, int respawnTime, int respawnRandTime, String periodOfDay, int npc_Id, String npc_Name, String territoryName){
        StringBuffer npcStringBuffer = new StringBuffer();
        if (!arrTerritoryList.isEmpty()) {
            int npcCoord = 0;
            npcStringBuffer.append("\t<spawn count=\"").append(total).append("\" ")
                    .append("respawn=\"").append(respawnTime).append("\" ")
                    .append("respawn_random=\"").append(respawnRandTime).append("\" ");

            if (!periodOfDay.isEmpty()){
                npcStringBuffer.append("period_of_day=\"").append(periodOfDay).append("\">").append("\n")
                        .append("\t\t<territory>").append("\n");
            } else npcStringBuffer.append("\n").append("\t\t<territory>").append("\n");

            for (int i = 0; i < arrTerritoryList.size()/4; i++) {
                npcStringBuffer.append("\t\t\t<add")
                        .append(" x=\"").append(arrTerritoryList.get(npcCoord++))
                        .append("\" y=\"").append(arrTerritoryList.get(npcCoord++))
                        .append("\" zmin=\"").append(arrTerritoryList.get(npcCoord++))
                        .append("\" zmax=\"").append(arrTerritoryList.get(npcCoord++)).append("\" />")
                        .append("\n");
            }
            npcStringBuffer.append("\t\t</territory>").append("\n")
                    .append("\t\t<npc id=\"").append(npc_Id).append("\" /><!--").append(npc_Name).append("-->")
                    .append("\n").append("\t</spawn>\n");

            createXML.createXMLFile(territoryName, npcStringBuffer);
        }
    }
}
