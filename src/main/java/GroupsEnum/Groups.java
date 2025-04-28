package GroupsEnum;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 11.04.2025
 **/

public enum Groups {
    //Castle Siege
    GLUDIO("gludio_castle_siege"),  //gludio_castle_siege, //dbname=[gludio_siege_001]
    DION("dion_castle_siege"),  //dion_castle_siege, //dbname=[dion_siege_001]
    GIRAN("giran_castle_siege"),    //giran_castle_siege, //dbname=[giran_siege_001]
    OREN("oren_castle_siege"),//    oren_castle_siege, //dbname=[oren_siege_001]
    INNADRIL("innadril_castle_siege"),//    innadril_castle_siege, //dbname=[innadrile_siege_001]
    ADEN("aden_castle_siege"),//    aden_castle_siege, //dbname=[aden_siege_067]
    GODDARD("goddard_castle_siege"),//    goddard_castle_siege, //dbname=[godad_siege_423]
    RUNE("rune_castle_siege"),//    rune_castle_siege, //dbname=[rune_siege_001]
    SCHUTTGART("schuttgart_castle_siege"),//    schuttgart_castle_siege; //dbname=[schuttgart_castle_001] территория и npcmaker без обозначения castle

    //Elite Clan Hall Siege
    DEVASTATED("devastated_castle_guards"), //    devastated_castle_guards,   //dbname=[devastated_334]


    //Christmas group
    CHRISTMAS("christmas"),//npcmaker_ex_begin  ai_parameters={[EventName]=[christmas]}

    //Raid boss event groups
    BENOM("");//rune_castle_benom dbname=[benom]
    //npc_begin	[gustav]	pos={178298;-17624;-2194;32768}	total=1	respawn=2hour	dbname=[devastated_359]	dbsaving={death_time;parameters;pos}	npc_end
    //dawn ssq 	npcmaker_ex_begin	ai_parameters={[EventName]=[ssq_seal1_twilight]}

//    	<spawn group="devastated_castle_boss" count="1" respawn="300" respawn_random="0" period_of_day="none">
//		<point x="178298" y="-17624" z="-2194" h="0" />
//		<npc id="35410" /><!--Gustav-->
//	</spawn>
//	<spawn group="devastated_castle_boss" count="1" respawn="300" respawn_random="0" period_of_day="none">
//		<point x="178306" y="-17535" z="-2195" h="0" />
//		<npc id="35408" /><!--Dietrich-->
//	</spawn>
//	<spawn group="devastated_castle_boss" count="1" respawn="300" respawn_random="0" period_of_day="none">
//		<point x="178304" y="-17712" z="-2194" h="0" />
//		<npc id="35409" /><!--Mikhail-->
//	</spawn>



//элитные захватываемые клан холлы
//    Fortress of Resistance
//    Bandit Stronghold
//    Devastated Castle
//    Wild Beast Reserve
//    Fortress of the Dead
//    Rainbow Springs Chateau


    private String groupName;

    Groups(String groupName) {
        this.groupName = groupName;
    }

    private String getGroupName() {
        return groupName;
    }

    public static String getGroupByLine(String dbName) {
        String groupName="";
        if (dbName.contains("gludio_siege")){
            groupName = GLUDIO.getGroupName();
        } else if (dbName.contains("dion_siege")) {
            groupName = DION.getGroupName();
        } else if (dbName.contains("giran_siege")) {
            groupName = GIRAN.getGroupName();
        } else if (dbName.contains("oren_siege")) {
            groupName = OREN.getGroupName();
        } else if (dbName.contains("innadrile_siege")) {
            groupName = INNADRIL.getGroupName();
        } else if (dbName.contains("aden_siege")) {
            groupName = ADEN.getGroupName();
        } else if (dbName.contains("godad_siege")) {
            groupName = GODDARD.getGroupName();
        } else if (dbName.contains("rune_siege")) {
            groupName = RUNE.getGroupName();
        } else if (dbName.contains("devastated")) {
            groupName = DEVASTATED.getGroupName();
        } else if (dbName.contains("schuttgart_castle")) {
            groupName = SCHUTTGART.getGroupName();
        }
        return groupName;
    }
}
