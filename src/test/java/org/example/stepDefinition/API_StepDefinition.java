package org.example.stepDefinition;

import io.cucumber.java.en.Given;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class API_StepDefinition {
    private static final Logger LOG = Logger.getLogger(API_StepDefinition.class);
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    @Given("User triggers an API request and verifies response data with below details")
    public void user_triggers_an_api_request_and_verifies_response_data_with_below_details(io.cucumber.datatable.DataTable dataTable) throws IOException {
        //variable declaration
        String resBody = "";
        JSONParser parser = new JSONParser();
        JSONObject obj=null;

        //retriving data from data table
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String endpoint = data.get(0).get("ApiEndpoint");
        String activity = data.get(0).get("activity");
        String type = data.get(0).get("type");
        String participants = data.get(0).get("participants");
        String price = data.get(0).get("price");
        String link = data.get(0).get("link");
        String key = data.get(0).get("key");
        String accessibility = data.get(0).get("accessibility");

        try {
            //triggering get request
            Request request = new Request.Builder()
                    .url(endpoint)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            LOG.info("Triggering API request having endpoint '"+endpoint+"'");

            //specifically handling only status 200 for this request, in future we can add 201, 204 as necessary for functionality/verification
            if (response.code() == 200) {
                LOG.info("Received Success response for triggerd API. ");
                LOG.info("Response code received is: " + response.code());

                resBody = response.body().string();
                LOG.info("Response data received is: "+resBody); //this will be helpful for future debuging/verification purpose

                obj = (JSONObject) parser.parse(resBody);

                //extracting data from 'key" field
                String res_keyField=(String) obj.get("key");
                LOG.info("Data extracted from 'Key' field is: "+res_keyField);

                //verifying response data
                LOG.info("Verifying data from the response code.");
                Assert.assertEquals("Data verification for 'Activity' field.", activity, (String) obj.get("activity"));
                Assert.assertEquals("Data verification for 'type' field.", type, (String) obj.get("type"));
                Assert.assertEquals("Data verification for 'participants' field.", participants, (String) obj.get("participants"));
                Assert.assertEquals("Data verification for 'price' field.", price, (String) obj.get("price"));
                Assert.assertEquals("Data verification for 'link' field.", link, (String) obj.get("link"));
                Assert.assertEquals("Data verification for 'key' field.", key, (String) obj.get("key"));
                Assert.assertEquals("Data verification for 'accessibility' field.", accessibility, (String) obj.get("accessibility"));
            } else {
                LOG.error("Received failed response having response code " + response.code());
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            LOG.error("Error occurred while triggering API request. " + e.toString());
        }
    }
}
