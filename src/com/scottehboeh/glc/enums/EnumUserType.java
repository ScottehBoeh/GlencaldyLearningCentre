package com.scottehboeh.glc.enums;

/**
 * Created by 1503257 on 08/12/2017.
 * <p>
 * User Type - Enum used to distinguish the rank of a User
 */
public enum EnumUserType {

    NORMAL("Casual User"),
    FULL("Full User"),
    STAFF("Staff User");

    private String english;

    EnumUserType(String givenEnglish) {
        english = givenEnglish;
    }

    public String getEnglish() {
        return this.english;
    }

}
