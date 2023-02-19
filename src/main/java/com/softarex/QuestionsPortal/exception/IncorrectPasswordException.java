package com.softarex.QuestionsPortal.exception;

import com.softarex.QuestionsPortal.dto.UserDtoWithPassword;
import lombok.Data;

@Data
public class IncorrectPasswordException extends RuntimeException{

    private UserDtoWithPassword incorrectUserDto;
    public IncorrectPasswordException(){
      super();
    }
    public IncorrectPasswordException(String message){
        super(message);
    }

    public IncorrectPasswordException(UserDtoWithPassword incorrectUserDto){
        super();
        this.incorrectUserDto=incorrectUserDto;
    }
}
