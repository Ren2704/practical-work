package com.example.demo.mapper;

import com.example.demo.model.CompreFaceResponse;
import com.example.demo.model.RecognitionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompreFaceMapper {
    RecognitionResponse toRecognitionResponse(CompreFaceResponse.Subject subject);
}
