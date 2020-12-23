package pl.edu.pjatk.simulator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.simulator.model.Compartment;
import pl.edu.pjatk.simulator.repository.CompartmentJPARepo;

import java.util.List;

@Service
public class CompartmentService {

    @Autowired
    CompartmentJPARepo compartmentJPARepo;

    public List<Compartment> getAllCompartments() {
        return compartmentJPARepo.findAll();
    }

    public Compartment getCompartmentById(Long id) {
        return compartmentJPARepo.findAll()
                .stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Compartment saveCompartment(Compartment compartment) {
        return compartmentJPARepo.save(compartment);
    }

    public void deleteCompartment(Long id) {
        compartmentJPARepo.deleteById(id);
    }
}
