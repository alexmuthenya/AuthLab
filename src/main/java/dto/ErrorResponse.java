package dto;

import lombok.Data;

@Data  //will generate constructor, getters, setters.....
public class ErrorResponse {
    private String message;
    private int status;

}
