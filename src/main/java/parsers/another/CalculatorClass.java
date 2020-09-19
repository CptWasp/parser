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

public class CalculatorClass {



    public void getServiceByUnit() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


//        end excel write




        String nameOfService = null;

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

            JSONObject objectForFor = (JSONObject) a.get(4);

            System.out.println("Марка: "+(String) objectForFor.get("name"));
            System.out.println("-------------------------------");

            for (Object o : a)
            {
                countOfRow=0;
                Workbook workbook = new XSSFWorkbook();

                countOfRow++;



                JSONObject person = (JSONObject) o;

                String name = (String) person.get("name");
                Long brandId = (Long) person.get("id");
                String link = (String) person.get("url");
                System.out.println("Brand -> "+brandId +" :: "+ name +" :: "+ link);
                Sheet newSheet = workbook.createSheet("Калькулятор");
                nameOfService = name ;




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

//---------#>

                        Request requestE = new Request.Builder()
                                .url("https://www.stostayer.ru/calc/units?brandId="+brandId+"&seriesId="+seriesId+"&modelId="+modelId)
                                .build();
                        Response responseE = httpClient.newCall(requestE).execute();

                        if (!responseC.isSuccessful()) {
                            throw new IOException("Unexpected code " + responseC);
                        }
                        JSONParser parserE = new JSONParser();
                        JSONArray e = (JSONArray) parserE.parse(responseE.body().string());




                        for (Object ec : e) {

                            JSONObject personE = (JSONObject) ec;
                            Long ServiceId = (Long) personE.get("id");
//                            String ServiceName = (String) personE.get("name");
//                            Double ServicePrice = (Double) personE.get("price");
                            System.out.println("---- ##### servive ##### ---> id: "+ ServiceId);



//                                --------->
                            Request requestD = new Request.Builder()
                                    .url("https://www.stostayer.ru/calc/services?unitId="+ServiceId+"&brandId=" + brandId + "&seriesId=" + seriesId + "&modelId=" + modelId)
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
                                Double servicePrice = (Double) personD.get("price");
                                System.out.println("-------------- service()-----------> " + serviceName + " => " +
                                        servicePrice);

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name); // Brand
                            row.createCell(1).setCellValue(model); // Model
                            row.createCell(2).setCellValue(series); // Series

                            row.createCell(3).setCellValue(serviceName); // Название услуги
                            row.createCell(4).setCellValue(servicePrice); // цена услуги за данный модель указанной марки


                            name = null;
                            model = null;
                            series = null;

                                countOfRow++;

                            }


//                                --------->
                        }

//---------#>



                    }

//                        --->

                }


//                ->



//        excel write


                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(".\\Services "+nameOfService+".xlsx");
                    workbook.write(fileOutputStream);
                    fileOutputStream.close();
                    System.out.println("Файл Services\"+name+\".xlsx создан");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nameOfService = null;





            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        end excel write





    }


    public void getServiceByUnitSecondTry() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


//        end excel write




        String nameOfService = null;

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

            JSONObject objectForFor = (JSONObject) a.get(4);


            for (int i = 39; i<=59;i++)
            {
                countOfRow=0;
                Workbook workbook = new XSSFWorkbook();

                countOfRow++;

                JSONObject o = (JSONObject) a.get(i);
                JSONObject person = (JSONObject) o;

                String name = (String) person.get("name");
                Long brandId = (Long) person.get("id");
                String link = (String) person.get("url");
                System.out.println("Brand -> "+brandId +" :: "+ name +" :: "+ link);
                Sheet newSheet = workbook.createSheet("Калькулятор");
                nameOfService = name ;




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

//---------#>

                        Request requestE = new Request.Builder()
                                .url("https://www.stostayer.ru/calc/units?brandId="+brandId+"&seriesId="+seriesId+"&modelId="+modelId)
                                .build();
                        Response responseE = httpClient.newCall(requestE).execute();

                        if (!responseC.isSuccessful()) {
                            throw new IOException("Unexpected code " + responseC);
                        }
                        JSONParser parserE = new JSONParser();
                        JSONArray e = (JSONArray) parserE.parse(responseE.body().string());




                        for (Object ec : e) {

                            JSONObject personE = (JSONObject) ec;
                            Long ServiceId = (Long) personE.get("id");
//                            String ServiceName = (String) personE.get("name");
//                            Double ServicePrice = (Double) personE.get("price");
                            System.out.println("---- ##### servive ##### ---> id: "+ ServiceId);



//                                --------->
                            Request requestD = new Request.Builder()
                                    .url("https://www.stostayer.ru/calc/services?unitId="+ServiceId+"&brandId=" + brandId + "&seriesId=" + seriesId + "&modelId=" + modelId)
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
                                Double servicePrice = (Double) personD.get("price");
                                System.out.println("-------------- service()-----------> " + serviceName + " => " +
                                        servicePrice);

                                Row row = newSheet.createRow(countOfRow);   //

                                row.createCell(0).setCellValue(name); // Brand
                                row.createCell(1).setCellValue(model); // Model
                                row.createCell(2).setCellValue(series); // Series

                                row.createCell(3).setCellValue(serviceName); // Название услуги
                                row.createCell(4).setCellValue(servicePrice); // цена услуги за данный модель указанной марки


                                name = null;
                                model = null;
                                series = null;

                                countOfRow++;

                            }


//                                --------->
                        }

//---------#>



                    }

//                        --->

                }


//                ->



//        excel write


                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(".\\Services "+nameOfService+".xlsx");
                    workbook.write(fileOutputStream);
                    fileOutputStream.close();
                    System.out.println("Файл Services\"+name+\".xlsx создан");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nameOfService = null;





            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        end excel write





    }



}
