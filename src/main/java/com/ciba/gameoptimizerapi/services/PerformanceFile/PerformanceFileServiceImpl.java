package com.ciba.gameoptimizerapi.services.PerformanceFile;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.PerformanceFiles;
import com.ciba.gameoptimizerapi.models.User;
import com.ciba.gameoptimizerapi.repositories.ComponentsCombo.ComponentsComboRepository;
import com.ciba.gameoptimizerapi.repositories.PerformanceFile.PerformanceFileRepository;
import com.ciba.gameoptimizerapi.repositories.User.UserRepository;
import com.ciba.gameoptimizerapi.requests.PerformanceFilesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceFileServiceImpl implements PerformanceFileService {

    private final PerformanceFileRepository repository;
    private final ComponentsComboRepository componentsComboRepository;
    private final UserRepository userRepository;

    @Override
    public List<PerformanceFiles> getPerformanceFiles(PerformanceFilesRequest request) {
        ComponentsCombo combo = componentsComboRepository.getComponentsComboByComponents(
                request.getProcessorId(), request.getGraphicsCardId(), request.getRamId()
        ).orElse(null);

        if (combo == null) {
            return List.of();
        }
        List<PerformanceFiles> result = repository.getPerformanceFilesNamesByComponentsComboAndGame
                (combo.getId(), request.getGameId());

        List<User> users = userRepository.findByUUIDs(result.stream().map(PerformanceFiles::getUserId).toList());

        for (PerformanceFiles config : result) {
            User author = users.stream()
                    .filter(user -> user.getId().equals(config.getUserId()))
                    .findFirst()
                    .orElse(null);
            config.setUser(author);
        }

        return result;
    }
}
