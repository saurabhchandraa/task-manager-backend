package com.taskmanagerbackend;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasksMapper {

    @Autowired
    private ModelMapper modelMapper;

    private TypeMap<TaskEntity, TaskDto> typeMapEntityToDto;

    private TypeMap<TaskDto, TaskEntity> typeMapDtoToEntity;

    public TaskDto toTaskDto(TaskEntity entity) {

        if(typeMapEntityToDto == null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            typeMapEntityToDto = modelMapper.createTypeMap(TaskEntity.class, TaskDto.class);
        }
        TaskDto dto = typeMapEntityToDto.map(entity);
        return dto;
    }

    public TaskEntity toTaskEntity(TaskDto dto) {

        if(typeMapDtoToEntity == null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            typeMapDtoToEntity = modelMapper.createTypeMap(TaskDto.class, TaskEntity.class);
        }
        TaskEntity entity = typeMapDtoToEntity.map(dto);
        return entity;
    }
}
