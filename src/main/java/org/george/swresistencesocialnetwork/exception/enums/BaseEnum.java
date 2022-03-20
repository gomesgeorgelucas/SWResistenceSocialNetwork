package org.george.swresistencesocialnetwork.exception.enums;

public enum BaseEnum {
    ATOLLON ("Atollon – Chopper Base located on the Atollon Coral Mesa"),
    HAVOC_OUTOPOST ("Havoc Outpost"),
    PAUCRIS_MAJOR ("Paucris Major – Paucris Major Shipyards"),
    LOCATION_5251977 ("5251977"),
    CASSIDODE_VI ("Cassidode VI – Cassidode VI rebel outpost"),
    CRAIT ("Crait – Crait outpost"),
    DANTOOINE ("Dantooine – Dantooine base"),
    D_QAR ("D'Qar – Resistance base"),
    DURKTEEL ("Durkteel – Tak-Beam complex"),
    HOPE_STATION ("Hope Station"),
    HOROX_III ("Horox III – Horox III rebel outpost"),
    HOTH ("Hoth – Echo Base, Echo Station 3-T-8, Outpost Beta and Perimeter Outpost Delta"),
    MAKO_TA ("Mako-Ta Space Docks"),
    PRIMTARA ("Primtara – Primtara outpost"),
    REAMMA ("Reamma – LX-Robynsun V"),
    TALREZAN_FOUR ("Talrezan Four"),
    TIERFON_LAUNCH_BASE ("Tierfon Launch Base"),
    UNROOLA_DAWN ("Unroola Dawn"),
    VROGAS_VAS ("Vrogas Vas – Vrogas Vas refueling base"),
    YAVIN_4 ("Yavin 4 – Great Temple");

    String description;

    BaseEnum (String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
