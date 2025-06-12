package com.example.demo.mapper;

import com.example.demo.controller.request.outstanding.people.OutstandingPeopleCreateRequest;
import com.example.demo.entity.OutstandingPeopleEntity;

public interface OutstandingPeopleMapper {
    <T extends OutstandingPeopleCreateRequest> OutstandingPeopleEntity requestMapToOutstandingPeople(OutstandingPeopleEntity person, T request);
}
