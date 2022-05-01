package app.domain.model;

public class Address {

    private String strRoad;
    private String strZipCode;
    private String strLocal;

    public Address(String strRoad, String strZipCode, String strLocal) {
        if ( (strRoad == null) || (strZipCode == null) || (strLocal == null) ||
                (strRoad.isEmpty())|| (strZipCode.isEmpty()) || (strLocal.isEmpty()))
            throw new IllegalArgumentException("Arguments can't be null.");

        this.strRoad = strRoad;
        this.strZipCode = strZipCode;
        this.strLocal = strLocal;
    }

    @Override
    public String toString() {
        return "Address{" +
                "strRoad='" + strRoad + '\'' +
                ", strZipCode='" + strZipCode + '\'' +
                ", strLocal='" + strLocal + '\'' +
                '}';
    }
}
