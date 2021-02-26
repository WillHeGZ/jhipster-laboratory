package xyz.ttooc.laboratory.service;

import liquibase.pro.packaged.J;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseBoxPen;
import net.sf.jasperreports.engine.base.JRBoxPen;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.fill.JREvaluator;
import net.sf.jasperreports.engine.type.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Service
public class JasperReportService {
    private final EntityManager entityManager;
    private final DataSource dataSource;

    public JasperReportService(EntityManager entityManager, DataSource dataSource) {
        this.entityManager = entityManager;
        this.dataSource = dataSource;
    }

    public void compileReport(HttpServletResponse response) throws JRException, SQLException, IOException {
        response.setContentType("text/html");
        String sql = "SELECT order_id,store_name ,total_amt ,status_text from order_order oo where device = '114ecabae5eaa17b'";

        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("NoXmlDesignReport");
        jasperDesign.setPageWidth(595);
        jasperDesign.setPageHeight(842);
        jasperDesign.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
        jasperDesign.setColumnWidth(555);
        jasperDesign.setLeftMargin(20);
        jasperDesign.setRightMargin(20);
        jasperDesign.setTopMargin(20);
        jasperDesign.setBottomMargin(20);

        JRDesignStyle tableTHStyle = new JRDesignStyle();
        tableTHStyle.setName("Table_TH");
        tableTHStyle.setMode(ModeEnum.OPAQUE);
        tableTHStyle.setBackcolor(Color.decode("#F0F8FF"));
        tableTHStyle.getLinePen().setLineColor(Color.decode("#000000"));
        tableTHStyle.getLinePen().setLineWidth(0.5f);
        JRBoxPen tableTHBoxPen = new JRBaseBoxPen(tableTHStyle.getLineBox());
        tableTHBoxPen.setLineColor(Color.decode("#000000"));
        tableTHBoxPen.setLineWidth(0.5f);
        tableTHStyle.getLineBox().copyTopPen(tableTHBoxPen);
        tableTHStyle.getLineBox().copyLeftPen(tableTHBoxPen);
        tableTHStyle.getLineBox().copyBottomPen(tableTHBoxPen);
        tableTHStyle.getLineBox().copyRightPen(tableTHBoxPen);
        jasperDesign.addStyle(tableTHStyle);

        JRDesignStyle tableCHStyle = new JRDesignStyle();
        tableCHStyle.setName("Table_CH");
        tableCHStyle.setMode(ModeEnum.OPAQUE);
        tableCHStyle.setBackcolor(Color.decode("#BFE1FF"));
        tableCHStyle.getLinePen().setLineColor(Color.decode("#000000"));
        tableCHStyle.getLinePen().setLineWidth(0.5f);
        JRBoxPen tableCHBoxPen = new JRBaseBoxPen(tableCHStyle.getLineBox());
        tableCHBoxPen.setLineColor(Color.decode("#000000"));
        tableCHBoxPen.setLineWidth(0.5f);
        tableCHStyle.getLineBox().copyTopPen(tableCHBoxPen);
        tableCHStyle.getLineBox().copyLeftPen(tableCHBoxPen);
        tableCHStyle.getLineBox().copyBottomPen(tableCHBoxPen);
        tableCHStyle.getLineBox().copyRightPen(tableCHBoxPen);
        jasperDesign.addStyle(tableCHStyle);

        JRDesignStyle tableTDStyle = new JRDesignStyle();
        tableTDStyle.setName("Table_TD");
        tableTDStyle.setMode(ModeEnum.OPAQUE);
        tableTDStyle.setBackcolor(Color.decode("#FFFFFF"));
        tableTDStyle.getLinePen().setLineColor(Color.decode("#000000"));
        tableTDStyle.getLinePen().setLineWidth(0.5f);
        JRBoxPen tableTDBoxPen = new JRBaseBoxPen(tableTDStyle.getLineBox());
        tableTDBoxPen.setLineColor(Color.decode("#000000"));
        tableTDBoxPen.setLineWidth(0.5f);
        tableTDStyle.getLineBox().copyTopPen(tableTDBoxPen);
        tableTDStyle.getLineBox().copyLeftPen(tableTDBoxPen);
        tableTDStyle.getLineBox().copyBottomPen(tableTDBoxPen);
        tableTDStyle.getLineBox().copyRightPen(tableTDBoxPen);
        jasperDesign.addStyle(tableTDStyle);

        JRDesignParameter parameter = new JRDesignParameter();
        parameter.setName("ReportTitle");
        parameter.setValueClass(java.lang.String.class);
        jasperDesign.addParameter(parameter);

        JRDesignField field = new JRDesignField();
        field.setName("order_id");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("store_name");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("total_amt");
        field.setValueClass(BigDecimal.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("status_text");
        field.setValueClass(String.class);
        jasperDesign.addField(field);

        JRDesignBand band = null;
        JRDesignTextField textField = null;
        JRDesignExpression expression = null;

        // title
        band = new JRDesignBand();
        band.setHeight(79);
        band.setSplitType(SplitTypeEnum.STRETCH);
        textField = new JRDesignTextField();
        textField.setBlankWhenNull(true);
        textField.setX(-20);
        textField.setY(0);
        textField.setWidth(590);
        textField.setHeight(66);
        textField.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        textField.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        textField.setFontSize(26f);
        expression = new JRDesignExpression();
        expression.setText("$P{ReportTitle}");
        textField.setExpression(expression);
        band.addElement(textField);
        jasperDesign.setTitle(band);

        //Page Header
        jasperDesign.setPageHeader(null);
        //Column Header
        jasperDesign.setColumnHeader(null);
        //Column Footer
        jasperDesign.setColumnFooter(null);
        //Page Footer
        jasperDesign.setPageFooter(null);
        //Summary
        jasperDesign.setSummary(null);


        // Detail
        JRDesignComponentElement componentElement = new JRDesignComponentElement();
        StandardTable table = new StandardTable();
        StandardColumn column = null;
        DesignCell cell = null;
        JRBoxPen cellBoxPen = null;
        JRDesignStaticText staticText = null;


        JRDesignDataset subDataset = new JRDesignDataset(true);
        JRDesignQuery query = new JRDesignQuery();
        query.setLanguage("SQL");
        query.setText(sql);
        subDataset.setQuery(query);

        subDataset.setName("subDataSet");
        field = new JRDesignField();
        field.setName("order_id");
        field.setValueClass(java.lang.String.class);
        subDataset.addField(field);
        field = new JRDesignField();
        field.setName("store_name");
        field.setValueClass(java.lang.String.class);
        subDataset.addField(field);

        field = new JRDesignField();
        field.setName("total_amt");
        field.setValueClass(BigDecimal.class);
        subDataset.addField(field);

        field = new JRDesignField();
        field.setName("status_text");
        field.setValueClass(String.class);
        subDataset.addField(field);
        jasperDesign.addDataset(subDataset);

        // datasource
        JRDesignDatasetParameter param = new JRDesignDatasetParameter();
        param.setName("REPORT_DATA_SOURCE");
        JRDesignExpression exp = new JRDesignExpression("$P{parameter}");
        param.setExpression(exp);
        // datasetrun
        JRDesignDatasetRun dsr = new JRDesignDatasetRun();
        dsr.setDatasetName(subDataset.getName());
        JRDesignExpression jrDesignExpression = new JRDesignExpression();
        jrDesignExpression.setText("$P{REPORT_CONNECTION}");
        dsr.setConnectionExpression(jrDesignExpression);
//        dsr.addParameter(param);
        table.setDatasetRun(dsr);

        //order_id
        column = new StandardColumn();
        column.setWidth(140);
        cell = new DesignCell();
        cell.setStyle(tableCHStyle);
        cell.setHeight(30);
        cell.setRowSpan(1);
        cellBoxPen = new JRBaseBoxPen(cell.getLineBox());
        cellBoxPen.setLineWidth(1.0f);
        cellBoxPen.setLineStyle(LineStyleEnum.SOLID);
        cellBoxPen.setLineColor(Color.decode("#000000"));
        cell.getLineBox().copyTopPen(cellBoxPen);
        cell.getLineBox().copyLeftPen(cellBoxPen);
        cell.getLineBox().copyBottomPen(cellBoxPen);
        cell.getLineBox().copyRightPen(cellBoxPen);
        staticText = new JRDesignStaticText();
        staticText.setText("订单号");
        cell.addElement(staticText);
        column.setColumnHeader(cell);
        cell = new DesignCell();
        cell.setStyle(tableTDStyle);
        cell.setHeight(30);
        textField = new JRDesignTextField();
        textField.setX(0);
        textField.setY(0);
        textField.setWidth(140);
        textField.setHeight(30);
        textField.getLineBox().copyTopPen(cellBoxPen);
        textField.getLineBox().copyLeftPen(cellBoxPen);
        textField.getLineBox().copyBottomPen(cellBoxPen);
        textField.getLineBox().copyRightPen(cellBoxPen);
        expression = new JRDesignExpression();
        expression.setText("$F{order_id}");
        textField.setExpression(expression);
        cell.addElement(textField);
        column.setDetailCell(cell);
        table.addColumn(column);

        //store_name
        column = new StandardColumn();
        column.setWidth(140);
        cell = new DesignCell();
        cell.setStyle(tableCHStyle);
        cell.setHeight(30);
        cell.setRowSpan(1);
        cellBoxPen = new JRBaseBoxPen(cell.getLineBox());
        cellBoxPen.setLineWidth(1.0f);
        cellBoxPen.setLineStyle(LineStyleEnum.SOLID);
        cellBoxPen.setLineColor(Color.decode("#000000"));
        cell.getLineBox().copyTopPen(cellBoxPen);
        cell.getLineBox().copyLeftPen(cellBoxPen);
        cell.getLineBox().copyBottomPen(cellBoxPen);
        cell.getLineBox().copyRightPen(cellBoxPen);
        staticText = new JRDesignStaticText();
        staticText.setText("店铺号");
        cell.addElement(staticText);
        column.setColumnHeader(cell);
        cell = new DesignCell();
        cell.setStyle(tableTDStyle);
        cell.setHeight(30);
        textField = new JRDesignTextField();
        textField.setX(0);
        textField.setY(0);
        textField.setWidth(140);
        textField.setHeight(30);
        textField.getLineBox().copyTopPen(cellBoxPen);
        textField.getLineBox().copyLeftPen(cellBoxPen);
        textField.getLineBox().copyBottomPen(cellBoxPen);
        textField.getLineBox().copyRightPen(cellBoxPen);
        expression = new JRDesignExpression();
        expression.setText("$F{store_name}");
        textField.setExpression(expression);
        cell.addElement(textField);
        column.setDetailCell(cell);
        table.addColumn(column);

        //total_amt
        column = new StandardColumn();
        column.setWidth(140);
        cell = new DesignCell();
        cell.setStyle(tableCHStyle);
        cell.setHeight(30);
        cell.setRowSpan(1);
        cellBoxPen = new JRBaseBoxPen(cell.getLineBox());
        cellBoxPen.setLineWidth(1.0f);
        cellBoxPen.setLineStyle(LineStyleEnum.SOLID);
        cellBoxPen.setLineColor(Color.decode("#000000"));
        cell.getLineBox().copyTopPen(cellBoxPen);
        cell.getLineBox().copyLeftPen(cellBoxPen);
        cell.getLineBox().copyBottomPen(cellBoxPen);
        cell.getLineBox().copyRightPen(cellBoxPen);
        staticText = new JRDesignStaticText();
        staticText.setText("总金额");
        cell.addElement(staticText);
        column.setColumnHeader(cell);
        cell = new DesignCell();
        cell.setStyle(tableTDStyle);
        cell.setHeight(30);
        textField = new JRDesignTextField();
        textField.setX(0);
        textField.setY(0);
        textField.setWidth(140);
        textField.setHeight(30);
        textField.getLineBox().copyTopPen(cellBoxPen);
        textField.getLineBox().copyLeftPen(cellBoxPen);
        textField.getLineBox().copyBottomPen(cellBoxPen);
        textField.getLineBox().copyRightPen(cellBoxPen);
        expression = new JRDesignExpression();
        expression.setText("$F{total_amt}");
        textField.setExpression(expression);
        cell.addElement(textField);
        column.setDetailCell(cell);
        table.addColumn(column);

        //status_text
        column = new StandardColumn();
        column.setWidth(140);
        cell = new DesignCell();
        cell.setStyle(tableCHStyle);
        cell.setHeight(30);
        cell.setRowSpan(1);
        cellBoxPen = new JRBaseBoxPen(cell.getLineBox());
        cellBoxPen.setLineWidth(1.0f);
        cellBoxPen.setLineStyle(LineStyleEnum.SOLID);
        cellBoxPen.setLineColor(Color.decode("#000000"));
        cell.getLineBox().copyTopPen(cellBoxPen);
        cell.getLineBox().copyLeftPen(cellBoxPen);
        cell.getLineBox().copyBottomPen(cellBoxPen);
        cell.getLineBox().copyRightPen(cellBoxPen);
        staticText = new JRDesignStaticText();
        staticText.setText("状态");
        cell.addElement(staticText);
        column.setColumnHeader(cell);
        cell = new DesignCell();
        cell.setStyle(tableTDStyle);
        cell.setHeight(30);
        textField = new JRDesignTextField();
        textField.setX(0);
        textField.setY(0);
        textField.setWidth(140);
        textField.setHeight(30);
        textField.getLineBox().copyTopPen(cellBoxPen);
        textField.getLineBox().copyLeftPen(cellBoxPen);
        textField.getLineBox().copyBottomPen(cellBoxPen);
        textField.getLineBox().copyRightPen(cellBoxPen);
        expression = new JRDesignExpression();
        expression.setText("$F{status_text}");
        textField.setExpression(expression);
        cell.addElement(textField);
        column.setDetailCell(cell);
        table.addColumn(column);

        JRDesignComponentElement reportElement = new JRDesignComponentElement();
        reportElement.setX(-13);
        reportElement.setY(10);
        reportElement.setWidth(581);
        reportElement.setHeight(60);
        JRDesignPropertyExpression propertyExpression = new JRDesignPropertyExpression();
        propertyExpression.setName("com.jaspersoft.studio.layout");
        JRDesignExpression propertyValueExpression = new JRDesignExpression();
        propertyValueExpression.setText("com.jaspersoft.studio.editor.layout.VerticalRowLayout");
        propertyExpression.setValueExpression(propertyValueExpression);
        reportElement.addPropertyExpression(propertyExpression);
        propertyExpression = new JRDesignPropertyExpression();
        propertyExpression.setName("com.jaspersoft.studio.table.style.table_header");
        propertyValueExpression = new JRDesignExpression();
        propertyValueExpression.setText("Table_TH");
        propertyExpression.setValueExpression(propertyValueExpression);
        reportElement.addPropertyExpression(propertyExpression);
        propertyExpression = new JRDesignPropertyExpression();
        propertyExpression.setName("com.jaspersoft.studio.table.style.column_header");
        propertyValueExpression = new JRDesignExpression();
        propertyValueExpression.setText("Table_CH");
        propertyExpression.setValueExpression(propertyValueExpression);
        reportElement.addPropertyExpression(propertyExpression);
        propertyExpression = new JRDesignPropertyExpression();
        propertyExpression.setName("com.jaspersoft.studio.table.style.detail");
        propertyValueExpression = new JRDesignExpression();
        propertyValueExpression.setText("Table_TD");
        propertyExpression.setValueExpression(propertyValueExpression);
        reportElement.addPropertyExpression(propertyExpression);

        componentElement.setComponent(table);
        JRDesignElementGroup jrDesignElementGroup = new JRDesignElementGroup();
        jrDesignElementGroup.addElement(reportElement);
        componentElement.setElementGroup(jrDesignElementGroup);
        componentElement.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components","jr", "table"));

        band = new JRDesignBand();
        band.setHeight(95);
        band.addElement(componentElement);

        ((JRDesignSection)jasperDesign.getDetailSection()).addBand(band);

        jasperDesign.setQuery(query);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("ReportTitle", "Test Jasper Design");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, dataSource.getConnection());

        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }
}
