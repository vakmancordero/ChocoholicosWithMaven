package com.devlabs.servlet;

import com.devlabs.model.Record;
import com.devlabs.service.ConsultationService;
import com.devlabs.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.devlabs.servlet.UserController.CONTENT_TYPE_JSON;
import static com.devlabs.servlet.UserController.ENCODING_UTF_8;

/**
 *
 * @author VakSF
 */
public class ConsultationController extends HttpServlet {
    
    private final ConsultationService consultationService = new ConsultationService();
    
    private final Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding(ENCODING_UTF_8);
        
        PrintWriter writer = response.getWriter();
        
        writer.print(gson.toJson(this.consultationService.getRecords()));
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        
        String action = request.getParameter("action");
        
        if (action != null) {
            
            if (action.equalsIgnoreCase("save")) {
                
                try {
                    
                    String consultationSt = request.getParameter("consultation");
                    
                    JsonObject consultation = new JsonParser().parse(consultationSt).getAsJsonObject();
                    
                    Date date = DateUtil.parseDate(consultation.get("date").getAsString());
                    
                    Record record = new Record(
                            this.consultationService.getMember(Long.parseLong(consultation.get("member").getAsString())),
                            this.consultationService.getProvider(Long.parseLong(consultation.get("provider").getAsString())),
                            this.consultationService.getService(Long.parseLong(consultation.get("service").getAsString())),
                            new Date(), date,
                            consultation.get("description").getAsString(),
                            consultation.get("comment").getAsString()
                    );
                    
                    if (this.consultationService.createConsultation(record)) {
                        writer.print(record);
                    } else {
                        writer.print(false);
                    }
                    
                } catch (NullPointerException | ParseException ex) {
                    writer.print(false);
                }
                
            }
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "ConsultationController Info";
    }
    
}
