package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;

    private Date timestamp;

    private String message;

    private String description;

}
