package json;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import model.LocationArray;
import model.NameArray;
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

    public static NameArray decodeNameArray (String fileName) {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader(fileName);
            return gson.fromJson(fileReader, NameArray.class);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static LocationArray decodeLocationArray (String fileName) {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader(fileName);
            return gson.fromJson(fileReader, LocationArray.class);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

}
