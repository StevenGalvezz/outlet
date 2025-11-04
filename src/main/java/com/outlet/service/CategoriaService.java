/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.outlet.service;

import com.outlet.domain.Categoria;
import com.outlet.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author xsf
 */
@Service
public class CategoriaService {

    // Permite crear una única instancia de CategoriaRepository, y la crea automáticamente
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) {
        if (activo) { // Sólo activos...
            return categoriaRepository.findByActivoTrue();
        }
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaRepository.findById(categoria.getId_categoria()).orElse(null);
    }

    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public boolean delete(Categoria categoria) {
        try {
            categoriaRepository.delete(categoria);
            categoriaRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
