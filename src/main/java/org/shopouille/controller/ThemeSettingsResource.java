package org.shopouille.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.shopouille.dto.ErrorResponse;
import org.shopouille.dto.ThemeSettingsDTO;
import org.shopouille.dto.UpdateThemeSettingsDTO;
import org.shopouille.model.ThemeSettings;

@Path("/api/theme-settings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ThemeSettingsResource {

    @GET
    public Response getThemeSettings() {
        ThemeSettings settings = ThemeSettings.findTheOne();

        if (settings == null) {
            ErrorResponse error = ErrorResponse.fromMessage("Theme settings not found", 404);
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
        
        return Response.ok(ThemeSettingsDTO.fromEntity(settings)).build();
    }

    @PATCH
    public Response updateThemeSettings(@Valid UpdateThemeSettingsDTO updateDTO) {
        ThemeSettings settings = ThemeSettings.findTheOne();
        
        boolean isNew = settings == null;
        if (isNew) {
            settings = new ThemeSettings();
        }
        
        settings.setPrimaryColor(updateDTO.getPrimaryColor());
        settings.setSecondaryColor(updateDTO.getSecondaryColor());
        
        if (isNew) {
            settings.persist();
        } else {
            settings.update();
        }

        ThemeSettingsDTO dto = ThemeSettingsDTO.fromEntity(settings);

        return Response.ok(dto).build();
    }
}


