import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class StockuiFilter {
    @BeforeTest
    public void setUp() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void Stockuifilter(){
        String baseURL = "https://stock-backend-urtjok3rza-wl.a.run.app";
        String endpoint = "/api/auth/signin";
        File InputDATA = new File("src/test/Response/LoginCredentials.json");
        System.out.println("calling");
        Response res = given()
                .header("Content-Type", "application/json")
                .body(InputDATA)
                .when()
                .post(baseURL + endpoint).then().extract().response();
        JsonPath js = new JsonPath(res.asString());
        System.out.println("js done");
        String token = js.getString("accessToken"); // returns the access tken
        String id = js.getString("id"); // returns the id
        System.out.println("string done");
        System.out.println(id);

        endpoint="/filterStocks";
        InputDATA=new File("src/test/Response/Body.json");
        res=given()
                .header("Content-Type", "application/json")
                .body(InputDATA)
                .when()
                .post(baseURL + endpoint).then().extract().response();
         js = new JsonPath(res.asString());
         Assert.assertEquals(js!=null,true);

    }
}
