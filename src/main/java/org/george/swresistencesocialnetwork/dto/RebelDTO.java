package org.george.swresistencesocialnetwork.dto;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.BaseEnum;
import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RebelDTO {
    String name;
    Integer age;
    String gender;
    Double latitude;
    Double longitude;
    BaseEnum base;
    Collection<ItemDTO> inventory;
}
