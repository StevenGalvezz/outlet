/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.outlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Si usas una clase ProductoService y una entidad Producto:
//import tu.paquete.servicio.ProductoService;
//import tu.paquete.entidad.Producto;

// Otros imports comunes según tus necesidades:
import java.util.List;

/**
 *
 * @author xsf
 */
@Controller
@RequestMapping("/producto")
public class ProductoController {

    /*@Autowired
    private ProductoService productoService;/

    // Ver detalle
    @GetMapping("/{id}")
    public String verProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id);
        model.addAttribute("producto", producto);
        return "productos/detalle";
    }

    // Listado (opcional)
    @GetMapping("/listado")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.getAllProductos();
        model.addAttribute("productos", productos);
        return "productos/listado";
    }

    // Puedes agregar más rutas según lo que necesites*/
}