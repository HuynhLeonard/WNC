package com.wnc.actorserver.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ActorUpdateRequest {
    @Size(min = 1, max = 45, message = "FIRSTNAME_INVALID")
    private String first_name;
    @Size(min = 1, max = 45, message = "LASTNAME_INVALID")
    private String last_name;
}
