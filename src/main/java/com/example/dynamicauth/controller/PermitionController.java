package com.example.dynamicauth.controller;

import com.example.dynamicauth.entity.Permition;
import com.example.dynamicauth.repository.PermitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permition")
public class PermitionController {

    @Autowired
    PermitionRepository permitionRepository;

    // Tạo mới một quyền
    @PreAuthorize("hasAuthority('CREATE_PERMITION')")
    @PostMapping("/")
    public ResponseEntity<Permition> createPermition(@RequestBody Permition permition) {
        Permition savedPermition = permitionRepository.save(permition);
        return ResponseEntity.ok(savedPermition);
    }

    // Lấy tất cả quyền
    @PreAuthorize("hasAuthority('READ_PERMITION')")
    @GetMapping("/all")
    public ResponseEntity<List<Permition>> getAllPermitions() {
        List<Permition> permitionList = permitionRepository.findAll();
        return ResponseEntity.ok(permitionList);
    }

    // Lấy quyền theo ID
    @PreAuthorize("hasAuthority('READ_PERMITION')")
    @GetMapping("/{id}")
    public ResponseEntity<Permition> getPermitionById(@PathVariable Integer id) {
        return permitionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật quyền
    @PreAuthorize("hasAuthority('UPDATE_PERMITION')")
    @PutMapping("/{id}")
    public ResponseEntity<Permition> updatePermition(@PathVariable Integer id, @RequestBody Permition permition) {
        if (!permitionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        permition.setId(id); // Đặt ID cho quyền để cập nhật
        Permition updatedPermition = permitionRepository.save(permition);
        return ResponseEntity.ok(updatedPermition);
    }

    // Xóa quyền
    @PreAuthorize("hasAuthority('DELETE_PERMITION')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermition(@PathVariable Integer id) {
        if (!permitionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        permitionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
