package pl.mg.javatst.mapstruct;

import org.mapstruct.factory.Mappers;

public class MapStructTester {

    public static void main(String[] args) {
        SimpleEntity entity = new SimpleEntity("sam");
        SimpleDto mappedDto = Mappers.getMapper(SimpleMapper.class).entityToDto(entity);
        System.out.println(mappedDto);


        ExtremelySophisticatedEntity entity1 = new ExtremelySophisticatedEntity(1, "2");
        ExtremelySophisticatedDTO dto = Mappers.getMapper(SophisticatedMapper.class).entityToDto(entity1);
        System.out.println(dto.toString());

    }
}
