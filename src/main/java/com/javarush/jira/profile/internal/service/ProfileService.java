package com.javarush.jira.profile.internal.service;

import com.javarush.jira.profile.internal.ProfileRepository;
import com.javarush.jira.profile.internal.model.Profile;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
public class ProfileService {
    private ProfileRepository profileRepository;
    public Profile getOrCreate(long id){
        return profileRepository.getOrCreate(id);
    }
    private Map<Long, Profile> cache = new HashMap<>();

    public Profile get(long userId) {
        // Potential problem area
        Profile profile = cache.get(userId);
        if (profile == null) {
            // Load the profile if it's not in the cache
            profile = getOrCreate(userId);
            cache.put(userId, profile);
        }
        return profile;
    }
}
