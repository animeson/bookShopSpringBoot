package com.svistun.bookshoop.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svistun.bookshoop.dto.user.UserDto;
import com.svistun.bookshoop.dto.user.UserUpdatePassword;
import com.svistun.bookshoop.entity.Person;
import com.svistun.bookshoop.filter.PersonAuthenticationFilter;
import com.svistun.bookshoop.service.user.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
@Log4j2
public class PersonFacade {
    private final PersonServiceImpl personService;


    //Skip null properties with BeanUtils
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyProperties(UserDto userDto, Person user) {
        BeanUtils.copyProperties(userDto, user, getNullPropertyNames(userDto));
    }

    public static void copyProperties(Person user, UserDto userDto) {
        BeanUtils.copyProperties(user, userDto);
    }


    public void updatePassword(UserUpdatePassword password, Authentication authentication) {
        if (password.getNewPassword().equals(password.getCheckNewPassword())) {
                personService.updatePassword(password.getNewPassword(),
                        password.getOldPassword(), authentication.getName());
        } else {
            log.warn("passwords don't match");
            throw new IllegalArgumentException("passwords don't match");

        }
    }



    public static void token (HttpServletRequest request, HttpServletResponse response, Person person, List<String> roles)
            throws IOException {

        String access_token = PersonAuthenticationFilter.generatorAccessToken(request, person.getEmail(), roles);
        String refresh_token = PersonAuthenticationFilter.generatorRefreshToken(request, person.getEmail(), roles);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }


}
