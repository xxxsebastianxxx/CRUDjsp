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
import models.MascotaValidation;
import models.MascotasBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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


public class MascotasController {
    private JdbcTemplate jdbcTemplate;
    private MascotaValidation mostararDatos;

    public MascotasController() {
        this.mostararDatos = new MascotaValidation();
        ConectarDB con = new ConectarDB();
        jdbcTemplate = new JdbcTemplate(con.conDB());
    }
    
    
     @RequestMapping("ListarMascotas.htm")
        public ModelAndView formMascota(){
         ModelAndView mav = new ModelAndView();
         String sql = "select * from mascotas";
         List datosm = jdbcTemplate.queryForList(sql);
         mav.addObject("mascotas",datosm);
         mav.setViewName("views/ListarMascotas");
         return mav;
    }
        
        
    
     @RequestMapping(value = "formMascota.htm", method = RequestMethod.GET)
     public ModelAndView addmascotas(){
      ModelAndView mav = new ModelAndView();
      mav.addObject("mascotas", new MascotasBean());
      mav.setViewName("views/formMascota");
      return mav;
     }

      @RequestMapping(value = "formMascota.htm", method = RequestMethod.POST)
       
      public ModelAndView addMascotas( MascotasBean mas,
             @ModelAttribute("mascotas") MascotasBean mascotas, 
              BindingResult result,
              SessionStatus status
    
      ){
          
          this.mostararDatos.validate(mascotas, result);
                 if(result.hasErrors()){
                        ModelAndView mav = new ModelAndView();
                        mav.setViewName("views/formMascota");
                        return mav;
                 
                }else{ 
                    ModelAndView mav = new ModelAndView();
                    String sql ="insert into mascotas(nombre_mascota,raza,genero,tipo_de_mascotas,vacunas)values(?,?,?,?,?)";
                    jdbcTemplate.update(sql, mas.getNombre_mascota(), mas.getRaza(),mas.getGenero(),mas.getTipo_de_mascotas(),mas.getVacunas());
                    mav.setViewName("redirect:/ListarMascotas.htm");
                    return mav;
              
              }
         }  
     @RequestMapping("deleteMascota.htm")  
     public ModelAndView deleteMascota(  HttpServletRequest req){
     ModelAndView mav = new ModelAndView();
     int id_mascotas = Integer.parseInt(req.getParameter("id_mascotas"));
     String sql ="delete from mascotas where id_mascotas = ? ";
     jdbcTemplate.update(sql , id_mascotas);
     mav.setViewName("redirect:/ListarMascotas.htm");
     return mav;
     }
     
     
     
       @RequestMapping(value="updateMascota.htm" ,method = RequestMethod.GET)
        public ModelAndView actMascota(HttpServletRequest req){
         ModelAndView mav = new ModelAndView();
         int id_mascotas = Integer.parseInt(req.getParameter("id_mascotas"));
         MascotasBean mas = consultarMascotasxid(id_mascotas); 
         
         mav.addObject("mascotas",mas);
         mav.setViewName("views/updateMascota");
         return mav;
    }
        
       public MascotasBean consultarMascotasxid ( int id_mascotas){
         MascotasBean mas = new MascotasBean();
         String sql = "select * from mascotas where id_mascotas =" + id_mascotas ;
         return(MascotasBean)jdbcTemplate.query(
         sql, new ResultSetExtractor<MascotasBean>(){

             @Override
             public MascotasBean extractData(ResultSet rs) throws SQLException, DataAccessException {
              if(rs.next()){
                   mas.setId_mascotas(rs.getInt("id_mascotas"));
                   mas.setNombre_mascota(rs.getString("nombre_mascota"));
                   mas.setRaza(rs.getString("raza"));
                   mas.setGenero(rs.getString("genero"));
                   mas.setTipo_de_mascotas(rs.getString("tipo_de_mascotas"));
                   mas.setVacunas(rs.getString("vacunas"));
                   
              }
               
            return mas;
             }
         
           }
         );
       
       }
       @RequestMapping(value = "updateMascota.htm",method = RequestMethod.POST)
        public ModelAndView actMascota( MascotasBean mas,
             @ModelAttribute("mascotas") MascotasBean mascotas, 
              BindingResult result,
              SessionStatus status
    
      ){
          
          this.mostararDatos.validate(mascotas, result);
                 if(result.hasErrors()){
                        ModelAndView mav = new ModelAndView();
                        mav.setViewName("views/formMascota");
                        return mav;
                 
                }else{ 
                    ModelAndView mav = new ModelAndView();
                    String sql ="update mascotas set nombre_mascota = ?,raza = ?, genero = ?,tipo_de_mascotas = ?,vacunas = ? where id_mascotas = ?";
                    jdbcTemplate.update(sql, mas.getNombre_mascota(), mas.getRaza(),mas.getGenero(),mas.getTipo_de_mascotas(),mas.getVacunas(), mas.getId_mascotas());
                    mav.setViewName("redirect:/ListarMascotas.htm");
                    return mav;
              
              }
         }  
       
       }
