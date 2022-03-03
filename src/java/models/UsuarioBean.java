/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.ejb.Stateless;

/**
 *
 * @author ryzen3
 */
@Stateless
public class UsuarioBean {
   private Integer id_usuario;
   private String nombre;
   private String apellido;
   private String tipo_de_identificacion;
   private Integer identificacion;
   private String direccion;

    public UsuarioBean() {
    }

    public UsuarioBean(String nombre, String apellido, String tipo_de_identificacion, Integer identificacion, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_de_identificacion = tipo_de_identificacion;
        this.identificacion = identificacion;
        this.direccion = direccion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    

  

   
   
   
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_de_identificacion() {
        return tipo_de_identificacion;
    }

    public void setTipo_de_identificacion(String tipo_de_identificacion) {
        this.tipo_de_identificacion = tipo_de_identificacion;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
   
}
