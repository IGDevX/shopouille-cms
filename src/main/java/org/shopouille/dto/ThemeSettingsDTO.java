package org.shopouille.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import org.shopouille.model.ThemeSettings;

@Data
@RegisterForReflection
public class ThemeSettingsDTO {
    private String id;
    private String primaryColor;
    private String secondaryColor;

    public static ThemeSettingsDTO fromEntity(ThemeSettings settings) {
        ThemeSettingsDTO dto = new ThemeSettingsDTO();
        dto.setId(settings.id.toString());
        dto.setPrimaryColor(settings.primaryColor);
        dto.setSecondaryColor(settings.secondaryColor);
        return dto;
    }
}
