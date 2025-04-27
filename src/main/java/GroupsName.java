import GroupsEnum.CastleSiegeGroup;

/**
 * @author : Dmitrii Frolov, a.k.a. A1Rinzler
 * @created : 27.04.2025
 **/

public class GroupsName {

    public String getGroupName(String dbName){
        CastleSiegeGroup castleSiegeGroup;
        String groupName="";
        if (dbName.contains("gludio_siege")){
            castleSiegeGroup = CastleSiegeGroup.GLUDIO;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("dion_siege")) {
            castleSiegeGroup = CastleSiegeGroup.DION;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("giran_siege")) {
            castleSiegeGroup = CastleSiegeGroup.GIRAN;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("oren_siege")) {
            castleSiegeGroup = CastleSiegeGroup.OREN;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("innadrile_siege")) {
            castleSiegeGroup = CastleSiegeGroup.INNADRIL;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("aden_siege")) {
            castleSiegeGroup = CastleSiegeGroup.ADEN;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("godad_siege")) {
            castleSiegeGroup = CastleSiegeGroup.GODDARD;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("rune_siege")) {
            castleSiegeGroup = CastleSiegeGroup.RUNE;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("devastated")) {
            castleSiegeGroup = CastleSiegeGroup.DEVASTATED;
            groupName = castleSiegeGroup.getCastleNameGroup();
        } else if (dbName.contains("schuttgart_castle")) {
            castleSiegeGroup = CastleSiegeGroup.SCHUTTGART;
            groupName = castleSiegeGroup.getCastleNameGroup();
        }
        return groupName;
    }
}
