package com.example.crmdemo.common.export;

import com.google.common.base.Strings;
import lombok.Data;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 导出Excel
 */

@Data
public class ExportExcelUtils {

  //表头
  private String title;
  //各个列的表头
  private String[] heardList;
  //各个列的元素key值
  private String[] heardKey;
  //需要填充的数据信息
  private List<Map> data;
  //字体大小
  private int fontSize = 14;
  //行高
  private int rowHeight = 30;
  //列宽
  private int columWidth = 200;
  //工作表
  private String sheetName = "sheet1";


/**
 * 导出
 * @return
 */
  public byte[] exportExport() throws IOException {
      ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletResponse response = servletRequestAttributes.getResponse();
   //检查参数配置信息
   checkConfig();
   //创建工作簿
   HSSFWorkbook wb = new HSSFWorkbook();
   //创建工作表
   HSSFSheet wbSheet = wb.createSheet(this.sheetName);
   //设置默认行宽
   wbSheet.setDefaultColumnWidth(20);
   // 标题样式（加粗，垂直居中）
   HSSFCellStyle cellStyle = wb.createCellStyle();
   cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
//   cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
   HSSFFont fontStyle = wb.createFont();
   fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//   fontStyle.setBold(true);   //加粗
   fontStyle.setFontHeightInPoints((short)16);  //设置标题字体大小
   cellStyle.setFont(fontStyle);

   //在第0行创建rows  (表标题)
   HSSFRow title = wbSheet.createRow((int) 0);
   title.setHeightInPoints(30);//行高
   HSSFCell cellValue = title.createCell(0);
   cellValue.setCellValue(this.title);
   cellValue.setCellStyle(cellStyle);
   wbSheet.addMergedRegion(new CellRangeAddress(0,0,0,(this.heardList.length-1)));
   //设置表头样式，表头居中
   HSSFCellStyle style = wb.createCellStyle();
   //设置单元格样式
   style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
   style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
   //设置字体
   HSSFFont font = wb.createFont();
   font.setFontHeightInPoints((short) this.fontSize);
   style.setFont(font);
   //在第1行创建rows
   HSSFRow row = wbSheet.createRow((int) 1);
   //设置列头元素
   HSSFCell cellHead = null;
   for (int i = 0; i < heardList.length; i++) {
        cellHead = row.createCell(i);
        cellHead.setCellValue(heardList[i]);
        cellHead.setCellStyle(style);
   }

   //设置每格数据的样式 （字体红色）
   HSSFCellStyle cellParamStyle = wb.createCellStyle();
   HSSFFont ParamFontStyle = wb.createFont();
   cellParamStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
   cellParamStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
   ParamFontStyle.setColor(HSSFColor.DARK_RED.index);   //设置字体颜色 (红色)
   ParamFontStyle.setFontHeightInPoints((short) this.fontSize);
   cellParamStyle.setFont(ParamFontStyle);
   //设置每格数据的样式2（字体蓝色）
   HSSFCellStyle cellParamStyle2 = wb.createCellStyle();
   cellParamStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
   cellParamStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
   HSSFFont ParamFontStyle2 = wb.createFont();
   ParamFontStyle2.setColor(HSSFColor.BLUE.index);   //设置字体颜色 (蓝色)
   ParamFontStyle2.setFontHeightInPoints((short) this.fontSize);
   cellParamStyle2.setFont(ParamFontStyle2);

   //开始写入实体数据信息
   int a = 2;
   for (int i = 0; i < data.size(); i++) {
    HSSFRow roww = wbSheet.createRow((int) a);
    Map map = data.get(i);
    HSSFCell cell = null;
    for (int j = 0; j < heardKey.length; j++) {
      cell = roww.createCell(j);
      cell.setCellStyle(style);
      Object valueObject = map.get(heardKey[j]);
      String value = null;
     if (valueObject == null) {
       valueObject = "";
      }
     if (valueObject instanceof String) {
      //取出的数据是字符串直接赋值
      value = (String) map.get(heardKey[j]);
     } else if (valueObject instanceof Integer) {
      //取出的数据是Integer
      value = String.valueOf(((Integer) (valueObject)).floatValue());
     }else if (valueObject instanceof BigDecimal){
      //取出的数据是BigDecimal
      value = String.valueOf(((BigDecimal) (valueObject)).floatValue());
     } else {
      value = valueObject.toString();
     }

     //设置单个单元格的字体颜色
     if(heardKey[j].equals("ddNum") || heardKey[j].equals("sjNum")){
      if((Long)map.get("ddNum")!=null){
       if((Long)map.get("sjNum")==null){
        cell.setCellStyle(cellParamStyle);
       }else if((Long) map.get("ddNum") != (Long) map.get("sjNum")){
        if ((Long) map.get("ddNum") > (Long) map.get("sjNum")) {
         cell.setCellStyle(cellParamStyle);
        }
        if ((Long) map.get("ddNum") < (Long) map.get("sjNum")) {
         cell.setCellStyle(cellParamStyle2);
        }
       }else {
        cell.setCellStyle(style);
       }
      }
     }
     cell.setCellValue(Strings.isNullOrEmpty(value) ? "" : value);
    }
    a++;
   }
   //设置Http响应头告诉浏览器下载这个附件
   try {
    response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".xls");
    OutputStream outputStream = response.getOutputStream();
    wb.write(outputStream);
    outputStream.close();
    return wb.getBytes();
   } catch (IOException e) {
    e.printStackTrace();
    throw new IOException("导出Excel出现严重异常，异常信息：" + e.getMessage());
   }
  }

 protected void checkConfig() throws IOException {
   if (heardKey == null || heardList.length == 0) {
    throw new IOException("列名数组不能为空或者为NULL");
   }
   if (fontSize < 0 || rowHeight < 0 || columWidth < 0) {
    throw new IOException("字体、宽度或者高度不能为负值");
   }
   if (Strings.isNullOrEmpty(sheetName)) {
    throw new IOException("工作表表名不能为NULL");
   }
 }
}
