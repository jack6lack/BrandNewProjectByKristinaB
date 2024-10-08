package ru.innopolis.java.attestation03.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String characterName;
    private String characterRace;
    private String characterClass;

    public void setId(Long id) {
        throw new UnsupportedOperationException("Id cannot be manually set");
    }

    @Builder
    public UserDTO(String characterName, String characterRace, String characterClass) {
        this.characterName = characterName;
        this.characterRace = characterRace;
        this.characterClass = characterClass;
    }
}
