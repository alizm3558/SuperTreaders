package com.eva.SuperTraders.service;

import com.eva.SuperTraders.domain.dto.ShareDto;

import java.util.List;

public interface ShareService {


    ShareDto save(ShareDto shareDto);

    ShareDto update(ShareDto shareDto);

    ShareDto getById(Long id);

    List<ShareDto> getAll();

    void deleteById(Long id, Long deleteUserId);
}
