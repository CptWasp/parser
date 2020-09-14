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

public class Services {
    Workbook workbook = new XSSFWorkbook();


    public void getServices1() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write

        String serviceNameMain = "Технический осмотр";

        Sheet newSheet = workbook.createSheet(serviceNameMain);
//        end excel write


        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000001/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //


            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;


            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices2() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write

        String serviceNameMain = "Подвески";
        Sheet newSheet = workbook.createSheet(serviceNameMain);

//        end excel write


        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000002/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //


            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices3() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write

        String serviceNameMain = "Двигатель";
        Sheet newSheet = workbook.createSheet(serviceNameMain);//        end excel write


        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000003/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //



            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices4() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Трансмиссия";
        Sheet newSheet = workbook.createSheet(serviceNameMain);//        end excel write



        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000004/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //



            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices5() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Рулевая система";
        Sheet newSheet = workbook.createSheet(serviceNameMain);//        end excel write



        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000005/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //



            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices6() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "система охлаждения";
        Sheet newSheet = workbook.createSheet(serviceNameMain);//        end excel write



        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000006/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //



            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices7() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Выхлопная система";
        Sheet newSheet = workbook.createSheet(serviceNameMain);


        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000007/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //



            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices8() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Тормоза";
        Sheet newSheet = workbook.createSheet(serviceNameMain);



        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000008/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //



            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices9() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Диагностика";
        Sheet newSheet = workbook.createSheet(serviceNameMain);



        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000009/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //



            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices10() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "электорника";
        Sheet newSheet = workbook.createSheet(serviceNameMain);



        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000010/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //



            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices11() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Кондиционирование";
        Sheet newSheet = workbook.createSheet(serviceNameMain);



        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000011/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //




            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }

    public void getServices15() throws IOException, ParseException {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

//        excel write


        String serviceNameMain = "Топливная система";
        Sheet newSheet = workbook.createSheet(serviceNameMain);



        int countOfRow = 0;
//        000000001 Технический осмотр
//        000000002 Подвески
//        000000003 Двигатель
//        000000004 Трансмиссия
//        000000005 Рулевая Система
//        000000006 системы охлаждения
//        000000007 Выхлопная система
//        000000008 Тормозные системы
//        000000009 Диагностика
//        000000010 электроника
//        000000011 Кондиционирование

//        000000015 Топливная система

        Request requestD = new Request.Builder()
                .url("https://www.stostayer.ru/price/categories/000000015/services?brandId=1&seriesId=4&modelId=240")
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

            System.out.println("-------------- service()-----------> " + serviceName+" => ");

            Row row = newSheet.createRow(countOfRow);   //




            row.createCell(0).setCellValue(serviceNameMain);
            row.createCell(1).setCellValue(serviceName);
            serviceNameMain = null;

            countOfRow++;

        }






//        excel write
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\services.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл services.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        end excel write

    }










}
