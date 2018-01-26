package com.scottehboeh.glc.enums;

/**
 * Created by 1503257 on 29/11/2017.
 * <p>
 * User Status - Enum used to distinguish the ban-status of a User
 */
public enum EnumUserStatus {

    /**
     * Declared Enum Options
     */
    IDLE("Idle"),
    BANNED("Banned");

    /**
     * Declared Enum Variables
     */
    private String englishForm;

    /**
     * Default Enum User Status Constructor
     *
     * @param asEnglish - Given English Term
     */
    EnumUserStatus(String asEnglish) {
        this.englishForm = asEnglish;
    }

    /**
     * Get As English - Get the Enum as normal English Format (Not Full-caps)
     *
     * @return
     */
    public String getAsEnglish() {
        return this.englishForm;
    }

}
