package edu.mum.cs.salmans.serviceImpl;

import edu.mum.cs.salmans.models.HairStyle;
import edu.mum.cs.salmans.repository.HairStyleRepository;
import edu.mum.cs.salmans.service.HairStyleService;
import edu.mum.cs.salmans.utility.AppValues;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HairStyleServiceImplementation implements HairStyleService {
    private HairStyleRepository hairStyleRepository;

    public HairStyleServiceImplementation(HairStyleRepository hairStyleRepository) {
        this.hairStyleRepository = hairStyleRepository;
    }

    @Override
    public boolean defaultStylesExist() {
        return hairStyleRepository.count() > 0;
    }


    @Override
    public void saveStyle(HairStyle hairStyle) {
        hairStyleRepository.save(hairStyle);
    }

    @Override
    public List<HairStyle> getAllHairStyles() {
        return hairStyleRepository.findAll(Sort.by(AppValues.HAIRSTYLE_SORT_BY.toString()));
    }

    @Override
    public Optional<HairStyle> getHairstyleWithId(Integer hairstyleId) {
        return hairStyleRepository.findById(hairstyleId);
    }
}
