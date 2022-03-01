package org.george.swresistencesocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.george.swresistencesocialnetwork.enums.BaseEnum;
import java.util.Collection;

@Getter
@Setter
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
