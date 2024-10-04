package com.pranay.pranay_social_youtube.request;

import com.pranay.pranay_social_youtube.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRequest {

    private Integer userId;
}
