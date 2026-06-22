package com.example.demo.mapper;

import com.example.demo.dao.entity.OutstandingPersonEntity;
import com.example.demo.mapper.helper.PersonMapperHelper;
import com.example.model.*;
import org.mapstruct.*;
import org.springframework.data.domain.Slice;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                AcademicDegreeMapper.class,
                AcademicTitleMapper.class,
                EducationSubjectMapper.class,
                PersonMapperHelper.class,
        }
)
public interface OutstandingPersonMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "personJob", ignore = true)
    @Mapping(target = "academicTitle", ignore = true)
    @Mapping(target = "academicDegree", ignore = true)
    @Mapping(target = "educationSubject", ignore = true)
    @Mapping(target = "photoLink", ignore = true)
    @Mapping(target = "contentType", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "deletedAt", ignore = true)
    OutstandingPersonEntity toEntity(OutstandingPersonCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoLink", ignore = true)
    @Mapping(target = "contentType", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "personJob", ignore = true)
    @Mapping(target = "academicTitle", ignore = true)
    @Mapping(target = "academicDegree", ignore = true)
    @Mapping(target = "educationSubject", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(@MappingTarget OutstandingPersonEntity entity, OutstandingPersonUpdateRequest request);

    OutstandingPersonResponse toResponse(OutstandingPersonEntity entity);
    PersonResponse toSimpleResponse(OutstandingPersonEntity entity);

    default PersonSliceResponse toSliceResponse(Slice<OutstandingPersonEntity> slice) {
        List<PersonResponse> content = slice.getContent().stream().map(this::toSimpleResponse).toList();

        PersonSliceResponse response = new PersonSliceResponse();
        response.setContent(content);
        response.setPage(slice.getNumber());
        response.setSize(slice.getSize());
        response.setHasNext(slice.hasNext());

        return response;
    }

    @Mapping(target = "photoLink", source = "entity", qualifiedByName = "mapPhotoUrl")
    PhotoResponse toPhotoResponse(OutstandingPersonEntity entity);
}
