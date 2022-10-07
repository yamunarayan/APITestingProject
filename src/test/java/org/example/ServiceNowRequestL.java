package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceNowRequestL {

    @JsonProperty("short_description")
    public String shortDescription;
    @JsonProperty("category")
    public String category;
    @JsonProperty("priority")
    public String priority;
}
