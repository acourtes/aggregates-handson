package org.ovation.scrum.repository;

public class Version {
    private long version;

    public Version() {
        version = 1;
    }

    public long version() {
        return version;
    }

    public void updateVersion() {
        version = version + 1;
    }
}
