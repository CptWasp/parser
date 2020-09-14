package parsers.another;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cars {
    Workbook workbook = new XSSFWorkbook();

    public void brandsModelsSeries() throws IOException{

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Бренды-модели-серии машин");
//        end excel write



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

            int countOfRow = 0;     //
            int countOfColl = 0;    //

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

                        Row row = newSheet.createRow(countOfRow);   //

                        row.createCell(0).setCellValue(name);

                        row.createCell(1).setCellValue(model);
                        row.createCell(2).setCellValue(series);



                        name = null;
                        model = null;
                        series = null;

                        countOfRow++;


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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\cars.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл cars.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void brandModels() throws IOException{

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Бренды-модели машин");
//        end excel write



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

            int countOfRow = 0;     //
            int countOfColl = 0;    //

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

                    Row row = newSheet.createRow(countOfRow);   //

                    row.createCell(0).setCellValue(name);

                    row.createCell(1).setCellValue(model);



                    name = null;
                    model = null;

                    countOfRow++;


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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\cars.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл cars.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void brands() throws IOException{

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Бренды машин");
//        end excel write



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

            int countOfRow = 0;     //
            int countOfColl = 0;    //

            for (Object o : a)
            {


                JSONObject person = (JSONObject) o;

                String name = (String) person.get("name");
                Long id = (Long) person.get("id");
                String link = (String) person.get("url");
                System.out.println("Brand -> "+id +" :: "+ name +" :: "+ link);

                Row row = newSheet.createRow(countOfRow);   //

                row.createCell(0).setCellValue(name);

                name = null;
                countOfRow++;



            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\cars.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл cars.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }


}
