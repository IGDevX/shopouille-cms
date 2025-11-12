package org.shopouille.dto;

import lombok.Data;
import org.shopouille.model.PageContent;

@Data
public class PageContentDTO {
    private String id;
    private String type;
    private String title;
    private String content;

    public static PageContentDTO fromEntity(PageContent page) {
        PageContentDTO dto = new PageContentDTO();
        dto.setId(page.id.toString());
        dto.setType(page.type);
        dto.setTitle(page.title);
        dto.setContent(page.content);
        return dto;
    }
}
