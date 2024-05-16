package com.eva.SuperTraders.service.impl;

import com.eva.SuperTraders.domain.dto.ShareDto;
import com.eva.SuperTraders.domain.entity.Share;
import com.eva.SuperTraders.repository.ShareRepository;
import com.eva.SuperTraders.service.ShareService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ShareRepository shareRepository;

    @Override
    public ShareDto save(ShareDto shareDto) {

        shareDto.setIsDeleted(Boolean.FALSE);
        return this.modelMapper.map(
                this.shareRepository.save(
                        this.modelMapper.map(shareDto, Share.class)
                ), ShareDto.class
        );
    }

    @Override
    public ShareDto update(ShareDto shareDto) {
        ShareDto oldDto = this.getById(shareDto.getId());

        oldDto.setName(shareDto.getName());
        oldDto.setPrice(shareDto.getPrice());
        oldDto.setTotalQuantity(shareDto.getTotalQuantity());
        oldDto.setSymbol(shareDto.getSymbol());
        oldDto.setUpdateDate(new Date());
        oldDto.setUpdateUserId(shareDto.getUpdateUserId());
        oldDto.setIsDeleted(shareDto.getIsDeleted());
        return this.modelMapper.map(
                this.shareRepository.save(
                        this.modelMapper.map(
                                oldDto,
                                Share.class
                        )
                ),
                ShareDto.class
        );
    }

    @Override
    public ShareDto getById(Long id) {
        Optional<Share> shareOptional =shareRepository.findById(id);
        if(shareOptional.isPresent()){
            Share share = shareOptional.get();
            return modelMapper.map(share,ShareDto.class);
        }
        else {
            return null;
        }
    }

    @Override
    public List<ShareDto> getAll() {
        return this.shareRepository.findAll().stream()
                .map(shareEntity -> this.modelMapper.map(shareEntity, ShareDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id, Long deleteUserId) {
        ShareDto shareDto = this.getById(id);
        shareDto.setIsDeleted(Boolean.TRUE);
        shareDto.setDeleteDate(new Date());
        shareDto.setDeleteUserId(deleteUserId);
        shareDto.setDeleteUserId(deleteUserId);
        update(shareDto);
    }
}
