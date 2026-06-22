package com.example.demo.mapper.helper;

import com.example.demo.dao.entity.OutstandingPersonEntity;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class PersonMapperHelper {
    @Named("mapPhotoUrl")
    public String mapPhotoUrl(OutstandingPersonEntity entity) {
        if (entity.getPhotoLink() == null) {
            return null;
        }
        return "/outstanding-people/" + entity.getId() + "/photo";
    }
}
