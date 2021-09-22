package edu.pet.votesystem.model;

public enum Permission {

    READ("read"),
    WRITE("write"),
    VOTE("vote");

    private final String permission;

     Permission(String PERMISSION) {
        this.permission = PERMISSION;
    }

    public String getPermission() {
        return permission;
    }
}
