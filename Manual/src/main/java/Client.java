import java.util.*;
public class Client {
    private List<Address> listOfAddress = new ArrayList<>();

    public List<Address> getAddresses() {
        return listOfAddress;
    }

    public void addAddress(Address addressA) {
        listOfAddress.add(addressA);
    }
}
