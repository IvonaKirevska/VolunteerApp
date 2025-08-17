package mk.ukim.finki.kiii.volunteerapp.service.domain.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.repository.ParticipationRepository;
import mk.ukim.finki.kiii.volunteerapp.service.domain.ParticipationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;

    public ParticipationServiceImpl(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    @Override
    public List<Participation> findAll() {
        return participationRepository.findAll();
    }

    @Override
    public Optional<Participation> findById(Long id) {
        return participationRepository.findById(id);
    }

    @Override
    public Participation save(Participation participation) {
        if (participation.getUser() == null || participation.getEvent() == null) {
            throw new IllegalArgumentException();
        }
        Participation participation1 = new Participation(participation.getUser(), participation.getEvent());
        return participationRepository.save(participation1);
    }

    @Override
    public Optional<Participation> update(Long id, Participation participation) {
        return participationRepository.findById(id)
                .map(existingParticipation -> {
                    if (participation.getUser() != null) {
                        existingParticipation.setUser(existingParticipation.getUser());
                    }
                    if (participation.getEvent() != null) {
                        existingParticipation.setEvent(existingParticipation.getEvent());
                    }
                    return participationRepository.save(existingParticipation);
                });
    }

    @Override
    public void deleteById(Long id) {
        participationRepository.deleteById(id);
    }
}
