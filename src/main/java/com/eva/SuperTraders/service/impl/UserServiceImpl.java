package com.eva.SuperTraders.service.impl;

import com.eva.SuperTraders.domain.dto.UserDto;
import com.eva.SuperTraders.domain.entity.User;
import com.eva.SuperTraders.repository.UserRepository;
import com.eva.SuperTraders.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto save(UserDto userDto) {
        userDto.setIsDeleted(Boolean.FALSE);
        return this.modelMapper.map(
                this.userRepository.save(
                        this.modelMapper.map(userDto, User.class)
                ), UserDto.class
        );
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserDto oldDto = this.getById(userDto.getId());

        oldDto.setUpdateDate(new Date());
        oldDto.setUpdateUserId(userDto.getUpdateUserId());
        oldDto.setPassword(userDto.getPassword());
        oldDto.setUsername(userDto.getUsername());
        return this.modelMapper.map(
                this.userRepository.save(
                        this.modelMapper.map(
                                oldDto,
                                User.class
                        )
                ),
                UserDto.class
        );
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> userOptional =userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return modelMapper.map(user,UserDto.class);
        }
        else {
            return null;
        }
    }

    @Override
    public List<UserDto> getAll() {
        return this.userRepository.findAll().stream()
                .map(entity -> this.modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id, Long deleteUserId) {
        UserDto dto = this.getById(id);
        dto.setIsDeleted(Boolean.TRUE);
        dto.setDeleteDate(new Date());
        dto.setDeleteUserId(deleteUserId);
        dto.setDeleteUserId(deleteUserId);
        update(dto);
    }
}
