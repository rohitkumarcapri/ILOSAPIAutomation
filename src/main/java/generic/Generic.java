package generic;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import payloads.LoginPayload;
import utils.PropertiesReadWrite;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;

public class Generic {

    public static String getToken(String user,String password){
        String tokenvalue =null;
        String endPoint = PropertiesReadWrite.getValue("baseURL") +"/ilosuser/v1/login";
        Map<String, Object> payload = LoginPayload.getLoginPayloadMap(user, password);
        Response response = RestUtils.performPost(endPoint, payload, new HashMap<>());
        Assert.assertEquals(response.getStatusCode(), 200);
        tokenvalue = JsonPath.from(response.asString()).getString("dt.token");
      //  PropertiesReadWrite.setValue("token",tokenvalue);
        return tokenvalue;
    }

    public static void validateResponse(Response response) {
        if (response.getStatusCode() != 200) {
            response.prettyPrint();
            Assert.assertEquals(response.getStatusCode(), 200);
        }
    }


}
