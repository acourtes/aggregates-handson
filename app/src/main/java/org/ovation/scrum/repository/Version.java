package org.ovation.scrum.repository;

public class Version {
    private Long version;

    public Version() {
        version = 1L;
    }

    public Long version() {
        return version;
    }

    public void updateVersion() {
        version = version + 1;
    }
}
