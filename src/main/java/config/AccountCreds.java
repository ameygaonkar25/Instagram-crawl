package config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class AccountCreds {

    private JSONObject jsonObject;
    public AccountCreds() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/credentials.json"));
    }

    public String getUsername(){
        return (String) jsonObject.get("username");
    }

    public String getPassword(){
        return (String) jsonObject.get("password");
    }

}

