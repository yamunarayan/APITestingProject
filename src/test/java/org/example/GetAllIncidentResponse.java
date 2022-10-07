package org.example;

import lombok.Getter;

import java.util.List;
@Getter
public class GetAllIncidentResponse {
    List<CreateIncidentResponse.Result> result;
}
    