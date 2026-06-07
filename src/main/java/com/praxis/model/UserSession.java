package com.praxis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "experiment_id")
    private Experiment experiment;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public UserSession(Experiment experiment) {
        this.experiment = experiment;
    }
}
