package ng.com.sokoto.web.domain.enumeration;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
