package com.devlabs.servlet;

import com.devlabs.model.Member;
import com.devlabs.model.Provider;
import com.devlabs.model.Service;
import com.devlabs.service.UserService;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author VakSF
 */
public class UserController extends HttpServlet {
    
    private final UserService userService = new UserService();

    private final Gson gson = new Gson();
    
    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/json";
    
    @Override
    public void init() throws ServletException {
        super.init();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding(ENCODING_UTF_8);
        
        String type = request.getParameter("type");
        String action = request.getParameter("action");
        
        if (type != null) {
            
            String word = request.getParameter("word");
            
            if (type.equalsIgnoreCase("member")) {
                
                List<Member> members = this.userService.searchMembers(word);
                
                System.out.println(members);
                
                writer.print("{\"members\": " + gson.toJson(members) + "}");
                
            } else if (type.equalsIgnoreCase("provider")) {
                
                List<Provider> providers = this.userService.searchProviders(word);
                
                System.out.println(providers);
                
                writer.print("{\"providers\": " + gson.toJson(providers) + "}");
                
            } else if (type.equalsIgnoreCase("service")) {
                
                List<Service> services = this.userService.searchServices(word);
                
                System.out.println(services);
                
                writer.print("{\"services\": " + gson.toJson(services) + "}");
                
            }
            
        } else {
            
            if (action != null) {
                
                if (action.equalsIgnoreCase("closeSession")) {
                    
                    HttpSession session = request.getSession(false);
                    
                    if (session != null) session.invalidate();
                    
                    response.sendRedirect("home.jsp");
                    
                }
                
            }
            
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding(ENCODING_UTF_8);
        
        String action = request.getParameter("action");
        
        System.out.println(action);
        
        if (action != null) {
            
            action = action.toLowerCase();
            
            if (action.equals("save")) {
                
                String user = request.getParameter("user");
                JsonObject object = new JsonParser().parse(user).getAsJsonObject();
                
                String type = object.get("type").getAsString();
                
                boolean saved = false;
                
                if (type.equalsIgnoreCase("member")) {
                    
                    Member member = this.getMember(object, false);
                    
                    saved = this.userService.createMember(member);
                    
                } else if (type.equalsIgnoreCase("provider")) {
                    
                    Provider provider = new Provider(
                            object.get("name").getAsString(),
                            object.get("address").getAsString(),
                            object.get("city").getAsString(),
                            object.get("state").getAsString(),
                            object.get("cp").getAsString(),
                            object.get("user").getAsString(),
                            object.get("password").getAsString()
                    );
                    
                    saved = this.userService.createProvider(provider);
                    
                }
                
                writer.print(saved);
                
            } else if (action.equals("list")) {
                
                String typeUser = request.getParameter("type");
                
                if (typeUser.equalsIgnoreCase("member")) {
                    
                    writer.print(gson.toJson(this.userService.getMembers()));
                    
                } else if (typeUser.equalsIgnoreCase("provider")) {
                    
                    List<Provider> providers = this.userService.getProviders();
                    
                    writer.print(gson.toJson(providers));
                    
                }
                
            } else if (action.equals("edit_member")) {
                
                String user = request.getParameter("user");
                JsonObject object = new JsonParser().parse(user).getAsJsonObject();
                
                Member member = this.getMember(object, true);
                
                System.out.println("Miembro a editar con ID = " + member.getId());
                
                boolean editedMember = this.userService.editMember(member);
                
                System.out.println("Editado? " + editedMember);
                
                writer.print(editedMember);
                
            } else if (action.equals("edit_provider")) {
                
                String user = request.getParameter("user");
                JsonObject object = new JsonParser().parse(user).getAsJsonObject();
                
                Provider provider = this.getProvider(object, true);
                
                System.out.println("Proveedor a editar con ID = " + provider.getId());
                
                boolean editedMember = this.userService.editProvider(provider);
                
                System.out.println("Editado? " + editedMember);
                
                writer.print(editedMember);
                
                
            } else if (action.equals("delete_member")) {
                
                Long id = new Long(request.getParameter("id"));
                
                System.out.println("Miembro a eliminar con ID = " + id);
                
                boolean deletedMember = this.userService.deleteMember(id);
                
                System.out.println("Eliminado? " + deletedMember);
                
                writer.print(deletedMember);
                
            } else if (action.equals("delete_provider")) {
                
                Long id = new Long(request.getParameter("id"));
                
                System.out.println("Proveedor a eliminar con ID = " + id);
                
                boolean deletedProvider = this.userService.deleteProvider(id);
                
                System.out.println("Eliminado? " + deletedProvider);
                
                writer.print(deletedProvider);
                
            }
            
        }
        
    }
    
    private Member getMember(JsonObject object, boolean hasId) {
        
        Member member = new Member(
                object.get("name").getAsString(),
                object.get("address").getAsString(),
                object.get("city").getAsString(),
                object.get("state").getAsString(),
                object.get("cp").getAsString()
        );
        
        if (hasId)
            member.setId(object.get("id").getAsLong());
        
        return member;
    }
    
    private Provider getProvider(JsonObject object, boolean hasId) {
        
        Provider provider = new Provider(
                object.get("name").getAsString(),
                object.get("address").getAsString(),
                object.get("city").getAsString(),
                object.get("state").getAsString(),
                object.get("cp").getAsString(),
                object.get("user").getAsString(),
                object.get("password").getAsString()
        );
        
        if (hasId)
            provider.setId(object.get("id").getAsLong());
        
        return provider;
    }
    
    @Override
    public String getServletInfo() {
        return "UserController Info";
    }
    
}
