package com.softarex.QuestionsPortal.dto;

import com.softarex.QuestionsPortal.group.Creation;
import com.softarex.QuestionsPortal.group.Update;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto{

    @NotNull(groups = {Creation.class})
    @Size(min=2,groups = {Creation.class,Update.class})
    private String firstName;
    @NotNull(groups = {Creation.class})
    @Size(min=2,groups = {Creation.class,Update.class})
    private String lastName;
    @Email(groups = {Creation.class})
    private String email;
    @NotNull(groups = {Creation.class})
    @Pattern(regexp = "\\+375[0-9]{9}",groups = {Creation.class,Update.class})
    private String number;
}
