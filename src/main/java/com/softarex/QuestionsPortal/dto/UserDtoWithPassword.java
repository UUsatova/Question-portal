package com.softarex.QuestionsPortal.dto;

import com.softarex.QuestionsPortal.group.Creation;
import com.softarex.QuestionsPortal.group.Update;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoWithPassword extends UserDto {

    @NotNull(groups = {Creation.class})
    @Size(min=8,groups = {Creation.class,Update.class})
    private String password;

    @NotNull(groups = {Creation.class})
    @Size(min=8,groups = {Creation.class,Update.class})
    private String helperPassword;

}
