package pl.edu.pjatk.simulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.simulator.model.Compartment;
import pl.edu.pjatk.simulator.service.CompartmentService;
import java.util.List;

@RestController
@RequestMapping("/compartments")
public class CompartmentController {

    @Autowired
    CompartmentService compartmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Compartment>> getAllCompartments() {
        try {
            var execution = compartmentService.getAllCompartments();
            return ResponseEntity.ok(execution);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compartment> getCompartmentById(@PathVariable("id") Long id) {
        try {
            var execution = compartmentService.getCompartmentById(id);
            return ResponseEntity.ok(execution);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Compartment> addCompartment(@RequestBody Compartment compartment) {
        try {
            var execution = compartmentService.saveCompartment(compartment);
            return ResponseEntity.ok(execution);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Compartment> updateCompartment(@RequestBody Compartment compartment) {
        try {
            var execution = compartmentService.saveCompartment(compartment);
            return ResponseEntity.ok(execution);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Compartment> deleteCompartment(@PathVariable("id") Long id) {
        try {
            compartmentService.deleteCompartment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
