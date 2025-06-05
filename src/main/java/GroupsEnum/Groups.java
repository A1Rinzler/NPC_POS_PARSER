package GroupsEnum;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 11.04.2025
 **/

public enum Groups {
    //Castle Siege
    GLUDIO("gludio_castle_siege"),
    DION("dion_castle_siege"),
    GIRAN("giran_castle_siege"),
    OREN("oren_castle_siege"),
    INNADRIL("innadril_castle_siege"),
    ADEN("aden_castle_siege"),
    GODDARD("goddard_castle_siege"),
    RUNE("rune_castle_siege"),
    SCHUTTGART("schuttgart_castle_siege"),

    //Elite Clan Hall Siege
    DEVASTATED("devastated_castle_guards"),
    BANDIT("bandits_stronghold"),
    WILD_BEAST("wild_beast_reserve"),
    DEAD("fortress_of_dead_guards"),
    CHATEAU("rainbow_springs"),
    //Fortress of Resistance нету, обработать вручную dion23_레지스탕스의요새_아지트전_2121


    //Christmas group
    CHRISTMAS("christmas"),//npcmaker_ex_begin  ai_parameters={[EventName]=[christmas]}

    //Raid boss event groups
    BENOM("rune_castle_benom"),//rune_castle_benom dbname=[benom]

    //Seven Signs
    COMPETITION_SSQ("competition"),  //ssq_event
    WINNER_SSQ_DUCK("dusk_spawn"), //ssq_seal1_twilight
    WINNER_SSQ_DAWN("dawn_spawn"), //ssq_seal1_dawn
    SSQ_NO_WINNER("no_winner"), //ssq_seal1_none
    COMPETITION_SSQ_NULL(""),//у оверов только 3 стадии, рассвет, закат и без группы. Без группы это период соревнования и без победителя.
    EVENT_GATEKEEPER("event_gatekeeper");

    private String groupName;

    Groups(String groupName) {
        this.groupName = groupName;
    }

    private String getGroupName() {
        return groupName;
    }

    public static String getGroupByLine(String dbName) {
        String groupName="";
        //осады замков
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
        else if (dbName.contains("tbb") || dbName.contains("tbf")) {
            groupName = BANDIT.getGroupName();}
        else if (dbName.contains("devastated")) {
            groupName = DEVASTATED.getGroupName();}
        else if (dbName.contains("farmazit")) {
            groupName = WILD_BEAST.getGroupName();}
        else if (dbName.contains("RestlessAzit")) {
            groupName = DEAD.getGroupName();}
        else if (dbName.contains("rainbow_azit")) {
            groupName = CHATEAU.getGroupName();}
        //ивент новый год
        else if (dbName.contains("christmas")) {
            groupName = CHRISTMAS.getGroupName();}
        //рб руны Benom
        else if (dbName.contains("benom")) {
            groupName = BENOM.getGroupName();}
        //семь печатей
        else if (dbName.equals("ssq_event")){
            //groupName = COMPETITIONSSQ.getGroupName();
            groupName = COMPETITION_SSQ_NULL.getGroupName();}
        else if (dbName.equals("none")){
            // = SSQNOWINNER.getGroupName();
            groupName = COMPETITION_SSQ_NULL.getGroupName();}
        else if (dbName.contains("dawn")){
            groupName = WINNER_SSQ_DAWN.getGroupName();}
        else if (dbName.contains("twilight")){
            groupName = WINNER_SSQ_DUCK.getGroupName();}

        else if (dbName.contains("event_gate")){
            groupName = EVENT_GATEKEEPER.getGroupName();}

        return groupName;
    }
}
