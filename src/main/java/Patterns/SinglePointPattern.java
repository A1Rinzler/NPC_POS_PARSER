package Patterns;

import XmlEngine.CreateXML;

import java.util.List;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 04.05.2025
 **/

public class SinglePointPattern {
    CreateXML createXML = new CreateXML();

    public void singlePattern(List<String> Npc_Pos, String groupName, int total, int respawnTime, int respawnRandTime,
                                      String periodOfDay, int npc_id, String npc_Name, String territoryName, String eventGroupName) {
        StringBuffer npcStringBuffer = new StringBuffer();
        if (!Npc_Pos.isEmpty()) {
            int npcCoord = 0;
            for (int i = 0; i < Npc_Pos.size(); i+=4) {
                npcStringBuffer.append("\t<spawn");
                if (!groupName.isEmpty()){
                    npcStringBuffer.append(" group=\"").append(groupName).append("\"");
                }
                if (!eventGroupName.isEmpty() && !eventGroupName.equals("event_gatekeeper") && !eventGroupName.equals("class_master") && !eventGroupName.equals("dusk_spawn") && !eventGroupName.equals("dawn_spawn")){
                    npcStringBuffer.append(" group=\"").append(eventGroupName).append("\"");
                }
                npcStringBuffer.append(" count=\"").append(total).append("\" ")
                        .append("respawn=\"").append(respawnTime).append("\" ")
                        .append("respawn_random=\"").append(respawnRandTime).append("\" ")
                        .append("period_of_day=\"").append(periodOfDay).append("\"");

                if ((eventGroupName.equals("event_gatekeeper") || eventGroupName.equals("class_master") || eventGroupName.equals("dusk_spawn")  || eventGroupName.equals("dawn_spawn"))){
                    npcStringBuffer.append(" group=\"").append(eventGroupName).append("\"");
                }

                npcStringBuffer.append(">\n")
                        .append("\t<!--").append(territoryName).append("-->")
                        .append("\n")
                        .append("\t\t<point x=\"").append(Npc_Pos.get(npcCoord++)).append("\" y=\"").append(Npc_Pos.get(npcCoord++)).append("\" ")
                        .append("z=\"").append(Npc_Pos.get(npcCoord++)).append("\" h=\"").append(Npc_Pos.get(npcCoord++)).append("\" />\n")
                        .append("\t\t<npc id=\"").append(npc_id).append("\" /><!--").append(npc_Name).append("-->").append("\n")
                        .append("\t</spawn>\n");

                createXML.createXMLFile(territoryName, npcStringBuffer);
            }
        }
    }
}
