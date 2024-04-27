package com.grocery.controller;

import com.grocery.co.GroceryCO;
import com.grocery.co.GroceryUpdateCO;
import com.grocery.co.OrderCO;
import com.grocery.enums.RoleType;
import com.grocery.service.GroceryService;
import com.grocery.vo.GroceryVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/grocery")
public class GroceryController {

    private GroceryService groceryService;

    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @PostMapping("/{roleType}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> addGrocery(@NotNull @PathVariable RoleType roleType, @RequestBody GroceryCO groceryCO) {
        return ResponseEntity.ok(groceryService.save(groceryCO));
    }

    @GetMapping("/{roleType}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GroceryVO>> getAllGrocery(@NotNull @PathVariable RoleType roleType) {
        return ResponseEntity.ok(groceryService.getAll(roleType));
    }

    @DeleteMapping("/{roleType}/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@NotNull @PathVariable Long id, @NotNull @PathVariable RoleType roleType) {
        groceryService.delete(id, roleType);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{roleType}/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity update(@NotNull @PathVariable Long id, @RequestBody GroceryUpdateCO groceryUpdateCO) {
        groceryService.update(id, groceryUpdateCO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{roleType}/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<GroceryVO>> getAllAvailableGrocery(@NotNull @PathVariable RoleType roleType) {
        return ResponseEntity.ok(groceryService.getAllAvailableGrocery(roleType));
    }

    @PostMapping("/{roleType}/order")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Long> order(@NotNull @PathVariable RoleType roleType, @RequestBody OrderCO orderCO) {
        return ResponseEntity.ok(groceryService.orderGrocery(roleType, orderCO));
    }
}