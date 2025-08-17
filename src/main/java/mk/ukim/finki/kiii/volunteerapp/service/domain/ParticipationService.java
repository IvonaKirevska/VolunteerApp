package mk.ukim.finki.kiii.volunteerapp.service.domain;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;

import java.util.List;
import java.util.Optional;

public interface ParticipationService {
    List<Participation> findAll();
    Optional<Participation> findById(Long id);
    Participation save(Participation participation);
    Optional<Participation> update(Long id, Participation participation);
    void deleteById(Long id);
}
