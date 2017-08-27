package com.devlabs.servlet;

import com.devlabs.model.Provider;
import com.devlabs.model.Record;
import com.devlabs.model.report.SimpleRecord;
import com.devlabs.service.ConsultationService;
import com.devlabs.util.DateUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author VakSF
 */
public class ReportController extends HttpServlet {

    private final ConsultationService consultationService = new ConsultationService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long providerId = new Long(request.getParameter("id"));
        
        Provider provider = this.consultationService.getProvider(providerId);
        
        if (provider == null) {
            
            PrintWriter writer = response.getWriter();
            
            response.sendRedirect("404.html");
            
            return;
        }
        
        List<Record> records = this.consultationService.getConsultationsById(providerId);
        
        URL url = getClass().getClassLoader().getResource("Choco.jasper");
        
        String fileName = new File(url.getFile()).getAbsolutePath();
        
        ArrayList<SimpleRecord> filterDataSource = new ArrayList<>();
        
        filterDataSource.add(new SimpleRecord());
        
        for (Record record : records) {
            filterDataSource.add(new SimpleRecord(record));
        }
        
        JRBeanCollectionDataSource beans = new JRBeanCollectionDataSource(filterDataSource);
        
        Map parameters = new HashMap();
        
        parameters.put("provider", "Proveedor: " + provider.getName());
        parameters.put("details", "Fecha: " + DateUtil.getDateString(new Date()));
        
        try {
            
            JasperPrint print = JasperFillManager.fillReport(fileName, parameters, beans);
            
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, byteArray);
            
            response.setContentLength(byteArray.size());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"export.pdf\"");
            
            ServletOutputStream outputStream = response.getOutputStream();
            byteArray.writeTo(outputStream);
            outputStream.flush();
            
        } catch (JRException ex) {
            System.out.println("Error: " +ex.toString());
        }
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
