package br.com.ithappens.model;

import lombok.Data;

@Data
public class DetalhesErro {
    private String description;
    private Long status;
    private Long timestamp;
    private String route;
}
