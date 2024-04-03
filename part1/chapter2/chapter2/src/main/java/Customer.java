import java.security.cert.CertPathBuilder;
import java.util.Date;

public class Customer {
    private Gender gender;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date becomeCustomer;
    public static class Builder {

        private Gender gender;
        private String lastName;
        private String firstName;

        private String middleName;
        private Date becomeCustomer;
        public Builder(Gender gender, String firstName, String lastName) {
            this.gender = gender;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder withMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder withBecomeCustomer(Date becomeCustomer) {
            this.becomeCustomer = becomeCustomer;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public Customer(Builder builder) {
        this.gender = builder.gender;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.becomeCustomer = builder.becomeCustomer;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Date getBecomeCustomer() {
        return becomeCustomer;
    }
}
