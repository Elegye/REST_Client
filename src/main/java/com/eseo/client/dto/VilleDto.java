package com.eseo.client.dto;

import lombok.Data;

@Data
public class VilleDto implements DtoInterface {

        private String inseeCode;

        private String name;

        private String postalCode;

        private String label;

        private String line5;

        private String lat;

        private String lon;

}
