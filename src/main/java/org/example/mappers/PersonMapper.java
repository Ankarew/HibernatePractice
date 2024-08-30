package org.example.mappers;

import org.example.dto.PersonCreateDto;
import org.example.dto.PersonDto;
import org.example.models.Person;
import org.mapstruct.*;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePersonFromDto(PersonDto dto, @MappingTarget Person entity);

    void createPersonFromDTO(PersonCreateDto dto, @MappingTarget Person entity);
}
