package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The field name city can not empty")
    private String nameCity;

    @ManyToOne
    @JoinColumn(name = "city_Id")
    private Country country;

    @NotNull(message = "The field square can not empty")
    @Min(value = 10, message = "Value of square min is 10, please try again")
    private Double square;

    @NotNull(message = "The field population can not empty")
    @Min(value = 100,message = "The population min is 100")
    private Long population;

    @NotNull(message = "The field GDP can not empty")
    @Min(value = 1,message = "This field can not less than 0")
    private Double gdp;

    private String description;
}
