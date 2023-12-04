package com.ciba.gameoptimizerapi.responses;

import com.ciba.gameoptimizerapi.models.PerformanceFiles;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class PerformanceFilesResponse {

    @JsonProperty("files")
    List<FileResponse> files;

    @JsonProperty("by")
    String username;

    public static PerformanceFilesResponse fromPerformanceFile(PerformanceFiles result, boolean isRegistered) {
        PerformanceFilesResponse response = new PerformanceFilesResponse();

        String[] fileNames = result.getPerformanceFilesNames();
        List<FileResponse> fileResponses = new ArrayList<>();
        for (String filename : fileNames) {
            FileResponse fileResponse = new FileResponse();

            fileResponse.setName(filename);
            if (isRegistered) {
                fileResponse.setLink("http://somelinktoazureblobors3.com/" + filename);
            }

            fileResponses.add(fileResponse);
        }
        response.setFiles(fileResponses);

        response.setUsername(result.getUser().getUsername());

        return response;
    }
}
