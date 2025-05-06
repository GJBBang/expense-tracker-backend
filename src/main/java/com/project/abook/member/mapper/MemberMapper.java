package com.project.abook.member.mapper;

import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.MemberRegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toEntity(MemberRegisterRequest request);
}
