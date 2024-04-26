import java.util.Objects;

public class Passenger {
    private String name;
    private Country country;

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Passenger passenger = (Passenger) obj;
        return name.equals(passenger.name) &&
                Objects.equals(country, passenger.country);
    }
}
