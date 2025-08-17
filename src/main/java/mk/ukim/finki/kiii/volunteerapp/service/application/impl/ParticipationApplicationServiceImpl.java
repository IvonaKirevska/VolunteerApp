package mk.ukim.finki.kiii.volunteerapp.service.application.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.service.application.ParticipationApplicationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.ParticipationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationApplicationServiceImpl implements ParticipationApplicationService {

    private final ParticipationService participationService;

    public ParticipationApplicationServiceImpl(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @Override
    public List<DisplayParticipationDto> findAll() {
        return DisplayParticipationDto.from(participationService.findAll());
    }

    @Override
    public Optional<DisplayParticipationDto> findById(Long id) {
        return participationService.findById(id).map(DisplayParticipationDto::from);
    }

    @Override
    public DisplayParticipationDto save(CreateParticipationDto createParticipationDto) {
        return DisplayParticipationDto.from(participationService.save(createParticipationDto.toParticipation()));
    }

    @Override
    public Optional<DisplayParticipationDto> update(Long id, CreateParticipationDto createParticipationDto) {
        return participationService.update(id, createParticipationDto.toParticipation()).map(DisplayParticipationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        participationService.deleteById(id);
    }
}
