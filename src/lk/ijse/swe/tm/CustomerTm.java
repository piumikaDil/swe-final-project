package lk.ijse.swe.tm;

public class CustomerTm {
    private String id;
    private String name;
    private String nic;
    private String tp;
    private String email;
    private String address;

    public CustomerTm() {
    }

    public CustomerTm(String id, String name, String nic, String tp, String email, String address) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.tp = tp;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
