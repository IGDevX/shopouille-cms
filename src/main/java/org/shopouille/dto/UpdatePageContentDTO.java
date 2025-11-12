package org.shopouille.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.shopouille.model.PageContent;

@Data
public class UpdatePageContentDTO {
    @NotBlank()
    private String content;

    public static UpdatePageContentDTO fromEntity(PageContent page) {
        UpdatePageContentDTO dto = new UpdatePageContentDTO();
        dto.setContent(page.content);
        return dto;
    }
}
