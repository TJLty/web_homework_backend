package lty.web2021.backend.model;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer uId;

    private String password;

    private String username;

    private String phone;

    private String email;

    private String img;

    private String type;

    private static final long serialVersionUID = 1L;
}