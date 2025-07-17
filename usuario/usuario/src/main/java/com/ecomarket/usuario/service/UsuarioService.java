package com.ecomarket.usuario.service;

import com.ecomarket.usuario.model.Usuario;
import com.ecomarket.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

public Usuario crearUsuario(Usuario nuevoUsuario) {
    if (usuarioRepository.findByNombre(nuevoUsuario.getNombre()).isPresent()) {
        throw new RuntimeException("El nombre de usuario ya está en uso");
    }
    if (usuarioRepository.findByTelefono(nuevoUsuario.getTelefono()).isPresent()) {
        throw new RuntimeException("El teléfono ya está en uso");
    }
    if (usuarioRepository.findByEmail(nuevoUsuario.getEmail()).isPresent()) {
        throw new RuntimeException("El email ya está en uso");
    }
    return usuarioRepository.save(nuevoUsuario);
}
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(usuarioActualizado.getNombre());
                    usuario.setEmail(usuarioActualizado.getEmail());
                    usuario.setPassword(usuarioActualizado.getPassword());
                    usuario.setTelefono(usuarioActualizado.getTelefono());
                    return usuarioRepository.save(usuario);
                }).orElse(null);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}