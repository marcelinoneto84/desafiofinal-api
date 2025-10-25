package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private Long id;
    private String nome;
    private String email;
    private String documento;
    private Integer score;
}