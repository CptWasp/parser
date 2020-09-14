package megacalss;

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

public class SuperClass {

    Workbook workbook = new XSSFWorkbook();

    public void techObs() throws IOException {

            // one instance, reuse
            final OkHttpClient httpClient = new OkHttpClient();

//        excel write


                Sheet newSheet = workbook.createSheet("Техническое обслуживание");
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
                        countOfRow++;



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
                                        .url("https://www.stostayer.ru/price/categories/000000001/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                                    Row row = newSheet.createRow(countOfRow);   //

                                    row.createCell(0).setCellValue(name);

                                    row.createCell(1).setCellValue(model);
                                    row.createCell(2).setCellValue(series);

                                    row.createCell(3).setCellValue(serviceName);
                                    row.createCell(4).setCellValue(serviceSale);
                                    row.createCell(5).setCellValue(servicePiece);


                                    name = null;
                                    model = null;
                                    series = null;

                                    countOfRow++;

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

//        excel write
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(".\\connector.xlsx");
                    workbook.write(fileOutputStream);
                    fileOutputStream.close();
                    System.out.println("Файл writed.xlsx создан");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//        end excel write






//      #######################################
//   ##############     GSON    ##################
//      #######################################

//        Gson gson = new Gson();
//
//        BrandList brands = gson.fromJson(response.body().string(), BrandList.class);
//        List<Brands> brandsList = brands.getBrands();
//
//
//        for (Brands brnd : brandsList){
//            System.out.println(brnd.getName());
//        }

//      #######################################
//   ##############     /GSON    ##################
//      #######################################


    }

    public void diagnostic() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Диагностика");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000009/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write






//      #######################################
//   ##############     GSON    ##################
//      #######################################

//        Gson gson = new Gson();
//
//        BrandList brands = gson.fromJson(response.body().string(), BrandList.class);
//        List<Brands> brandsList = brands.getBrands();
//
//
//        for (Brands brnd : brandsList){
//            System.out.println(brnd.getName());
//        }

//      #######################################
//   ##############     /GSON    ##################
//      #######################################

    }

    public void amortization() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("подвеска и амортизаторы");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000002/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void boosts() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Тормозная система");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000008/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void freezer() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Кондиционеры");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000006/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void diezel() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Топливная система");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000015/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void transmission() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Трансмиссия");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000004/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void electicity() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Электронная система");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000010/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void condisioner() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Кондиционирование воздуха");
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
                countOfRow++;



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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void rule() throws IOException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Рулевая система");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000005/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void engine() throws IOException{

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Ремонт двигателя");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000003/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void turbo() throws IOException{

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        Sheet newSheet = workbook.createSheet("Ремонт выхлопной системы");
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
                countOfRow++;



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
                                .url("https://www.stostayer.ru/price/categories/000000007/services?brandId="+id+"&seriesId="+seriesId+"&modelId="+modelId)
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

                            Row row = newSheet.createRow(countOfRow);   //

                            row.createCell(0).setCellValue(name);

                            row.createCell(1).setCellValue(model);
                            row.createCell(2).setCellValue(series);

                            row.createCell(3).setCellValue(serviceName);
                            row.createCell(4).setCellValue(serviceSale);
                            row.createCell(5).setCellValue(servicePiece);


                            name = null;
                            model = null;
                            series = null;

                            countOfRow++;

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

//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\connector.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }



}
