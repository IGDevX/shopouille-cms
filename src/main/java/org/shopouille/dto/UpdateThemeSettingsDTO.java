package org.shopouille.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.shopouille.model.ThemeSettings;

@Data
@RegisterForReflection
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
