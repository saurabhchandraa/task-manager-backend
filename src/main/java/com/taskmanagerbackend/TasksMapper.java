package com.taskmanagerbackend;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Tasks mapper.
 */
@Component
public class TasksMapper {

    /**
     * The Model mapper.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * The Type map entity to dto.
     */
    private TypeMap<TaskEntity, TaskDto> typeMapEntityToDto;

    /**
     * The Type map dto to entity.
     */
    private TypeMap<TaskDto, TaskEntity> typeMapDtoToEntity;

    /**
     * To task dto task dto.
     *
     * @param entity the entity
     * @return the task dto
     */
    public TaskDto toTaskDto(TaskEntity entity) {

        if(typeMapEntityToDto == null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            typeMapEntityToDto = modelMapper.createTypeMap(TaskEntity.class, TaskDto.class);
        }
        return typeMapEntityToDto.map(entity);
    }

    /**
     * To task entity task entity.
     *
     * @param dto the dto
     * @return the task entity
     */
    public TaskEntity toTaskEntity(TaskDto dto) {

        if(typeMapDtoToEntity == null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            typeMapDtoToEntity = modelMapper.createTypeMap(TaskDto.class, TaskEntity.class);
        }
        return typeMapDtoToEntity.map(dto);
    }
}
