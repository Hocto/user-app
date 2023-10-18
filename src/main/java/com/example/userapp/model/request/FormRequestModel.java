package com.example.userapp.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormRequestModel {

    @NotBlank
    private String name;

    @Size(min = 1, max = 5)
    private Set<Long> sectorItemIds;

    @NotNull
    private boolean agreeToTerms;
}
