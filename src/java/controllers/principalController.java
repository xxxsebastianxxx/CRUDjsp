/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;


import Dao.ConectarDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.UsuarioBean;
import models.UsuariosValidation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author ryzen3
 */
@Controller

public class principalController {
    private JdbcTemplate jdbcTemplate;
    private UsuariosValidation mostararDatos;
 
    public principalController() {
        this.mostararDatos = new UsuariosValidation();
        ConectarDB con = new ConectarDB();
        jdbcTemplate = new JdbcTemplate(con.conDB());
    }
    
   
    
     
    @RequestMapping("ListarUsuario.htm")
    
    public ModelAndView formUsuario(){
     ModelAndView mav = new ModelAndView();
     String sql = "select * from usuarios";
     List datos = jdbcTemplate.queryForList(sql);
     mav.addObject("usuarios",datos);
     mav.setViewName("views/ListarUsuario");
     return mav;
    }
     
    
   @RequestMapping( value = "formUsuario.htm",method = RequestMethod.GET)
   public ModelAndView addCliente (){
     ModelAndView mav  = new ModelAndView();
     mav.addObject("usuario", new UsuarioBean());
     mav.setViewName("views/formUsuario");
     return mav;    
   }
   
   
   
   @RequestMapping(value = "formUsuario.htm",method = RequestMethod.POST)
   
    public ModelAndView addCliente (UsuarioBean usu,
            @ModelAttribute("usuario") UsuarioBean users,   
            BindingResult result,
             SessionStatus status
    ){  
     this.mostararDatos.validate(users, result);
     if(result.hasErrors()){
                    ModelAndView mav = new ModelAndView();
                    mav.setViewName("views/formUsuario");
                    return mav;
    }else{
            ModelAndView mav  = new ModelAndView();
             String sql = "insert into usuarios ( nombre, apellido,  tipo_de_identificacion, identificacion, direccion ) values (?,?,?,?,?)";
             jdbcTemplate.update(sql, usu.getNombre(), usu.getApellido(), usu.getTipo_de_identificacion(),usu.getIdentificacion(), usu.getDireccion());
             mav.setViewName("redirect:/ListarUsuario.htm");
             return mav;    
           }
    }  
    
    
   @RequestMapping("deleteUsuario.htm")    
     public ModelAndView deleteUsuario(  HttpServletRequest req){
     ModelAndView mav = new ModelAndView();
     int id_usuario = Integer.parseInt(req.getParameter("id_usuario"));
     String sql ="delete from usuarios where id_usuario = ? ";
     jdbcTemplate.update(sql , id_usuario);
     mav.setViewName("redirect:/ListarUsuario.htm");
    
     return mav;
    }
     
     
     
     
     
    @RequestMapping(value="updateUsuario.htm" ,method = RequestMethod.GET)
    public ModelAndView actUsuario(HttpServletRequest req){
        ModelAndView mav = new ModelAndView();
        int id_usuario = Integer.parseInt(req.getParameter("id_usuario"));
        
        UsuarioBean usu = cunsultarUsuarioxId(id_usuario);
        //(UsuarioBean) List datos = jdbcTemplate.queryForList(sql);
        
        mav.addObject("usuario",usu);
        mav.setViewName("views/updateUsuario");
     return mav;
    }
    
    
    
    public UsuarioBean cunsultarUsuarioxId( int id_usuario){
        UsuarioBean usu = new UsuarioBean();
        String sql = "select * from usuarios where id_usuario = " + id_usuario ;
        return(UsuarioBean)jdbcTemplate.query(
                sql, new ResultSetExtractor<UsuarioBean>(){
                 @Override
                 public UsuarioBean extractData(ResultSet rs) throws SQLException, DataAccessException {
                   if(rs.next()){
                      usu.setId_usuario(rs.getInt("id_usuario"));
                      usu.setNombre(rs.getString("nombre"));
                      usu.setApellido(rs.getString("apellido"));
                      usu.setTipo_de_identificacion(rs.getString("tipo_de_identificacion"));
                      usu.setIdentificacion(rs.getInt("identificacion"));
                      usu.setDireccion(rs.getString("direccion"));
                   }
                     
                     return usu;
              }
          }
                
         );
    }
    
    
    
    
     @RequestMapping(value = "updateUsuario.htm",method = RequestMethod.POST)
   
    public ModelAndView actUsuario (UsuarioBean usu,
            @ModelAttribute("usuario") UsuarioBean users,   
            BindingResult result,
             SessionStatus status
    ){  
     this.mostararDatos.validate(users, result);
     if(result.hasErrors()){
                    ModelAndView mav = new ModelAndView();
                    mav.setViewName("views/formUsuario");
                    return mav;
    }else{
            ModelAndView mav  = new ModelAndView();
             String sql = "update usuarios set nombre = ?, apellido = ?,  tipo_de_identificacion = ?, identificacion = ?, direccion = ? where id_usuario = ?";
             jdbcTemplate.update(sql, usu.getNombre(), usu.getApellido(), usu.getTipo_de_identificacion(),usu.getIdentificacion(), usu.getDireccion(), usu.getId_usuario());
             mav.setViewName("redirect:/ListarUsuario.htm");
             return mav;    
           }
    } 
}
