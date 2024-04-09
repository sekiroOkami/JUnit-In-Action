public class SUT {
    private String SystemName;

    public SUT(String message) {
        this.SystemName = message;
    }

    public String getSystemName() {
        return SystemName;
    }

    public boolean isVerified() {
        return false;
    }
}
