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
    BENOM("rune_castle_benom"),//rune_castle_benom dbname=[benom]

    //Seven Signs
    COMPETITIONSSQ("competition"),  //ssq_event
    COMPETITIONSSQDUCK("dusk_spawn"), //ssq_seal1_twilight
    COMPETITIONSSQDAWN("dawn_spawn"), //ssq_seal1_dawn
    COMPETITIONSSQNOWINNER("no_winner"), //ssq_seal1_none
    COMPETITIONSSQNULL(""); //у оверов только 3 стадии, рассвет, закат и без группы. Без группы это период соревнования и без победителя.

//элитные захватываемые клан холлы
//    Fortress of Resistance            нету, обработать вручную                            // dion23_레지스탕스의요새_아지트전_2121;
//    Bandit Stronghold                 dbname=[tbb1] dbname=[tbf1]                         // oren15_산적단산채_아지트전_
//    Devastated Castle                 dbname=[devastated_001]                             // aden05_파괴된성채_아지트전_2517;
//    Wild Beast Reserve                dbname=[farmazit01]	                                // rune07_야수농장_아지트전_2115;
//    Fortress of the Dead npc_begin	dbname=[RestlessAzit_086]                           // [rune11][망자의요새][아지트전]
//    Rainbow Springs Chateau           dbname=[rainbow_azit_001] dbname=[farmazitfinal01]  // [godard06][레인보우스프링][아지트전] 2414


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
        } else if (dbName.contains("schuttgart_castle")) {
            groupName = SCHUTTGART.getGroupName();}
        //элитные захватываемые клан холлы
        else if (dbName.contains("devastated")) {
            groupName = DEVASTATED.getGroupName();}


        //ивент новый год
        else if (dbName.contains("christmas")) {
            groupName = CHRISTMAS.getGroupName();}
        //рб руны Benom
        else if (dbName.contains("benom")) {
            groupName = BENOM.getGroupName();}
        //семь печатей
        else if (dbName.equals("ssq_event")){
            //groupName = COMPETITIONSSQ.getGroupName();
            groupName = COMPETITIONSSQNULL.getGroupName();}
        else if (dbName.equals("none")){
            // = COMPETITIONSSQNOWINNER.getGroupName();
            groupName = COMPETITIONSSQNULL.getGroupName();}
        else if (dbName.contains("dawn")){
            groupName = COMPETITIONSSQDAWN.getGroupName();}
        else if (dbName.contains("twilight")){
            groupName = COMPETITIONSSQDUCK.getGroupName();}

        return groupName;
    }
}
