package pl.edu.pjatk.simulator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.simulator.model.Train;
import pl.edu.pjatk.simulator.repository.TrainJPARepo;
import pl.edu.pjatk.simulator.repository.TrainRepository;

import java.util.Collection;

@Service
public class TrainService {
    private final Collection<Train> trains;

    public TrainService(TrainRepository repository) {
        this.trains = repository.getAllTrains();
    }

    @Autowired
    TrainJPARepo trainJPARepo;

    public void moveTimeForward() {
        trains.forEach(Train::move);
    }

    public Collection<Train> getAll() {
        return trains;
    }

    public Train getById(Long id) {
        return trains.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Train saveTrain(Train train) {
        return trainJPARepo.save(train);
    }

    public void deleteTrain(Long id) {
        trainJPARepo.deleteById(id);
    }
}
