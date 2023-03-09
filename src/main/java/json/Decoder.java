package json;

import com.google.gson.Gson;

import java.io.Reader;
import request.*;

public class Decoder {

    public static LoadRequest decodeLoadReq(Reader json){
        LoadRequest loadReq;
        Gson gson = new Gson();
        loadReq = gson.fromJson(json, LoadRequest.class);
        return loadReq;
    }

    public static LoginRequest decodeLoginReq(Reader json){
        LoginRequest loginReq;
        Gson gson = new Gson();
        loginReq = gson.fromJson(json, LoginRequest.class);
        return loginReq;
    }

    public static RegisterRequest decodeRegisterReq(Reader json){
        RegisterRequest regReq;
        Gson gson = new Gson();
        regReq = gson.fromJson(json, RegisterRequest.class);
        return regReq;
    }

}
