package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.HairStyle;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface HairStyleService {
    List<HairStyle> getAllHairStyles();

    public Optional<HairStyle> getHairstyleWithId(Integer hairstyleId);

    public void saveStyle(HairStyle hairStyle);

    Page<HairStyle> getAllHairstylesPaged(int page);

    public boolean defaultStylesExist();


}

