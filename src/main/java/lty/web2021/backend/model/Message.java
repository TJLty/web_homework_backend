package lty.web2021.backend.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * message
 * @author 
 */
@Data
public class Message implements Serializable {
    private Integer id;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;

    private String type;

    private Integer from;

    private Integer to;

}