package com.softarex.QuestionsPortal.dto;

import com.softarex.QuestionsPortal.group.Creation;
import com.softarex.QuestionsPortal.group.Update;
import com.softarex.QuestionsPortal.validation.annotation.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Size(min=2,groups = {Creation.class,Update.class},message = "Your firstName should contain more than 2 letters")
    private String firstName;
    @NotNull(groups = {Creation.class})
    @Size(min=2,groups = {Creation.class,Update.class},message = "Your lastName should contain more than 2 letters")
    private String lastName;
    @Email(groups = {Creation.class, Update.class},message = "Incorrect email")
    @NotBlank(groups = {Creation.class,Update.class},message = "Must not be blank")
    @Unique(groups = {Creation.class})
    private String email;
    @NotBlank(groups = {Creation.class})
    @Pattern(regexp = "\\+375[0-9]{9}",groups = {Creation.class,Update.class},message = "Must match \"\\+375[0-9]{9}\"")
    private String number;
}
