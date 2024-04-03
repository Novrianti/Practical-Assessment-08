import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Test Recipes")
public class RecipesTest extends BaseTest{
    String apiKey = "1e9c1a771cba43478e8cc19f6db694e2";
    //String hash = "222fc5f64853f6fe93fff321d4df64733904705a";
    //String username = "novri1";

    @Test(description = "Search Recipes")
    public void SearchRecipes(){
        given()
                .queryParam("apiKey",apiKey)
                .log().ifValidationFails()
                .when()
                .get("complexSearch")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }

    @Test(description = "Get Recipe Information")
    public void GetRecipeInformation(){
        given()
                .queryParam("apiKey",apiKey)
                .log().ifValidationFails()
                .when()
                .get("782585/information")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }

    @Test(description = "Analyze Recipe")
    public  void AnalyzeRecipe() {
        String requestBody =
                        " {\n" +
                        "        \"title\": \"Spaghetti Carbonara\",\n" +
                        "        \"servings\": 2,\n" +
                        "        \"type\": \"Spaghetti Carbonara\",\n" +
                        "        \"ingredients\": \n" +
                        "             [\n" +
                        "                    \"1 1b spaghetti\",\n" +
                        "                    \"3.5 oz pancetta\",\n" +
                        "                    \"2 Tbsps olive oil\": \"1\",\n" +
                        "                    \"1 egg\",\n" +
                        "                    \"0.5 cup parmesan cheese\"\n" +
                        "            ],\n" +
                        "        \"instructions\": \"Bring a large pot of water to a boil and season generously with salt. Add the pasta to the water once boiling and cook until al dente. Reserve 2 cups of cooking water and drain the pasta.\"\n" +
                        "    }";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey",apiKey)
                .body(requestBody)
                .log().ifValidationFails()
                .when()
                .post("analyze")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
                //.body("status", equalTo("success"));

    }

    @Test(description = "Classify Cuisine")
    public  void ClassifyCuisine(){
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParams("apiKey",apiKey)
                .log().ifValidationFails()
                .when()
                .post("cuisine")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
                //.body("status",equalTo("success"));

    }

    @Test(description = "Summarize Recipe")
    public void SummarizeRecipe(){
        given()
                .queryParams("apiKey",apiKey)
                .log().ifValidationFails()
                .when()
                .get("4632/summary")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("id",equalTo(4632));
    }

}
