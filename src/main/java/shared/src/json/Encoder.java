package json;

import com.google.gson.Gson;

public class Encoder {

    public static String encode(JSONObject toEncode) {

        Gson gson = new Gson();
        String jsonStr = gson.toJson(toEncode);
        return jsonStr;
    }
}
