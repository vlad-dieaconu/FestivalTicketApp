package FTB.model;


import java.util.Objects;

public class Festival {

    private String name;
    private String details;

    public Festival(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public String getFestivalName() {
        return name;
    }

    public void setFestivalName(String name) {
        this.name = name;
    }

    public String getFestivalDetails() {
        return details;
    }

    public void setFestivalDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Festival festival = (Festival) o;
        return Objects.equals(name, festival.name) &&
                Objects.equals(details, festival.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, details);
    }

    @Override
    public String toString() {
        return "Festival{" +
                "name='" + name + '\'' +
                ", details=" + details +
                '}';
    }
}
