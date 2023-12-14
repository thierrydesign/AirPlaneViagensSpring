package com.airplane.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
import com.airplane.model.Usuario;
import com.airplane.repositorio.UsuarioRepositorio;
 
@Controller
@RequestMapping("/")
public class UsuarioController {
 
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
 
    @GetMapping
    public ModelAndView listarUsuarios() {
        ModelAndView modelAndView = new ModelAndView("lista-usuarios");
        modelAndView.addObject("usuarios", usuarioRepositorio.findAll());
        return modelAndView;
    }
 
    @GetMapping("/cadastrar")
    public ModelAndView cadastrarUsuario() {
        ModelAndView modelAndView = new ModelAndView("cadastro-usuario");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }
 
    @PostMapping("/cadastrar")
    public String salvarUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
        return "redirect:/";
    }
 
    @GetMapping("/editar/{id}")
    public ModelAndView editarUsuario(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("editar-usuario");
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }
 
    @PostMapping("/editar/{id}")
    public String atualizarUsuario(@PathVariable Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepositorio.findById(id).orElse(null);
 
        if (usuarioExistente != null) {
            // Atualizar os campos relevantes
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setSenha(usuarioAtualizado.getSenha());
 
            usuarioRepositorio.save(usuarioExistente);
        }
        return "redirect:/";
    }
 
    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        usuarioRepositorio.deleteById(id);
        return "redirect:/";
    }
 
}
