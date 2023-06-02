package com.micromimariwork.micromimariwork.respond;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendlyMessage {
    private  String title;
    private  String description;
    private  String buttonPositive;
}
