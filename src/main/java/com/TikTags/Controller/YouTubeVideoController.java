package com.TikTags.Controller;

import com.TikTags.Model.VideoDetails;
import com.TikTags.Service.ThumbnailService;
import com.TikTags.Service.YouTubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class YouTubeVideoController {
    private final YouTubeService youTubeService;

    private final ThumbnailService service;

    @GetMapping("/youtube/video-details")
    public String showVideoFrom(){
        return "video-details";
    }

    @PostMapping("/youtube/video-details")
    public String fetchVideoDetails(@RequestParam String videoUrlOrId, Model model){
        String videoId = service.extractVideoId(videoUrlOrId);

        if(videoId==null){
            model.addAttribute("error","Invalid Youtube URL or ID. ");
        }

        VideoDetails details =youTubeService.getVideoDetails(videoId);
        if(details==null){
            model.addAttribute("error","video not found");
        }else {
            model.addAttribute("videoDetails",details);
        }

        model.addAttribute("videoDetails",details);
        return "video-details";

    }

}


