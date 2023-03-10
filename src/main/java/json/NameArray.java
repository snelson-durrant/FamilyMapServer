package json;

public class NameArray {

    private String[] data;

    public NameArray() {
        data = new String[160];
    }

    public String getName(int i) {
        return data[i];
    }

}
