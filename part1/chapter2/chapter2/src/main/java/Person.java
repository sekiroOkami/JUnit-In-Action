public class Person {
    private final String name;
    private final int age;
    private final String email;
    private final String address;

    public Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.address = builder.address;
    }

    public static class Builder {
        private final String name;
        private int age;
        private String email;
        private String address;

        public Builder(String name) {
            this.name = name;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder address(String address) {
            this.address =address;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }
}
