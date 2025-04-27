package GroupsEnum;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 11.04.2025
 **/

public enum CastleSiegeGroup {
    GLUDIO("gludio_castle_siege"),  //gludio_castle_siege, //dbname=[gludio_siege_001]
    DION("dion_castle_siege"),  //dion_castle_siege, //dbname=[dion_siege_001]
    GIRAN("giran_castle_siege"),    //giran_castle_siege, //dbname=[giran_siege_001]
    OREN("oren_castle_siege"),//    oren_castle_siege, //dbname=[oren_siege_001]
    INNADRIL("innadril_castle_siege"),//    innadril_castle_siege, //dbname=[innadrile_siege_001]
    ADEN("aden_castle_siege"),//    aden_castle_siege, //dbname=[aden_siege_067]
    GODDARD("goddard_castle_siege"),//    goddard_castle_siege, //dbname=[godad_siege_423]
    RUNE("rune_castle_siege"),//    rune_castle_siege, //dbname=[rune_siege_001]
    DEVASTATED("devastated_castle_guards"), //    devastated_castle_guards,   //dbname=[devastated_334]
    SCHUTTGART("schuttgart_castle_siege");//    schuttgart_castle_siege; //dbname=[schuttgart_castle_001] территория и npcmaker без обозначения castle

    private String castleNameGroup;

    CastleSiegeGroup(String castleName) {
        this.castleNameGroup = castleName;
    }

    public String getCastleNameGroup() {
        return castleNameGroup;
    }
}
