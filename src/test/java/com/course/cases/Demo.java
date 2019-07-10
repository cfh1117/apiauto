package com.course.cases;

public class Demo {
    public static void main(String[] args) throws Exception {
//        ExcelUtil.setExcelFile(Constants.File_Path + Constants.File_Name, "新增客户");
//        System.out.println(ExcelUtil.getCellData(1,1));
//        String File_Path = System.getProperty("user.dir") +"/src/main/resources/";
//        String File_Name = "apidata.xlsx";
//        System.out.println(File_Path+File_Name);
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        for (int i = 1; i <100 ; i++) {
//            formparams.add(new BasicNameValuePair(ExcelUtil.getCellData(i,0), RandomUtil.getChineseName()));
//            System.out.println(formparams);
//
////             System.out.println(ExcelUtil.getCellData(i,0));
//            if (ExcelUtil.getCellData(i,0).equalsIgnoreCase("")||ExcelUtil.getCellData(i,0)==null||ExcelUtil.getCellData(i,0)==""){
//
//            	break;
//            }
//        String file=Constants.File_Path+Constants.File_Name2;
//        System.out.println(file);
        RestfulClient restfulClient=new RestfulClient();
//        restfulClient.yamladd();
        System.out.println(restfulClient.yamladd());
        }


//    }
}
