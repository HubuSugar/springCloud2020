package edu.hubu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * created by Sugar  2020/5/31 0:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment implements Serializable {

    private Long id;
    private String series;
}
