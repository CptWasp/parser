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
import parsers.another.entitys.Categories;
import parsers.another.entitys.RootObject;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Wheels {

    Workbook workbook = new XSSFWorkbook();


//    car   легковой
//    suv   паркетник
//    van   микроавтобус

//    wheelType=steel
//    wheelType=alloy

//    wheelCount=6
//    wheelCount=4



    public void getWheelsCarAlloy() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Легковой литые";
        Sheet newSheet = workbook.createSheet(serviceNameMain);



        int countOfRow = 0;

        for(int wheelSize = 13; wheelSize <= 17; wheelSize++) {
           String serviceMain = String.valueOf(wheelSize);
//      получение
            Request requestD = new Request.Builder()
                    .url("https://www.stostayer.ru/price/tireService?city=spb&vehicleType=car&wheelType=alloy&size="+wheelSize)
                    .build();
            Response responseD = httpClient.newCall(requestD).execute();

            if (!responseD.isSuccessful()) {
                throw new IOException("Unexpected code " + responseD);
            }
//      /получение


            JSONParser parserD = new JSONParser();
//        JSONArray d = (JSONArray) parserD.parse(responseD.body().string());
            JSONObject jsonObject = (JSONObject) parserD.parse(responseD.body().string());

            JSONArray categories = (JSONArray) jsonObject.get("categories");
            Iterator i = categories.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println(innerObj.get("title"));

//                -->

                JSONArray services = (JSONArray) innerObj.get("services");
                Iterator s = services.iterator();
                while (s.hasNext()) {
                    JSONObject sInnerObj = (JSONObject) s.next();
                    String serviceWork = (String) sInnerObj.get("title");
                    Double servicePrice = (Double) sInnerObj.get("price");
                    System.out.println("----- "+wheelSize+" ------> " + serviceWork + " цена: " + servicePrice);

                    Row row = newSheet.createRow(countOfRow);              //
                    row.createCell(0).setCellValue(serviceMain);
                    row.createCell(1).setCellValue(serviceWork);     //
                    row.createCell(2).setCellValue(servicePrice);         //
                    serviceMain = null;                                                       //
                    countOfRow++;

                }
//                -->

            }
        }




