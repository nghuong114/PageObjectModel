package DataProviderFactory;

import helpers.ExcelHelper;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class DataProviderFactory {
    ExcelHelper excelHelper = new ExcelHelper();

    @DataProvider
    public Object[][] DataLoginInvalid() {
        Object[][] data = excelHelper.getExcelData("D:\\PageObjectModel\\data.xlsx", "Login");
        System.out.println("Get data from excel file success");
        return data;
    }

    @DataProvider
    public Object[][] DataLoginEmpty() {
        Object[][] data = excelHelper.getExcelData("D:\\PageObjectModel\\data.xlsx", "Login_empty");
        System.out.println("Get data from excel file success");
        return data;
    }

    @DataProvider
    public Object[][] DataAddNewCategory() {
        Object[][] data = excelHelper.getExcelData("D:\\PageObjectModel\\data.xlsx", "AddNewCategory");
        System.out.println("Get data from excel file success");
        return data;
    }

    @DataProvider
    public Object[][] DataAddNewProduct() {
        ArrayList<String> listTags = new ArrayList<>(Arrays.asList("Bánh táo", "Bánh nhập khẩu", "Úc"));
        ArrayList<String> listColors = new ArrayList<>(Arrays.asList("Yellow", "Blue", "Red"));
        ArrayList<String> listSize = new ArrayList<>(Arrays.asList("22", "23"));
        return new Object[][]{
                {"Bánh táo úc nhập khẩu", "Giỏ bánh kẹo", "san pham", "Hộp", "10", "100", listTags, listColors, listSize, "89000000", "10", "100"}
        };
    }


}
