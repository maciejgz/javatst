package pl.mg.javatst.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface SophisticatedMapper {


    @Mappings({
            @Mapping(source = "firstValue", target = "first"),
            @Mapping(source = "secondValue", target = "second")
    })
    ExtremelySophisticatedDTO entityToDto(ExtremelySophisticatedEntity source);
}