//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\wheels.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл wheels.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getWheelsCarSteel() throws IOException, ParseException {
        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Легковой стальные";
        Sheet newSheet = workbook.createSheet(serviceNameMain);


        int countOfRow = 0;

        for(int wheelSize = 13; wheelSize <= 20; wheelSize++) {
            String serviceMain = String.valueOf(wheelSize);

            //      получение
            Request requestD = new Request.Builder()
                    .url("https://www.stostayer.ru/price/tireService?city=spb&vehicleType=car&wheelType=steel&size="+wheelSize)
                    .build();
            Response responseD = httpClient.newCall(requestD).execute();

            if (!responseD.isSuccessful()) {
                throw new IOException("Unexpected code " + responseD);
            }
//      /получение


            JSONParser parserD = new JSONParser();
            //        JSONArray d = (JSONArray) parserD.parse(responseD.body().string());
            JSONObject jsonObject = (JSONObject) parserD.parse(responseD.body().string());

            JSONArray categories = (JSONArray) jsonObject.get("categories");
            Iterator i = categories.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println(innerObj.get("title"));

//                -->

                JSONArray services = (JSONArray) innerObj.get("services");
                Iterator s = services.iterator();
                while (s.hasNext()) {
                    JSONObject sInnerObj = (JSONObject) s.next();
                    String serviceWork = (String) sInnerObj.get("title");
                    Double servicePrice = (Double) sInnerObj.get("price");
                    System.out.println("------>" + serviceWork + " цена: " + servicePrice);

                    Row row = newSheet.createRow(countOfRow);              //
                    row.createCell(0).setCellValue(serviceMain);
                    row.createCell(1).setCellValue(serviceWork);     //
                    row.createCell(2).setCellValue(servicePrice);         //
                    serviceMain = null;         //
                    countOfRow++;

                }
//                -->

            }
        }


        //        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\wheels.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл wheels.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }


    public void getWheelsSuvAlloy() throws IOException, ParseException {
        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Внедорожник литые";
        Sheet newSheet = workbook.createSheet(serviceNameMain);


        int countOfRow = 0;

        for(int wheelSize = 16; wheelSize <= 20; wheelSize++) {
            String serviceMain = String.valueOf(wheelSize);

            //      получение
            Request requestD = new Request.Builder()
                    .url("https://www.stostayer.ru/price/tireService?city=spb&vehicleType=suv&wheelType=alloy&size="+wheelSize)
                    .build();
            Response responseD = httpClient.newCall(requestD).execute();

            if (!responseD.isSuccessful()) {
                throw new IOException("Unexpected code " + responseD);
            }
//      /получение


            JSONParser parserD = new JSONParser();
            //        JSONArray d = (JSONArray) parserD.parse(responseD.body().string());
            JSONObject jsonObject = (JSONObject) parserD.parse(responseD.body().string());

            JSONArray categories = (JSONArray) jsonObject.get("categories");
            Iterator i = categories.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println(innerObj.get("title"));

//                -->

                JSONArray services = (JSONArray) innerObj.get("services");
                Iterator s = services.iterator();
                while (s.hasNext()) {
                    JSONObject sInnerObj = (JSONObject) s.next();
                    String serviceWork = (String) sInnerObj.get("title");
                    Double servicePrice = (Double) sInnerObj.get("price");
                    System.out.println("------>" + serviceWork + " цена: " + servicePrice);

                    Row row = newSheet.createRow(countOfRow);              //
                    row.createCell(0).setCellValue(serviceMain);
                    row.createCell(1).setCellValue(serviceWork);     //
                    row.createCell(2).setCellValue(servicePrice);         //
                    serviceMain = null;                                //
                    countOfRow++;

                }
//                -->

            }
        }


        //        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\wheels.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл wheels.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }

    public void getWheelsSuvSteel() throws IOException, ParseException {
        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Внедорожник сатльные";
        Sheet newSheet = workbook.createSheet(serviceNameMain);


        int countOfRow = 0;

        for(int wheelSize = 16; wheelSize <= 17; wheelSize++) {
            String serviceMain = String.valueOf(wheelSize);


            //      получение
            Request requestD = new Request.Builder()
                    .url("https://www.stostayer.ru/price/tireService?city=spb&vehicleType=suv&wheelType=steel&size="+wheelSize)
                    .build();
            Response responseD = httpClient.newCall(requestD).execute();

            if (!responseD.isSuccessful()) {
                throw new IOException("Unexpected code " + responseD);
            }
//      /получение


            JSONParser parserD = new JSONParser();
            //        JSONArray d = (JSONArray) parserD.parse(responseD.body().string());
            JSONObject jsonObject = (JSONObject) parserD.parse(responseD.body().string());

            JSONArray categories = (JSONArray) jsonObject.get("categories");
            Iterator i = categories.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println(innerObj.get("title"));

//                -->

                JSONArray services = (JSONArray) innerObj.get("services");
                Iterator s = services.iterator();
                while (s.hasNext()) {
                    JSONObject sInnerObj = (JSONObject) s.next();
                    String serviceWork = (String) sInnerObj.get("title");
                    Double servicePrice = (Double) sInnerObj.get("price");
                    System.out.println("------>" + serviceWork + " цена: " + servicePrice);

                    Row row = newSheet.createRow(countOfRow);              //
                    row.createCell(0).setCellValue(serviceMain);
                    row.createCell(1).setCellValue(serviceWork);     //
                    row.createCell(2).setCellValue(servicePrice);         //
                    serviceMain = null;         //
                    countOfRow++;

                }
//                -->

            }
        }


        //        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\wheels.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл wheels.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }


    public void getWheelsVan4() throws IOException, ParseException {
        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Минивэны";
        Sheet newSheet = workbook.createSheet(serviceNameMain);


        int countOfRow = 0;

        for(int wheelSize = 4; wheelSize <= 6; wheelSize+=2) {
            String serviceMain = String.valueOf(wheelSize);


            //      получение
            Request requestD = new Request.Builder()
                    .url("https://www.stostayer.ru/price/tireService?city=spb&vehicleType=van&wheelCount="+wheelSize)
                    .build();
            Response responseD = httpClient.newCall(requestD).execute();

            if (!responseD.isSuccessful()) {
                throw new IOException("Unexpected code " + responseD);
            }
//      /получение


            JSONParser parserD = new JSONParser();
            //        JSONArray d = (JSONArray) parserD.parse(responseD.body().string());
            JSONObject jsonObject = (JSONObject) parserD.parse(responseD.body().string());

            JSONArray categories = (JSONArray) jsonObject.get("categories");
            Iterator i = categories.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println(innerObj.get("title"));

//                -->

                JSONArray services = (JSONArray) innerObj.get("services");
                Iterator s = services.iterator();
                while (s.hasNext()) {
                    JSONObject sInnerObj = (JSONObject) s.next();
                    String serviceWork = (String) sInnerObj.get("title");
                    Double servicePrice = (Double) sInnerObj.get("price");
                    System.out.println("------>" + serviceWork + " цена: " + servicePrice);

                    Row row = newSheet.createRow(countOfRow);              //
                    row.createCell(0).setCellValue(serviceMain);
                    row.createCell(1).setCellValue(serviceWork);     //
                    row.createCell(2).setCellValue(servicePrice);         //
                    serviceMain = null;                                //
                    countOfRow++;

                }
//                -->

            }
        }


        //        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\wheels.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл wheels.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }







    public void getWheelsVan6() throws IOException, ParseException {
        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Минивэн 6";
        Sheet newSheet = workbook.createSheet(serviceNameMain);


        int countOfRow = 0;
        //      получение
        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/tireService?city=spb&vehicleType=van&wheelCount=6")
                .build();
        Response responseD = httpClient.newCall(requestD).execute();

        if (!responseD.isSuccessful()) {
            throw new IOException("Unexpected code " + responseD);
        }
//      /получение


        JSONParser parserD = new JSONParser();
        //        JSONArray d = (JSONArray) parserD.parse(responseD.body().string());
        JSONObject jsonObject = (JSONObject) parserD.parse(responseD.body().string());

        JSONArray categories = (JSONArray) jsonObject.get("categories");
        Iterator i = categories.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            System.out.println(innerObj.get("title"));

//                -->

            JSONArray services = (JSONArray) innerObj.get("services");
            Iterator s = services.iterator();
            while (s.hasNext()) {
                JSONObject sInnerObj = (JSONObject) s.next();
                String serviceWork = (String) sInnerObj.get("title");
                Double servicePrice = (Double) sInnerObj.get("price");
                System.out.println("------>" + serviceWork + " цена: " + servicePrice);

                Row row = newSheet.createRow(countOfRow);              //
                row.createCell(0).setCellValue(serviceNameMain);
                row.createCell(1).setCellValue(serviceWork);     //
                row.createCell(2).setCellValue(servicePrice);         //
                serviceNameMain = null;                                //
                countOfRow++;

            }
//                -->

        }


        //        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\wheels.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл wheels.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write
    }


}
