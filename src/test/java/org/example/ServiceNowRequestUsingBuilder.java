package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder(toBuilder = true)
public class ServiceNowRequestUsingBuilder {

    @JsonProperty("short_description")
    public String shortDescription;
    public String category;
    @Builder.Default
    public String priority="2";
}
