package pl.mg.javatst.mapstruct;

import org.mapstruct.Mapper;

@Mapper
public interface SimpleMapper {

    SimpleDto entityToDto(SimpleEntity entity);

}
