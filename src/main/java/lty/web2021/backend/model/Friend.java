package lty.web2021.backend.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * friend
 * @author 
 */
@Data
public class Friend implements Serializable {
    private Integer uId;

    private Integer fId;

    private String username;

    private String img;

    private static final long serialVersionUID = 1L;
}