package io.robe.convert.excel;


import io.robe.convert.SamplePojo;
import io.robe.convert.TestData;
import io.robe.convert.excel.exporter.XLSExporter;
import io.robe.convert.excel.importer.XLSImporter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class XLSExporterTest {
    @Test
    public void exportStream() throws Exception {
        XLSImporter xlsImporter = new XLSImporter(false);


        OutputStream outputStream = new FileOutputStream(XLSExporterTest.class.getClassLoader().getResource("sample.xls").getFile());
        XLSExporter xlsExporter = new XLSExporter(false);
        xlsExporter.exportStream(SamplePojo.class, outputStream, TestData.getData().iterator());

        outputStream.close();

    }
}