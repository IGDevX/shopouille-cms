package org.shopouille.dto;

import org.shopouille.model.ThemeSettings;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateThemeSettingsDTO {
    @NotBlank()
    private String primaryColor;
    
    @NotBlank()
    private String secondaryColor;

    public static UpdateThemeSettingsDTO fromEntity(ThemeSettings settings) {
        UpdateThemeSettingsDTO dto = new UpdateThemeSettingsDTO();
        dto.setPrimaryColor(settings.primaryColor);
        dto.setSecondaryColor(settings.secondaryColor);
        return dto;
    }
}

