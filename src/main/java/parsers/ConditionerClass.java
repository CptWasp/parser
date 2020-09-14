package parsers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ConditionerClass {

    // one instance, reuse
    private final OkHttpClient httpClient = new OkHttpClient();



    public void sendGet() throws Exception {

        Request request = new Request.Builder()
                .url("https://www.stostayer.ru/price/brands")
                .build();


        Response response = httpClient.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }


//        System.out.println(response.body().string());


        JSONParser parser = new JSONParser();

        try {
            JSONArray a = (JSONArray) parser.parse(response.body().string());

            for (Object o : a)
            {
                JSONObject person = (JSONObject) o;

                String name = (String) person.get("name");
                Long id = (Long) person.get("id");
                String link = (String) person.get("url");
                System.out.println("Brand -> "+id +" :: "+ name +" :: "+ link);

//                ->
                Request requestB = new Request.Builder()
                        .url("https://www.stostayer.ru/price/brands"+link+"/series")
                        .build();
                Response responseB = httpClient.newCall(requestB).execute();

                if (!responseB.isSuccessful()) {
                    throw new IOException("Unexpected code " + responseB);
                }
                JSONParser parserB = new JSONParser();
                JSONArray b = (JSONArray) parserB.parse(responseB.body().string());
                for (Object ob : b){
                    JSONObject personB = (JSONObject) ob;
                    String model = (String) personB.get("name");
                    Long seriesId = (Long) personB.get("id");
                    String seriesB = (String) personB.get("alias");

                    System.out.println("- model --> "+ model);

//                        --->
                    Request requestC = new Request.Builder()
                            .url("https://www.stostayer.ru/price/brands"+link+"/series/"+seriesB+"/models")
                            .build();
                    Response responseC = httpClient.newCall(requestC).execute();

                    if (!responseC.isSuccessful()) {
                        throw new IOException("Unexpected code " + responseC);
                    }
                    JSONParser parserC = new JSONParser();
                    JSONArray c = (JSONArray) parserC.parse(responseC.body().string());
                    for (Object oc : c) {
                        JSONObject personC = (JSONObject) oc;
                        String series = (String) personC.get("name");
                        Long modelId = (Long) personC.get("id");
                        System.out.println("---- series ---> " + series);

//                                --------->
                        Request requestD = new Request.Builder()
                                .url("https://www.stostayer.ru/price/categories/000000011/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
                                .build();
                        Response responseD = httpClient.newCall(requestD).execute();

                        if (!responseD.isSuccessful()) {
                            throw new IOException("Unexpected code " + responseD);
                        }
                        JSONParser parserD = new JSONParser();
                        JSONArray d = (JSONArray) parserD.parse(responseD.body().string());
                        for (Object od : d) {
                            JSONObject personD = (JSONObject) od;
                            String serviceName = (String) personD.get("name");
                            String serviceSale = (String) personD.get("priceType");
                            Double servicePiece = (Double) personD.get("priceValue");

                            System.out.println("-------------- service()-----------> " + serviceName+" => "+
                                    serviceSale+" "+ servicePiece);
                        }


//                                --------->


                    }

//                        --->

                }


//                ->


            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

