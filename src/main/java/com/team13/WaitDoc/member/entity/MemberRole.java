package com.team13.WaitDoc.member.entity;

public enum MemberRole {
    ROLE_USER, ROLE_ADMIN;


    public String getKey() {
        return name();
    }

}
