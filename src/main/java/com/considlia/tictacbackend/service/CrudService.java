package com.considlia.tictacbackend.service;

import com.considlia.tictacbackend.domain.model.AbstractEntity;
import com.considlia.tictacbackend.dto.Dto;
import com.considlia.tictacbackend.service.exception.ResourceNotFoundException;
import com.considlia.tictacbackend.util.EntityDtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CrudService<S extends AbstractEntity, N extends Number, T extends Dto> {

    final JpaRepository<S, N> jpaRepository;
    final Class<S> entityClass;
    final Class<T> dtoClass;
    final EntityDtoMapper entityDtoMapper;
    final ModelMapper modelMapper;

    /**
     * @param jpaRepository Extended repository of Entity type and numerical id
     * @param entityClass
     * @param dtoClass      the DtoClass modelMapper should map entity to
     */
    public CrudService(JpaRepository<S, N> jpaRepository, Class<S> entityClass, Class<T> dtoClass, EntityDtoMapper entityDtoMapper) {
        this.jpaRepository = jpaRepository;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.entityDtoMapper = entityDtoMapper;
        this.modelMapper = entityDtoMapper.getModelMapper();
    }

    public List<T> getAll() {
        return jpaRepository.findAll()
                            .stream()
                            .map(abstractEntity -> modelMapper.map(abstractEntity, dtoClass))
                            .collect(Collectors.toList());
    }

    public S getEntity(final N id) {
        return jpaRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No " + entityClass.getName() + "with " + id + " found"));
    }

    public T getDto(final N id) {
        return modelMapper.map(getEntity(id), dtoClass);
    }

    public T update(final N id, T dto) {
        S entity = getEntity(id);
        System.out.println(dto);
        modelMapper.map(dto, entity);
        System.out.println(entity);
        return modelMapper.map(jpaRepository.save(entity), dtoClass);
    }
}
