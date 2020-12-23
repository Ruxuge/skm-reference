package pl.edu.pjatk.simulator.service;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Entity
@Table(name = "skmInfo")
public class TrainServiceConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numberOfTrains", nullable = false)
    private int numberOfTrains;
    @Column(name = "numberOfCompartments", nullable = false)
    private int numberOfCompartments;
    @Column(name = "compartmentCapacity", nullable = false)
    private int compartmentCapacity;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getNumberOfTrains() {
        return numberOfTrains;
    }

    public void setNumberOfTrains(int numberOfTrains) {
        this.numberOfTrains = numberOfTrains;
    }

    public int getNumberOfCompartments() {
        return numberOfCompartments;
    }

    public void setNumberOfCompartments(int numberOfCompartments) {
        this.numberOfCompartments = numberOfCompartments;
    }

    public int getCompartmentCapacity() {
        return compartmentCapacity;
    }

    public void setCompartmentCapacity(int compartmentCapacity) {
        this.compartmentCapacity = compartmentCapacity;
    }

}
