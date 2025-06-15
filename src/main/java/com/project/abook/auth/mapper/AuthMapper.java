package com.project.abook.auth.mapper;

import com.project.abook.auth.dto.request.LoginRequest;
import com.project.abook.member.dto.event.MemberRegisterEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    LoginRequest toLoginRequest(MemberRegisterEvent event);

}
