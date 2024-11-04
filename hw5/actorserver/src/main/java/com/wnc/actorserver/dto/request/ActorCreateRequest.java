package com.wnc.actorserver.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ActorCreateRequest {
    @NotNull(message = "MISSING_FIELD")
    @Size(min = 1, max = 45, message = "FIRSTNAME_INVALID")
    private String first_name;

    @NotNull(message = "MISSING_FIELD")
    @Size(min = 1, max = 45, message = "LASTNAME_INVALID")
    private String last_name;
}
